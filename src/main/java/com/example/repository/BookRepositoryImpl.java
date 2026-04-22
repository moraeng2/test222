package com.example.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
	private JdbcTemplate template;

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	public BookRepositoryImpl() {
		System.out.println("BookRepositoryImpl 객체 생성");
	}

	@Override
	public List<Book> getAllBookList() {
		String SQL = "select * from book";
		List<Book> listOfBooks = template.query(SQL, new BookRowMapper());
		return listOfBooks;
	}

	@Override
	public List<Book> getBookListByCategory(String category) {
		// TODO Auto-generated method stub
		List<Book> booksByCategory = new ArrayList<Book>();
		String SQL = "select * from book where b_category like '%" + category +"%'";
		booksByCategory = template.query(SQL, new BookRowMapper());		return booksByCategory;
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		// TODO Auto-generated method stub
		Set<Book> booksByPublisher = new HashSet<Book>();
		Set<Book> booksByCategory = new HashSet<Book>();
		
		// Map 안에 있는 모든 키들의 집합(Set<String>)
		// filter에 어떤 조건들이 있는지 확인하기 위함
		Set<String> booksByFilter = filter.keySet();

		// 매트릭스 변수 중 publisher를 포함한 경우 실행
		if (booksByFilter.contains("publisher")) {

			/*
			 * filter.get("publisher") : 출판사 키를 가진 값의 리스트/배열 ~.size() : ~의 갯수
			 * 
			 */
			for (int j = 0; j < filter.get("publisher").size(); j++) {
				String publisherName = filter.get("publisher").get(j);
				String SQL = "select * from book where b_publisher like '%"+publisherName + "%'";
				booksByPublisher.addAll(template.query(SQL, new BookRowMapper()));
			}
		}

		if (booksByFilter.contains("category")) {
			for (int i = 0; i < filter.get("category").size(); i++) {
				String category = filter.get("category").get(i);
				String SQL="select * from book where b_category like '%"+category + "%'";
				booksByCategory.addAll(template.query(SQL, new BookRowMapper()));
			}
		}

		// retainAll() : 둘다 가지고 있는 원소로만 구성됨(수정됨, 원본 데이터 손실), 호출한 쪽만 바뀐다.
		// 반환값은 boolean. List, Set, Queue 등 모든 Collection 구현체 가능
		booksByCategory.retainAll(booksByPublisher);
		return booksByCategory;
	}

	@Override
	public Book getBookById(String bookId) {
		Book bookInfo = null;
		String SQL = "select count(*) from book where b_bookId = ?";
		int rowCount = template.queryForObject(SQL, Integer.class, bookId);
		if (rowCount != 0) {
			SQL = "select * from book where b_bookId = ?";
			bookInfo = template.queryForObject(SQL, new Object[] {bookId}, new BookRowMapper());
		}

		return bookInfo;
	}

	@Override
	public void setNewBook(Book book) {
		String SQL = "INSERT INTO book (b_bookId, b_name, b_unitPrice, b_author, b_description, b_publisher, b_category, b_unitsInStock, b_releaseDate,b_condition, b_fileName) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(SQL, book.getBookId(), book.getName(), book.getUnitPrice(), book.getAuthor(),
        book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(),
        book.getReleaseDate(), book.getCondition(), book.getFileName());
        return;
		
	}

}
