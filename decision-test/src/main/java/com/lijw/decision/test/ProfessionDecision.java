package com.lijw.decision.test;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.DefaultValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfessionDecision extends AbstractDecision {

	private Logger logger = LoggerFactory.getLogger(ProfessionDecision.class);

	@Override
	public Boolean canDecide(Context context) {
		return DefaultValue.TRUE;
	}

	@Override
	public void decide(Context context) {
		Profession item = context.getDecisionItem(Profession.class);
		logger.info("开始处理数据项: {}", item.getDecisionItemName());


	}

}
