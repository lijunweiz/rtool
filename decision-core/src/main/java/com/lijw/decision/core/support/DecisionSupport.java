package com.lijw.decision.core.support;

import com.lijw.decision.core.DecisionFunction;
import com.lijw.decision.core.util.StringUtils;

import java.util.*;

public class DecisionSupport {

    private List<DecisionFunction> decisionFunctions = new ArrayList<>();
    private Map<String, DecisionFunction> decisionFunctionMap = new HashMap<>();

    /**
     * 加载接口{@link DecisionFunction}的所有实现类，必须写在目录META-INF/services
     *
     * 应该判断是否是spring环境
     * @return
     */
    protected List<DecisionFunction> initDecisionResource() {
        ServiceLoader<DecisionFunction> serviceLoader = ServiceLoader.load(DecisionFunction.class);
        Iterator<DecisionFunction> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            DecisionFunction function = iterator.next();
            decisionFunctions.add(function);
            decisionFunctionMap.put(StringUtils.getCamelName(function.getClass()), function);
        }

        return decisionFunctions;
    }

    public List<DecisionFunction> getDecisionFunctions() {
        return decisionFunctions;
    }

    public void setDecisionFunctions(List<DecisionFunction> decisionFunctions) {
        this.decisionFunctions = decisionFunctions;
    }

    public Map<String, DecisionFunction> getDecisionFunctionMap() {
        return decisionFunctionMap;
    }

    public void setDecisionFunctionMap(Map<String, DecisionFunction> decisionFunctionMap) {
        this.decisionFunctionMap = decisionFunctionMap;
    }

}
