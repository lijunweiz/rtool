package com.lijw.decision.core;

import com.lijw.decision.core.util.StringUtils;

/**
 * 简单实现决策
 * 
 */
public abstract class AbstractDecision implements DecisionFunction {

    /** 决策名称 */
    private String decisionName;

    public AbstractDecision() {
        decisionName = StringUtils.getCamelName(getClass());
    }

    /**
     * 默认实现, 以决策的类名（驼峰）格式作为决策名称{@link AbstractDecision#AbstractDecision()}
     * @return
     */
    @Override
    public String getDecisionName() {
        return decisionName;
    }
}
