package com.lijw.decision.core.support;

import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.DecisionType;
import com.lijw.decision.core.DefaultValue;
import com.lijw.decision.core.product.Product;
import com.lijw.decision.core.util.CollectionUtil;
import com.lijw.decision.core.util.IOUtils;
import com.lijw.decision.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 决策支持，完成决策执行时的一些基础配置
 */
public abstract class DecisionSupport {

    private Logger logger = LoggerFactory.getLogger(DecisionSupport.class);

    /** 决策类型 */
    private List<DecisionType> decisionTypes = new ArrayList<>();
    /** 决策项list */
    private List<DecisionFunction> decisionFunctions = new ArrayList<>();
    /** 产品 */
    private List<Product> products = new ArrayList<>();
    /** 配置系统属性名称，指定配置文件所在位置 */
    private static final String DECISION_CONFIG = "DECISION_CONFIG";
    /** 配置文件默认所在位置 */
    private static final String DEFAULT_LOCATION = "/decision.properties";
    /** 配置属性value分隔符 */
    private static final String SEPARATOR = ",";
    /** 解析完成的配置属性 */
    private Properties properties = new Properties();
    /** spring环境 */
    private final static String APPLICATION_CONTEXT = "org.springframework.context.ApplicationContext";

    public DecisionSupport() {
    }

    public List<DecisionType> getDecisionTypes() {
        return decisionTypes;
    }

    public void setDecisionTypes(List<DecisionType> decisionTypes) {
        this.decisionTypes = decisionTypes;
    }

    public List<DecisionFunction> getDecisionFunctions() {
        return decisionFunctions;
    }

    public void setDecisionFunctions(List<DecisionFunction> decisionFunctions) {
        this.decisionFunctions = decisionFunctions;
    }

    /**
     * 排序从小到大
     * @param decisionFunctions
     * @return
     */
    public List<DecisionFunction> getDecisionFunctions(List<DecisionFunction> decisionFunctions) {
        if (CollectionUtil.isNullOrEmpty(decisionFunctions)) {
            return decisionFunctions;
        }

        return decisionFunctions.stream().sorted(Comparator.comparing(DecisionFunction::order)).collect(Collectors.toList());
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * 初始化决策配置信息
     */
    public void init() {
        if (detectEnvironment()) {
            this.initConfig();// 只有在普通环境才加载独有配置项
        }
        this.initDecisionFunction();
        this.initDecisionType();
        this.initProduct();
    }

    protected void initConfig() {
        String current = DEFAULT_LOCATION;
        String config = System.getProperty(DECISION_CONFIG);
        if (StringUtils.isNullOrEmpty(config)) {
            if (logger.isDebugEnabled()) {
                logger.debug("未探测到系统属性: {}, 使用默认配置文件: {}", DECISION_CONFIG, current);
            }
        } else {
            current = config;
            if (logger.isDebugEnabled()) {
                logger.debug("探测到系统属性: {}, 使用配置文件: {}", DECISION_CONFIG, current);
            }
        }

        // 获取classpath下的文件绝对路径, 进行文件判断
        File file = null;
        URL resource = getClass().getResource(current);
        if (Objects.isNull(resource)) {
            return;
        } else {
            String path = resource.getPath();
            file = new File(path);
            if (!file.isFile()) {
                return;
            }
        }

        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file.getName());
            if (Objects.nonNull(is)) {
                properties.load(is);
                if (logger.isDebugEnabled()) {
                    logger.debug("配置文件: {} 加载成功", current);
                }
            } else {
                logger.warn("配置文件: {} 加载失败", current);
            }
        } catch (Exception e) {
            logger.error("配置文件加载失败", e);
        } finally {
            IOUtils.close(is);
        }
    }

    /**
     * 探测是普通环境还是spring环境
     * @return 是普通环境返回true spring环境返回false
     */
    @SuppressWarnings("rawtype")
    private boolean detectEnvironment() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Class<?> appContext = null;
            if (Objects.isNull(loader)) {
                appContext = Class.forName(APPLICATION_CONTEXT);
            } else {
                appContext = Class.forName(APPLICATION_CONTEXT, DefaultValue.FALSE, loader);
            }

            return Objects.isNull(appContext);
        } catch (Throwable e) {
            // ignore e
            return DefaultValue.TRUE;
        }
    }

    /**
     * 加载接口{@link DecisionFunction}的所有实现类，/META-INF/services 优先级更高
     * @return
     */
    protected void initDecisionFunction() {
        initLoaderService(DecisionFunction.class, decisionFunctions);
        initConfigService(DecisionFunction.class, decisionFunctions);
        decisionFunctions = getDecisionFunctions(decisionFunctions);
    }

    /**
     * 初始化决策类型 {@link DecisionType}的所有实现类，/META-INF/services 优先级更高
     */
    protected void initDecisionType() {
        initLoaderService(DecisionType.class, decisionTypes);
        initConfigService(DecisionType.class, decisionTypes);
    }

    protected void initProduct() {
        initLoaderService(Product.class, products);
        initConfigService(Product.class, products);
    }

    /**
     * 从/META-INF/services/ 加载指定类型的所有实现类
     * @param clazz 接口或超类的class
     * @param list
     * @param <T>
     */
    private <T> void initLoaderService(Class<T> clazz, List<T> list) {
        if (Objects.isNull(clazz) || Objects.isNull(list)) {
            return;
        }
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        Iterator<T> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            T type = iterator.next();
            list.add(type);
        }
    }

    /**
     * 从properties获取一个接口的所有实现类
     * @param clazz 接口或超类的class
     * @param list
     * @param <T>
     */
    private <T> void initConfigService(Class<T> clazz, List<T> list) {
        if (Objects.isNull(clazz) || Objects.isNull(list)) {
            return;
        }
        if (properties.containsKey(clazz.getName())) {
            String property = properties.getProperty(clazz.getName());
            if (StringUtils.isNullOrEmpty(property)) {
                logger.warn("参数: {}值为空, 忽略该参数", clazz.getName());
                return;
            }

            for (String className : property.split(SEPARATOR)) {
                try {
                    T o = (T) Class.forName(className).asSubclass(clazz).newInstance();
                    boolean anyMatch = list.stream().anyMatch(x -> x.getClass().equals(o.getClass()));
                    if (anyMatch) {
                        logger.warn("忽略重复定义bean: {}", o.getClass().getName());
                    } else {
                        list.add(o);
                    }
                } catch (Exception e) {
                    logger.error("无法实例化: {}", className, e);
                }
            }
        }
    }

}
