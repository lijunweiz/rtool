package cn.unminded.rtool.core;

/**
 * 拒绝策略，当执行一个rule时{@link RuleTemplate#execute(Context, Rule)}
 */
public interface RejectedPolicy {

    /**
     * reject 处理
     * @param context rule执行的上下文{@link Context}
     * @param rule {@link Rule}
     * @param e {@link RuleException}
     */
    void reject(Context context, Rule rule, RuleException e);

}
