package com.lijw.decision.core.support;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.exception.CannotDecisionException;

import java.util.List;

public interface DecisionOperations {

    void execute() throws CannotDecisionException;

    void execute(Context context) throws CannotDecisionException;

    void execute(Context context, List<DecisionFunction> decisionFlow) throws CannotDecisionException;

}
