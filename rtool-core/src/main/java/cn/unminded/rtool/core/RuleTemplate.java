package cn.unminded.rtool.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 规则模板, 规则{@link Rule}由条件{@link Condition}和动作{@link Action}组成, 根据条件判定时否满足情况下执行不用的动作,
 * 条件匹配成功执行{@link Action#start(Context)}
 * @author lijunwei
 *
 */
public class RuleTemplate extends RuleSupport implements Operations {
	
	protected final Logger logger = LoggerFactory.getLogger(RuleTemplate.class);

    /**
     * 拒绝策略
     */
	private RejectedPolicy rejectedPolicy;

    /**
     * 默认的拒绝策略
     */
	private static final RejectedPolicy defaultRejectedPolicy = new ReportPolicy();

	public RuleTemplate() {
		this.rejectedPolicy = defaultRejectedPolicy;
	}

	public RuleTemplate(RejectedPolicy rejectedPolicy) {
		this.rejectedPolicy = rejectedPolicy;
	}

	@Override
	public void execute(Context context) {
		this.execute(context, getRule());
	}

	@Override
	public void execute(Context context, Rule rule) {
		Objects.requireNonNull(context);
		Objects.requireNonNull(rule);

		if (!context.getPass()) {
			return;
		}

		if (rule instanceof CompositeRule) {
			CompositeRule rules = (CompositeRule) rule;
			logger.info("发现复合规则: {}", rules.getName());
			for (Rule item : rules.getRules()) {
				logger.info("处理的当前规则rule: {}", item.getName());
				this.execute(context, item, new Object(){});
			}
		} else {
			logger.info("处理的当前规则rule: {}", rule.getName());
			this.execute(context, rule, new Object(){});
		}
	}

	@Override
	public void execute(Context context, Rule rule, Object ... args) {
		this.doExecute(context, rule, args);
	}

	private void doExecute(Context context, Rule rule, Object ... args) {
		Objects.requireNonNull(context);
		Objects.requireNonNull(rule);

		Condition condition = rule.getCondition();
		Action action = rule.getAction();

		Objects.requireNonNull(condition);
		Objects.requireNonNull(action);

		logger.info("contextId: {}, 开始处理condition: {}, action: {}", context.getContextId(),
				condition.getName(), action.getName());
		try {
			if (context.getPass() && condition.match(context)) {
				boolean start = action.start(context);
				context.setPass(start);
				context.setMessage(DefaultValue.MSG_SUCCESS);
			}
		} catch (RuleException e) {
			context.setPass(DefaultValue.FALSE);
			context.setMessage("规则处理异常, " + e.getMessage());
			reject(context, rule, e);
		} catch (Exception e) {
			context.setPass(DefaultValue.FALSE);
			context.setMessage("未知异常, " + e.getMessage());
			reject(context, rule, new RuleException(e));
		}
		logger.info("contextId: {}, 完成处理condition: {}, action: {}", context.getContextId(),
				condition.getName(), action.getName());
	}

	public void setRejectedPolicy(RejectedPolicy rejectedPolicy) {
		Objects.requireNonNull(rejectedPolicy);
		this.rejectedPolicy = rejectedPolicy;
	}

	public RejectedPolicy getRejectedPolicy() {
		return rejectedPolicy;
	}

	/**
	 * 异常拒绝处理
	 * @param context 当前使用的context
	 * @param e 异常信息
	 */
	final void reject(Context context, Rule rule, RuleException e) {
		this.rejectedPolicy.reject(context, rule, e);
	}

	/**
	 * 标记某个规则的异常信息
	 */
	public static class MarkPolicy implements RejectedPolicy {

		final Logger logger = LoggerFactory.getLogger(MarkPolicy.class);

		/**
		 * 标记异常，只是输出
		 */
		public MarkPolicy() {
		}

		/**
		 * 只输出某个异常信息
		 * @param context
		 * @param rule 正在处理的rule
		 * @param e 程序的异常信息
		 */
		@Override
		public void reject(Context context, Rule rule, RuleException e) {
			logger.error("contextId: {}, 规则rule: {}", context.getContextId(), rule.getName(), e);
		}
	}

	/**
	 * 抛出某个规则的异常信息
	 */
	public static class ReportPolicy implements RejectedPolicy {

		/**
		 * 报告异常，通知调用方
		 */
		public ReportPolicy() {
		}

		/**
		 * 总是抛出{@link RuleException}
		 * @param context
		 * @param rule 正在处理的rule
		 * @param e
		 */
		@Override
		public void reject(Context context, Rule rule, RuleException e) {
			throw new RuleException("contextId: " + context.getContextId() +
					", 规则rule: " + rule.getName() + " 执行失败", e);
		}
	}
}
