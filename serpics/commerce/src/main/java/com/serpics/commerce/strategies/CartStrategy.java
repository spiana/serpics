package com.serpics.commerce.strategies;

import com.serpics.membership.data.model.Member;

public interface CartStrategy {
	
	public void mergeCart (Member user, Member Customer);

}
