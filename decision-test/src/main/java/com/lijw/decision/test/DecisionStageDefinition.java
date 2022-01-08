package com.lijw.decision.test;

import com.lijw.decision.core.DecisionStage;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 决策属于什么阶段，即决策流走到哪一步
 */
@Getter
@AllArgsConstructor
public enum DecisionStageDefinition implements DecisionStage {

    EDUCATION("0001", "教育", "education"),
    PHONE("0002", "教育", "phone"),
    PROFESSION("0003", "教育", "profession");

    /** 执行阶段数字代码 */
    private String stageValue;
    /** 执行阶段中文代码 */
    private String stageNameCN;
    /** 执行阶段英文代码 */
    private String stageNameEN;

}
