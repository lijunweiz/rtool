package com.lijunweiz.rtool.boot.config;

import com.lijunweiz.rtool.core.CompositeRule;
import com.lijunweiz.rtool.core.DefaultValue;
import com.lijunweiz.rtool.core.Rule;
import com.lijunweiz.rtool.core.RuleTemplate;
import com.lijunweiz.rtool.util.CollectionUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@ConditionalOnProperty(name = "rtool.rule.enable", matchIfMissing = true)
@Configuration
@ConditionalOnClass(RuleTemplate.class)
public class RtoolRuleAutoConfiguration {

    @ConditionalOnMissingBean(CompositeRule.class)
    @Bean
    public CompositeRule compositeRule(ApplicationContext applicationContext) {
        CompositeRule compositeRule = new CompositeRule();
        Map<String, Rule> beansOfType = applicationContext.getBeansOfType(Rule.class);
        if (CollectionUtil.notNullAndEmpty(beansOfType)) {
            compositeRule.getRules().addAll(beansOfType.values());
            if (compositeRule.getSorted()) {
                compositeRule.setSorted(DefaultValue.FALSE);
            }
            compositeRule.sortedRules();
            compositeRule.setSorted(DefaultValue.TRUE);
        }

        return compositeRule;
    }

    @ConditionalOnBean(CompositeRule.class)
    @ConditionalOnMissingBean(RuleTemplate.class)
    @Bean
    public RuleTemplate ruleTemplate(CompositeRule compositeRule) {
        RuleTemplate ruleTemplate = new RuleTemplate();
        ruleTemplate.setRule(compositeRule);
        
        return ruleTemplate;
    }

}
