package com.deepak.just_hdm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LiveRate {

	@Id
	@GeneratedValue
	private int Id;
	
	private String scripName;
	private double currentMktPrice;
	private double lotSizeDifference;
	private int lotsize;
	private double marginLimit;
	private double mwpl;
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	
	
	public double getMwpl() {
		return mwpl;
	}
	public void setMwpl(double mwpl) {
		this.mwpl = mwpl;
	}
	public double getMarginLimit() {
		return marginLimit;
	}
	public void setMarginLimit(double marginLimit) {
		this.marginLimit = marginLimit;
	}
	public int getLotsize() {
		return lotsize;
	}
	public void setLotsize(int lotsize) {
		this.lotsize = lotsize;
	}
	public double getLotSizeDifference() {
		return lotSizeDifference;
	}
	public void setLotSizeDifference(double lotSizeDifference) {
		this.lotSizeDifference = lotSizeDifference;
	}
	public String getScripName() {
		return scripName;
	}
	public void setScripName(String scripName) {
		this.scripName = scripName;
	}
	public double getCurrentMktPrice() {
		return currentMktPrice;
	}
	public void setCurrentMktPrice(double currentMktPrice) {
		this.currentMktPrice = currentMktPrice;
	}
	
	
	
	
}
