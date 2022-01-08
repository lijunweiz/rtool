package com.lijw.decision.core;

/**
 * 默认值
 */
public final class DefaultValue {

    private DefaultValue() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static final Boolean TRUE = Boolean.TRUE;
    public static final Boolean FALSE = Boolean.FALSE;
    public static final Integer INT_200 = 200;
    public static final String MSG_FAIL = "处理失败";
    public static final String MSG_SUCCESS = "处理成功";

}
