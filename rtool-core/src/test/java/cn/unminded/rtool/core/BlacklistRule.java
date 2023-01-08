package cn.unminded.rtool.core;

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
			public boolean start(Context context) {
				return true;
			}
			
			@Override
			public String getName() {
				return getClass().getName();
			}
			
		};
	}
	
}
