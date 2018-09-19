package com.deepak.just_hdm.blackScholes;

import java.text.DecimalFormat;

import com.deepak.just_hdm.AppConstant;

public class CalculateVolatility {

	
	
	public static void main(String[] args) {
		
		BlackScholesFormula bc = new BlackScholesFormula();
		
		OptionDetails req = new OptionDetails(true, 330, 330, 6/100.0, 9.0/365, 0.5);
		
		
		double volatility = 10;
		double actualOptPrice = 10.57; 
		double v =0;
		double i=0;
		double tempOptPrice = 0;
			
			
			 while(true){
				 
				 tempOptPrice = BlackScholesFormula.calculate(true, 330, 330, 6/100.0, 9.0/365, volatility/100);
				 if(actualOptPrice < tempOptPrice || volatility > 1000){
					 break;
				 }
				 volatility = volatility+10;
			 }
			 
			 for(v = volatility -9 ; v < volatility ; v++){
				 
				 tempOptPrice = BlackScholesFormula.calculate(true, 330, 330, 6/100.0, 9.0/365, v/100);
				 if(actualOptPrice < tempOptPrice){
					 break;
				 }
			 }
			 
			 for(i = v-0.9; i < v ; i=i+0.1){
				 tempOptPrice = BlackScholesFormula.calculate(true, 330, 330, 6/100.0, 9.0/365, (i-5) /100);
				 if(actualOptPrice < tempOptPrice){
					 break;
				 }
			 }
			
			 i = i-0.1;
			 
		
			  i = Double.parseDouble(new DecimalFormat("0.00").format(i));
			 System.out.println(i);
		//System.out.println(bc.calculateWithGreeks(req));
	}
	
	
	public static double calaculateIV(boolean callType, double spot, double strike, double daysToExpire, double actualOptPrice){
		
		OptionDetails req = new OptionDetails(callType, spot, strike, AppConstant.FIXED_INTEREST_RATE/100.0, daysToExpire/365, 0.5);
		
		
		double volatility = 10;
		double v =0;
		double i=0;
		double tempOptPrice = 0;
			
			
			 while(true){
				 
				 tempOptPrice = BlackScholesFormula.calculate(callType, spot, strike, AppConstant.FIXED_INTEREST_RATE/100.0, daysToExpire/365, volatility/100);
				 if(actualOptPrice < tempOptPrice || volatility > 1000){
					 break;
				 }
				 volatility = volatility+10;
			 }
			 
			 for(v = volatility -9 ; v < volatility ; v++){
				 
				 tempOptPrice = BlackScholesFormula.calculate(callType, spot, strike, AppConstant.FIXED_INTEREST_RATE/100.0, daysToExpire/365, v/100);
				 if(actualOptPrice < tempOptPrice){
					 break;
				 }
			 }
			 
			 for(i = v-0.9; i < v ; i=i+0.1){
				 tempOptPrice = BlackScholesFormula.calculate(callType, spot, strike, AppConstant.FIXED_INTEREST_RATE/100.0, daysToExpire/365, (i-5) /100);
				 if(actualOptPrice < tempOptPrice){
					 break;
				 }
			 }
			
			 i = i-0.1;
			 
		
			  i = Double.parseDouble(new DecimalFormat("0.00").format(i));

		return i;
		
	}
}
