package com.mycolumnist.model.persistence.hibernate;

import java.util.List;

import com.mycolumnist.model.persistence.entity.Newspaper;
import com.mycolumnist.model.persistence.hibernate.base.HibernateBase;

public class NewspaperHibernate extends HibernateBase {

	public static Newspaper findById(Long id){
		return (Newspaper) findEntityById(id, Newspaper.class);
	}

	public static List<Newspaper> findAll() {
		return (List<Newspaper>) findAllEntities(null,null,null,null,Newspaper.class);
	}

}
