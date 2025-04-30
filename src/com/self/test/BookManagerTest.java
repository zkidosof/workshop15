package com.self.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.self.service.impl.BookManagerImpl;
import com.self.util.Date;
import com.self.vo.Book;
import com.self.vo.Magazine;
import com.self.vo.Novel;

public class BookManagerTest {

	public static void main(String[] args) {
		BookManagerImpl service = BookManagerImpl.getInstance();

		service.insertBook(new Novel(100, "작별하지않는다", "한강", "출판사1", 16000.0, "한국소설"));
		service.insertBook(new Novel(101, "소년이온다", "한강", "출판사1", 15000.0, "한국소설"));
		service.insertBook(new Novel(151, "해리포터1", "조앤K롤링", "출판사2", 17000.0, "해외소설"));
		service.insertBook(new Novel(152, "해리포터3", "조앤K롤링", "출판사2", 17000.0, "해외소설"));
		service.insertBook(new Novel(153, "해리포터2", "조앤K롤링", "출판사2", 17000.0, "해외소설"));
		service.insertBook(new Magazine(201, "ELLE", "작가", "출판사10", 5000.0, new Date(2025,02)));
		service.insertBook(new Magazine(202, "ELLE", "작가", "출판사10", 5000.0, new Date(2025,02)));
		service.insertBook(new Magazine(203, "ELLE", "작가", "출판사10", 5000.0, new Date(2025,03)));
		service.insertBook(new Magazine(204, "ELLE", "작가", "출판사10", 5000.0, new Date(2025,04)));
		
		System.out.println("현재 책 목록입니다.");
		System.out.println(service.getAllBook());
		System.out.println("현재 책의 수는 "+service.getNumberOfBooks()+"권입니다.");
		
		System.out.println("isbn=100인 책을 검색합니다.");
		System.out.println(service.getBook(100));
		
		System.out.println("isbn=201인 책 정보를 수정합니다.");
		service.updateBook(new Magazine(201, "ELLE", "작가", "출판사11", 5000.0, new Date(2025,01)));
		
		System.out.println("ELLE라는 제목으로 책을 검색합니다.");
		System.out.println(service.searchBookByTitle("ELLE"));
		
		System.out.println("10000.0원~15000.0원 사이의 책을 검색합니다.");
		System.out.println(service.searchBookByPrice(10000.0, 15000.0));
		
		System.out.println("전체 책의 가격 총합 : "+service.getSumPriceOfBooks()+"원");
		System.out.println("전체 책의 가격 평균 : "+service.getAvgPriceOfBooks()+"원");
		
		System.out.println("가장 최근에 출판된 잡지 : "+service.getRecentBook());
		System.out.println("가장 인기있는 소설 장르 : "+service.getPopularGenre());
		
		System.out.println("isbn=101인 책을 삭제합니다.");
		service.deleteBook(101);
		
		System.out.println("현재 책 목록입니다.");
		System.out.println(service.getAllBook());
		System.out.println("현재 책의 수는 "+service.getNumberOfBooks()+"권입니다.");
	
		System.out.println("====1번째 정렬====");
		// getBook() 반환받은 데이터 Title 기준 오름차순 정렬
		ArrayList<Book> list1 = service.getAllBook();
		Collections.sort(list1, new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}
		});
		for(Book b:list1)
			System.out.println(b);
			
		System.out.println("====2번째 정렬====");
		// magazineOfThisYearInfo() 반환받은 데이터를 잡지 월 기준으로 내림차순 정렬
		ArrayList<Book> list2 = service.magazineOfThisYearInfo();
		Collections.sort(list2, new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return ((Magazine) o2).getPublishDate().getMonth() - ((Magazine) o1).getPublishDate().getMonth();
		}});
		for(Book b:list2)
			System.out.println(b);
	
	}
}