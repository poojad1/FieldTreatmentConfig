package com.cubic.anonymization.entity;


import java.util.List;

public class Dataset {	
	
	private String name;
	
	private List<Field> fields ;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Field> getFields() {
		return fields;
	}


	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	

}
