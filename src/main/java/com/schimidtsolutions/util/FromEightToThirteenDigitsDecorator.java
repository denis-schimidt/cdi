package com.schimidtsolutions.util;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import com.schimidtsolutions.util.annotations.Issn;

@Decorator
public class FromEightToThirteenDigitsDecorator implements NumberGenerator {

	@Inject @Issn @Delegate
	private NumberGenerator numberGenerator;

	@Override
	public String next() {
		String issn = numberGenerator.next();
		String isbn = "13-84356" + issn.substring(1);

		return isbn;
	}
}
