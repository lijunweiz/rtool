package com.lijw.decision.test;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfessionDecision extends AbstractDecision {

	private Logger logger = LoggerFactory.getLogger(ProfessionDecision.class);

	@Override
	public Boolean canDecide(Context context) {
		return super.canDecide(context);
	}

	@Override
	public void decide(Context context) {
		logger.info("获取决策数据项: profession");

		Profession item = context.getDecisionItem(Profession.class);

	}

}
