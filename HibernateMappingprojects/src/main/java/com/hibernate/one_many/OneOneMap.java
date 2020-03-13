package com.hibernate.one_many;


	import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.Transaction;
	import org.hibernate.cfg.Configuration;




	public class OneOneMap{
		public static void insert(SessionFactory sessionfactory)
		{
			 Session session =sessionfactory.openSession();
		      Transaction transcation1 =session.beginTransaction();
		    
		      Enquiry enquiry=new Enquiry("whatis my account balance?");
		      Customer customer=new Customer("Harish",enquiry);
		      session.persist(customer);
		      //System.out.println("Product  id="+productId);
		      transcation1.commit();
		      session.close();
		}
		public static List<Customer> getAllCustomer(SessionFactory sessionFactory)
		{
			Session newSession=sessionFactory.openSession();
			Transaction t=newSession.beginTransaction();
			
			Query q=newSession.createQuery("from Customer");
			t.commit();
			
			return q.list();
			
		}
		public static void main(String[] args) {
			 Configuration configuration =  new Configuration();
		      configuration.configure("hibernate.cfg.xml");
		      SessionFactory sessionFactory = configuration.buildSessionFactory();
		      insert(sessionFactory);
		     System.out.println(getAllCustomer(sessionFactory));
		      sessionFactory.close();

		}

	}

