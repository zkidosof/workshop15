package com.self.service;

import java.util.HashMap;

import com.self.vo.Book;


public interface BookManager {
	void insertBook(Book book);
	void deleteBook(int isbn);
	void updateBook(Book book);
	Book getBook(int isbn);
	HashMap<Integer, Book> getAllBook();
	int getNumberOfBooks();
	HashMap<Integer, Book> searchBookByTitle(String title);
	HashMap<Integer, Book> searchBookByPrice(double min, double max);
	double getSumPriceOfBooks();
	double getAvgPriceOfBooks();
	Book getRecentBook();
	String getPopularGenre();
	HashMap<Integer, Book> magazineOfThisYearInfo();
	HashMap<Integer, Book> magazineOfThisYearInfo(int year);
}