package com.example.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.domain.Book;

public interface BookRepository {
	/* 인터페이스 안에서 정의한 변수는 자동으로 public static final (상수)로 취급됨 
	 * 구현 내용 없는 메서드는 자동으로 public abstract (추상 메서드: 몸체 없음, 강제성)로 취급됨
	 * default 메서드 : Java 8~ default 키워드를 앞에 붙이면, 구현부가 있는 메서드도 정의 가능
	 * static 메서드 : Java 8~ static 키워드를 앞에 붙이면, 인터페이스 자체에서 호출할 수 있는 메서드
	 * private 메서드 : Java 9~ private 키워드를 앞에 붙이면, default/static 메서드 안에서 공통 로직을 뽑아낼 때 사용
	 * 클래스는 implements로 구현, 인터페이스는 extends로 확장 (클래스와 다르게 다중 상속 가능)
	 * */
	
	List<Book> getAllBookList(); //모든 책 리스트 반환, 순서 중요(ex. 등록순, 출간일순, 가격순), 중복허용하나 DB자체가 중복허용X List타입
	List<Book> getBookListByCategory(String category); //주어진 카테고리와 일치하는 책 리스트 반환, 순서 중요 List타입
	Set<Book> getBookListByFilter(Map<String, List<String>> filter); //여러 조건을 동시에 적용할 때, 같은 책이 여러 조건에 동시에 걸릴 수 있어 중복 제거 Set타입
	Book getBookById(String bookId);	
	void setNewBook(Book book);
}
