package com.lijw.decision.test.decision;

import com.lijw.decision.core.AbstractDecisionType;

public class WithdrawDecisionType extends AbstractDecisionType {

    @Override
    public String getDecisionTypeName() {
        return DecisionTypeEnum.WITHDRAW.getValueEN();
    }

}
