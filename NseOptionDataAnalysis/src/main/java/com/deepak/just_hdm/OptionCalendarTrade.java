package com.deepak.just_hdm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OptionCalendarTrade {

	@Id
	@GeneratedValue
	private int id;
	private double strikePrice;
	private String scripName;
	private String optionType;
	private double point;
	private int lotSize;
	private double price;
	private double currentSeriesBidPrice;
	private double currentSeriesAskPrice;
	private double nextSeriesBidPrice;
	private double nextSeriesAskPrice;
	private double currenSeriestIv;
	private double nextSeriesIv;
	private double currentSeriesOI;
	private double nextSeriesOI;
	private double cmp; 
	
	//Using actual :: optionCurrentSeries.getBidPrice()/optionNextSeries.getAskPrice()
	private double rewardRisk;
	
	//Using tentative :: optionCurrentSeries.getBidPrice()/(optionNextSeries.getAskPrice()
	private double rewardRiskTentative;
	
	
	private double currentSeriesDelta;
	private double currentSeriesTheta;
	private double currentSeriesGamma;
	private double currentSeriesVega;
	
	

	private double nextSeriesDelta;
	private double nextSeriesTheta;
	private double nextSeriesGamma;
	private double nextSeriesVega;
	
	
	
	
	
	
	public double getCurrentSeriesDelta() {
		return currentSeriesDelta;
	}
	public void setCurrentSeriesDelta(double currentSeriesDelta) {
		this.currentSeriesDelta = currentSeriesDelta;
	}
	public double getCurrentSeriesTheta() {
		return currentSeriesTheta;
	}
	public void setCurrentSeriesTheta(double currentSeriesTheta) {
		this.currentSeriesTheta = currentSeriesTheta;
	}
	public double getCurrentSeriesGamma() {
		return currentSeriesGamma;
	}
	public void setCurrentSeriesGamma(double currentSeriesGamma) {
		this.currentSeriesGamma = currentSeriesGamma;
	}
	public double getCurrentSeriesVega() {
		return currentSeriesVega;
	}
	public void setCurrentSeriesVega(double currentSeriesVega) {
		this.currentSeriesVega = currentSeriesVega;
	}
	public double getNextSeriesDelta() {
		return nextSeriesDelta;
	}
	public void setNextSeriesDelta(double nextSeriesDelta) {
		this.nextSeriesDelta = nextSeriesDelta;
	}
	public double getNextSeriesTheta() {
		return nextSeriesTheta;
	}
	public void setNextSeriesTheta(double nextSeriesTheta) {
		this.nextSeriesTheta = nextSeriesTheta;
	}
	public double getNextSeriesGamma() {
		return nextSeriesGamma;
	}
	public void setNextSeriesGamma(double nextSeriesGamma) {
		this.nextSeriesGamma = nextSeriesGamma;
	}
	public double getNextSeriesVega() {
		return nextSeriesVega;
	}
	public void setNextSeriesVega(double nextSeriesVega) {
		this.nextSeriesVega = nextSeriesVega;
	}
	public double getCmp() {
		return cmp;
	}
	public void setCmp(double cmp) {
		this.cmp = cmp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public double getRewardRiskTentative() {
		return rewardRiskTentative;
	}
	public void setRewardRiskTentative(double rewardRiskTentative) {
		this.rewardRiskTentative = rewardRiskTentative;
	}
	public double getRewardRisk() {
		return rewardRisk;
	}
	public void setRewardRisk(double rewardRisk) {
		this.rewardRisk = rewardRisk;
	}
	public double getStrikePrice() {
		return strikePrice;
	}
	public void setStrikePrice(double strikePrice) {
		this.strikePrice = strikePrice;
	}
	public String getScripName() {
		return scripName;
	}
	public void setScripName(String scripName) {
		this.scripName = scripName;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public int getLotSize() {
		return lotSize;
	}
	public void setLotSize(int lotSize) {
		this.lotSize = lotSize;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCurrentSeriesBidPrice() {
		return currentSeriesBidPrice;
	}
	public void setCurrentSeriesBidPrice(double currentSeriesBidPrice) {
		this.currentSeriesBidPrice = currentSeriesBidPrice;
	}
	public double getCurrentSeriesAskPrice() {
		return currentSeriesAskPrice;
	}
	public void setCurrentSeriesAskPrice(double currentSeriesAskPrice) {
		this.currentSeriesAskPrice = currentSeriesAskPrice;
	}
	public double getNextSeriesBidPrice() {
		return nextSeriesBidPrice;
	}
	public void setNextSeriesBidPrice(double nextSeriesBidPrice) {
		this.nextSeriesBidPrice = nextSeriesBidPrice;
	}
	public double getNextSeriesAskPrice() {
		return nextSeriesAskPrice;
	}
	public void setNextSeriesAskPrice(double nextSeriesAskPrice) {
		this.nextSeriesAskPrice = nextSeriesAskPrice;
	}
	public double getCurrenSeriestIv() {
		return currenSeriestIv;
	}
	public void setCurrenSeriestIv(double currenSeriestIv) {
		this.currenSeriestIv = currenSeriestIv;
	}
	public double getNextSeriesIv() {
		return nextSeriesIv;
	}
	public void setNextSeriesIv(double nextSeriesIv) {
		this.nextSeriesIv = nextSeriesIv;
	}
	public double getCurrentSeriesOI() {
		return currentSeriesOI;
	}
	public void setCurrentSeriesOI(double currentSeriesOI) {
		this.currentSeriesOI = currentSeriesOI;
	}
	public double getNextSeriesOI() {
		return nextSeriesOI;
	}
	public void setNextSeriesOI(double nextSeriesOI) {
		this.nextSeriesOI = nextSeriesOI;
	}
	
	
	
	
}
