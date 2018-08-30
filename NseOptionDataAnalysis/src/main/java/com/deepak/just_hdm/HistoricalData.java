package com.deepak.just_hdm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HistoricalData {

	@Id
	@GeneratedValue
	private int id;
	
	private String scripName;
	
	private Date currentDate;
	
	private Date expiryDate;
	
	private String optionType;
	
	private Double strikePrice;
	
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double ltp;
	private Double settlePrice;
	private Double noContracts;
	private Double oi;
	private Double changeInOi;
	private Double cmp;
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
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public Double getStrikePrice() {
		return strikePrice;
	}
	public void setStrikePrice(Double strikePrice) {
		this.strikePrice = strikePrice;
	}
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	public Double getLtp() {
		return ltp;
	}
	public void setLtp(Double ltp) {
		this.ltp = ltp;
	}
	public Double getSettlePrice() {
		return settlePrice;
	}
	public void setSettlePrice(Double settlePrice) {
		this.settlePrice = settlePrice;
	}
	public Double getNoContracts() {
		return noContracts;
	}
	public void setNoContracts(Double noContracts) {
		this.noContracts = noContracts;
	}
	public Double getOi() {
		return oi;
	}
	public void setOi(Double oi) {
		this.oi = oi;
	}
	public Double getChangeInOi() {
		return changeInOi;
	}
	public void setChangeInOi(Double changeInOi) {
		this.changeInOi = changeInOi;
	}
	public Double getCmp() {
		return cmp;
	}
	public void setCmp(Double cmp) {
		this.cmp = cmp;
	}
	
	
	
}
