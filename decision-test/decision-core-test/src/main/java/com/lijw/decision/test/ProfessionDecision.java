package com.lijw.decision.test;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.DefaultValue;
import com.lijw.decision.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfessionDecision extends AbstractDecision {

	private Logger logger = LoggerFactory.getLogger(ProfessionDecision.class);

	@Override
	public boolean canDecide(Context context) {
		return DefaultValue.TRUE;
	}

	@Override
	public void decide(Context context) {
		Profession item = context.getDecisionItem(StringUtils.getCamelName(Profession.class), Profession.class);
		logger.info("开始处理数据项: {}", item.getName());


	}

	@Override
	public int order() {
		return -1;
	}

}
