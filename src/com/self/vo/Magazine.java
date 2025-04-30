package com.self.vo;

import com.self.util.Date;

public class Magazine extends Book{
	private Date publishDate;
	private static final int DEFAULT_YEAR = 0;
	private static final int DEFAULT_MONTH = 0;
	
	public Magazine() {
		super();
	}
	public Magazine(int isbn, String title, String author, String publisher, double price) {
		this(isbn, title, author, publisher, price, new Date(DEFAULT_YEAR, DEFAULT_MONTH));
	}
	public Magazine(int isbn, String title, String author, String publisher, double price, Date publishDate) {
		super(isbn, title, author, publisher, price);
		this.publishDate = publishDate;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", publishDate=" + publishDate;
	}
}