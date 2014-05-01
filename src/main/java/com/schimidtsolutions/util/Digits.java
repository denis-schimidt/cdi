package com.schimidtsolutions.util;


public enum Digits {
	TWO(2), EIGHT(8), TEN(10), THIRTEEN(13);
	
	private int length;
	
	private Digits( int length ){
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}
}
