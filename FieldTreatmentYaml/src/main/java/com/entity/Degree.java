package com.entity;
import java.util.List;

public class Degree {
	
	 private String name;
	 
	 private List<Treatment> treatments;	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	public List<Treatment> getTreatments() {
		return treatments;
	}


	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}


	
	

}
