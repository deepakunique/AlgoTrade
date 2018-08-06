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

import org.hibernate.annotations.Generated;

@Entity
public class Car implements Serializable{

	@Id
	
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "abc")
	//@SequenceGenerator(name= "abc", sequenceName= "nySeq")
	
	//@GeneratedValue(strategy = GenerationType.TABLE, generator = "myseq")
	//@TableGenerator(name="myseq", table="rollnumber", pkColumnName = "roll_num", pkColumnValue= "myValue",allocationSize=4)
	@GeneratedValue
	private int myId = 0;
	
	
	/*@EmbeddedId
	 * 
	private CompositeKey cmp;*/
	
	private String carName;

	private String color;
	
	
	private int price;
	
	
	
	
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@OneToMany
	@JoinColumn(name = "myId")
	private List<Engine> engines;
	
	
	
	
	
	/*@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "myId")
	@JoinTable(name = "carEngineMap", joinColumns = @JoinColumn(name = "myId"))
	private List<Engine> engines;*/
	
	
	/*
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "engineId")
	private Engine e;*/
	
	
	

/*	public Engine getE() {
		return e;
	}


	public void setE(Engine e) {
		this.e = e;*/


	public List<Engine> getEngines() {
		return engines;
	}


	public void setEngines(List<Engine> engines) {
		this.engines = engines;
	}


	public String getColor() {
		return color;
	}

	
	/*public CompositeKey getCmp() {
		return cmp;
	}


	public void setCmp(CompositeKey cmp) {
		this.cmp = cmp;
	}
*/

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
