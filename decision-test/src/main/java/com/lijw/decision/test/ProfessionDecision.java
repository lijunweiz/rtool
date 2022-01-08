package com.lijw.decision.test;

import com.lijw.decision.core.AbstractDecision;
import com.lijw.decision.core.Context;
import com.lijw.decision.core.DecideResult;
import lombok.extern.java.Log;

@Log
public class ProfessionDecision extends AbstractDecision {

	@Override
	public Boolean canDecide(Context context) {
		return super.canDecide(context);
	}

	@Override
	public void decide(Context context) {
		log.info("获取决策数据项: profession");

		Profession item = context.getDecisionItem(Profession.class);

	}

}
