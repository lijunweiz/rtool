package com.lijw.decision.core.support;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.DecisionStatus;
import com.lijw.decision.core.DecisionType;
import com.lijw.decision.core.exception.DecisionException;
import com.lijw.decision.core.product.Product;
import com.lijw.decision.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DecisionTemplate extends DecisionSupport implements DecisionOperations {

    private Logger logger = LoggerFactory.getLogger(DecisionTemplate.class);

    public DecisionTemplate() {
        super();
    }

    @Override
    public void execute(Context context) throws DecisionException {
        execute(context, getDecisionFunctions());
    }

    @Override
    public void execute(Context context, List<DecisionFunction> decisionFunctions) throws DecisionException {
        executeDecide(context, decisionFunctions);
    }

    protected void executeDecide(Context context, List<DecisionFunction> decisionFunctions) throws DecisionException {
        // 1 确认请求
        context.setStatus(DecisionStatus.CONFIRM);

        // 2 决策类型探测，且条件预判断
        DecisionType decisionType = decisionTypeProcess(context);
        if (logger.isDebugEnabled()) {
            logger.debug("当前决策类型详情: {}", decisionType);
        }

        // 3 产品类型探测, 且条件预判断
        Product product = productTypeProcess(context);
        if (logger.isDebugEnabled()) {
            logger.debug("当前产品类型详情: {}", product);
        }

        // 4 决策处理中
        context.setStatus(DecisionStatus.PROCESSING);
        for (DecisionFunction function: decisionFunctions) {
            try {
                if (function.canDecide(context)) {
                    function.decide(context);
                }
            } catch (Exception e) {
                if (context.getResult().getForceContinue()) {
                    logger.warn("执行决策: {} 失败, 强制执行剩余决策", StringUtils.getCamelName(function.getClass()));
                } else {
                    throw new DecisionException("决策条件不满足, 执行流终止", e);
                }
            }
        }

        // 5 决策流处理完成
        context.setStatus(DecisionStatus.FINISHED);
    }

    /**
     * 决策类型探测，且决策类型条件预判断
     * @param context
     * @return 决策类型
     * @throws DecisionException
     */
    private DecisionType decisionTypeProcess(Context context) throws DecisionException {
        String decisionTypeName = context.getDecisionType().getDecisionTypeName();
        if (StringUtils.isNullOrEmpty(decisionTypeName)) {
            throw new DecisionException("决策类型名称不能为空");
        }
        DecisionType decisionType = this.getDecisionTypes()
                .stream()
                .filter(x -> x.getDecisionTypeName().equalsIgnoreCase(decisionTypeName))
                .findFirst()
                .orElseThrow(() -> new DecisionException("未匹配到决策类型: " + decisionTypeName));

        logger.info("探测到决策类型: {}", decisionTypeName);

        boolean process = decisionType.process();
        if (process) {
            logger.info("决策类型: {}, 预判定成功", decisionTypeName);
        } else {
            throw new DecisionException();
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
                .filter(x -> x.getProductName().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new DecisionException("未匹配到产品: " + productName));

        logger.info("探测到产品类型: {}", productName);

        boolean process = product.process();
        if (process) {
            logger.info("产品类型: {} 处理成功", productName);
        } else {
            throw new DecisionException();
        }

        return product;
    }

}
