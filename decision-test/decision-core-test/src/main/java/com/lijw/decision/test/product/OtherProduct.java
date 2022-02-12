package com.lijw.decision.test.product;

import com.lijw.decision.core.product.AbstractProduct;

public abstract class OtherProduct extends AbstractProduct {

    private String productSource = "2";

    private String productCode;

    private String productNameCN;

    private String platformName;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductNameCN() {
        return productNameCN;
    }

    public void setProductNameCN(String productNameCN) {
        this.productNameCN = productNameCN;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

}
