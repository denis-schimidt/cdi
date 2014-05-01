package com.schimidtsolutions.service;

import com.schimidtsolutions.model.Book;

public interface BookService {

	public abstract Book createBook(String title, Double price, String description);
}