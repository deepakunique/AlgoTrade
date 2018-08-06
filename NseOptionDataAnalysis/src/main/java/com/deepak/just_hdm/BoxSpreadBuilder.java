package com.deepak.just_hdm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;import java.util.SplittableRandom;

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
 Logic is as follows ::
	Buy 1 ITM Call
	Sell 1 OTM Call
	Buy 1 ITM Put
	Sell 1 OTM Put
	
	Expiration Value of Box = Higher Strike Price - Lower Strike Price

	Risk-free Profit = Expiration Value of Box - Net Premium Paid
	
	Hence look for retrun greater than 0 in the sql search.
 *
 *
 */

public class BoxSpreadBuilder 
{
	private SessionFactory factory = null;
	
	public BoxSpreadBuilder() {
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
        
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(cg.getProperties());
         factory = cg.buildSessionFactory(builder.buildServiceRegistry());
		
	}
	
	
	
    public static void main( String[] args )
    {
    	BoxSpreadBuilder bc = new BoxSpreadBuilder();
        Session s= bc.factory.openSession();
        
		
        
        List<LiveRate> liveRateList = s.createQuery("from LiveRate ").list();
        for(LiveRate liveRate : liveRateList){
        	int multiple =(int) Math.round((liveRate.getCurrentMktPrice()/liveRate.getLotSizeDifference()));
        	//double itm1 = (multiple -1)*liveRate.getLotSizeDifference();
        	double atm = (multiple)*liveRate.getLotSizeDifference();
        	//double otm1 = (multiple + 1)*liveRate.getLotSizeDifference();
        	double itm;
        	double otm;
        	String spListString ="";
        	if(liveRate.getCurrentMktPrice() > atm){
        		itm = atm;
        		otm = (multiple + 1)*liveRate.getLotSizeDifference();
        	}else {
        		
        		itm = (multiple -1)*liveRate.getLotSizeDifference();
        		otm = atm;
        	}
        		
        	
        	
        	 spListString =  itm + "," +otm;
        	//SQLQuery sq = s.createQuery("from OptionCalendarTrade where scripName =" +"'"+ 
        		//	liveRate.getScripName() +"'  and strikePrice in (" +spListString+") order by strikePrice, optionType ");
        	
        	int i=0;
        	List<OptionCalendarTrade> spList = s.createQuery("from OptionCalendarTrade where scripName =" +"'"+ 
        			liveRate.getScripName() +"'  and strikePrice in (" +spListString+") order by strikePrice, optionType ").list();
        	
        	if(spList.size()>=4){
        		
        	
        	OptionCalendarTrade itmBeanCE = (OptionCalendarTrade)spList.get(i++);
        	OptionCalendarTrade otmBeanPE = spList.get(i++);
        	OptionCalendarTrade otmBeanCE = spList.get(i++);
        	OptionCalendarTrade itmBeanPE = spList.get(i++);
        	
        	
        	double boxSpread = 0.0;
        	double expirationValue= 0.0;
        	BoxSpread ic = new BoxSpread();
        	
        		boxSpread =  (otm-itm) - (itmBeanCE.getCurrentSeriesAskPrice() +itmBeanPE.getCurrentSeriesAskPrice()
        		-otmBeanCE.getCurrentSeriesBidPrice()  - otmBeanPE.getCurrentSeriesBidPrice() );
        				
        				
        				
        		expirationValue = otm-itm;
        		ic.setItmCallStrikePrice(itm);
        		ic.setOtmCallStrikePrice(otm);
            	
        	
        	

        	ic.setScripName(liveRate.getScripName());
        	ic.setStrikePriceDifference(liveRate.getLotSizeDifference());
        	ic.setBoxSpreadReturn(boxSpread);
        	ic.setCmp(liveRate.getCurrentMktPrice());
        	Transaction t=s.beginTransaction();
        	s.saveOrUpdate(ic);
        	t.commit();
        	}
        }
        
        //updateLotSizeDifference(s,AppConstant.scripNames);
    }
    
	
}
