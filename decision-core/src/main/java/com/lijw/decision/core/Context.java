package com.lijw.decision.core;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 贯穿整个决策流的上下文
 */
public class Context {

    /** contextId 每一次创建都生成一个唯一id */
    private String contextId;
    /** 决策类型 */
    private String decisionType;
    /** 决策产品 */
    private String productName;
    /** 参与决策的数据k为决策项名称，v为决策数据 */
    private Map<String, DecisionItem> decisionItems;
    /** 决策状态，表示当前正在处理的数据项的处理状态 */
    private DecisionStatus status;
    /** 用户需要额外新增的其他数据 */
    private Map<String, Object> otherData;
    /** 决策结果 */
    private DecideResult result = new DecideResult();

    public Context() {
        this.contextId = UUID.randomUUID().toString();
    }

    public String getContextId() {
        return contextId;
    }

    public Context setContextId(String contextId) {
        this.contextId = contextId;
        return this;
    }

    public String getDecisionType() {
        return decisionType;
    }

    public Context setDecisionType(String decisionType) {
        this.decisionType = decisionType;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public Context setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Map<String, DecisionItem> getDecisionItems() {
        return decisionItems;
    }

    public Context setDecisionItems(Map<String, DecisionItem> decisionItems) {
        this.decisionItems = decisionItems;
        return this;
    }

    public DecisionStatus getStatus() {
        return status;
    }

    public Context setStatus(DecisionStatus status) {
        this.status = status;
        return this;
    }

    public Map<String, Object> getOtherData() {
        return otherData;
    }

    public Context setOtherData(Map<String, Object> otherData) {
        this.otherData = otherData;
        return this;
    }

    public DecideResult getResult() {
        return result;
    }

    public Context setResult(DecideResult result) {
        this.result = result;
        return this;
    }

    /**
     * 返回指定名称及类型的决策项
     * @param decisionItemName
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getDecisionItem(String decisionItemName, Class<T> clazz) {
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("参数clazz不能为null");
        } else {
            return (T) getDecisionItem(decisionItemName);
        }
    }

    /**
     * 返回指定名称的决策项
     * @return
     */
    public DecisionItem getDecisionItem(String decisionItemName) {
        return getDecisionItems().get(decisionItemName);
    }

    /**
     * 放入指定名字的决策项
     * @param decisionItemName
     * @param decisionItem
     */
    public Context setDecisionItem(String decisionItemName, DecisionItem decisionItem) {
        getDecisionItems().put(decisionItemName, decisionItem);
        return this;
    }

    public static Context getInstance() {
        return new Context();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Context context = (Context) o;
        return Objects.equals(contextId, context.contextId)
                && Objects.equals(decisionType, context.decisionType)
                && Objects.equals(productName, context.productName)
                && Objects.equals(decisionItems, context.decisionItems)
                && status == context.status
                && Objects.equals(otherData, context.otherData)
                && Objects.equals(result, context.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextId, decisionType, productName, decisionItems, status, otherData, result);
    }

    @Override
    public String toString() {
        return "Context{" +
                "contextId='" + contextId + '\'' +
                ", decisionType='" + decisionType + '\'' +
                ", productName='" + productName + '\'' +
                ", decisionItems=" + decisionItems +
                ", status=" + status +
                ", otherData=" + otherData +
                ", result=" + result +
                '}';
    }
}
