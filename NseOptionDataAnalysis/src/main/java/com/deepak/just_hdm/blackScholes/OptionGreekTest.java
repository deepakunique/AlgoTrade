package com.deepak.just_hdm.blackScholes;

public class OptionGreekTest {
	
	public static void main(String[] args) {
		
		 
		
		BlackScholesFormula bc = new BlackScholesFormula();
		
		OptionDetails req = new OptionDetails(true, 330, 330, 6/100.0, 9.0/365, 0.5);
		
		System.out.println(bc.calculateWithGreeks(req));
		
		
		
	}
}
