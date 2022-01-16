package com.lijw.decision.core.support;

import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.DecisionType;
import com.lijw.decision.core.product.Product;
import com.lijw.decision.core.util.IOUtils;
import com.lijw.decision.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public abstract class DecisionSupport {

    private Logger logger = LoggerFactory.getLogger(DecisionSupport.class);

    /** 决策类型 */
    private List<DecisionType> decisionTypes = new ArrayList<>();
    /** 决策项map */
    private Map<String, DecisionFunction> decisionFunctionMap = new HashMap<>();
    /** 决策项list */
    private List<DecisionFunction> decisionFunctions = new ArrayList<>();
    /** 产品 */
    private List<Product> products = new ArrayList<>();
    /** 配置系统属性名称，指定配置文件所在位置 */
    private static final String DECISION_CONFIG = "DECISION_CONFIG";
    /** 配置文件默认所在位置 */
    private static final String defaultLocation = "./decision.properties";
    /** 配置属性value分隔符 */
    private static final String SEPARATOR = ",";
    /** 解析完成的配置属性 */
    private Properties properties = new Properties();

    public DecisionSupport() {
        initConfig();
        initDecisionFunction();
        initDecisionType();
        initProduct();
    }

    public List<DecisionType> getDecisionTypes() {
        return decisionTypes;
    }

    public List<DecisionFunction> getDecisionFunctions() {
        return decisionFunctions;
    }

    public Map<String, DecisionFunction> getDecisionFunctionMap() {
        return decisionFunctionMap;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Properties getProperties() {
        return properties;
    }

    private void initConfig() {
        String current = "";
        String config = System.getProperty(DECISION_CONFIG);
        if (StringUtils.isNullOrEmpty(config)) {
            current = defaultLocation;
            logger.info("未探测到系统属性: {}, 使用默认配置文件: {}", DECISION_CONFIG, defaultLocation);
        } else {
            current = config;
            logger.info("探测到系统属性: {}, 使用配置文件: {}", DECISION_CONFIG, config);
        }
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(current);
        try {
            properties.load(is);
            logger.info("配置文件: {} 加载成功", current);
        } catch (IOException e) {
            logger.error("配置文件加载失败", e);
        } finally {
            IOUtils.close(is);
        }
    }

    /**
     * 加载接口{@link DecisionFunction}的所有实现类，/META-INF/services 优先级更高
     * @return
     */
    protected void initDecisionFunction() {
        initLoaderService(DecisionFunction.class, decisionFunctions);
        initConfigService(DecisionFunction.class, decisionFunctions);
        decisionFunctions.forEach(x -> {
            decisionFunctionMap.put(StringUtils.getCamelName(x.getClass()), x);
        });
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
    private <T> void initLoaderService(Class<?> clazz, List<T> list) {
        ServiceLoader<T> serviceLoader = (ServiceLoader<T>) ServiceLoader.load(clazz);
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
    private <T> void initConfigService(Class<?> clazz, List<T> list) {
        if (properties.containsKey(clazz.getName())) {
            String property = properties.getProperty(clazz.getName());
            if (StringUtils.isNullOrEmpty(property)) {
                logger.warn("参数: {}值为空, 忽略该参数", clazz.getName());
                return;
            }

            for (String className : property.split(SEPARATOR)) {
                try {
                    T o = (T) Class.forName(className).newInstance();
                    boolean anyMatch = list.stream().anyMatch(x -> x.getClass().equals(o.getClass()));
                    if (!anyMatch) {
                        list.add(o);
                    }
                } catch (Exception e) {
                    logger.error("无法实例化: {}", className, e);
                }
            }
        }
    }

}
