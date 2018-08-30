package com.deepak.just_hdm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test {

	// http://localhost:8080/RESTfulExample/json/product/get
	public static void main(String[] args) {

	  try {
		 //https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteFO.jsp?underlying=ITC&instrument=FUTSTK&type=-&strike=-&expiry=30AUG2018
		URL url = new URL("https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuoteFO.jsp?underlying=ITC&instrument=FUTSTK&type=-&strike=-&expiry=30AUG2018");
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
		
		org.jsoup.nodes.Document document = Jsoup.parse(sb.toString());
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
		System.out.println(fs);
		
	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

	}
	
	

}