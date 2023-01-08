package cn.unminded.rtool.core;

/**
 * 执行顺序
 * @author lijunwei
 *
 */
public interface Order {

	/**
	 * 执行顺序, 从小到大排序, 值越小优先级越高
	 * @return
	 */
	default int order() {
		return Integer.MIN_VALUE;
	}
	
}
