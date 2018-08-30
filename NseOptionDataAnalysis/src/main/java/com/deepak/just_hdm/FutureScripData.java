package com.deepak.just_hdm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class FutureScripData {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String scripName;
	
	private String seriesName;
	
	@Column(name ="BuyPrice")
	private String buyPrice1;
	
	@Column(name ="SellPrice")
	private String sellPrice1;
	
	
	
	

	public String getBuyPrice1() {
		return buyPrice1;
	}
	public void setBuyPrice1(String buyPrice1) {
		this.buyPrice1 = buyPrice1;
	}
	public String getSellPrice1() {
		return sellPrice1;
	}
	public void setSellPrice1(String sellPrice1) {
		this.sellPrice1 = sellPrice1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getScripName() {
		return scripName;
	}
	public void setScripName(String scripName) {
		this.scripName = scripName;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	
	

}
