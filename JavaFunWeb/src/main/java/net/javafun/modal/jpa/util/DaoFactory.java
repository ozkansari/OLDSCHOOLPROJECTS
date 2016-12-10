package net.javafun.modal.jpa.util;

import net.javafun.modal.jpa.service.ArticleDao;
import net.javafun.modal.jpa.service.BlogDao;
import net.javafun.modal.jpa.service.ConstantTypeDao;
import net.javafun.modal.jpa.service.ConstantValueDao;
import net.javafun.modal.jpa.service.LinkDao;
import net.javafun.modal.jpa.service.UserInfoDao;
import net.javafun.modal.jpa.service.UserDetailDao;
import net.javafun.modal.jpa.service.hibernate.ArticleDaoHibernateImpl;
import net.javafun.modal.jpa.service.hibernate.BlogDaoHibernateImpl;
import net.javafun.modal.jpa.service.hibernate.ConstantTypeDaoHibernateImpl;
import net.javafun.modal.jpa.service.hibernate.ConstantValueDaoDaoHibernateImpl;
import net.javafun.modal.jpa.service.hibernate.LinkDaoHibernateImpl;
import net.javafun.modal.jpa.service.hibernate.UserInfoDaoHibernateImpl;
import net.javafun.modal.jpa.service.hibernate.UserDetailDaoHibernateImpl;

/**
 * @author ozkansari
 *
 */
public class DaoFactory {

	private static UserInfoDao userDao  = null;
	private static UserDetailDao userDetailDao  = null;
	private static BlogDao blogDao  = null;
	private static ArticleDao articleDao  = null;
	private static ConstantTypeDao constantTypeDao  = null;
	private static ConstantValueDao constantValueDao  = null;
	private static LinkDao linkDao = null;
	
	public static UserInfoDao getUserInfoDao() {
		if(userDao==null) {
			userDao = new UserInfoDaoHibernateImpl();
		}
		return userDao;
	}
	
	public static UserDetailDao getUserDetailDao() {
		if(userDetailDao==null) {
			userDetailDao = new UserDetailDaoHibernateImpl();
		}
		return userDetailDao;
	}
	
	public static BlogDao getBlogDao() {
		if(blogDao==null) {
			blogDao = new BlogDaoHibernateImpl();
		}
		return blogDao;
	}

	public static ArticleDao getArticleDao() {
		if(articleDao==null) {
			articleDao = new ArticleDaoHibernateImpl();
		}
		return articleDao;
	}
	
	public static ConstantTypeDao getConstantTypeDao() {
		if(constantTypeDao==null) {
			constantTypeDao = new ConstantTypeDaoHibernateImpl();
		}
		return constantTypeDao;
	}
	
	public static ConstantValueDao getConstantValueDao() {
		if(constantValueDao==null) {
			constantValueDao = new ConstantValueDaoDaoHibernateImpl();
		}
		return constantValueDao;
	}
	
	public static LinkDao getLinkDao() {
		if(linkDao==null) {
			linkDao = new LinkDaoHibernateImpl();
		}
		return linkDao;
	}
	

}
