package com.lijw.decision.type;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.DecisionType;
import com.lijw.decision.core.DefaultValue;
import org.springframework.stereotype.Component;

@Component
public class CreditDecisionType implements DecisionType {

    @Override
    public boolean process(Context context) {
        return DefaultValue.TRUE;
    }

    @Override
    public String getName() {
        return DecisionTypeEnum.CREDIT.getValueEN();
    }

}
