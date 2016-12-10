/**
 * 
 */
package net.javafun.modal.jpa.service.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import net.javafun.modal.jpa.entity.UserInfo;
import net.javafun.modal.jpa.service.UserInfoDao;
import net.javafun.modal.jpa.util.EMF;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * @author ozkansari
 *
 */
public class UserInfoDaoHibernateImpl extends HibernateImplBase<UserInfo> implements UserInfoDao {

	public UserInfo findOrCreateUser(String username, String pwd, String email) {
		
		assert username != null;
		assert pwd != null;
		assert email != null;
		
		if (username == null || username.trim().length() == 0)
			throw new RuntimeException("Name cannot be null or empty");

		UserInfo user = null;
		EntityManager em = EMF.getEntityManager();
		em.getTransaction().begin();
		try {
			Query q = em
					.createQuery("select u from UserInfo u where u.username = :username");
			q.setParameter("username", username);
			user = (UserInfo) q.getSingleResult();
		} catch (NoResultException e) {
			user = new UserInfo(username, pwd, email);
			em.persist(user);
		} finally  {
			em.getTransaction().commit();
		}
		
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> findUserInfoEntities(int pageSize, int firstResult) {

		EntityManager em = EMF.getEntityManager();
		em.getTransaction().begin();
		try {
			// get the native hibernate session
			Session session = (Session) em.getDelegate(); 
			
			// Create criteria
			Criteria userCriteria = session.createCriteria(UserInfo.class);
			userCriteria.setMaxResults(pageSize);
			userCriteria.setFirstResult(firstResult);
			
			// return result
			return userCriteria.list();
			
		} catch (NoResultException e) {
			
		} finally  {
			em.getTransaction().commit();
		}
		return null;
	}
}
