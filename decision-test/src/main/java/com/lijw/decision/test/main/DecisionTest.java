package com.lijw.decision.test.main;

import com.alibaba.fastjson.JSONObject;
import com.lijw.decision.common.util.StringUtils;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.exception.CannotDecisionException;
import com.lijw.decision.core.support.DecisionTemplate;
import com.lijw.decision.test.*;

import java.util.HashMap;


/**
 * 决策系统
 *
 */
public class DecisionTest {

	public static void main(String[] args) throws CannotDecisionException {
		// 决策上下文
		Context context = new Context();
		context.setDecisionItem(new HashMap<>());
		context.setDecisionItem(DecisionStageDefinition.EDUCATION.getStageNameEN(), new Education());
		context.setDecisionItem(StringUtils.getCamelName(Phone.class), new Phone());
		context.setDecisionItem(StringUtils.getCamelName(Profession.class), new Profession());

		DecisionTemplate decisionTemplate = new DecisionTemplate(context);

		decisionTemplate.execute();

		System.out.println(JSONObject.toJSONString(context));
		System.out.println(JSONObject.toJSONString(decisionTemplate.getDecisionFunctionMap()));
	}
	
}