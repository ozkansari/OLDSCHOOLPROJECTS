package com.mycolumnist.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mycolumnist.model.persistence.entity.Column;
import com.mycolumnist.model.persistence.entity.Columnist;

public class HibernateTest {

	public static void main(String[] args) {

		Session session = null;
		try {
			// This step will read hibernate.cfg.xml 
			// and prepare hibernate for use
			SessionFactory sessionFactory = new

			Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			
			// Create new instance of Contact and set values in it by reading
			// them from form object
			System.out.println("Inserting Record");
			Columnist columnist = new Columnist("newspaper", "name", "nameTag", "image",
					"category",null,null);
			columnist.setId(1L);
			session.save(columnist);
			System.out.println("Done");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Actual contact insertion will happen at this step
			session.flush();
			session.close();

		}

	}
}
