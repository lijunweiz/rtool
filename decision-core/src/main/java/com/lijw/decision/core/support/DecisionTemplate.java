package com.lijw.decision.core.support;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.exception.CannotDecisionException;
import com.lijw.decision.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DecisionTemplate extends DecisionSupport implements DecisionOperations {

    private Logger logger = LoggerFactory.getLogger(DecisionTemplate.class);

    private Context context;

    public DecisionTemplate() {
        this(new Context());
    }

    public DecisionTemplate(Context context) {
        this.context = context;
        super.initDecisionResource();
    }

    public DecisionTemplate(Context context, List<DecisionFunction> decisionFunctions) {
        this.context = context;
        super.setDecisionFunctions(decisionFunctions);
        super.setDecisionFunctionMap(decisionFunctions.stream().collect(
                Collectors.toMap(function -> StringUtils.isNullOrEmpty(function.getDecisionName())
                        ? StringUtils.getCamelName(function.getClass()) : function.getDecisionName(), Function.identity())));
    }

    @Override
    public void execute() throws CannotDecisionException {
        execute(this.context);
    }

    @Override
    public void execute(Context context) throws CannotDecisionException {
        execute(context, getDecisionFunctions());
    }

    @Override
    public void execute(Context context, List<DecisionFunction> decisionFunctions) throws CannotDecisionException {
        executeDecide(context, decisionFunctions, true);
    }

    protected void executeDecide(Context context, List<DecisionFunction> decisionFunctions, boolean order) throws CannotDecisionException {
        if (order) {
            for (DecisionFunction function: decisionFunctions) {
                if (function.canDecide(context)) {
                    function.decide(context);
                } else {
                    logger.error("执行决策: {} 条件不满足, 执行流终止", StringUtils.isNullOrEmpty(function.getDecisionName())
                            ? StringUtils.getCamelName(function.getClass()) : function.getDecisionName());
                    throw new CannotDecisionException("条件不满足, 执行流终止");
                }
            }
        }
    }

}
