package com.self.util;

public class Date {
	private int year;
	private int month;
	
	public Date() {}
	public Date(int year, int month) {
		this.year = year;
		this.month = month;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	@Override
	public String toString() {
		return year+"-"+month;
	}
}