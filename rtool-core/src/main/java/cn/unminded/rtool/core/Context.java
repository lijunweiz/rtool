package cn.unminded.rtool.core;

import cn.unminded.rtool.util.IDUtil;

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
	private final String contextId;

	/**
	 * 执行是否通过
	 */
	private boolean pass;

	/**
	 * 提示信息
	 */
	private String message;

	/**
	 * 当前context在不同的{@link Rule}之间进行参数传递的媒介
	 */
	private final Map<String, Object> data = new HashMap<>();
	
	public Context() {
		this.contextId = IDUtil.uuid();
		this.pass = DefaultValue.TRUE;
	}
	
	public String getContextId() {
		return contextId;
	}

	public boolean getPass() {
		return pass;
	}

	public Context setPass(boolean pass) {
		this.pass = pass;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Context setMessage(String message) {
		this.message = message;
		return this;
	}

	public Map<String, Object> getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Context{" +
				"contextId=" + contextId +
				", pass=" + pass +
				", message=" + message +
				", data=" + data +
				'}';
	}
}
