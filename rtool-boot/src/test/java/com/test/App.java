package com.test;

import com.lijunweiz.rtool.core.CompositeRule;
import com.lijunweiz.rtool.core.Context;
import com.lijunweiz.rtool.core.Rule;
import com.lijunweiz.rtool.core.RuleTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@SpringBootApplication
public class App {

    private Logger logger = LoggerFactory.getLogger(App.class);

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private Rule compositeRule;

    @Resource
    private RuleTemplate ruleTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RequestMapping("/g")
    public void test01() {
        logger.info("对比ruleTemplate.getRule() == compositeRule = {}", ruleTemplate.getRule() == compositeRule);
        List<String> collect = CompositeRule.class.cast(ruleTemplate.getRule()).getRules().stream().map(x -> x.getName()).collect(Collectors.toList());
        logger.info("规则: " + collect.toString());
        ruleTemplate.execute(new Context());
    }


}
