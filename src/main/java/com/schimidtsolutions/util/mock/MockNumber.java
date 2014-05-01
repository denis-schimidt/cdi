package com.schimidtsolutions.util.mock;

import javax.enterprise.inject.Alternative;

import com.schimidtsolutions.util.NumberGenerator;


@Alternative
public class MockNumber implements NumberGenerator {

	@Override
	public String next() {
		return "666";
	}
}
