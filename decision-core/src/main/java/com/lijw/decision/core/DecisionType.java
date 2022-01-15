package com.lijw.decision.core;

/**
 * 决策类型，当前决策的是那类业务
 *
 */
public interface DecisionType {

    String getDecisionTypeName();

    boolean process();

}
