package com.lijunweiz.rtool.core;

/**
 * 规则的抽象实现, 这里使用类的全类名作为规则名称{@link #getName()}
 * @author lijunwei
 *
 */
public abstract class AbstractRule implements Rule {
	
	@Override
	public Condition getCondition() {
		return null;
	}
	
	@Override
	public Action getAction() {
		return null;
	}
	
	@Override
	public String getName() {
		return getClass().getName();
	}
}
