package com.deepak.just_hdm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class FutureArbitrage {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String scripName;
	
	private double reward;
	
	private double tentativeReward;

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

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public double getTentativeReward() {
		return tentativeReward;
	}

	public void setTentativeReward(double tentativeReward) {
		this.tentativeReward = tentativeReward;
	}
	
	

}
