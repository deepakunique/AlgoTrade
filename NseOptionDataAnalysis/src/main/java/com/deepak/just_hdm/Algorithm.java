package com.deepak.just_hdm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
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

import com.entity.Car2;
import com.entity.Engine2;

/**
 * Hello world!
 *
 */
public class Algorithm 
{
	private SessionFactory factory = null;
	
	public Algorithm() {
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
        
        
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(cg.getProperties());
         factory = cg.buildSessionFactory(builder.buildServiceRegistry());
		
	}
	
	
	
    public static void main( String[] args )
    {
    	long x =System.currentTimeMillis();
    	
    	
    	Algorithm bc = new Algorithm();
        Session s= bc.factory.openSession();
        int count = 0;
        List<String> failedScrip = new ArrayList<String>();
        List<String> successScrip = new ArrayList<String>();
        
        int index = 0;
		for(String scripLot : AppConstant.scripLotSizeArray){
			try{
				/*if(++count > 20){
					Thread.sleep(1000*20);
					count =0;
				}*/
					
				
				StringTokenizer st = new StringTokenizer(scripLot,"|");
				String scripName = st.nextToken();
				int lotSize = Integer.parseInt(st.nextToken());
				getScripSeriesData(s, scripName, AppConstant.currentSeries, AppConstant.nextSeries, lotSize,failedScrip);
				successScrip.add(scripName);
			}
			catch(Exception e){
						
					failedScrip.add(scripLot);
			}
		}
			
		System.out.println("Failed Scrip Count ::" + failedScrip.size() );
		
		ListIterator<String> itr = failedScrip.listIterator();
		int expCount =0;
		while(itr.hasNext()){
			
			try{
				StringTokenizer st = new StringTokenizer(itr.next(),"|");
				String scripName = st.nextToken();
				int lotSize = Integer.parseInt(st.nextToken());
				getScripSeriesData(s, scripName, AppConstant.currentSeries, AppConstant.nextSeries, lotSize,failedScrip);
				itr.remove();
			}
			catch(Exception e){
				if(++expCount> 100)
					break;
				System.out.println("exception ::"); 
			}
		}
		
        updateLotSizeDifference(s,AppConstant.scripNames);
        System.out.println("Failed Scrip Count ::" + failedScrip.size() +  " :::::"+failedScrip);
        
        
        
        MarginUpdate.executeMarginUpdate(s);
		ShortBoxBuilder.executeShortBoxStrategy(s);
		BoxSpreadBuilder.executeBoxSpreadStartegy(s);
		IronCondorBuilder.executeIronCondorStrategy(s);
		
		
		System.out.println("Total time taken in seconds::" + (System.currentTimeMillis() - x)/1000);
		
    }

    
    
    
    
    private static void updateLotSizeDifference(Session s, String successScrip []){
    	
    	
    	//to get the data
    	for(String scripLot :  successScrip) {
    		
    		StringTokenizer st = new StringTokenizer(scripLot,"|");
			String scripName = st.nextToken();
			
    		SQLQuery sq = s.createSQLQuery("select strikeprice from OptionBean where scripName =" +"'"+ scripName +"'  and optionType= 'CE' and seriesName = '"+
    				AppConstant.currentSeries+ "' order by strikePrice desc ");
        	
    		Query q = s.createQuery("from LiveRate where scripName = '"+scripName+"'");
    		LiveRate lv =(LiveRate) q.uniqueResult();
    		
        	List<Object> spList = sq.list();
        	int count =0;
        	if(spList!=null && spList.size()>1) {
        		Double sp1 = new Double(spList.get(count).toString());
        		Double sp2 = 0.0;
        		while(lv.getCurrentMktPrice() < sp1  ){
        			sp2 = sp1;
        			sp1 = new Double(spList.get(++count).toString());
        		}
        		
            	System.out.println("scripName :: " + scripName + "Lot difference :: " + (sp2-sp1));
            	
            	lv.setLotSizeDifference(sp2-sp1);
            	Transaction t = s.beginTransaction();
            	s.saveOrUpdate(lv);
            	t.commit();
            	
            	
        	} else{
        		
        		System.out.println("scripName :: " + scripName + " not exist");
        	}
        	
    	}
    	
    	
    	
    }

	private static void getScripSeriesData(Session s, String scripName, final String currentSeries,
			final String nextSeries, final int lotSize, List<String> failedScrip) {
		
		Set<Double> strikePriceSet = new HashSet<Double>();
		Map<String, Map<String, OptionBean>> obMap = new HashMap<String, Map<String, OptionBean>>();
		Map<String,LiveRate> liveRateMap = new HashMap<String, LiveRate>();
		
		StringBuffer sb = new StringBuffer();
		String instrumentType = "OPTSTK";
		Transaction t = null;
		try {
			
			t = s.beginTransaction();
			
			if(scripName.contains("NIFTY")){
				
				instrumentType = "OPTIDX";
			}
			ParsingUtility.getURLAction(sb, AppConstant.charset,currentSeries,scripName, instrumentType);
			ParsingUtility.persistOptionBean(s, sb, currentSeries, obMap,strikePriceSet,liveRateMap,lotSize);
			
			sb= new StringBuffer();
			ParsingUtility.getURLAction(sb, AppConstant.charset,nextSeries,scripName,instrumentType);
			ParsingUtility.persistOptionBean(s, sb, nextSeries, obMap,strikePriceSet,liveRateMap,lotSize);
			
			ParsingUtility.persistOptionCalendarTrade(s, obMap, scripName, currentSeries, nextSeries, lotSize, AppConstant.callOptionType,
						AppConstant.putOptionType, strikePriceSet);
			
			
			for(String key: liveRateMap.keySet()){
				s.saveOrUpdate(liveRateMap.get(key));
			} 
			t.commit();
		} catch(Exception e){
			
			failedScrip.add(scripName+"|"+lotSize);
			t.rollback();
		}
		
		
	}

	
}
