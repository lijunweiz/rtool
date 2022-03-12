package com.lijunweiz.rtool.core;

/**
 * 动作 干什么, 处理事件
 * @author lijunwei
 *
 */
public interface Action {
	
	/**
	 * 当条件{@link Condition#match(Context)}}匹配成功时执行
	 * @param context 当前规则流携带或者要返回的数据
	 */
	void dispose(Context context);

	/**
	 * 当条件{@link Condition#match(Context)}}匹配失败时执行
	 * @param context 当前规则流携带或者要返回的数据
	 */
 	default void opposite(Context context) {
 		return;
	}

	/**
	 * 动作名称, 动作的唯一标识符
	 */
	default String getName() {
		return getClass().getName();
	}
}
