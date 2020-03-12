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
import org.hibernate.criterion.Restrictions;



public class Refer {
	public static void insert(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		Product p=new Product("Chair",5000);
		long id=(Long)newSession.save(p);
		System.out.println(id);
		t.commit();
		newSession.close();
		
	}
	public static List<Product> getProductList(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		Query q=newSession.createQuery("from Product");
		t.commit();
		
		return q.list();
		
	}
	public static void update(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		Product p=newSession.load(Product.class,3L);
		p.setName("SofaSet");
		p.setPrice(10000);
		t.commit();
		newSession.close();
	}
	public static void insertRecordUsingSql(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		SQLQuery query=newSession.createSQLQuery("INSERT INTO product_master (p_id,name,cost) VALUES (4,'Table', 6000)");
		query.executeUpdate();
		t.commit();
		System.out.println("Inserted");
		newSession.close();
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
		Query q=newSession.getNamedQuery("orderProductByCost");
		System.out.println(q.list());
		t.commit();
		newSession.close();
	}
	public static void averageCostOfProduct(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();

		Query q=newSession.createQuery("SELECT avg"
				+ "(p.price) FROM Product p");
		
		System.out.println(q.list());
		t.commit();
		newSession.close();
		
	}
	public static void productPriceMoreThan5000(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		t.begin();
		Criteria c=newSession.createCriteria(Product.class);
		c.add(Restrictions.gt("price",5000.0));
		System.out.println(c.list());
		t.commit();
		newSession.close();
		
	}
	public static void main(String args[])
	{
		Configuration config=new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory s=config.buildSessionFactory();
		//insertRecordUsingSql(s);
		//update(s);
		//System.out.println(getProductList(s));
		calculateSumofPriceOfAllProducts(s);
		//orderProductByCost(s);
		//averageCostOfProduct(s);
		//productPriceMoreThan5000(s);
		s.close();
		
		
	}
}