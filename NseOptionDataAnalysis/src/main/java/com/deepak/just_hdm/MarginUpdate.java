package com.deepak.just_hdm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.entity.Car2;
import com.entity.Engine2;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MarginUpdate {
	
private SessionFactory factory = null;
	
	public MarginUpdate() {
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
    	MarginUpdate bc = new MarginUpdate();
        Session s= bc.factory.openSession();
        executeMarginUpdate(s);
    }
    

	public static void executeMarginUpdate(Session s) {

		List<String> failedScrip = new ArrayList<String>();
		String scripName = "";
			
			
			try {
				//StringBuffer sb = getURLResponse();
				StringBuffer sb = new StringBuffer(); 
				
				FileReader fr = new FileReader("D:\\AlgoTrade\\NseOptionDataAnalysis\\NseOptionDataAnalysis"
						+ "\\src\\main\\resource\\response-margin-list"); 
				BufferedReader br = new BufferedReader(fr); 
				String line; 
				while((line = br.readLine()) != null) { 
					sb.append(line+"\n"); 
				} 
				fr.close(); 
				getMarginUpdateData(sb.toString(),s);
				
				
				
			} catch (Exception e){
				e.printStackTrace();
				failedScrip.add(scripName);
				
			}
		}
	
	
	
	public static String parseValueFromColumn(Elements strikeRow, int i) {
		String value;
		value = strikeRow.get(i).getAllElements().get(0).childNode(0).toString().replaceAll(",","");
		if(value.equalsIgnoreCase(" ") || value.equalsIgnoreCase("-"))
			value = "0";
		return value;
	}
	
	
	private static FutureScripData getMarginUpdateData(String response, Session s) throws IOException, JsonParseException, JsonMappingException {
		org.jsoup.nodes.Document document = Jsoup.parse(response.toString());
		org.jsoup.nodes.Element tableCMP = document.select("div").get(8);
		//org.jsoup.nodes.Element cmpElement = tableCMP.select("b").get(0);
		Elements optionRows = tableCMP.select("tr");
		
		int rowCount =0;
		for(org.jsoup.nodes.Element tr :  optionRows){
			
			if(++rowCount==1)
				continue;
			
			Elements strikeRow = tr.select("td");
			String scripName = parseValueFromColumn(strikeRow, 1).trim();
			String marginLimit = parseValueFromColumn(strikeRow, 5);
			String mwpl= parseValueFromColumn(strikeRow, 7).replace("%", "");
			
		
			Query q = s.createQuery("from LiveRate where scripName = '"+scripName+"'");
    		LiveRate lv =(LiveRate) q.uniqueResult();
    		if(lv==null)
    			continue;
    		lv.setMarginLimit(Double.parseDouble(marginLimit));
    		lv.setMwpl(Double.parseDouble(mwpl));
			Transaction t = s.beginTransaction();
			s.saveOrUpdate(lv);
			t.commit();
			
		}
		
		org.jsoup.nodes.Node cmElement = tableCMP.childNode(1);
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
}
