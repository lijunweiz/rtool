package com.lijw.decision.test;

import com.lijw.decision.core.AbstractDecisionItem;
import com.lijw.decision.core.DecisionItem;

import java.io.Serializable;
import java.util.Map;

/**
 * 电话号码决策项
 *
 */
public class Phone extends AbstractDecisionItem implements Serializable {

	private String phoneNumber;

	private Map<String, Object> extensionData;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Phone setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	/**
	 * @return the extensionData
	 */
	public Map<String, Object> getExtensionData() {
		return extensionData;
	}

	/**
	 * @param extensionData the extensionData to set
	 */
	public Phone setExtensionData(Map<String, Object> extensionData) {
		this.extensionData = extensionData;
		return this;
	}
	
}
