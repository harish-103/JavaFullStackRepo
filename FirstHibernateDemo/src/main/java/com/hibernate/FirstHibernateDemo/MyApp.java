package com.hibernate.FirstHibernateDemo;





import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;


public class MyApp { 
	
	public static void update(SessionFactory sessionFactory)
	{
		
	      Session session =sessionFactory.openSession();
	      Transaction tx =session.beginTransaction();
	      tx.begin();
	      Product product=session.load(Product.class,1L);
	      product.setName("Tables");
	      tx.commit();
	      session.close();
	      
	}
	public static List<Product> getAllProducts(SessionFactory sessionFactory)
	{
		Session session =sessionFactory.openSession();
		Query query =session.createQuery("from Product");
		List<Product> productList=query.list();
		session.close();
		return productList;
	}
	public static void insert(SessionFactory sessionFactory) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Product product1=new Product("Chairs",5000);
		Product product2=new Product("Ch",50);
		Product product3=new Product("Chai",500);
		Product product4=new Product("Chai",500);
		Product product5=new Product("Chai",500);
		long productId1=(Long)session.save(product1);
		long productId2=(Long)session.save(product2);
		long productId3=(Long)session.save(product3);
		long productId4=(Long)session.save(product4);
		long productId5=(Long)session.save(product5);
		tx.commit();
		
	}
	public static void calculateSumofPriceOfAllProducts(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		Query q=newSession.createQuery("SELECT sum(p.price) FROM Product p");
		
		System.out.println(q.list());
		t.commit();
		newSession.close();
		
	}
	public static void orderProductByCost(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		/*
		Criteria c=newSession.createCriteria(Product.class);
		c.addOrder(Order.asc("price"));
		System.out.println(c.list());
		*/
		Query q=newSession.createQuery("SELECT p.price FROM Product p ORDER BY p.price ");
		System.out.println(q.list());
		t.commit();
		newSession.close();
	}
	public static void orderProductByNames(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		
		Criteria c=newSession.createCriteria(Product.class);
		c.addOrder(Order.asc("price"));
		System.out.println("This is criteria"+c.list());
		
		Query q=newSession.createQuery("SELECT p.name FROM Product p ORDER BY p.price ");
		//System.out.println(q.list());
		t.commit();
		newSession.close();
	}
	public static void avgOfAllProducts(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		Query q=newSession.createQuery("SELECT avg(p.price) FROM Product p");
		
		System.out.println(q.list());
		t.commit();
		newSession.close();
		
	}
	public static void salarygreaterThan(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		Query q=newSession.createQuery("SELECT p.name FROM Product p WHERE p.price>1000");
		
		System.out.println(q.list());
		t.commit();
		newSession.close();
		
	}
	public static void delete(SessionFactory sessionFactory)
	{
		  Session session =sessionFactory.openSession();
	      Transaction transaction =session.beginTransaction();
	      transaction.begin();
	      Product product=session.get(Product.class,3L);
	      session.delete(product);
	      transaction.commit();
	      session.close();
	
	}
	public static void insertSQLUsingHQL(SessionFactory sessionFactory)
	{
			Session session=sessionFactory.openSession();
			Query sqlQuery=session.createSQLQuery(" insert into PRO values(4,'laptop',400000)");
			sqlQuery.executeUpdate();
			session.close();
	}
	
	public static void main(String[] args) throws Exception
	{ 
		Configuration configuration=new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		insert(sessionFactory);
		//insertSQLUsingHQL(sessionFactory);
		//update(sessionFactory);
		//System.out.println(getAllProducts(sessionFactory));
		//delete(sessionFactory);
		calculateSumofPriceOfAllProducts(sessionFactory);
		//orderProductByCost(sessionFactory);
		//orderProductByNames(sessionFactory);
		//avgOfAllProducts(sessionFactory);
		//salarygreaterThan(sessionFactory);
		sessionFactory.close();
  	}

	
	
	
	
	
}