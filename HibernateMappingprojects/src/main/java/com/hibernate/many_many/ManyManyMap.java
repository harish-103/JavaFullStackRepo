package com.hibernate.many_many;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class ManyManyMap
{

	public static void insert(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		Course course_1=new Course("ESP");
		Course course_2=new Course("University Physics");
		Course course_3=new Course("Digital Signal System");
		Set<Course> course=new HashSet<Course>();
		Set<Student> student=new HashSet<Student>();
		course.add(course_1);
		course.add(course_2);
		course.add(course_3);
		Student student_1=new Student("Harish",course);
		Student student_2=new Student("John",course);
		Student student_3=new Student("Bheem",course);
		student.add(student_1);
		student.add(student_2);
		student.add(student_3);
		newSession.save(student_1);
		newSession.persist(student_2);
		t.commit();
		newSession.close();	
	}
	
	public static void update(SessionFactory sessionFactory)
	{
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		Student s=newSession.load(Student.class,1L);
		s.setStudentName("Winston");
		t.commit();
		newSession.close();	
	}
	
	
	
	public static void delete(SessionFactory sessionFactory) {
		Session newSession=sessionFactory.openSession();
		Transaction t=newSession.beginTransaction();
		Student student=newSession.load(Student.class,1L);
		newSession.delete(student);
		t.commit();
		newSession.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration config=new Configuration();
		config.configure("hibernate.cfg.xml");
		SessionFactory s=config.buildSessionFactory();
		insert(s);
		update(s);
		//delete(s);
		s.close();
	}

}