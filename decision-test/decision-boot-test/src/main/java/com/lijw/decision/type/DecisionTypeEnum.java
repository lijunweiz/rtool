package com.lijw.decision.type;

import com.lijw.decision.core.util.StringUtils;

import java.util.Optional;

public enum DecisionTypeEnum {

    CREDIT(1, "credit", "授信"),
    WITHDRAW(2, "withdraw", "提现"),
    INCREASE_QUOTA(3, "increaseQuota", "提额");

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

    public static Optional<DecisionTypeEnum> getDecisionType(String name) {
        if (StringUtils.isNullOrEmpty(name)) {
            return Optional.empty();
        }

        for (DecisionTypeEnum typeEnum : values()) {
            if (typeEnum.getValueEN().equalsIgnoreCase(name)) {
                return Optional.of(typeEnum);
            }
        }

        return Optional.empty();
    }

}
