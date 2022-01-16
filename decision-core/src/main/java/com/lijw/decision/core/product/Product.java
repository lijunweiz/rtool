package com.lijw.decision.core.product;

/**
 * 所有待决策产品基类
 */
public interface Product {

    /** 产品名称 */
    String getProductName();

    /** 产品预处理 */
    boolean process();

}
