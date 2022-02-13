package com.lijw.decision.test.main;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lijw.decision.core.*;
import com.lijw.decision.core.exception.DecisionException;
import com.lijw.decision.core.result.Result;
import com.lijw.decision.core.support.DecisionTemplate;
import com.lijw.decision.core.util.JsonUtil;
import com.lijw.decision.core.util.StringUtils;
import com.lijw.decision.test.Education;
import com.lijw.decision.test.Phone;
import com.lijw.decision.test.Profession;
import com.lijw.decision.test.decision.CreditDecisionType;
import com.lijw.decision.test.decision.DecisionTypeEnum;
import com.lijw.decision.test.product.AliJieBei;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * 决策系统
 *
 */
public class DecisionTest {

	static Logger logger = LoggerFactory.getLogger(DecisionTest.class);

	public static void main(String[] args) throws DecisionException {
		DecisionTemplate decisionTemplate = new DecisionTemplate();
		decisionTemplate.init();

		// 创建决策上下文
		Context context = new Context();

		// 设置决策类型
		context.setDecisionType(DecisionTypeEnum.CREDIT.getValueEN());

		// 设置待决策产品名称
		context.setProductName(StringUtils.getCamelName(AliJieBei.class));

		// 设置决策项
		Map<String, Object> decisionItem = new HashMap<>();
		Education education = new Education().setDegree("本科");
		Phone phone = new Phone().setPhoneNumber("123456");
		Profession profession = new Profession().setProfession("IT");
		decisionItem.put(education.getName(), education);
		decisionItem.put(phone.getName(), phone);
		decisionItem.put(profession.getName(), profession);
		context.setDecisionItem(decisionItem);

		context.setStatus(DecisionStatus.SUBMIT);

		// 执行决策
		decisionTemplate.execute(context);

		// 收集结果
		String result = JSONObject.toJSONString(context.getResult());
		result = JSONObject.toJSONString(context, SerializerFeature.PrettyFormat);

		Context context1 = JsonUtil.parseObject(result, Context.class);
		System.out.println(context1.getDecisionItem("phone", Phone.class));

		logger.info("执行结果: {}", result);

		DecideResult decideResult = context.getResult();
		if (decideResult.getPass()) {
			logger.info("返回Result: {}", Result.success(decideResult.getData()).toJsonString());
		} else {
			logger.info("返回Result: {}", Result.fail().toJsonString());
		}

	}

}