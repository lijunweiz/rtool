package com.lijw.decision.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 决策结果
 */
@NoArgsConstructor
@Data
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


}
