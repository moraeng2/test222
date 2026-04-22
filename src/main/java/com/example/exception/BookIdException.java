package com.example.exception;

public class BookIdException extends RuntimeException{
	private String bookId;
	
	public BookIdException(String bookId) {
		this.bookId = bookId;
	}
	
	public String getBookId() {
		return bookId;
	}
}
