package com.schimidtsolutions.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import com.schimidtsolutions.model.Book;
import com.schimidtsolutions.service.annotations.CurrentService;
import com.schimidtsolutions.util.NumberGenerator;
import com.schimidtsolutions.util.annotations.Isbn;

@CurrentService
public class CurrentBookService implements BookService {

	@Inject @Isbn
	private NumberGenerator numberGenerator;

	public Book createBook( String title, Double price, String description ){
		return new Book(numberGenerator.next(), title, price, description);
	}
	
	@PostConstruct
	private void init(){
		System.out.println( "Iniciando CurrentBooksService..."  );
	}
	
	@PreDestroy
	private void destroy(){
		System.out.println( "Destruindo CurrentBooksService..."  );
	}
}
