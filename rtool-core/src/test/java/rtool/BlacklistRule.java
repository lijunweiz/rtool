package rtool;

import com.lijunweiz.rtool.core.AbstractRule;
import com.lijunweiz.rtool.core.Action;
import com.lijunweiz.rtool.core.Condition;
import com.lijunweiz.rtool.core.Context;

public class BlacklistRule extends AbstractRule {

	@Override
	public Condition getCondition() {
		// TODO Auto-generated method stub
		return new Condition() {
			
			@Override
			public boolean match(Context context) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return getClass().getName();
			}
		};
	}
	
	@Override
	public Action getAction() {
		return new Action() {
			
			@Override
			public void opposite(Context context) {
				
			}
			
			@Override
			public String getName() {
				return getClass().getName();
			}
			
			@Override
			public void dispose(Context context) {
				
			}
		};
	}
	
}
