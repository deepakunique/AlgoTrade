package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Generated;

@Entity
public class Car2 implements Serializable{

	@Id
	
	@GeneratedValue
	private int myId = 0;
	
	
	
	private String carName;

	private String color;
	
	
	private int price;
	
	
	
	
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
	private Engine2 engine;
	
	
	
	

	public Engine2 getEngine() {
		return engine;
	}


	public void setEngine(Engine2 engine) {
		this.engine = engine;
	}


	public String getColor() {
		return color;
	}

	
	

	public void setColor(String color) {
		this.color = color;
	}


	public int getMyId() {
		return myId;
	}


	public void setMyId(int id) {
		this.myId = id;
	}


	public String getCarName() {
		return carName;
	}


	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	
	
}
