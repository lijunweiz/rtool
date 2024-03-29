package cn.unminded.rtool.core;

/**
 * 默认值
 */
public final class DefaultValue {

    private DefaultValue() {
        throw new UnsupportedOperationException();
    }

    public static final String EMPTY_STRING = "";
    public static final boolean TRUE = true;
    public static final boolean FALSE = false;
    public static final Integer INT_200 = 200;//成功
    public static final Integer INT_400 = 400;//失败
    public static final String MSG_FAIL = "处理失败";
    public static final String MSG_SUCCESS = "处理成功";

}
