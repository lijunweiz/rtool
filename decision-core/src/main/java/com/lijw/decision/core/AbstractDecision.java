package com.lijw.decision.core;

/**
 * 简单实现决策
 * 
 */
public abstract class AbstractDecision implements DecisionFunction {

    @Override
    public Boolean canDecide(Context context) {
        Boolean previousPast = context.getResult().getPreviousPast();
        Boolean forceContinue = context.getResult().getForceContinue();
        if (previousPast) {
            return DefaultValue.TRUE;
        } else if (forceContinue) {
            return DefaultValue.TRUE;
        } else {
            return DefaultValue.FALSE;
        }
    }

    @Override
    public void decide(Context context) {
        context.getResult().setCurrentPass(DefaultValue.TRUE);
        context.getResult().setPreviousPast(DefaultValue.TRUE);// 对于下一决策，当前决策是其前一决策
        context.getResult().setForceContinue(DefaultValue.TRUE);
    }

}
