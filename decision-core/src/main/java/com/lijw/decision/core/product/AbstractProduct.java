package com.lijw.decision.core.product;

import com.lijw.decision.core.util.StringUtils;

/**
 * 简单实现，可以对产品做一些预定义
 */
public abstract class AbstractProduct implements Product {

    /**
     * 产品名称
     */
    protected String productName;

    public AbstractProduct() {
        productName = StringUtils.getCamelName(getClass());
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public boolean process() {
        return true;
    }

}
