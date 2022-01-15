package com.lijw.decision.core;

import com.lijw.decision.core.util.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * 贯穿整个决策流的上下文
 */
public class Context {

    /** 决策类型 */
    private DecisionType decisionType;
    /** 参与决策的数据k为决策项名称，v为决策数据 */
    private Map<String, DecisionItem> decisionItem;
    /** 决策状态，表示当前正在处理的数据项的处理状态 */
    private DecisionStatus status;
    /** 用户需要额外新增的其他数据 */
    private Map<String, Object> otherData;
    /** 决策结果 */
    private DecideResult result = new DecideResult();

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(DecisionType decisionType) {
        this.decisionType = decisionType;
    }

    public Map<String, DecisionItem> getDecisionItem() {
        return decisionItem;
    }

    public void setDecisionItem(Map<String, DecisionItem> decisionItem) {
        this.decisionItem = decisionItem;
    }

    public DecisionStatus getStatus() {
        return status;
    }

    public void setStatus(DecisionStatus status) {
        this.status = status;
    }

    public Map<String, Object> getOtherData() {
        return otherData;
    }

    public void setOtherData(Map<String, Object> otherData) {
        this.otherData = otherData;
    }

    public DecideResult getResult() {
        return result;
    }

    public void setResult(DecideResult result) {
        this.result = result;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Context context = (Context) o;
        return Objects.equals(decisionItem, context.decisionItem)
                && status == context.status
                && Objects.equals(otherData, context.otherData)
                && Objects.equals(result, context.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decisionItem, status, otherData, result);
    }

    @Override
    public String toString() {
        return "Context{" +
                "decisionItem=" + decisionItem +
                ", status=" + status +
                ", otherData=" + otherData +
                ", result=" + result +
                '}';
    }
}
