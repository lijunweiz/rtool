package cn.unminded.rtool.core;

import cn.unminded.rtool.util.JSONUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RuleTemplateTest {

	@Test
	public void testRuleTemplate() {
		
		CompositeRule compositeRule = new CompositeRule();
		compositeRule.getRules().add(new BlacklistRule());
		compositeRule.getRules().add(new VIPRule());
		if (compositeRule.getSorted()) {
			compositeRule.setSorted(DefaultValue.FALSE);
		}
		compositeRule.sortedRules();

		Context context = new Context();
		
		RuleTemplate ruleTemplate = new RuleTemplate();
		ruleTemplate.execute(context, compositeRule);
		
		System.out.println(JSONUtil.toJSONString(context));

		Assert.assertTrue(context.getPass());
	}
	
}
