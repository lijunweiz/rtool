package cn.unminded.rtool.core;

import cn.unminded.rtool.util.CollectionUtil;

import java.util.*;

/**
 * 复合规则表示所有规则的归处, 从此处可以获取所有已配置的规则
 * @author lijunwei
 *
 */
public class CompositeRule extends AbstractRule implements Rule {

	/** 规则是否已排序 */
	private boolean sorted = DefaultValue.FALSE;
	
	/** 规则集 */
	private List<Rule> rules = new ArrayList<>();

	public CompositeRule() {
	}

	public CompositeRule(List<Rule> rules) {
		if (CollectionUtil.notNullAndEmpty(rules)) {
			this.rules.addAll(rules);
		}
	}

	public CompositeRule(Rule ... rules) {
		if (Objects.nonNull(rules)) {
			for (Rule rule : rules) {
				this.rules.add(rule);
			}
		}
	}

	/** 获取排序完成的规则集 */
	public List<Rule> getRules() {
		return rules;
	}

	public boolean getSorted() {
		return sorted;
	}

	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}

	/**
	 * 如果运行时添加了新的规则，则需要重新排序，那么此时需要重新对所有rule进行排序,
	 * 需要先调用{@link #setSorted}将sorted置为false
	 */
	public synchronized void sortedRules() {
		if (sorted) {
			return;
		}

		if (CollectionUtil.isNullOrEmpty(rules)) {
			return;
		}

		Collections.sort(rules, Comparator.comparing(Rule::order));
	}

}
