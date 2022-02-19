package com.lijw.decision.test.decision;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.DecisionType;
import com.lijw.decision.core.DefaultValue;

public class IncreaseQuotaDecisionType implements DecisionType {

    @Override
    public boolean process(Context context) {
        return DefaultValue.TRUE;
    }

    @Override
    public String getName() {
        return DecisionTypeEnum.INCREASE_QUOTA.getValueEN();
    }

}
