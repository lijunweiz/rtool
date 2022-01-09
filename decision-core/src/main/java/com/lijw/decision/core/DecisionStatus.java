package com.lijw.decision.core;

/**
 * 决策状态
 */
public enum DecisionStatus {

    CONFIRM(1, "请求确认"),
    PROCESSING(2, "处理中"),
    FINISHED(3, "处理完成"),

    ;

    private Integer statusValue;

    private String statusDesc;

    DecisionStatus(Integer statusValue, String statusDesc) {
        this.statusValue = statusValue;
        this.statusDesc = statusDesc;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
}
