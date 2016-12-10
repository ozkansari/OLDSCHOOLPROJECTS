package com.mycolumnist.model.persistence.hibernate;

import java.util.List;

import com.mycolumnist.model.persistence.entity.Columnist;
import com.mycolumnist.model.persistence.hibernate.base.HibernateBase;

public class ColumnistHibernate extends HibernateBase {

	public static Columnist findColumnistByName(String name) {
		return (Columnist) findEntityByProperty("name", name, Columnist.class);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Columnist> findColumnistsByName(String name) {
		return findEntitiesLikeProperty("name", "%"+name+"%", null, null, Columnist.class);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Columnist> findColumnistsByNewspaper(String newspaperName,Integer firstResult, Integer maxResult) {
		return findEntitiesLikeProperty("newspaperName", newspaperName, firstResult, maxResult, Columnist.class);
	}
	
	public static Columnist findColumnistByNameAndNewspaper(String name, String newspaperName) {
		return (Columnist) findEntityByProperty("name", name, "newspaperName", newspaperName, Columnist.class);
	}
	
	public static Integer findColumnistsCountByNewspaper(String newspaperName) {
		return findEntityCountByProperty("newspaperName", newspaperName, null, null, Columnist.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Columnist> findColumnistsByCategory(String category,Integer firstResult, Integer maxResult) {
		return findEntitiesLikeProperty("category", category, firstResult, maxResult, Columnist.class);
	}

	public static Integer findColumnistsCountByCategory(String category) {
		return findEntityCountByProperty("category", category, null, null, Columnist.class);
	}
	

}
