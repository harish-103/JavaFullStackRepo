package com.hibernate.one_many;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneManyMap {

	public static void insert(SessionFactory sessionfactory)
	{
		 Session session =sessionfactory.openSession();
	      Transaction transaction1 =session.beginTransaction();
	      
	      Market market=new Market("IBM");
	     
	      
	      Set<Stock> stockList=new HashSet<Stock>();
	      stockList.add(new Stock("IBM",1000,market));
	      stockList.add(new Stock("IBM",100,market));
	      stockList.add(new Stock("IBM",100,market));
	      
	      Market markets=new Market("IBM",stockList);
	      session.persist(markets);
	      transaction1.commit();
	      session.close();
	}
	
	public static List<Stock>getAllStocks(SessionFactory sessionfactory )
	{
		 Session session =sessionfactory.openSession();
		Query query=session.createQuery("From Market");
		 List<Stock> sl=query.list();
	      session.close();
	      return sl;
	}
	
	public static void main(String[] args) throws Exception {
		 Configuration configuration =  new Configuration();
	      configuration.configure("hibernate.cfg.xml");
	      SessionFactory sessionFactory = configuration.buildSessionFactory();
	      insert(sessionFactory);
	      sessionFactory.close();

	}

}



