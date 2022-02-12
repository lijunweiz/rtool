package com.lijw.decision.test.decision;

import com.lijw.decision.core.AbstractDecisionType;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.DefaultValue;

public class WithdrawDecisionType extends AbstractDecisionType {

    @Override
    public boolean process(Context context) {
        return DefaultValue.TRUE;
    }

    @Override
    public String getName() {
        return DecisionTypeEnum.WITHDRAW.getValueEN();
    }

}
