package com.schimidtsolutions.util;

import com.schimidtsolutions.util.annotations.Issn;

@Issn
public class IssnGenerator implements NumberGenerator {

	@Override
	public String next() {
		return Long.toString( (long) Math.random() * 99999 );
	}
}
