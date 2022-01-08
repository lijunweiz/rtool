package com.lijw.decision.core;

public interface Decision {

    /**
     * 每一个实现类，应该在此处关系需要进行判定的数据，并给出决策结果
     * @param context
     * @return
     */
    void decide(Context context);

}
