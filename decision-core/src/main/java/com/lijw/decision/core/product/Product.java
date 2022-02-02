package com.lijw.decision.core.product;

import com.lijw.decision.core.Context;
import com.lijw.decision.core.Decision;

/**
 * 所有待决策产品基类
 */
public interface Product extends Decision {

    /** 产品预处理 */
    boolean process(Context context);

}
