package com.lijw.decision.core.support;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.DecisionStatus;
import com.lijw.decision.core.DecisionType;
import com.lijw.decision.core.exception.DecisionException;
import com.lijw.decision.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DecisionTemplate extends DecisionSupport implements DecisionOperations {

    private Logger logger = LoggerFactory.getLogger(DecisionTemplate.class);

    private Context context;

    public DecisionTemplate(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void execute() throws DecisionException {
        execute(this.context);
    }

    @Override
    public void execute(Context context) throws DecisionException {
        execute(context, getDecisionFunctions());
    }

    @Override
    public void execute(Context context, List<DecisionFunction> decisionFunctions) throws DecisionException {
        executeDecide(context, decisionFunctions, true);
    }

    protected void executeDecide(Context context, List<DecisionFunction> decisionFunctions, boolean order) throws DecisionException {
        // 1 确认请求
        context.setStatus(DecisionStatus.CONFIRM);

        // 2 决策类型探测，且条件预判断
        boolean decisionTypeProcess = decisionTypeProcess(context);
        if (decisionTypeProcess) {
            logger.info("决策类型: {}, 预判定成功", context.getDecisionType().getDecisionTypeName());
        } else {
            logger.error("决策类型: {}, 预判定失败", context.getDecisionType().getDecisionTypeName());
            return;
        }

        // 3 决策处理中
        if (order) {
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
        }

        // 4 决策流处理完成
        context.setStatus(DecisionStatus.FINISHED);
    }

    /**
     * 决策类型探测，且决策类型条件预判断
     * @param context
     * @return 决策类型
     * @throws DecisionException
     */
    private boolean decisionTypeProcess(Context context) throws DecisionException {
        String decisionTypeName = context.getDecisionType().getDecisionTypeName();
        DecisionType decisionType = this.getDecisionTypes()
                .stream()
                .filter(x -> x.getDecisionTypeName().equalsIgnoreCase(decisionTypeName))
                .findFirst()
                .orElseThrow(() -> new DecisionException("未匹配到决策类型: " + decisionTypeName));

        logger.info("探测到决策类型: {}", decisionTypeName);
        return decisionType.process();
    }

}
