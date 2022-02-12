package com.lijw.decision.boot.support.config;

import com.lijw.decision.boot.support.BootDecisionTemplate;
import com.lijw.decision.core.support.DecisionTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动生成BootDecisionTemplate对象
 */
@Configuration
@ConditionalOnClass(DecisionTemplate.class)
@ConditionalOnBean(ApplicationContextHolder.class)
public class BootDecisionTemplateAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BootDecisionTemplate bootDecisionTemplate() {
        BootDecisionTemplate bootDecisionTemplate = new BootDecisionTemplate();
        return bootDecisionTemplate;
    }

}
