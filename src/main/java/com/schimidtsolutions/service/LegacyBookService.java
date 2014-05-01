package com.schimidtsolutions.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import com.schimidtsolutions.model.Book;
import com.schimidtsolutions.service.annotations.LegacyService;
import com.schimidtsolutions.util.NumberGenerator;
import com.schimidtsolutions.util.annotations.Issn;

@LegacyService
public class LegacyBookService implements BookService {
	
	@Inject @Issn
	private NumberGenerator numberGenerator;

	@Override
	public Book createBook( String title, Double price, String description ){
		return new Book(numberGenerator.next(), title, price, description);
	}
	
	@PostConstruct
	private void init(){
		System.out.println( "Iniciando LegacyBookService..."  );
	}
	
	@PreDestroy
	private void destroy(){
		System.out.println( "Destruindo LegacyBookService..."  );
	}
}
