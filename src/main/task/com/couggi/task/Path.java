package com.couggi.task;

public class Path {

	private String value;
	
	public Path() {
	}
	
	public Path(String value) {
		this.value = value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	
	@Override
	public String toString() {
		return value;
	}
}
