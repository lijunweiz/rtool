package com.lijw;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.util.StringUtils;
import com.lijw.item.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PhoneDecision extends AbstractDecision {

    private Logger logger = LoggerFactory.getLogger(PhoneDecision.class);

    @Override
    public boolean canDecide(Context context) {
        return super.canDecide(context);
    }

    @Override
	public void decide(Context context) {
        Phone item = context.getDecisionItem(StringUtils.getCamelName(Phone.class), Phone.class);
        logger.info("开始处理数据项: {}", item.getName());


	}

    @Override
    public int order() {
        return Integer.MIN_VALUE + 5;
    }

}
