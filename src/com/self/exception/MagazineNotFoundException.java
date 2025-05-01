package com.self.exception;

public class MagazineNotFoundException extends Exception {
	public MagazineNotFoundException(){
		this("This is MagazineNotFoundException");
	}
	public MagazineNotFoundException(String message){
		super(message);
	}
}