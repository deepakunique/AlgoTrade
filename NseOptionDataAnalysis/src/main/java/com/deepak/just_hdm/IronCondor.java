package com.deepak.just_hdm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class IronCondor {
	
	
	@Id
	@GeneratedValue
	private int id;
	
	private double pointUsingOtm;
	private double pointUsingAtm;
	private double atmStrikePrice;
	private double itm1StrikePrice;
	private double itm2StrikePrice;
	private double otm1trikePrice;
	private double otm2trikePrice;
	private String scripName;
	private String desciption;
	private double cost;
	private double cmp;
	private double strikePriceDifference;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
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
	public void setStrikePriceDifference(double lotSize) {
		this.strikePriceDifference = lotSize;
	}
	
	public double getPointUsingOtm() {
		return pointUsingOtm;
	}
	public void setPointUsingOtm(double pointUsingOtm) {
		this.pointUsingOtm = pointUsingOtm;
	}
	public double getPointUsingAtm() {
		return pointUsingAtm;
	}
	public void setPointUsingAtm(double pointUsingAtm) {
		this.pointUsingAtm = pointUsingAtm;
	}
	public double getAtmStrikePrice() {
		return atmStrikePrice;
	}
	public void setAtmStrikePrice(double atmStrikePrice) {
		this.atmStrikePrice = atmStrikePrice;
	}
	public double getItm1StrikePrice() {
		return itm1StrikePrice;
	}
	public void setItm1StrikePrice(double itm1StrikePrice) {
		this.itm1StrikePrice = itm1StrikePrice;
	}
	public double getItm2StrikePrice() {
		return itm2StrikePrice;
	}
	public void setItm2StrikePrice(double itm2StrikePrice) {
		this.itm2StrikePrice = itm2StrikePrice;
	}
	public double getOtm1trikePrice() {
		return otm1trikePrice;
	}
	public void setOtm1trikePrice(double otm1trikePrice) {
		this.otm1trikePrice = otm1trikePrice;
	}
	public double getOtm2trikePrice() {
		return otm2trikePrice;
	}
	public void setOtm2trikePrice(double otm2trikePrice) {
		this.otm2trikePrice = otm2trikePrice;
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
	
	

}
