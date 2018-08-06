package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Engine2 {

	@Id
	@GeneratedValue
	private int engineId;
	
	private String engineName;
	
	private int capacity;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	
	private Car2 car;
	

	public Car2 getCar() {
		return car;
	}

	public void setCar(Car2 car) {
		this.car = car;
	}

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
