package com.lijw.decision.test;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.DefaultValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneDecision extends AbstractDecision {

    private Logger logger = LoggerFactory.getLogger(PhoneDecision.class);

    @Override
    public Boolean canDecide(Context context) {
        return super.canDecide(context);
    }

    @Override
	public void decide(Context context) {
        logger.info("获取决策数据项: phone");

        Phone item = context.getDecisionItem(Phone.class);

        context.getResult().setForceContinue(DefaultValue.FALSE);// 可以终端决策流
        context.getResult().setPreviousPast(DefaultValue.FALSE);
        context.getResult().setCurrentPass(DefaultValue.FALSE);
	}

}
