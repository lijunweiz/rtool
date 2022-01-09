package com.lijw.decision.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 决策结果
 */
public class DecideResult implements Serializable {

    /** 当前决策项 */
    private DecisionStage decisionStage;

    /** 当前决策项是否通过 */
    private Boolean currentPass = DefaultValue.TRUE;

    /** 上一个决策项是否通过 */
    private Boolean previousPast = DefaultValue.TRUE;

    /** 当前决策项不通过，是否强制继续执行下一个决策项 */
    private Boolean forceContinue = DefaultValue.TRUE;

    /** 决策流是否最终通过 */
    private Boolean pass = DefaultValue.TRUE;

    /** 提示信息 */
    private String msg = DefaultValue.MSG_SUCCESS;

    /** 状态码 */
    private Integer code = DefaultValue.INT_200;

    /** 返回给客户端的决策结果数据 */
    private Map<String, Object> data = new HashMap<>();

    public DecisionStage getDecisionStage() {
        return decisionStage;
    }

    public void setDecisionStage(DecisionStage decisionStage) {
        this.decisionStage = decisionStage;
    }

    public Boolean getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(Boolean currentPass) {
        this.currentPass = currentPass;
    }

    public Boolean getPreviousPast() {
        return previousPast;
    }

    public void setPreviousPast(Boolean previousPast) {
        this.previousPast = previousPast;
    }

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
        return Objects.equals(decisionStage, result.decisionStage)
                && Objects.equals(currentPass, result.currentPass)
                && Objects.equals(previousPast, result.previousPast)
                && Objects.equals(forceContinue, result.forceContinue)
                && Objects.equals(pass, result.pass)
                && Objects.equals(msg, result.msg)
                && Objects.equals(code, result.code)
                && Objects.equals(data, result.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decisionStage, currentPass, previousPast, forceContinue, pass, msg, code, data);
    }

    @Override
    public String toString() {
        return "DecideResult{" +
                "decisionStage=" + decisionStage +
                ", currentPass=" + currentPass +
                ", previousPast=" + previousPast +
                ", forceContinue=" + forceContinue +
                ", pass=" + pass +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
