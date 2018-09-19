package com.deepak.just_hdm;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class TestMysql {
	
	public static void main(String[] args) {
		
		// "JAN","FEB", "MAR", "APR", "MAY" ,"JUN", "JUL","AUG","SEP","OCT","NOV","DEC"
		
		String month[] = {"JUL","AUG","SEP"};
		String year[] = {"2018"};
		String day = "";
		
		for (int k =0; k<year.length;k++){
			
			for (int j =0;j<month.length;j++){
				
				for(int i= 1 ; i < 32 ; i ++){
					
					if(i<10)
						day = "0"+i;
					else
						day =i+"";
					
					
					String file = "D:/AlgoTrade/BhavCopy/"+ year[k] +"/"+
							month[j] +"/fo"+day+month[j]+year[k]+"bhav.csv";
			        int size = insertData(file);
			        System.out.println( "Finish ::" + year[k] + "::" + month[j] + "::" + day + "::" + size );
				}
				
			}
		}
		
		
	}
	
	
	static int  insertData(String file){
		
		int x = 0;
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hbtest", "root","root");
			
			Statement statement = connection.createStatement();
			 x = statement.executeUpdate("LOAD DATA INFILE '"+file+"' "
			 		+ "INTO TABLE HistoricalOptionData FIELDS TERMINATED BY ','  ENCLOSED BY '\"' LINES TERMINATED BY '\n' "
			 		+ "IGNORE 1 ROWS (INSTRUMENT,SYMBOL,@EXPIRY_DT,STRIKE_PR,OPTION_TYP,OPEN,HIGH,LOW,CLOSE,SETTLE_PR,CONTRACTS,VAL_INLAKH,OPEN_INT,CHG_IN_OI,@currentDate) "
			 		+ "SET EXPIRY_DT = STR_TO_DATE(@EXPIRY_DT, '%d-%b-%Y'), currentDate = STR_TO_DATE(@currentDate, '%d-%b-%Y');");
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
         // Setup the connection with the DB
		  
         // Statements allow to issue SQL queries to the database
		return x;
	}

}
