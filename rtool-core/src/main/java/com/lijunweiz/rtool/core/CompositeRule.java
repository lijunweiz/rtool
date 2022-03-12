package com.lijunweiz.rtool.core;

import com.lijunweiz.rtool.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 复合规则表示所有规则的归处, 从此处可以获取所有已配置的规则
 * @author lijunwei
 *
 */
public class CompositeRule extends AbstractRule implements Rule {

	/** 规则是否已排序 */
	private boolean sorted = false;
	
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
		for (Rule rule : rules) {
			this.rules.add(rule);
		}
	}

	/** 获取排序完成的规则集 */
	public List<Rule> getRules() {
		if(!getSorted()) {
			List<Rule> collect = rules.stream().sorted(Comparator.comparing(Rule::order)).collect(Collectors.toList());
			rules.clear();
			rules.addAll(collect);
			setSorted(true);
		}
		
		return rules;
	}

	public boolean getSorted() {
		return sorted;
	}

	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}
}
