package cn.unminded.rtool.core;

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
	boolean start(Context context);

	/**
	 * 动作名称, 动作的唯一标识符
	 */
	default String getName() {
		return getClass().getName();
	}
}
