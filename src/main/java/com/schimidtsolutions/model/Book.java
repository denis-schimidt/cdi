package com.schimidtsolutions.model;

public class Book {
	private String id;
	private String title;
	private Double price;
	private String description;
	
	public Book(String id, String title, Double price, String description) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Book [id=%s, title=%s, price=%s, description=%s]", id, title,
				price, description);
	}

	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public Double getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
}
