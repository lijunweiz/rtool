package com.lijw.decision.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 决策结果
 */
public class DecideResult implements Serializable {

    /** 当前决策项不通过或者出现异常时，是否强制继续执行下一个决策项 */
    private Boolean forceContinue = DefaultValue.TRUE;

    /** 决策流是否最终通过 */
    private Boolean pass = DefaultValue.TRUE;

    /** 提示信息 */
    private String msg = DefaultValue.MSG_SUCCESS;

    /** 状态码 */
    private Integer code = DefaultValue.INT_200;

    /** 返回给客户端的决策结果数据 */
    private Map<String, Object> data = new HashMap<>();

    public Boolean getForceContinue() {
        return forceContinue;
    }

    public void setForceContinue(Boolean forceContinue) {
        this.forceContinue = forceContinue;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecideResult result = (DecideResult) o;
        return Objects.equals(forceContinue, result.forceContinue)
                && Objects.equals(pass, result.pass)
                && Objects.equals(msg, result.msg)
                && Objects.equals(code, result.code)
                && Objects.equals(data, result.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forceContinue, pass, msg, code, data);
    }

    @Override
    public String toString() {
        return "DecideResult{" +
                "forceContinue=" + forceContinue +
                ", pass=" + pass +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
