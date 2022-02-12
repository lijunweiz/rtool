package com.lijw.decision.core.support;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.exception.DecisionException;

import java.util.List;

/**
 * 执行操作
 */
public interface DecisionOperations {

    /**
     * 决策流执行
     * @param context 决策流执行上下文
     * @throws DecisionException
     */
    void execute(Context context) throws DecisionException;

    /**
     * 决策流执行
     * @param context 决策流执行上下文
     * @param decisionFlow 执行流
     * @throws DecisionException
     */
    void execute(Context context, List<DecisionFunction> decisionFlow) throws DecisionException;

    /**
     * 决策流执行
     * @param context 决策流执行上下文
     * @param decisionFlow 执行流
     * @param transfer 是否是传递的，true or false
     * @throws DecisionException
     */
    void execute(Context context, List<DecisionFunction> decisionFlow, boolean transfer) throws DecisionException;

}
