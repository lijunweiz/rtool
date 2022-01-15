package com.lijw.decision.test;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EducationDecision extends AbstractDecision {

	private Logger logger = LoggerFactory.getLogger(EducationDecision.class);

	private String decisionName;

	@Override
	public Boolean canDecide(Context context) {
		return super.canDecide(context);
	}

	@Override
	public void decide(Context context) {
		Education item = context.getDecisionItem(Education.class);
		logger.info("开始处理数据项: {}", item.getDecisionItemName());
		Map<String, Object> data = new HashMap<>();
		data.put("edu", "eduData");
		context.getResult().setData(data);
	}

	@Override
	public String getDecisionName() {
		if (Objects.isNull(decisionName) || StringUtils.EMPTY.equals(decisionName)) {
			decisionName = StringUtils.getCamelName(EducationDecision.class);
		}

		return decisionName;
	}
}
