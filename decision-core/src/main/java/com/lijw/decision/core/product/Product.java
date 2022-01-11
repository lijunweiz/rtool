package com.lijw.decision.core.product;

/**
 * 所有待决策产品基类
 */
public interface Product<T> {

    /** 得到产品，可以某个产品具体的组合值 */
    T getProduct();

    /** 产品名称 */
    String productName();

}
