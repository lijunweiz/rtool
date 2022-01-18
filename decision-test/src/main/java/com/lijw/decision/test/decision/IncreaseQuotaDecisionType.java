package com.lijw.decision.test.decision;

import com.lijw.decision.core.AbstractDecisionType;

public class IncreaseQuotaDecisionType extends AbstractDecisionType {

    @Override
    public String getDecisionTypeName() {
        return DecisionTypeEnum.INCREASE_QUOTA.getValueEN();
    }

}
