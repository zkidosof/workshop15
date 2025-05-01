package com.self.exception;

public class DuplicateIsbnException extends Exception {
	public DuplicateIsbnException() {
		this("This is DuplicateIsbnException");
	}
	public DuplicateIsbnException(String message) {
		super(message);
	}
}