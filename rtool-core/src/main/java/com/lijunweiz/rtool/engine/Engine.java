package com.lijunweiz.rtool.engine;

import java.util.Map;

public interface Engine {

    Class<?> getEngineClass();

    Object execute(final String expression);

    Object execute(final String expression, final Map<String, Object> env);

    Object execute(final String expression, final Map<String, Object> env, final boolean cached);

    void invalidateCache(final String expression);
}
