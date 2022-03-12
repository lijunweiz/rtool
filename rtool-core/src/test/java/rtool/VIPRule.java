package rtool;

import com.lijunweiz.rtool.core.AbstractRule;
import com.lijunweiz.rtool.core.Action;
import com.lijunweiz.rtool.core.Condition;
import com.lijunweiz.rtool.core.Context;

public class VIPRule extends AbstractRule {

	@Override
	public Condition getCondition() {
		return new Condition() {
			
			@Override
			public boolean match(Context context) {
				return false;
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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return getClass().getName();
			}
			
			@Override
			public void dispose(Context context) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
}
