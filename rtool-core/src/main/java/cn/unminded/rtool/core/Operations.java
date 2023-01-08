package cn.unminded.rtool.core;

/**
 * 定义规则的操作
 * @author lijunwei
 *
 */
public interface Operations {

	/**
	 * 规则的执行处理, 当使用这种方式时 规则rule{@link CompositeRule#getRules()}是全局性的
	 * @param context 当前规则模板执行的上下文, 每一次执行都要创建一个新的context, 用来在不同的规则{@link Rule}之间进行数据传递
	 * @throws RuleException
	 */
	void execute(Context context);

	/**
	 * 规则的执行处理
	 * @param context 当前规则模板执行的上下文, 每一次执行都要创建一个新的context, 用来在不同的规则{@link Rule}之间进行数据传递
	 * @param rule 当前执行的规则, 每个数据的处理方式 每次使用时可以直接传递
	 * @throws RuleException 
	 */
	void execute(Context context, Rule rule);
	
	/**
	 * 规则的执行处理
	 * @param context 当前规则模板执行的上下文, 每一次执行都要创建一个新的context, 用来在不同的规则{@link Rule}之间进行数据传递
	 * @param rule 当前执行的规则, 每个数据的处理方式 每次使用时可以直接传递
	 * @param args 预留参数
	 * @throws RuleException
	 */
	void execute(Context context, Rule rule, Object... args);
	
}
