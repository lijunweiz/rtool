package com.lijw.decision.test.product;

import com.lijw.decision.core.product.AbstractProduct;

public abstract class PlatformProduct extends AbstractProduct {

    private String productSource = "1";

    private String productCode;

    private String productNameCN;

    private String platformName;

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
