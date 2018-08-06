package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Engine {

	@Id
	@GeneratedValue
	private int engineId;
	
	private String engineName;
	
	private int capacity;
	
	//@OneToOne(mappedBy = "e" )
	//private Car car;
	

	public int getEngineId() {
		return engineId;
	}

	public void setEngineId(int engineId) {
		this.engineId = engineId;
	}

	public String getEngineName() {
		return engineName;
	}

	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
}
