package com.lijunweiz.rtool.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 规则上下文 每一次的规则执行都会生成一个唯一的contextId
 * @author lijunwei
 *
 */
public class Context implements Serializable {
	
	private static final long serialVersionUID = 7559386874417840210L;

	/**
	 * 当前context的唯一标识符
	 */
	private long contextId;

	/**
	 * 当前context在不同的{@link Rule}之间进行参数传递的媒介
	 */
	private Map<String, Object> data = new HashMap<>(6);
	
	public Context() {
		this.contextId = System.currentTimeMillis();
	}
	
	public long getContextId() {
		return contextId;
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Context [contextId=" + contextId + ", data=" + data + "]";
	}
}
