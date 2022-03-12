package com.lijunweiz.rtool.core;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 规则模板, 规则{@link Rule}由条件{@link Condition}和动作{@link Action}组成, 根据条件判定时否满足情况下执行不用的动作,
 * 条件匹配成功执行{@link Action#dispose(Context)}, 条件匹配失败执行{@link Action#opposite(Context)}
 * @author lijunwei
 *
 */
public class RuleTemplate extends RuleSupport implements Operations {
	
	private Logger logger = LoggerFactory.getLogger(RuleTemplate.class);

    /**
     * 拒绝策略
     */
	private volatile RejectedPolicy rejectedPolicy;

    /**
     * 默认的拒绝策略
     */
	private final static RejectedPolicy defaultRejectedPolicy = new ReportPolicy();

    /**
     * 正在执行的rule
     */
	private volatile Rule currentRule;

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
		if (Objects.isNull(rule)) {
			return;
		}

		if (rule instanceof CompositeRule) {
			CompositeRule rules = CompositeRule.class.cast(rule);
			logger.info("发现复合规则: {}", rules.getName());
			for (Rule item : rules.getRules()) {
				logger.info("处理的当前规则rule: {}", item.getName());
				this.setCurrentRule(item);
				this.execute(context, item.getCondition(), item.getAction());
			}
		} else {
			logger.info("处理的当前规则rule: {}", rule.getName());
			this.setCurrentRule(rule);
			this.execute(context, rule.getCondition(), rule.getAction());
		}
	}

	@Override
	public void execute(Context context, Condition condition, Action action) {
		this.execute(context, condition, action, new Object() {});
	}

	protected void execute(Context context, Condition condition, Action action, Object ... args) {
		if (Objects.isNull(context)) {
			context = new Context();
		}

		Objects.requireNonNull(condition);
		Objects.requireNonNull(action);

		logger.info("contextId: {}, 处理前condition: {}, action: {}", context.getContextId(),
				condition.getName(), action.getName());
		try {
			if (condition.match(context)) {
				action.dispose(context);
			} else {
				action.opposite(context);
			}
		} catch (RuleException e) {
			this.reject(context, e);
		} catch (Exception e) {
			throw e;
		} finally {
			this.clear();
		}
		logger.info("contextId: {}, 处理后condition: {}, action: {}", context.getContextId(),
				condition.getName(), action.getName());
	}

	/**
	 * 重置当前处理的rule信息
	 */
	protected void clear() {
		this.setCurrentRule(null);
	}

	public void setRejectedPolicy(RejectedPolicy rejectedPolicy) {
		Objects.requireNonNull(rejectedPolicy);
		this.rejectedPolicy = rejectedPolicy;
	}

	public RejectedPolicy getRejectedPolicy() {
		return rejectedPolicy;
	}

	/**
	 * 设置当前正常处理的rule
	 * @param rule
	 */
	public void setCurrentRule(Rule rule) {
		this.currentRule = rule;
	}

	/**
	 * 异常拒绝处理
	 * @param context 当前使用的context
	 * @param e 异常信息
	 */
	final void reject(Context context, RuleException e) {
		this.rejectedPolicy.reject(context, this, e);
	}

	/**
	 * 静默处理某个规则的异常
	 */
	public static class SilentPolicy implements RejectedPolicy {
		public SilentPolicy() {
		}

		/**
		 * 什么都不干, 忽略当前错误
		 * @param context
		 * @param template
		 * @param e 程序的异常信息
		 */
		@Override
		public void reject(Context context, RuleTemplate template, RuleException e) {

		}
	}

	/**
	 * 抛出某个规则的异常信息
	 */
	public static class ReportPolicy implements RejectedPolicy {
		public ReportPolicy() {
		}

		/**
		 * 总是抛出{@link RuleException}
		 * @param context
		 * @param template
		 * @param e
		 */
		@Override
		public void reject(Context context, RuleTemplate template, RuleException e) {
			throw new RuleException("contextId: " + context.getContextId() +
					", 规则rule: " + (Objects.isNull(template.currentRule) ?
									new AbstractRule(){}.getName() : template.currentRule.getName()
									)
					+ " 执行失败", e);
		}
	}
}
