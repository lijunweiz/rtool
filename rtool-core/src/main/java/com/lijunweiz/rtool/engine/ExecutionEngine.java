package com.lijunweiz.rtool.engine;

import com.lijunweiz.rtool.engine.aviator.AviatorEngine;

/**
 * 规则表达式执行引擎
 */
public final class ExecutionEngine {

    public static Engine getDefaultEngine() {
        return ExecutionEngineHold.AVIATOR_ENGINE;
    }

    public static Engine getAviatorEngine() {
        return ExecutionEngineHold.AVIATOR_ENGINE;
    }

    private static class ExecutionEngineHold {
        private static final AviatorEngine AVIATOR_ENGINE = new AviatorEngine();
    }


}
