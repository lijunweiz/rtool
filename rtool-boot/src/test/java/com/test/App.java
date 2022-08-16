package com.test;

import com.lijunweiz.rtool.core.CompositeRule;
import com.lijunweiz.rtool.core.Context;
import com.lijunweiz.rtool.core.RuleTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        RuleTemplate ruleTemplate = context.getBean(RuleTemplate.class);
        CompositeRule compositeRule = context.getBean(CompositeRule.class);

        logger.info("对比ruleTemplate.getRule() == compositeRule = {}", ruleTemplate.getRule() == compositeRule);
        List<String> collect = CompositeRule.class.cast(ruleTemplate.getRule()).getRules().stream().map(x -> x.getName()).collect(Collectors.toList());
        logger.info("规则: " + collect);
        ruleTemplate.execute(new Context());
    }

}
