package com.self.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.self.service.BookManager;
import com.self.vo.Book;
import com.self.vo.Magazine;
import com.self.vo.Novel;

public class BookManagerImpl implements BookManager{

	// private Book[] books;
	private ArrayList<Book> bookList;

	public static final int MAX_SIZE = 100;
	private int idx;
	private int numberOfBook; // 총 입고된 책의 수량 등으로 활용할 수 있음
	
	// 싱글톤
	private static BookManagerImpl service = new BookManagerImpl();
	private BookManagerImpl() {

		// books = new Book[MAX_SIZE];
		bookList = new ArrayList<Book>();
	}
	public static BookManagerImpl getInstance() {
		return service;
	}
	
	@Override
	public void insertBook(Book book) {
		boolean find = false;//못찾았다라는 뜻
		for(Book b: bookList) {
			if(b.getIsbn() == book.getIsbn()) {
				find = true; //찾았다
				System.out.println("이미 등록되어진 책 입니다.");
				return;
			}
		}
		if(find == false) {
			bookList.add(book);
			System.out.println("<"+book.getTitle()+">가 등록되었습니다.");
			idx++;
			numberOfBook++;
		}
	}
		
		
		
	

	@Override 
	public void deleteBook(int isbn) {
		int find = -1;
		if(bookList.isEmpty()) return;
		for(int i=0; i<idx; i++) {
			if(bookList.get(i).getIsbn() == isbn) {
				
				find = i;
				break;
			}
		}
		if(find !=  -1) {
			bookList.remove(find);
		}
	}
	
	@Override
	public void updateBook(Book book) {
		 int idx = 0;
		for ( Book b : bookList) {
			if(bookList.isEmpty()) break;
			if(b.getIsbn() == book.getIsbn()) {
												//idx ++
				b.setTitle(book.getTitle()); //books.set(idx, book); -> instanc of를 쓰면 안됨 두줄로 끝남
				b.setAuthor(book.getAuthor());
				b.setPrice(book.getPrice());
				b.setPublisher(book.getPublisher());
				
				if(b instanceof Magazine) {
//					Magazine magazine = (Magazine)b;
//					Magazine bk = (Magazine) book;
					((Magazine)b).setPublishDate(((Magazine) book).getPublishDate());
				}
				else if(b instanceof Novel ) {
//					Novel novel = (Novel)b;
//					Novel bk = (Novel) book;
					((Novel)b).setGenre(((Novel) book).getGenre());
				}
			}
		}
	}
	
	@Override
	public Book getBook(int isbn) {
		Book find = null;
		for(Book b : bookList) {
			
			if(b.getIsbn()==isbn) {
				find = b;
				break;
			}
		}
		if(find == null)System.out.println("해당 책을 찾을 수 없습니다.");
		return find;
	}

	@Override
	public ArrayList<Book> getAllBook() {
		return bookList;
	}

	@Override
	public int getNumberOfBooks() {
		return idx;
		// 삭제된 것과 상관 없이 입고된 책의 수 반환할 경우
		// return numberOfBook;
	}

	@Override
	public ArrayList<Book> searchBookByTitle(String title) {//박성우 3
		ArrayList<Book> result = new ArrayList<>();
		if(bookList.isEmpty())  return result; // 전에는 size를 정하고 들어가니까 안에 있음
		
		for(Book b: bookList) {
		
			if(b.getTitle().equals(title) ){
				result.add(b);
			}
		}
		return result;
	}

	@Override
	public ArrayList<Book> searchBookByPrice(double min, double max) {//박성우 4
		ArrayList<Book> result = new ArrayList<>();
		if(bookList.isEmpty()) return result;
		for(Book b: bookList) {
			
			if(b.getPrice() >= min && b.getPrice()  <= max)
				result.add(b);
		}
		return result;
	}

	@Override
	public double getSumPriceOfBooks() {
		double sum = 0.0;
		for(Book b: bookList) {
			sum += b.getPrice();
		}
		return sum;
	}

	@Override
	public double getAvgPriceOfBooks() {
		return getSumPriceOfBooks()/idx;
	}

	@Override
	public Book getRecentBook() {
		Book temp = null;
		int year = 0;
		int month = 0;
		for(Book b: bookList){
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
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add(0, "한국소설");
		list1.add(1, "해외소설");
		list1.add(2, "기타소설");
		int n = list1.size();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(0);
		list2.add(0);
		list2.add(0);
		for(Book b:bookList) {
			if(b instanceof Novel) {
				if(((Novel) b).getGenre().equals("한국소설")) {
					list2.set(0, list2.get(0)+1);
				} else if (((Novel) b).getGenre().equals("해외소설")) {
					list2.set(1, list2.get(1)+1);
				} else if (((Novel) b).getGenre().equals("기타소설")) {
					list2.set(2, list2.get(2)+1);
				}
			}
		}
		int max = 0;
		String genre = "";
		for(int i=0; i<list1.size(); i++) {
			if (list2.get(i) > max) {
				max = list2.get(i);
				genre = list1.get(i);
			}
		}
		return genre;
	}
	
	@Override
	public ArrayList<Book> magazineOfThisYearInfo() {
		Calendar c = Calendar.getInstance();
		int thisYear = c.get(Calendar.YEAR);
		ArrayList<Book> temp = new ArrayList<Book>();
		for(Book b:bookList) {
			if(b instanceof Magazine && ((Magazine) b).getPublishDate().getYear() == thisYear)
				temp.add(b);
		}
		return temp;
	}
	
}