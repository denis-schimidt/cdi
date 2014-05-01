package com.schimidtsolutions.util;

import com.schimidtsolutions.util.annotations.Issn;

@Issn
public class IssnGenerator implements NumberGenerator {

	@Override
	public final String next() {
		return "11111";
	}
}
