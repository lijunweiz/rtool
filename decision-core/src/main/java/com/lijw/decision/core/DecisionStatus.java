package com.lijw.decision.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 决策状态
 */
@Getter
@AllArgsConstructor
public enum DecisionStatus {

    CONFIRM(1, "请求确认"),
    PROCESSING(2, "处理中"),
    FINISHED(3, "处理完成"),

    ;

    private Integer statusValue;

    private String statusDesc;

}
