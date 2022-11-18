package com.lijunweiz.rtool.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;

import java.util.Map;

/**
 * 规则表达式执行引擎
 */
public final class ExecutionEngine {

    private static final AviatorEvaluatorInstance INSTANCE = AviatorEvaluator.getInstance();

    public static AviatorEvaluatorInstance getInstance() {
        return INSTANCE;
    }

    public static Object execute(final String expression) {
        return getInstance().execute(expression);
    }

    public static Object execute(final String expression, final Map<String, Object> env) {
        return getInstance().execute(expression, env);
    }

    public static Object execute(final String expression, final Map<String, Object> env,
                                 final boolean cached) {
        return getInstance().execute(expression, env, cached);
    }

    public static void invalidateCache(final String expression) {
        getInstance().invalidateCache(expression);
    }


}
