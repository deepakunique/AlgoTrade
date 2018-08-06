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
 */


public class IronCondorBuilder 
{
	private SessionFactory factory = null;
	
	public IronCondorBuilder() {
		// TODO Auto-generated constructor stub
		Configuration cg = new Configuration().configure("hibernate.cfg.xml");
				 //Configuration config = new Configuration().configure("annotations/hibernate.cfg.xml");
        cg.addAnnotatedClass(Car2.class);
        cg.addAnnotatedClass(Engine2.class);
        cg.addAnnotatedClass(OptionBean.class);
        cg.addAnnotatedClass(OptionCalendarTrade.class);
        cg.addAnnotatedClass(LiveRate.class);
        cg.addAnnotatedClass(IronCondor.class);
        
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(cg.getProperties());
         factory = cg.buildSessionFactory(builder.buildServiceRegistry());
		
	}
	
	
	
    public static void main( String[] args )
    {
    	IronCondorBuilder bc = new IronCondorBuilder();
        Session s= bc.factory.openSession();
        
        executeIronCondorStrategy(s);
        
        //updateLotSizeDifference(s,AppConstant.scripNames);
    }



	public static void executeIronCondorStrategy(Session s) {
		System.out.println("Iron condor strategy started ::");
		
		List<LiveRate> liveRateList = s.createQuery("from LiveRate").list();
        for(LiveRate liveRate : liveRateList){
        	int multiple =(int) Math.round((liveRate.getCurrentMktPrice()/liveRate.getLotSizeDifference()));
        	double itm1 = (multiple -1)*liveRate.getLotSizeDifference();
        	double itm2 = (multiple - 2)*liveRate.getLotSizeDifference();
        	double atm = (multiple)*liveRate.getLotSizeDifference();
        	double otm1 = (multiple + 1)*liveRate.getLotSizeDifference();
        	double otm2 = (multiple + 2)*liveRate.getLotSizeDifference();
        	
        	String spListString =  atm + "," +itm1+","+ itm2 + "," +otm1+"," +otm2;
        	//SQLQuery sq = s.createQuery("from OptionCalendarTrade where scripName =" +"'"+ 
        		//	liveRate.getScripName() +"'  and strikePrice in (" +spListString+") order by strikePrice, optionType ");
        	
        	int i=0;
        	List<OptionCalendarTrade> spList = s.createQuery("from OptionCalendarTrade where scripName =" +"'"+ 
        			liveRate.getScripName() +"'  and strikePrice in (" +spListString+") order by strikePrice, optionType ").list();
        	
        	if(spList.size()>=10){
        		
        	
        	OptionCalendarTrade itm2BeanCE = (OptionCalendarTrade)spList.get(i++);
        	OptionCalendarTrade itm2BeanPE = spList.get(i++);
        	OptionCalendarTrade itm1BeanCE = spList.get(i++);
        	OptionCalendarTrade itm1BeanPE = spList.get(i++);
        	OptionCalendarTrade atmBeanCE = spList.get(i++);
        	OptionCalendarTrade atmBeanPE = spList.get(i++);
        	OptionCalendarTrade otm1BeanCE = spList.get(i++);
        	OptionCalendarTrade otm1BeanPE = spList.get(i++);
        	OptionCalendarTrade otm2BeanCE = spList.get(i++);
        	OptionCalendarTrade otm2BeanPE = spList.get(i++);
        	
        	// Case 1 considering atm is almost equal to live price
        	double ironCondorOtm = itm1BeanPE.getCurrentSeriesBidPrice() -itm2BeanPE.getCurrentSeriesAskPrice()
        			+ otm1BeanCE.getCurrentSeriesBidPrice() -otm2BeanCE.getCurrentSeriesAskPrice();
        	

        	double ironCondorAtm = itm1BeanPE.getCurrentSeriesBidPrice() -itm2BeanPE.getCurrentSeriesAskPrice()
        			+ atmBeanCE.getCurrentSeriesBidPrice() -otm1BeanCE.getCurrentSeriesAskPrice();
        	
        	IronCondor ic = new IronCondor();
        	ic.setPointUsingOtm(ironCondorOtm);
        	ic.setPointUsingAtm(ironCondorAtm);
        	ic.setAtmStrikePrice(atm);
        	ic.setScripName(liveRate.getScripName());
        	ic.setItm1StrikePrice(itm1);
        	ic.setItm2StrikePrice(itm2);
        	ic.setOtm1trikePrice(otm1);
        	ic.setOtm2trikePrice(otm2);
        	ic.setStrikePriceDifference(liveRate.getLotSizeDifference());
        	ic.setCost(ironCondorAtm*liveRate.getLotsize());
        	ic.setCmp(liveRate.getCurrentMktPrice());
        	Transaction t=s.beginTransaction();
        	s.saveOrUpdate(ic);
        	t.commit();
        	}
        }
        System.out.println("Iron condor strategy completed ::");
	}
    
	
}
