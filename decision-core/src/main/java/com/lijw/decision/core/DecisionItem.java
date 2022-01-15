package com.lijw.decision.core;

import com.lijw.decision.core.util.StringUtils;

/**
 * 决策项，标识当前决策数据项，所有决策数据项必须实现该接口
 * @param
 */
public interface DecisionItem {

    String getDecisionItemName();

}
