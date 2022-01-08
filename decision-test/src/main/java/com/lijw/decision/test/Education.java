package com.lijw.decision.test;


import com.lijw.decision.core.DecisionItem;

import java.io.Serializable;
import java.util.Map;

/**
 * 教育决策项
 * @author MrLi takealeaf@yeah.net
 *
 */
public class Education implements DecisionItem, Serializable {
	
	private Map<String, Object> extensionData;

	/**
	 * @return the extensionData
	 */
	public Map<String, Object> getExtensionData() {
		return extensionData;
	}

	/**
	 * @param extensionData the extensionData to set
	 */
	public void setExtensionData(Map<String, Object> extensionData) {
		this.extensionData = extensionData;
	}

}
