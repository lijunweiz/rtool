package com.lijw;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.exception.DecisionException;
import com.lijw.decision.core.result.Result;
import com.lijw.decision.core.support.DecisionTemplate;
import com.lijw.decision.type.DecisionTypeEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class BootDecision {

    @Resource
    private DecisionTemplate decisionTemplate;

    public static void main(String[] args) {
        SpringApplication.run(BootDecision.class, args);
    }

    @GetMapping("/g")
    public Result<?> getResult() throws DecisionException {

        Context context = Context.getInstance()
                .setDecisionType(DecisionTypeEnum.CREDIT.getValueEN())
                .setProductName("aliJieBei");

        decisionTemplate.execute(context);
        return Result.success();
    }

}
