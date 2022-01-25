package com.lijw.decision.core.support;

import com.lijw.decision.core.*;
import com.lijw.decision.core.exception.DecisionException;
import com.lijw.decision.core.product.Product;
import com.lijw.decision.core.util.CollectionUtil;
import com.lijw.decision.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

/**
 * 决策模板，一些简单易用的决策方法
 */
public class DecisionTemplate extends DecisionSupport implements DecisionOperations {

    private Logger logger = LoggerFactory.getLogger(DecisionTemplate.class);

    public DecisionTemplate() {
        super();
    }

    @Override
    public void execute(Context context) throws DecisionException {
        execute(context, getDecisionFunctions(), DefaultValue.TRUE.booleanValue());
    }

    @Override
    public void execute(Context context, List<DecisionFunction> decisionFunctions, boolean transfer) throws DecisionException {
        if (Objects.isNull(context)) {
            throw new IllegalArgumentException("context不能为null");
        }
        if (CollectionUtil.isNullOrEmpty(decisionFunctions)) {
            throw new IllegalArgumentException("decisionFunctions不能为null或者空");
        }

        if (transfer) {
            executeDecide(context, decisionFunctions);
        } else {
            executeDecide(context, getDecisionFunctions(decisionFunctions));
        }
    }

    protected void executeDecide(Context context, List<DecisionFunction> decisionFunctions) throws DecisionException {
        // 1 确认请求
        context.setStatus(DecisionStatus.CONFIRM);

        // 1.1 决策类型探测，且条件预判断
        DecisionType decisionType = decisionTypeProcess(context);
        if (logger.isDebugEnabled()) {
            logger.debug("当前决策类型详情: {}", decisionType);
        }

        // 1.2 产品类型探测, 且条件预判断
        Product product = productTypeProcess(context);
        if (logger.isDebugEnabled()) {
            logger.debug("当前产品类型详情: {}", product);
        }

        // 2 决策处理中
        context.setStatus(DecisionStatus.PROCESSING);
        for (DecisionFunction function: decisionFunctions) {
            try {
                if (function.canDecide(context)) {
                    function.decide(context);
                }
            } catch (Exception e) {
                if (context.getResult().getForceContinue()) {
                    logger.warn("执行决策: {} 失败, 继续执行剩余决策", function.getName());
                } else {
                    throw new DecisionException("决策条件不满足, 执行流终止", e);
                }
            }
        }

        // 3 决策流处理完成
        context.setStatus(DecisionStatus.FINISHED);
    }

    /**
     * 决策类型探测，且决策类型条件预判断
     * @param context
     * @return 决策类型
     * @throws DecisionException
     */
    private DecisionType decisionTypeProcess(Context context) throws DecisionException {
        String decisionTypeName = context.getDecisionType().getName();
        if (StringUtils.isNullOrEmpty(decisionTypeName)) {
            throw new DecisionException("决策类型名称不能为空");
        }
        DecisionType decisionType = this.getDecisionTypes()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(decisionTypeName))
                .findFirst()
                .orElseThrow(() -> new DecisionException("未匹配到决策类型: " + decisionTypeName));

        logger.info("探测到决策类型: {}", decisionTypeName);

        boolean process = decisionType.process();
        if (process) {
            logger.info("决策类型: {}, 预判定成功", decisionTypeName);
        } else {
            throw new DecisionException("决策类型: " + decisionTypeName + ", 预判定失败");
        }

        return decisionType;
    }

    /**
     * 产品类型探测，且决策产品条件预判断
     * @param context
     * @return 产品
     */
    private Product productTypeProcess(Context context) throws DecisionException {
        String productName = context.getProductName();
        if (StringUtils.isNullOrEmpty(productName)) {
            throw new DecisionException("产品名称不能为空");
        }
        Product product = getProducts()
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new DecisionException("未匹配到产品: " + productName));

        logger.info("探测到产品类型: {}", productName);

        boolean process = product.process();
        if (process) {
            logger.info("产品类型: {} 处理成功", productName);
        } else {
            throw new DecisionException("产品类型: "+ productName + " 处理失败");
        }

        return product;
    }

}
