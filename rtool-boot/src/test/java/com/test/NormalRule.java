package com.test;

import com.lijunweiz.rtool.boot.annotation.Rule;
import com.lijunweiz.rtool.core.AbstractRule;
import com.lijunweiz.rtool.core.Action;
import com.lijunweiz.rtool.core.Condition;

@Rule
public class NormalRule extends AbstractRule {

    @Override
    public Condition getCondition() {
        return context -> true;
    }

    @Override
    public Action getAction() {
        return context -> {
            context.getData().put("normal", "1");
        };
    }
}
