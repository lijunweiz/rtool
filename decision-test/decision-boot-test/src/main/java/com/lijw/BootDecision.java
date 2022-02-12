package com.lijw;

import com.lijw.decision.core.exception.DecisionException;
import com.lijw.decision.core.result.Result;
import com.lijw.decision.core.support.DecisionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@SpringBootApplication
public class BootDecision {

    public static void main(String[] args) throws DecisionException {
        ApplicationContext context = SpringApplication.run(BootDecision.class, args);
        DecisionTemplate decisionTemplate = context.getBean(DecisionTemplate.class);
        System.out.println(decisionTemplate.getClass());
    }

    @GetMapping("/g")
    public ResponseEntity<Result> getResult() {
        return ResponseEntity.of(Result.success().optional());
    }

}
