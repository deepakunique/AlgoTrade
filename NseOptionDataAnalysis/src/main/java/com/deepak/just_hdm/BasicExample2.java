package com.deepak.just_hdm;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.sql.ordering.antlr.Factory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.entity.Car;
import com.entity.Car2;
import com.entity.CompositeKey;
import com.entity.Engine;
import com.entity.Engine2;

/**
 * Hello world!
 *
 */
public class BasicExample2 
{
	private SessionFactory factory = null;
	
	public BasicExample2() {
		// TODO Auto-generated constructor stub
		Configuration cg = new Configuration().configure("hibernate.cfg.xml");
				 //Configuration config = new Configuration().configure("annotations/hibernate.cfg.xml");
        cg.addAnnotatedClass(Car2.class);
        cg.addAnnotatedClass(Engine2.class);
        cg.addAnnotatedClass(OptionBean.class);
        cg.addAnnotatedClass(OptionCalendarTrade.class);
        cg.addAnnotatedClass(LiveRate.class);
        
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(cg.getProperties());
         factory = cg.buildSessionFactory(builder.buildServiceRegistry());
        
        
		
		
	}
	
	
	
    public static void main( String[] args )
    {
    	BasicExample2 bc = new BasicExample2();
        System.out.println( "Hello World!" );
        Car2 c = new Car2();
        c.setCarName("BMW");
        c.setColor("blue"); 
        
       
        
        
                        
        Engine2 e1 = new Engine2();
        e1.setCapacity(1000);
        e1.setEngineName("Maruti");
        
        c.setEngine(e1);
       e1.setCar(c);
        Session s= bc.factory.openSession();
        Transaction t = s.beginTransaction();
        s.saveOrUpdate(e1);
        
        
        t.commit();
        if(s==null)
        	System.out.println("null");
        else
        	System.out.println("not null");
	
		for(String scripName : AppConstant.scripNames)
			getScripSeriesData(s, scripName, AppConstant.currentSeries, AppConstant.nextSeries, AppConstant.lotSize);
		
		
    }



	private static void getScripSeriesData(Session s, String scripName, final String currentSeries,
			final String nextSeries, final int lotSize) {
		
			
		
		
		Set<Double> strikePriceSet = new HashSet<Double>();
		Map<String, Map<String, OptionBean>> obMap = new HashMap<String, Map<String, OptionBean>>();
		Map<String,LiveRate> liveRateMap = new HashMap<String, LiveRate>();
		
		StringBuffer sb = new StringBuffer();
		
		
		ParsingUtility.getURLAction(sb, AppConstant.charset,currentSeries,scripName,"");
		ParsingUtility.persistOptionBean(s, sb, currentSeries, obMap,strikePriceSet,liveRateMap,lotSize);
		
		sb= new StringBuffer();
		ParsingUtility.getURLAction(sb, AppConstant.charset,nextSeries,scripName,"");
		ParsingUtility.persistOptionBean(s, sb, nextSeries, obMap,strikePriceSet,liveRateMap,lotSize);
		
		Transaction t = s.beginTransaction();
		for(String key: liveRateMap.keySet()){
			s.saveOrUpdate(liveRateMap.get(key));
		}
		t.commit();
		ParsingUtility.persistOptionCalendarTrade(s, obMap, scripName, currentSeries, nextSeries, lotSize, AppConstant.callOptionType,
					AppConstant.putOptionType, strikePriceSet);
		
		
	}

	
}
