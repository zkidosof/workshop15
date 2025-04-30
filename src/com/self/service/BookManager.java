package com.self.service;

import java.util.ArrayList;

import com.self.vo.Book;

public interface BookManager {
	
	void insertBook(Book book);
	void deleteBook(int isbn);
	void updateBook(Book book);
	Book getBook(int isbn);
	ArrayList<Book> getAllBook();
	int getNumberOfBooks();
	ArrayList<Book> searchBookByTitle(String title);
	ArrayList<Book> searchBookByPrice(double min, double max);
	double getSumPriceOfBooks();
	double getAvgPriceOfBooks();
	Book getRecentBook();
	String getPopularGenre();
	ArrayList<Book> magazineOfThisYearInfo();
}