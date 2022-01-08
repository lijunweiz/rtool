package com.lijw.decision.core;

public interface DecisionFunction extends Decidable, Decision {

	/**
	 * 决策名称，表示当前的决策数据所用是哪个决策，实现类可按需定义，对于主流程不是必须的
	 * @return
	 */
	default String getDecisionName() {
		return null;
	}

}
