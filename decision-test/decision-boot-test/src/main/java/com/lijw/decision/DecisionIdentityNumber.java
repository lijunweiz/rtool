package com.lijw.decision;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DecisionIdentityNumber extends AbstractDecision {

    private Logger logger = LoggerFactory.getLogger(DecisionIdentityNumber.class);

    @Override
    public void decide(Context context) {
        logger.info(getName());
    }

    @Override
    public int order() {
        return Integer.MIN_VALUE + 1;
    }
}