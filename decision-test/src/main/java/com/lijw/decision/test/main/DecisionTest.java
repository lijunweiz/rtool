package com.lijw.decision.test.main;

import com.alibaba.fastjson.JSONObject;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.exception.DecisionException;
import com.lijw.decision.core.support.DecisionTemplate;
import com.lijw.decision.core.util.StringUtils;
import com.lijw.decision.test.DecisionStageDefinition;
import com.lijw.decision.test.Education;
import com.lijw.decision.test.Phone;
import com.lijw.decision.test.Profession;
import com.lijw.decision.test.decision.CreditDecisionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 决策系统
 *
 */
public class DecisionTest {

	static Logger logger = LoggerFactory.getLogger(DecisionTest.class);

	public static void main(String[] args) throws DecisionException {
		// 决策上下文
		Context context = new Context();
		context.setDecisionItem(new HashMap<>());
		context.setDecisionItem(DecisionStageDefinition.EDUCATION.getStageNameEN(), new Education().setDegree("本科"));
		context.setDecisionItem(StringUtils.getCamelName(Phone.class), new Phone().setPhoneNumber("123456"));
		context.setDecisionItem(StringUtils.getCamelName(Profession.class), new Profession().setProfession("IT"));

		context.setDecisionType(new CreditDecisionType());

		DecisionTemplate decisionTemplate = new DecisionTemplate(context);

		decisionTemplate.execute();

		String result = JSONObject.toJSONString(context.getResult());

		logger.info("执行结果: {}", result);

	}
	
}
