package com.lijw.decision.core;

import com.lijw.decision.core.util.StringUtils;

public abstract class AbstractDecisionType implements DecisionType {

    /** 决策类型名称 */
    private String decisionTypeName;

    public AbstractDecisionType() {
        this(null);
    }

    public AbstractDecisionType(Context context) {
        this.decisionTypeName = StringUtils.getCamelName(getClass());
    }

    @Override
    public String getName() {
        return decisionTypeName;
    }

}
