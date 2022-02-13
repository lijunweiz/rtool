package com.lijw.item;

import com.lijw.decision.core.AbstractDecisionItem;

import java.io.Serializable;

public class IdentityNumber  extends AbstractDecisionItem implements Serializable {

    private String idNumber;

    public IdentityNumber() {
        super.decisionItemName = "decisionItemName";
    }

    public String getIdNumber() {
        return idNumber;
    }

    public IdentityNumber setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

}
