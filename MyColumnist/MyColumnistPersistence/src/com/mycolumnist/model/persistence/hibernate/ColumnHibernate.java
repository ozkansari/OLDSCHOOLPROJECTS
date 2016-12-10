package com.mycolumnist.model.persistence.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;

import com.mycolumnist.model.persistence.entity.Column;
import com.mycolumnist.model.persistence.entity.Columnist;
import com.mycolumnist.model.persistence.entity.Newspaper;
import com.mycolumnist.model.persistence.hibernate.base.HibernateBase;

public class ColumnHibernate extends HibernateBase {

	public static Column findColumnByHyperlink(String hyperlink) {
		return (Column) findEntityByProperty("hyperlink", hyperlink, Column.class);
	}
	
	public static Column findColumnByTitle(String title) {
		return (Column) findEntityByProperty("title", title,Column.class);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Column> findColumnsByTitle(String title) {
		return (List<Column>) findEntitiesLikeProperty("title", "%"+title+"%", null, null, Column.class);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Column> findColumnsByColumnistId(Long columnistId,int firstResult, int maxResult) {
		return (List<Column>) findAllEntities("columnist.id",columnistId,firstResult,maxResult,Column.class);
	}
	
	public static List<Column> findColumnsByColumnist( Columnist columnist,int firstResult, int maxResult ) {
		// TODO: Implement
		return null;
	}
	
	public static Integer findColumnsCountByColumnist(Columnist columnist) {
		return findEntityCountByProperty("columnist.name", columnist.getName(), null, null, Columnist.class);
	}

	public static Integer findColumnsCountByColumnistAndDate(Columnist columnist,
			Date date) {
		
		try {
			Session session = getSession();
			Criteria columnCriteria = session.createCriteria(Column.class);
			Criteria columnistCriteria = columnCriteria.createCriteria("columnist");
			columnistCriteria.add(Expression.eq("name", columnist.getName()));
			columnCriteria.add(Expression.eq("date", date));
			columnCriteria.setProjection(Projections.rowCount());
			return (Integer) columnCriteria.uniqueResult();
		} catch (HibernateException e) {
			logger.error(e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Column> findLatestColumnsByNewspaper(String newspaperName) {
		try {
			Session session = getSession();
			Query latestColumnQuery = session.createQuery(
					"select c from Column c WHERE c.columnist.newspaper=:newspaper AND c.date = (select MAX(b.date) from Column b)");
			latestColumnQuery.setString("newspaper", newspaperName);
			return (List<Column>) latestColumnQuery.list();
		} catch (HibernateException e) {
			logger.error(e);
			return null;
		}
	}
}