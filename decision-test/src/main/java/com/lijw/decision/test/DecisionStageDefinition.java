package com.lijw.decision.test;

import com.lijw.decision.core.DecisionStage;

/**
 * 决策属于什么阶段，即决策流走到哪一步
 */
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

    DecisionStageDefinition(String stageValue, String stageNameCN, String stageNameEN) {
        this.stageValue = stageValue;
        this.stageNameCN = stageNameCN;
        this.stageNameEN = stageNameEN;
    }

    public String getStageValue() {
        return stageValue;
    }

    public String getStageNameCN() {
        return stageNameCN;
    }

    public String getStageNameEN() {
        return stageNameEN;
    }
}
