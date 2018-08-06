package com.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable {

	private String state;
	
	private String city;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
}
