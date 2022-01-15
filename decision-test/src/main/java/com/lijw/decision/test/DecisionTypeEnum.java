package com.lijw.decision.test;

public enum DecisionTypeEnum {

    CREDIT(1, "credit", "授信"),
    WITHDRAW(1, "withdraw", "提现"),
    INCREASE_QUOTA(1, "increaseQuota", "提额");

    private Integer value;
    private String valueEN;
    private String valueCN;

    DecisionTypeEnum(Integer value, String valueEN, String valueCN) {
        this.value = value;
        this.valueEN = valueEN;
        this.valueCN = valueCN;
    }

    public Integer getValue() {
        return value;
    }

    public String getValueEN() {
        return valueEN;
    }

    public String getValueCN() {
        return valueCN;
    }
}
