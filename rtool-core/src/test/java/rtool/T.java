package rtool;

import com.lijunweiz.rtool.core.CompositeRule;
import com.lijunweiz.rtool.core.Context;
import com.lijunweiz.rtool.core.RuleException;
import com.lijunweiz.rtool.core.RuleTemplate;

public class T {

	public static void main(String[] args) throws RuleException {
		
		CompositeRule compositeRule = new CompositeRule();
		compositeRule.getRules().add(new BlacklistRule());
		compositeRule.getRules().add(new VIPRule());
		
		Context context = new Context();
		
		RuleTemplate ruleTemplate = new RuleTemplate();
		ruleTemplate.execute(context, compositeRule);
		
		System.out.println(context);
	}
	
}
