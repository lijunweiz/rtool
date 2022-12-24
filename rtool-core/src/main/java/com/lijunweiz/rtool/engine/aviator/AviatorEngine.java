package com.lijunweiz.rtool.engine.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.lijunweiz.rtool.engine.AbstractEngine;
import com.lijunweiz.rtool.engine.Engine;

import java.util.Map;


public class AviatorEngine extends AbstractEngine implements Engine {

    private static final AviatorEvaluatorInstance INSTANCE = AviatorEvaluator.getInstance();

    private AviatorEvaluatorInstance getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<?> getEngineClass() {
        return AviatorEngine.class;
    }

    @Override
    public Object execute(String expression) {
        return getInstance().execute(expression);
    }

    @Override
    public Object execute(String expression, Map<String, Object> env) {
        return getInstance().execute(expression, env);
    }

    @Override
    public Object execute(String expression, Map<String, Object> env, boolean cached) {
        return getInstance().execute(expression, env, cached);
    }

    @Override
    public void invalidateCache(String expression) {
        getInstance().invalidateCache(expression);
    }

}
