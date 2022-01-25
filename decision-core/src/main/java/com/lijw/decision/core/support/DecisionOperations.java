package com.lijw.decision.core.support;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.exception.DecisionException;

import java.util.List;

/**
 * 执行操作
 */
public interface DecisionOperations {

    void execute(Context context) throws DecisionException;

    void execute(Context context, List<DecisionFunction> decisionFlow, boolean transfer) throws DecisionException;

}
