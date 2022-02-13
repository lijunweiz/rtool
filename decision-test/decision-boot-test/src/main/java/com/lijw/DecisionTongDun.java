package com.lijw;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DecisionTongDun extends AbstractDecision {

    private Logger logger = LoggerFactory.getLogger(DecisionTongDun.class);

    @Override
    public void decide(Context context) {
        logger.info(getName());
    }

    @Override
    public int order() {
        return Integer.MIN_VALUE + 3;
    }
}
