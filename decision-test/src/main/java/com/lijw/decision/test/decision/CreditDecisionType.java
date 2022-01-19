package com.lijw.decision.test.decision;

import com.lijw.decision.core.AbstractDecisionType;

public class CreditDecisionType extends AbstractDecisionType {

    @Override
    public String getDecisionTypeName() {
        return DecisionTypeEnum.CREDIT.getValueEN();
    }

}
