package com.lijw.decision;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.DefaultValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DecisionTongDun extends AbstractDecision {

    private Logger logger = LoggerFactory.getLogger(DecisionTongDun.class);

    @Override
    public boolean skip(Context context) {
        return DefaultValue.TRUE;
    }

    @Override
    public void decide(Context context) {
        logger.info("tongDun");
    }

    @Override
    public int order() {
        return Integer.MIN_VALUE + 3;
    }
}
