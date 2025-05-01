package com.self.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.self.exception.DuplicateIsbnException;
import com.self.exception.MagazineNotFoundException;
import com.self.exception.RecordNotFoundException;
import com.self.service.BookManager;
import com.self.vo.Book;
import com.self.vo.Magazine;
import com.self.vo.Novel;

public class BookManagerImpl implements BookManager{

	// private Book[] books;
	private HashMap<Integer, Book> bookMap;

	// 싱글톤
	private static BookManagerImpl service = new BookManagerImpl();
	private BookManagerImpl() {
		bookMap = new HashMap<Integer, Book>();
	}
	public static BookManagerImpl getInstance() {
		return service;
	}
	
	@Override
	public void insertBook(Book book) throws DuplicateIsbnException{
		if(!bookMap.containsKey(book.getIsbn())) {
			bookMap.put(book.getIsbn(), book);
			System.out.println("<"+book.getTitle()+">가 등록되었습니다.");
		}else throw new DuplicateIsbnException("이미 등록된 책입니다.");
	}

	@Override 
	public void deleteBook(int isbn) throws RecordNotFoundException {
	 if(bookMap.containsKey(isbn)) {
		 bookMap.remove(isbn);
		 System.out.println("삭제 되었습니다.");
	 }else throw new RecordNotFoundException("삭제 대상이 없습니다.");
	}
	
	@Override 
	public void updateBook(Book book) throws RecordNotFoundException {
		if(bookMap.containsKey(book.getIsbn())) {
			bookMap.put(book.getIsbn(), book);
			System.out.println("책 정보가 수정되었습니다.");
		}else throw new RecordNotFoundException("업데이트 대상을 찾지 못했습니다.");
	}
	
	@Override
	public Book getBook(int isbn) {
		if(!bookMap.containsKey(isbn)) {
			System.out.println("해당 책을 찾을 수 없습니다.");
		} 
		return bookMap.get(isbn);
	}

	@Override
	public ArrayList<Book> getAllBook() {
		ArrayList<Book> temp = new ArrayList<>();
		for(Book b: bookMap.values())
			temp.add(b);
		return temp;
	}

	@Override
	public int getNumberOfBooks() {
		return bookMap.size();
	}

	@Override
	public ArrayList<Book> searchBookByTitle(String title) {
		ArrayList<Book> result = new ArrayList<>();
		for (Book b : bookMap.values()) {
			if (b.getTitle().equals(title)) {
				result.add(b);
			}
		}
		return result;
	}

	@Override
	public ArrayList<Book> searchBookByPrice(double min, double max) {
		ArrayList<Book> result = new ArrayList<>();
		for(Book b: bookMap.values()) {
			if(b.getPrice() >= min && b.getPrice()  <= max)
				result.add(b);
		}
		return result;
	}

	@Override
	public double getSumPriceOfBooks() {
		double sum = 0.0;
		for(Book b: bookMap.values())
			sum += b.getPrice();
		return sum;
	}

	@Override
	public double getAvgPriceOfBooks() {
		return getSumPriceOfBooks()/bookMap.size();
	}

	@Override
	public Book getRecentBook() {
		Book temp = null;
		int year = 0;
		int month = 0;
		for(Book b: bookMap.values()){
			if(b instanceof Magazine) {
				if(((Magazine) b).getPublishDate().getYear() > year) {
					temp = b;
					year = ((Magazine) b).getPublishDate().getYear();
					month = ((Magazine) b).getPublishDate().getMonth();
				} else if(((Magazine) b).getPublishDate().getYear() == year) {
					if(((Magazine) b).getPublishDate().getMonth() > month) {
						temp = b;
						year = ((Magazine) b).getPublishDate().getYear();
						month = ((Magazine) b).getPublishDate().getMonth();
					}
				}
			}
		}
		return temp;
	}

	@Override
	public String getPopularGenre() {
		// 소설의 경우 장르가 한정되어 있다고 가정한다.
		// 여기서는 임시로 "한국소설"과 "해외소설", "기타소설"로 구분한다
		HashMap<String, Integer> genreMap = new HashMap<String, Integer>();
		genreMap.put("한국소설", 0);
		genreMap.put("해외소설", 0);
		genreMap.put("기타소설", 0);
		for(Book b: bookMap.values()) {
			if(b instanceof Novel) {
				if(((Novel) b).getGenre().equals("한국소설")) {
					genreMap.replace("한국소설", genreMap.get("한국소설")+1);
				} else if (((Novel) b).getGenre().equals("해외소설")) {
					genreMap.replace("해외소설", genreMap.get("해외소설")+1);
				} else if (((Novel) b).getGenre().equals("기타소설")) {
					genreMap.replace("기타소설", genreMap.get("기타소설")+1);
				}
			}
		}
		int max = 0;
		String genre = "";
		for(String g : genreMap.keySet()) {
			if (genreMap.get(g) > max) {
				max = genreMap.get(g);
				genre = g;
			}
		}
		return genre;
	}
	
	@Override
	public ArrayList<Book> magazineOfThisYearInfo(int year) throws MagazineNotFoundException{
		ArrayList<Book> temp = new ArrayList<>();
		for(Book b: bookMap.values()) {
			if(b instanceof Magazine && ((Magazine) b).getPublishDate().getYear() == year)
				temp.add(b);
		}
		if(temp.size() == 0)
			throw new MagazineNotFoundException("잡지 목록이 없습니다.");
		return temp;
	}

	//
}