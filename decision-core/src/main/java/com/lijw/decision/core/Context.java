package com.lijw.decision.core;

import com.lijw.decision.common.util.StringUtils;
import lombok.Data;

import java.util.Map;

/**
 * 贯穿整个决策流的上下文
 */
@Data
public class Context {

    /** 决策流id，贯穿一笔业务 */
    private Long flowId;
    /** 决策流中的决策项id，通常每个决策项均会生成一个 */
    private Long spanId;
    /** 参与决策的数据k为决策项名称，v为决策数据 */
    private Map<String, DecisionItem> decisionItem;
    /** 决策阶段，表示当前正在处理的数据项或正在执行的决策项 */
    private DecisionStage stage;
    /** 决策状态，表示当前正在处理的数据项的处理状态 */
    private DecisionStatus status;
    /** 用户需要额外新增的其他数据 */
    private Map<String, Object> otherData;
    /** 决策结果 */
    private DecideResult result = new DecideResult();

    /**
     * 返回指定类型的决策项
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getDecisionItem(Class<? extends DecisionItem> clazz) {
        return (T) getDecisionItem().get(StringUtils.getCamelName(clazz));
    }

    /**
     * 放入指定名字的决策项
     * @param decisionItemName
     * @param decisionItem
     */
    public void setDecisionItem(String decisionItemName, DecisionItem decisionItem) {
        getDecisionItem().put(decisionItemName, decisionItem);
    }

}
