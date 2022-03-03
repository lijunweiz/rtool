package com.lijw.decision.core;

public interface DecisionFunction extends Decidable, Decision {

	/**
	 * 执行顺序
	 * @return
	 */
	int order();

	/**
	 * 每一个实现类，应该在此处关系需要进行判定的数据，并给出决策结果
	 * @param context
	 * @return
	 */
	void decide(Context context);

	/**
	 * 跳过当前决策函数，默认false
	 * @param context
	 * @return 需要当前决策true or 否则false
	 */
	default boolean skip(Context context){
		return DefaultValue.FALSE;
	}

}
