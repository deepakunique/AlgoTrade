package com.deepak.just_hdm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BoxSpread {

	@Id
	@GeneratedValue
	private int id;
	

	private double itmCallStrikePrice;
	private double otmCallStrikePrice;
	private String scripName;
	private String desciption;
	private double boxSpreadReturn;
	private double cmp;
	private double strikePriceDifference;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	

	public double getItmCallStrikePrice() {
		return itmCallStrikePrice;
	}
	public void setItmCallStrikePrice(double itmCallStrikePrice) {
		this.itmCallStrikePrice = itmCallStrikePrice;
	}
	public double getOtmCallStrikePrice() {
		return otmCallStrikePrice;
	}
	public void setOtmCallStrikePrice(double otmCallStrikePrice) {
		this.otmCallStrikePrice = otmCallStrikePrice;
	}
	public String getScripName() {
		return scripName;
	}
	public void setScripName(String scripName) {
		this.scripName = scripName;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	
	public double getBoxSpreadReturn() {
		return boxSpreadReturn;
	}
	public void setBoxSpreadReturn(double boxSpreadReturn) {
		this.boxSpreadReturn = boxSpreadReturn;
	}
	public double getCmp() {
		return cmp;
	}
	public void setCmp(double cmp) {
		this.cmp = cmp;
	}
	public double getStrikePriceDifference() {
		return strikePriceDifference;
	}
	public void setStrikePriceDifference(double strikePriceDifference) {
		this.strikePriceDifference = strikePriceDifference;
	}
	
	
	
	
}
