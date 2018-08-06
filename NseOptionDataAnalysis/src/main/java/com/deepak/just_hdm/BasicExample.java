package com.deepak.just_hdm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.sql.ordering.antlr.Factory;

import com.entity.Car;
import com.entity.CompositeKey;
import com.entity.Engine;

/**
 * Hello world!
 *
 */
public class BasicExample 
{
	private SessionFactory factory = null;
	
	public BasicExample() {
		// TODO Auto-generated constructor stub
		Configuration cg = new Configuration().configure("hibernate.cfg.xml");
				 //Configuration config = new Configuration().configure("annotations/hibernate.cfg.xml");
        cg.addAnnotatedClass(Car.class);
        cg.addAnnotatedClass(Engine.class);
        
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(cg.getProperties());
         factory = cg.buildSessionFactory(builder.buildServiceRegistry());
        
        
		
		
	}
	
	
	
    public static void main( String[] args )
    {
    	BasicExample bc = new BasicExample();
        System.out.println( "Hello World!" );
        Car c = new Car();
        c.setCarName("BMW");
        c.setColor("blue"); 
        
        CompositeKey ck = new CompositeKey();
        ck.setCity("Indore1");
        ck.setState("MP1");
        
        
        
                        
        Engine e1 = new Engine();
        e1.setCapacity(1000);
        e1.setEngineName("Maruti");
        
        
        Engine e2 = new Engine();
        e2.setCapacity(2000);
        e2.setEngineName("Fx");
        
        List<Engine> ls  = new ArrayList<Engine>();
        ls.add(e1);
        ls.add(e2);
        
        
        //e1.setCar(c);
        
       //c.setCmp(ck);
       
        
        Session s= bc.factory.openSession();
        Transaction t = s.beginTransaction();
        s.saveOrUpdate(c);

        
        t.commit();
         t = s.beginTransaction();
         c.setEngines(ls);
        s.saveOrUpdate(e1);
        s.saveOrUpdate(e2);
       // s.saveOrUpdate(e1);
        
        t.commit();
        
        
       
        
        
        
        s.close();
        if(s==null)
        	System.out.println("null");
        else
        	System.out.println("not null");
       
        
        
        
        
        
    }
}
