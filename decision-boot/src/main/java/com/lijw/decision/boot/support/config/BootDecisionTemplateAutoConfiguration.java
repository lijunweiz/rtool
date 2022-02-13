package com.lijw.decision.boot.support.config;

import com.lijw.decision.boot.support.BootDecisionTemplate;
import com.lijw.decision.core.support.DecisionTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动生成BootDecisionTemplate对象
 */
@Configuration
@ConditionalOnClass(DecisionTemplate.class)
public class BootDecisionTemplateAutoConfiguration {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    @ConditionalOnMissingBean
    public BootDecisionTemplate bootDecisionTemplate(ApplicationContext applicationContext) {
        BootDecisionTemplate bootDecisionTemplate = new BootDecisionTemplate(applicationContext);
        bootDecisionTemplate.init();
        return bootDecisionTemplate;
    }

}
