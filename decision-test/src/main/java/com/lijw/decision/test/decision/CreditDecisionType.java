package com.lijw.decision.test.decision;

import com.lijw.decision.core.AbstractDecisionType;
import com.lijw.decision.test.DecisionTypeEnum;

public class CreditDecisionType extends AbstractDecisionType {

    @Override
    public String getDecisionTypeName() {
        return DecisionTypeEnum.CREDIT.getValueEN();
    }

}
