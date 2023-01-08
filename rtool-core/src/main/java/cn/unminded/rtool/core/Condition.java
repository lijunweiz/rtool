package cn.unminded.rtool.core;

/**
 * 规则的输入条件, 变量
 * @author lijunwei
 *
 */
public interface Condition {

	/**
	 * 规则匹配
	 * @param context 当前规则流携带或者要返回的数据
	 * @return 成功为true or 失败为false
	 */
	boolean match(Context context);

	/**
	 * 条件名字, 唯一标识符
	 * @return
	 */
	default String getName() {
		return getClass().getName();
	}
	
}
