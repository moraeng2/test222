package com.example.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Book;
import com.example.exception.BookIdException;
import com.example.service.BookService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookIdValidator implements ConstraintValidator<BookId, String>{

    @Autowired
    private BookService bookService;

    public void initialize(BookId constraintAnnotation) {  // @BookId ���� �ʱ�ȭ �޼���  
    }  
    public boolean isValid(String value, ConstraintValidatorContext context) {  // ��ȿ�� �˻� �޼���  
        Book book;
        try {
            book = bookService.getBookById(value);
        } catch (BookIdException e) {
            return true;
        }
        if(book!= null) {
            return false;
        }
        return true;
    } 
}