package com.self.service;

import java.util.ArrayList;

import com.self.exception.DuplicateIsbnException;
import com.self.exception.MagazineNotFoundException;
import com.self.exception.RecordNotFoundException;
import com.self.vo.Book;

public interface BookManager {
	void insertBook(Book book) throws DuplicateIsbnException;
	void deleteBook(int isbn) throws RecordNotFoundException;
	void updateBook(Book book) throws RecordNotFoundException;
	Book getBook(int isbn);
	ArrayList<Book> getAllBook();
	int getNumberOfBooks();
	ArrayList<Book> searchBookByTitle(String title); // 여기서 반환 타입이 HashMap일 필요는 없다
	ArrayList<Book> searchBookByPrice(double min, double max);
	double getSumPriceOfBooks();
	double getAvgPriceOfBooks();
	Book getRecentBook();
	String getPopularGenre();
	ArrayList<Book> magazineOfThisYearInfo(int year) throws MagazineNotFoundException;
}