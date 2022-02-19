package com.lijw.decision.boot.support;

import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.DecisionType;
import com.lijw.decision.core.product.Product;
import com.lijw.decision.core.support.DecisionTemplate;
import com.lijw.decision.core.util.CollectionUtil;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BootDecisionTemplate extends DecisionTemplate {

    private ApplicationContext applicationContext;

    public BootDecisionTemplate(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Override
    public void initDecisionFunction() {
        Map<String, DecisionFunction> decisionFunctionMap = applicationContext.getBeansOfType(DecisionFunction.class);
        if (CollectionUtil.notNullAndEmpty(decisionFunctionMap)) {
            List<DecisionFunction> decisionFunctions = super.getDecisionFunctions(decisionFunctionMap.values().stream().collect(Collectors.toList()));
            super.setDecisionFunctions(decisionFunctions);
        }
    }

    @Override
    public void initDecisionType() {
        Map<String, DecisionType> decisionTypeMap = applicationContext.getBeansOfType(DecisionType.class);
        if (CollectionUtil.notNullAndEmpty(decisionTypeMap)) {
            super.setDecisionTypes(decisionTypeMap.values().stream().collect(Collectors.toList()));
        }
    }

    @Override
    public void initProduct() {
        Map<String, Product> productMap = applicationContext.getBeansOfType(Product.class);
        if (CollectionUtil.notNullAndEmpty(productMap)) {
            super.setProducts(productMap.values().stream().collect(Collectors.toList()));
        }
    }

}
