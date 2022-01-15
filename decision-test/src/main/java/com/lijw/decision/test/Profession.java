package com.lijw.decision.test;

import com.lijw.decision.core.AbstractDecisionItem;
import com.lijw.decision.core.DecisionItem;

import java.io.Serializable;
import java.util.Map;

/**
 * 职业决策项
 * @author MrLi takealeaf@yeah.net
 *
 */
public class Profession extends AbstractDecisionItem implements Serializable {

	private String professionType;

	private Map<String, Object> extensionData;

	public String getProfessionType() {
		return professionType;
	}

	public Profession setProfession(String professionType) {
		this.professionType = professionType;
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
	public Profession setExtensionData(Map<String, Object> extensionData) {
		this.extensionData = extensionData;
		return this;
	}
	
	

}
