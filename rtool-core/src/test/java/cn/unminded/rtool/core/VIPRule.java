package cn.unminded.rtool.core;

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
			public boolean start(Context context) {
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
	
}
