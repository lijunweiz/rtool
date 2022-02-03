package com.lijw.decision.core;

/**
 * 决策项是否可以决策
 *
 */
public interface Decidable {

	/**
	 * 是否可以决策
	 * @param context
	 * @return
	 */
	boolean canDecide(Context context);

}
