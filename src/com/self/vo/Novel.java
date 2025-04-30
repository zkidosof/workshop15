package com.self.vo;

public class Novel extends Book{
	private String genre;
	private static final String DEFAULT_GENRE = "기타소설";
	
	public Novel() {
		super();
	}
	public Novel(int isbn, String title, String author, String publisher, double price) {
		this(isbn, title, author, publisher, price, DEFAULT_GENRE);
	}
	public Novel(int isbn, String title, String author, String publisher, double price, String genre) {
		super(isbn, title, author, publisher, price);
		this.genre = genre;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return super.toString()+", genre=" + genre;
	}
}