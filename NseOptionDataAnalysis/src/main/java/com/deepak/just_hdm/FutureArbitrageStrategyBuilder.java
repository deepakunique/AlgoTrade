package com.deepak.just_hdm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jsoup.Jsoup;

import com.entity.Car2;
import com.entity.Engine2;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FutureArbitrageStrategyBuilder {
	
private SessionFactory factory = null;
	
	public FutureArbitrageStrategyBuilder() {
		// TODO Auto-generated constructor stub
		Configuration cg = new Configuration().configure("hibernate.cfg.xml");
				 //Configuration config = new Configuration().configure("annotations/hibernate.cfg.xml");
        cg.addAnnotatedClass(Car2.class);
        cg.addAnnotatedClass(Engine2.class);
        cg.addAnnotatedClass(OptionBean.class);
        cg.addAnnotatedClass(OptionCalendarTrade.class);
        cg.addAnnotatedClass(LiveRate.class);
        cg.addAnnotatedClass(IronCondor.class);
        cg.addAnnotatedClass(ShortBox.class);
        cg.addAnnotatedClass(FutureArbitrage.class);
        cg.addAnnotatedClass(FutureScripData.class);
        
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(cg.getProperties());
         factory = cg.buildSessionFactory(builder.buildServiceRegistry());
		
	}
	
	
	
    public static void main( String[] args )
    {
    	FutureArbitrageStrategyBuilder bc = new FutureArbitrageStrategyBuilder();
        Session s= bc.factory.openSession();
        executeFutureArbitrage(s);
    }
    

	public static void executeFutureArbitrage(Session s) {

		List<String> failedScrip = new ArrayList<String>();
		String scripName = "";
		for(String scripLot : AppConstant.scripNames){
			
			
			try {
				StringTokenizer st = new StringTokenizer(scripLot,"|");
				scripName = st.nextToken();
				StringBuffer sb = getURLResponse(scripName,AppConstant.currentSeries);
				FutureScripData fsCurrentSeries = getFutureScripData(sb.toString());
				
				sb = getURLResponse(scripName,AppConstant.nextSeries);
				FutureScripData fsNextSeries = getFutureScripData(sb.toString());
				FutureArbitrage fa = new FutureArbitrage();
				fa.setScripName(scripName);
				fa.setReward(Double.parseDouble(fsCurrentSeries.getBuyPrice1().replace(",", "")) -
						Double.parseDouble(fsNextSeries.getSellPrice1().replace(",", "")));
				//fa.setReward(fsCurrentSeries.getBuyPrice1() - fsNextSeries.getSellPrice1());
				
				Transaction t = s.beginTransaction();
				s.save(fsCurrentSeries);
				s.save(fsNextSeries);
				s.save(fa);
				
				
				t.commit();
				
				
			} catch (Exception e){
				e.printStackTrace();
				failedScrip.add(scripName);
				
			}
		}
	}
	
	
	
	private static FutureScripData getFutureScripData(String response) throws IOException, JsonParseException, JsonMappingException {
		org.jsoup.nodes.Document document = Jsoup.parse(response.toString());
		org.jsoup.nodes.Element tableCMP = document.select("div").get(1);
		//org.jsoup.nodes.Element cmpElement = tableCMP.select("b").get(0);
		org.jsoup.nodes.Node cmElement = tableCMP.childNode(0);
		String jsonObjString = cmElement.toString().replace("[", "|||").replace("]", "|||");
		StringTokenizer st = new StringTokenizer(jsonObjString, "|||");
		st.nextToken();
		String jsonObj = st.nextToken();
		System.out.println("end");
		
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		FutureScripData fs = mapper.readValue(jsonObj, FutureScripData.class);
		return fs;
	}

	private static StringBuffer getURLResponse(String scripName, String seriesName) throws MalformedURLException, IOException, ProtocolException {
		
		
		String firstUrl ="https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteFO.jsp?"
				+ "underlying="+scripName+"&instrument=FUTSTK&type=-&strike=-&expiry="+seriesName;
				
		String secondUrl = "https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/ajaxFOGetQuoteJSON.jsp?"
				+ "underlying="+scripName 
				+ "&instrument=FUTSTK&expiry="+AppConstant.nextSeries
				+ "&type=SELECT&strike=SELECT;";
				
		if(seriesName.equals(AppConstant.nextSeries))
			firstUrl = secondUrl;
		
		URL url = new URL(firstUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		StringBuffer sb = new StringBuffer();
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			sb.append(output+"\n"); 
		}
		conn.disconnect();
		return sb;
	}
	
}
