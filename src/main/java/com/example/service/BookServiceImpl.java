package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Book;
import com.example.exception.BookIdException;
import com.example.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService {
	BookServiceImpl() {
		System.out.println("BookServiceImpl 객체 생성");
	}
	@Autowired
	private BookRepository bookRepository; //다형성
	/* BookRepositoryImpl이 아닌 인터페이스 속성을 쓴 이유 : ~Impl 구현체를 직접 참조하면, 해당 구현체에 강하게 묶임
	 * 인터페이스를 속성으로 쓰면, 의존이 느슨하게 연결되어 Service 계층은 "어떤 저장방식이든 getAllBookList()만 해주면 된다" 라는 규약에만 의존함.
	 * Spring DI(의존성 주입)은 "구현체가 아닌 인터페이스에 의존하라"를 원칙으로 한다.
	 * 
	 * 따라서 @Autowired는 BookRepository 타입 Bean을 찾아서 주입해준다.
	 * 실제로는 BookRepositoryImpl이 들어오지만, Service입장에선 몰라도 됨.
	 * 
	 * 만약 같은 인터페이스를 구현한 클래스가 여러개라면 충돌 발생하므로,
	 * 1) 생성자 주입 + @Qualifier : 가장 권장, 요즘 스프링 팀/실무 권장 방식
	 * @Service
		public class BookServiceImpl implements BookService {
		    private final BookRepository bookRepository;
		
		    @Autowired
		    public BookServiceImpl(@Qualifier("bookRepositoryJpaImpl") BookRepository bookRepository) {
		        this.bookRepository = bookRepository;
		    }
		}
	 * 2) @Primary 지정하여 기본값이다 알려주기 (@Repository 쪽에)
	 * 3) @Qualifier 지정하여 원하는 Bean 직접 지정 ex) @Qualifier("bookRepositoryJdbcImpl") -> (@Autowired 쪽에) : 잘 안씀
	 *  */

	@Override
	public List<Book> getAllBookList() {
		// TODO Auto-generated method stub
		return bookRepository.getAllBookList();
	}
	
	@Override
	public List<Book> getBookListByCategory(String category) {
		// TODO Auto-generated method stub
		List<Book> booksByCategory = bookRepository.getBookListByCategory(category);
		return booksByCategory;
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		// TODO Auto-generated method stub
		Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
		
		return booksByFilter;
	}

	@Override
	public Book getBookById(String bookId) {
		// TODO Auto-generated method stub
		Book bookInfo = bookRepository.getBookById(bookId);
		
		if (bookInfo == null) {
			throw new BookIdException(bookId);
		}
		return bookInfo;
	}
	
	@Override
	public void setNewBook(Book book) {  
        bookRepository.setNewBook(book);  
 }
	
}
