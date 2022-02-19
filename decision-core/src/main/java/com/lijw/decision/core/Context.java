package com.lijw.decision.core;

import com.lijw.decision.core.util.JsonUtil;

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
    private Map<String, Object> decisionItem;
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

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(String decisionType) {
        this.decisionType = decisionType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Map<String, Object> getDecisionItem() {
        return decisionItem;
    }

    public void setDecisionItem(Map<String, Object> decisionItem) {
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
            String json = JsonUtil.toJSONString(getDecisionItem(decisionItemName));
            return JsonUtil.parseObject(json, clazz);
        }
    }

    /**
     * 返回指定名称的决策项
     * @return
     */
    public Object getDecisionItem(String decisionItemName) {
        return getDecisionItem().get(decisionItemName);
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
        return Objects.equals(contextId, context.contextId)
                && Objects.equals(decisionType, context.decisionType)
                && Objects.equals(productName, context.productName)
                && Objects.equals(decisionItem, context.decisionItem)
                && status == context.status
                && Objects.equals(otherData, context.otherData)
                && Objects.equals(result, context.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextId, decisionType, productName, decisionItem, status, otherData, result);
    }

    @Override
    public String toString() {
        return "Context{" +
                "contextId='" + contextId + '\'' +
                ", decisionType='" + decisionType + '\'' +
                ", productName='" + productName + '\'' +
                ", decisionItem=" + decisionItem +
                ", status=" + status +
                ", otherData=" + otherData +
                ", result=" + result +
                '}';
    }
}
