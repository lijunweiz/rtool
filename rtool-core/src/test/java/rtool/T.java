package rtool;

import com.lijunweiz.rtool.core.*;

public class T {

	public static void main(String[] args) throws RuleException {
		
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
		
		System.out.println(context);
	}
	
}
