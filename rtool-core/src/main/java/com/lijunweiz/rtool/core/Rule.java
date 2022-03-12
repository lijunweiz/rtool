package com.lijunweiz.rtool.core;

/**
 * 规则{@link Rule}由条件{@link Condition}和动作{@link Action}及唯一标识符{@link Rule#getName()}组成, 
 * 并继承{@link Order}用来确认不通的规则执行顺序
 * @author lijunwei
 *
 */
public interface Rule extends Order {
	
	/**
	 * {@link Condition}
	 * @return 获取条件
	 */
	Condition getCondition();
	
	/**
	 * {@link Action}
	 * @return 获取动作
	 */
	Action getAction();

	/**
	 * 一个规则的唯一标识符
	 * @return 规则名称
	 */
	String getName();
	
}
