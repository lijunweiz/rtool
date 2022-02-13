package com.lijw.item;


import com.lijw.decision.core.AbstractDecisionItem;

import java.io.Serializable;
import java.util.Map;

/**
 * 教育决策项
 * @author MrLi takealeaf@yeah.net
 *
 */
public class Education extends AbstractDecisionItem implements Serializable {

	private String degree;

	private Map<String, Object> extensionData;

	public String getDegree() {
		return degree;
	}

	public Education setDegree(String degree) {
		this.degree = degree;
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
	public Education setExtensionData(Map<String, Object> extensionData) {
		this.extensionData = extensionData;
		return this;
	}

}
