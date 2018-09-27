package com.entity;
import java.util.List;

public class Field {
	
	private String name;
	private String degree;	
	private String type;


	private List<Treatment> treatments;



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDegree() {
		return degree;
	}



	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}

	
	public List<Treatment> getTreatments() {
		return treatments;
	}



	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	

}
