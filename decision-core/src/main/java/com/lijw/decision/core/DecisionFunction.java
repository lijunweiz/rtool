package com.lijw.decision.core;

public interface DecisionFunction extends Decidable, Decision {

	default <T> String getDecisionName(Class<T> clazz) {
		return clazz.getClass().getSimpleName();
	}

}
