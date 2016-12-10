package com.mycolumnist.model.persistence.hibernate.base;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;

import com.mycolumnist.model.persistence.entity.Newspaper;
import com.mycolumnist.model.persistence.entity.base.EntityBase;


public class HibernateBase {
	
	protected static Logger logger = Logger.getLogger(HibernateBase.class);
	
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			/*
			sessionFactory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
			*/
		} catch (Throwable ex) {
			// TODO: Log exception!
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
	
	public static void create(Object object) {
    	try {
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			session.persist(object);
			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(e);
		}
    }
	
	public static void update(Object object) {
    	try {
			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			session.update(object);
			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(e);
		}
    }
	
	@SuppressWarnings("unchecked")
	public static EntityBase findEntityById(Long id, Class entityClass) {
		try {
			Session session = getSession();
			Criteria c = session.createCriteria(entityClass);
			c.add(Expression.eq("id", id));
			return getOneEntityResult(c);
		} catch (HibernateException e) {
			logger.error(e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected static EntityBase findEntityByProperty(String propertyName,
			String propertyValue, Class entityClass) {
		try {
			Session session = getSession();
			Criteria c = session.createCriteria(entityClass);
			c.add(Expression.eq(propertyName, propertyValue));
			
			return getOneEntityResult(c);
			
		} catch (HibernateException e) {
			logger.error(e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected static EntityBase findEntityByProperty(String propertyName,
			String propertyValue, String propertyName2, Object propertyValue2, 
			Class entityClass) {
		try {
			Session session = getSession();
			Criteria c = session.createCriteria(entityClass);
			c.add(Expression.eq(propertyName, propertyValue));
			if(propertyName2!=null && propertyValue2!=null) {
				c.add(Expression.eq(propertyName2, propertyValue2));
			}
			return getOneEntityResult(c);
		} catch (HibernateException e) {
			logger.error(e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected static Integer findEntityCountByProperty(String propertyName1,
			Object propertyValue1, String propertyName2, Object propertyValue2,
			Class entityClass) {
		try {
			Session session = getSession();
			Criteria c = session.createCriteria(entityClass);
			c.add(Expression.eq(propertyName1, propertyValue1));
			if(propertyName2!=null && propertyValue2!=null) {
				c.add(Expression.eq(propertyName2, propertyValue2));
			}
			c.setProjection(Projections.rowCount());
			return (Integer) c.uniqueResult();
		} catch (HibernateException e) {
			logger.error(e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected static List findEntitiesLikeProperty(String propertyName,
			String propertyValue, Integer firstResult, Integer maxResult,
			Class entityClass) {
		try {
			Session session = getSession();
			Criteria c = session.createCriteria(entityClass);
			if(propertyValue.contains("%")){
				c.add(Expression.like(propertyName, propertyValue));
			} else {
				c.add(Expression.eq(propertyName, propertyValue));
			}
			if(firstResult!=null && firstResult>0){
				c.setFirstResult(firstResult);
			}
			if(maxResult!=null && maxResult>0){
				c.setMaxResults(maxResult);
			}
			return c.list();
		} catch (HibernateException e) {
			logger.error(e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected static List findAllEntities(String propertyName,
			Object propertyValue, Integer firstResult, Integer maxResult,
			Class entityClass) {
		try {
			Session session = getSession();
			Criteria c = session.createCriteria(entityClass);
			if(propertyName!=null && propertyValue!=null) {
				c.add(Expression.eq(propertyName, propertyValue));
			}
			if(firstResult!=null && firstResult>=0){
				c.setFirstResult(firstResult);
			}
			if(maxResult!=null && maxResult>0){
				c.setMaxResults(maxResult);
			}
			return c.list();
		} catch (HibernateException e) {
			logger.error(e);
			return null;
		}
	}
	
	
	/** **************************************************** */
	/** HELPER METHOD(S)) */
	/** **************************************************** */
	
	@SuppressWarnings("unchecked")
	private static EntityBase getOneEntityResult(Criteria criteria) {
		EntityBase result = null;
		try {
			result = (EntityBase) criteria.uniqueResult();
		} catch(Exception e){
			criteria.setMaxResults(1);
			List<EntityBase> resultList = criteria.list();
			if(resultList!=null && resultList.size()>0){
				return (EntityBase) resultList.get(0);
			}
		}
		return result;
	}
	
}
