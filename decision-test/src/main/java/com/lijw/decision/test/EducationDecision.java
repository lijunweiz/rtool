package com.lijw.decision.test;

import com.lijw.decision.common.util.StringUtils;
import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import lombok.extern.java.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log
public class EducationDecision extends AbstractDecision {

	private String decisionName;

	@Override
	public Boolean canDecide(Context context) {
		return super.canDecide(context);
	}

	@Override
	public void decide(Context context) {
		log.info("获取决策数据项: education");
		Education item = context.getDecisionItem(Education.class);
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
