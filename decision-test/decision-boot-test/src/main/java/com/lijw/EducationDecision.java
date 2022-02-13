package com.lijw;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.util.StringUtils;
import com.lijw.item.Education;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EducationDecision extends AbstractDecision {

	private Logger logger = LoggerFactory.getLogger(EducationDecision.class);

	@Override
	public boolean canDecide(Context context) {
		return super.canDecide(context);
	}

	@Override
	public void decide(Context context) {
		Education item = context.getDecisionItem(StringUtils.getCamelName(Education.class), Education.class);
		logger.info("开始处理数据项: {}", item.getName());


	}

	@Override
	public int order() {
		return Integer.MIN_VALUE + 4;
	}
}
