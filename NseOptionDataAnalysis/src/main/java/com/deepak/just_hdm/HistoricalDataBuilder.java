package com.deepak.just_hdm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;import java.util.SplittableRandom;
import java.util.StringTokenizer;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.entity.Car2;
import com.entity.Engine2;

/**
 * Hello world!
 *
 */
public class HistoricalDataBuilder 
{
	private SessionFactory factory = null;
	
	public HistoricalDataBuilder() {
		// TODO Auto-generated constructor stub
		Configuration cg = new Configuration().configure("hibernate.cfg.xml");
				 //Configuration config = new Configuration().configure("annotations/hibernate.cfg.xml");
        cg.addAnnotatedClass(Car2.class);
        cg.addAnnotatedClass(Engine2.class);
        cg.addAnnotatedClass(OptionBean.class);
        cg.addAnnotatedClass(OptionCalendarTrade.class);
        cg.addAnnotatedClass(LiveRate.class);
        cg.addAnnotatedClass(IronCondor.class);
        cg.addAnnotatedClass(BoxSpread.class);
        cg.addAnnotatedClass(ShortBox.class);
        cg.addAnnotatedClass(FutureArbitrage.class);
        cg.addAnnotatedClass(FutureScripData.class);
        cg.addAnnotatedClass(HistoricalData.class);
        
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(cg.getProperties());
         factory = cg.buildSessionFactory(builder.buildServiceRegistry());
		
	}
	
	
	
    public static void main( String[] args )
    {
    	long x =System.currentTimeMillis();
    	
    	
    	HistoricalDataBuilder bc = new HistoricalDataBuilder();
        Session s= bc.factory.openSession();
        int count = 0;
        List<String> failedScrip = new ArrayList<String>();
        List<String> successScrip = new ArrayList<String>();
        Map<Integer, String> dateMap = new HashMap<Integer, String>();
        dateMap.put(-1, "01-12-2017");
        dateMap.put(0, "01-01-2018");
        dateMap.put(1, "25-01-2018");
        dateMap.put(2, "22-02-2018");
        dateMap.put(3, "26-03-2018");
        dateMap.put(4, "28-04-2018");
        dateMap.put(5, "31-05-2018");
        dateMap.put(6, "28-06-2018");
        dateMap.put(7, "26-07-2018");
        dateMap.put(8, "30-08-2018");
        List <String> failCount = new ArrayList<String>();

        
       for(int i = 1; i< 9; i++){
    	   
    	   
    	   for(double sp = 75.0; sp < 135; sp =sp+2.5  ){
           	
           	StringBuffer sb = new StringBuffer();
               
           	System.out.println( "I : " + (i-2) + "SP :: "+sp);
           	
           	try{
           		
           		ParsingUtility.getURLActionForHistoricalData(sb, "FEDERALBNK", dateMap.get(i), "CE", sp, dateMap.get(i-2), dateMap.get(i-1));
                
                ParsingUtility.persistHistoryBean(s, sb, null, null, null, null, 0);
           	} catch(Exception e){
           		
           		System.out.println(" Exception for ::" + sp  + " I :: " + i);
           		failCount.add("SP::" +sp + "I::" + i);
           	}
               
               
               sb= new StringBuffer();
            System.out.println( "I : " + (i-1) + "SP :: "+sp);
            
            try{
            	
            	ParsingUtility.getURLActionForHistoricalData(sb, "FEDERALBNK", dateMap.get(i), "CE", sp, dateMap.get(i-1), dateMap.get(i));
                ParsingUtility.persistHistoryBean(s, sb, null, null, null, null, 0);
            }catch(Exception e){
           		
           		System.out.println(" Exception for ::" + sp  + " I :: " + i);
           		failCount.add("SP::" +sp + "I::" + i);
           	}
               
               
           	
           }
    	   
       }
        
        
		System.out.println("HistoryBuilder ended ::" + failedScrip.size() );
		
		
		
    }
}
