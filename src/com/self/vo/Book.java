package com.self.vo;

public class Book { // implements Comparable<Book>
	private int isbn;
	private String title;
	private String author;
	private String publisher;
	private double price;
	
//	private static final String DEFAULT_AUTHOR = "알수없음";
//	private static final String DAFAULT_PUBLISHER = "알수없음";
//	private static final double DEFAULT_PRICE = 1000.0;
	
	public Book() {
		super();
	}
	public Book(int isbn, String title, String author, String publisher, double price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}
	
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "isbn=" + isbn + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", price=" + price;
	}
	
//	public String compareTo(Book b) {
//		return -String.compare(title, b.getTitle());
//	}
}