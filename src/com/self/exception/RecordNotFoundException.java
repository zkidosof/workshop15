package com.self.exception;

public class RecordNotFoundException extends Exception{

	public RecordNotFoundException() {
		System.out.println("This is RecordNotFoundException");
	}
	public RecordNotFoundException(String message) {
		 super(message);
	}
}