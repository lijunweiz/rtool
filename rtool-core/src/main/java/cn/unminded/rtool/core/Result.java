package cn.unminded.rtool.core;


public class Result {

    private Integer code;

    private String desc;

    private Object data;

    public Result() {
    }

    public Result(Integer code, String desc, Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Result setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }

    public static Result ok() {
        return new Result(DefaultValue.INT_200,DefaultValue.MSG_SUCCESS, null);
    }

    public static Result ok(Object data) {
        return new Result(DefaultValue.INT_200, DefaultValue.MSG_SUCCESS, data);
    }

    public static Result ok(Integer code) {
        return new Result(code, DefaultValue.MSG_SUCCESS, null);
    }

    public static Result ok(String desc) {
        return new Result(DefaultValue.INT_200, desc, null);
    }

    public static Result ok(Integer code, String desc) {
        return new Result(code, desc, null);
    }

    public static Result ok(Integer code, String desc, Object data) {
        return new Result(code, desc, data);
    }

    public static Result fail() {
        return new Result(DefaultValue.INT_400, DefaultValue.MSG_FAIL, null);
    }

    public static Result fail(Integer code) {
        return new Result(code, DefaultValue.MSG_FAIL, null);
    }

    public static Result fail(String desc) {
        return new Result(DefaultValue.INT_400, desc, null);
    }

    public static Result fail(Integer code, String desc) {
        return new Result(code, desc, null);
    }

    public static Result fail(Integer code, String desc, Object data) {
        return new Result(code, desc, data);
    }

}
