package cn.unminded.rtool.core;

import cn.unminded.rtool.util.JSONUtil;

import java.util.Optional;

/**
 * 方便的结果表示
 * @param <T> 产生的结果数据类型
 */
public class Result<T> {

    /**
     * 结果状态码
     */
    private int code;
    /**
     * 结果标志，成功true,失败false
     */
    private boolean success;
    /**
     * 结果提示信息
     */
    private String msg;
    /**
     * 结果数据集
     */
    private T data;

    public Result() {
    }

    public Result(int code, boolean success, String msg, T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功方法
     * @return
     */
    public static <T> Result<T> success() {
        return Result.success(DefaultValue.INT_200, DefaultValue.MSG_SUCCESS, null);
    }

    /**
     * 成功方法
     * @param data 数据集
     * @param <T> 数据类型
     * @return
     */
    public static <T> Result<T> success(T data) {
        return Result.success(DefaultValue.INT_200, DefaultValue.MSG_SUCCESS, data);
    }

    /**
     * 成功方法
     * @param code 状态码
     * @param msg 提示信息
     * @param <T> 数据类型
     * @return
     */
    public static <T> Result<T> success(int code, String msg) {
        return Result.success(code, msg, null);
    }

    /**
     * 成功方法
     * @param code 状态码
     * @param msg 提示信息
     * @param data 数据集
     * @param <T> 数据类型
     * @return
     */
    public static <T> Result<T> success(int code, String msg, T data) {
        return Result.both(code, DefaultValue.TRUE, msg, data);
    }

    /**
     * 失败方法
     * @return
     */
    public static <T> Result<T> fail() {
        return fail(DefaultValue.INT_400, DefaultValue.MSG_FAIL, null);
    }

    /**
     * 失败方法
     * @param data 数据集
     * @param <T> 数据类型
     * @return
     */
    public static <T> Result<T> fail(T data) {
        return fail(DefaultValue.INT_400, DefaultValue.MSG_FAIL, data);
    }

    /**
     * 失败方法
     * @param code 状态码
     * @param msg 提示信息
     * @param <T> 数据类型
     * @return
     */
    public static <T> Result<T> fail(int code, String msg) {
        return fail(code, msg, null);
    }

    /**
     * 失败方法
     * @param code 状态码
     * @param msg 提示信息
     * @param data 数据集
     * @param <T> 数据类型
     * @return
     */
    public static <T> Result<T> fail(int code, String msg, T data) {
        return both(code, DefaultValue.FALSE, msg, data);
    }

    /**
     * 构造结果的通用方法
     * @param code 状态码
     * @param success 成功标志，成功true,失败false
     * @param msg 提示信息
     * @param data 结果数据集
     * @param <T> 数据类型
     * @return
     */
    private static <T> Result<T> both(int code, boolean success, String msg, T data) {
        return new Result<>(code, success, msg, data);
    }

    public static <T> Result<T> getInstance() {
        return new Result<>();
    }

    public Optional<Result<T>> optional() {
        return Optional.of(this);
    }

    public String toJSONString() {
        return JSONUtil.toJSONString(this);
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public boolean getSuccess() {
        return success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
