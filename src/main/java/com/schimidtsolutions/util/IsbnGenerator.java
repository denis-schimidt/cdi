package com.schimidtsolutions.util;

import javax.inject.Inject;

import com.schimidtsolutions.util.annotations.Isbn;

@Isbn
public class IsbnGenerator implements NumberGenerator {
	@Inject
	private String prefix;
	
	@Inject
	private int editorNumber;
	
	@Inject
	private double postfix;
	
	@Override
	public final String next() {
		return prefix + editorNumber + postfix;
	}
}