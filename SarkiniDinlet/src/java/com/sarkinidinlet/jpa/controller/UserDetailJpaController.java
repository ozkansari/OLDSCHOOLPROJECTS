/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.IllegalOrphanException;
import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.UserDetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Constant;
import com.sarkinidinlet.jpa.entity.User;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Özkan SARI
 */
public class UserDetailJpaController {

    public UserDetailJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserDetail userDetail) {
        if (userDetail.getUserCollection() == null) {
            userDetail.setUserCollection(new ArrayList<User>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Constant idCityFk = userDetail.getIdCityFk();
            if (idCityFk != null) {
                idCityFk = em.getReference(idCityFk.getClass(), idCityFk.getIdConstant());
                userDetail.setIdCityFk(idCityFk);
            }
            Constant idCountryFk = userDetail.getIdCountryFk();
            if (idCountryFk != null) {
                idCountryFk = em.getReference(idCountryFk.getClass(), idCountryFk.getIdConstant());
                userDetail.setIdCountryFk(idCountryFk);
            }
            Collection<User> attachedUserCollection = new ArrayList<User>();
            for (User userCollectionUserToAttach : userDetail.getUserCollection()) {
                userCollectionUserToAttach = em.getReference(userCollectionUserToAttach.getClass(), userCollectionUserToAttach.getIdUser());
                attachedUserCollection.add(userCollectionUserToAttach);
            }
            userDetail.setUserCollection(attachedUserCollection);
            em.persist(userDetail);
            if (idCityFk != null) {
                idCityFk.getUserDetailCollection().add(userDetail);
                idCityFk = em.merge(idCityFk);
            }
            if (idCountryFk != null) {
                idCountryFk.getUserDetailCollection().add(userDetail);
                idCountryFk = em.merge(idCountryFk);
            }
            for (User userCollectionUser : userDetail.getUserCollection()) {
                UserDetail oldIdUserDetailFkOfUserCollectionUser = userCollectionUser.getIdUserDetailFk();
                userCollectionUser.setIdUserDetailFk(userDetail);
                userCollectionUser = em.merge(userCollectionUser);
                if (oldIdUserDetailFkOfUserCollectionUser != null) {
                    oldIdUserDetailFkOfUserCollectionUser.getUserCollection().remove(userCollectionUser);
                    oldIdUserDetailFkOfUserCollectionUser = em.merge(oldIdUserDetailFkOfUserCollectionUser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserDetail userDetail) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserDetail persistentUserDetail = em.find(UserDetail.class, userDetail.getIdUserDetail());
            Constant idCityFkOld = persistentUserDetail.getIdCityFk();
            Constant idCityFkNew = userDetail.getIdCityFk();
            Constant idCountryFkOld = persistentUserDetail.getIdCountryFk();
            Constant idCountryFkNew = userDetail.getIdCountryFk();
            Collection<User> userCollectionOld = persistentUserDetail.getUserCollection();
            Collection<User> userCollectionNew = userDetail.getUserCollection();
            List<String> illegalOrphanMessages = null;
            for (User userCollectionOldUser : userCollectionOld) {
                if (!userCollectionNew.contains(userCollectionOldUser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain User " + userCollectionOldUser + " since its idUserDetailFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCityFkNew != null) {
                idCityFkNew = em.getReference(idCityFkNew.getClass(), idCityFkNew.getIdConstant());
                userDetail.setIdCityFk(idCityFkNew);
            }
            if (idCountryFkNew != null) {
                idCountryFkNew = em.getReference(idCountryFkNew.getClass(), idCountryFkNew.getIdConstant());
                userDetail.setIdCountryFk(idCountryFkNew);
            }
            Collection<User> attachedUserCollectionNew = new ArrayList<User>();
            for (User userCollectionNewUserToAttach : userCollectionNew) {
                userCollectionNewUserToAttach = em.getReference(userCollectionNewUserToAttach.getClass(), userCollectionNewUserToAttach.getIdUser());
                attachedUserCollectionNew.add(userCollectionNewUserToAttach);
            }
            userCollectionNew = attachedUserCollectionNew;
            userDetail.setUserCollection(userCollectionNew);
            userDetail = em.merge(userDetail);
            if (idCityFkOld != null && !idCityFkOld.equals(idCityFkNew)) {
                idCityFkOld.getUserDetailCollection().remove(userDetail);
                idCityFkOld = em.merge(idCityFkOld);
            }
            if (idCityFkNew != null && !idCityFkNew.equals(idCityFkOld)) {
                idCityFkNew.getUserDetailCollection().add(userDetail);
                idCityFkNew = em.merge(idCityFkNew);
            }
            if (idCountryFkOld != null && !idCountryFkOld.equals(idCountryFkNew)) {
                idCountryFkOld.getUserDetailCollection().remove(userDetail);
                idCountryFkOld = em.merge(idCountryFkOld);
            }
            if (idCountryFkNew != null && !idCountryFkNew.equals(idCountryFkOld)) {
                idCountryFkNew.getUserDetailCollection().add(userDetail);
                idCountryFkNew = em.merge(idCountryFkNew);
            }
            for (User userCollectionNewUser : userCollectionNew) {
                if (!userCollectionOld.contains(userCollectionNewUser)) {
                    UserDetail oldIdUserDetailFkOfUserCollectionNewUser = userCollectionNewUser.getIdUserDetailFk();
                    userCollectionNewUser.setIdUserDetailFk(userDetail);
                    userCollectionNewUser = em.merge(userCollectionNewUser);
                    if (oldIdUserDetailFkOfUserCollectionNewUser != null && !oldIdUserDetailFkOfUserCollectionNewUser.equals(userDetail)) {
                        oldIdUserDetailFkOfUserCollectionNewUser.getUserCollection().remove(userCollectionNewUser);
                        oldIdUserDetailFkOfUserCollectionNewUser = em.merge(oldIdUserDetailFkOfUserCollectionNewUser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userDetail.getIdUserDetail();
                if (findUserDetail(id) == null) {
                    throw new NonexistentEntityException("The userDetail with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserDetail userDetail;
            try {
                userDetail = em.getReference(UserDetail.class, id);
                userDetail.getIdUserDetail();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userDetail with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<User> userCollectionOrphanCheck = userDetail.getUserCollection();
            for (User userCollectionOrphanCheckUser : userCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserDetail (" + userDetail + ") cannot be destroyed since the User " + userCollectionOrphanCheckUser + " in its userCollection field has a non-nullable idUserDetailFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Constant idCityFk = userDetail.getIdCityFk();
            if (idCityFk != null) {
                idCityFk.getUserDetailCollection().remove(userDetail);
                idCityFk = em.merge(idCityFk);
            }
            Constant idCountryFk = userDetail.getIdCountryFk();
            if (idCountryFk != null) {
                idCountryFk.getUserDetailCollection().remove(userDetail);
                idCountryFk = em.merge(idCountryFk);
            }
            em.remove(userDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserDetail> findUserDetailEntities() {
        return findUserDetailEntities(true, -1, -1);
    }

    public List<UserDetail> findUserDetailEntities(int maxResults, int firstResult) {
        return findUserDetailEntities(false, maxResults, firstResult);
    }

    private List<UserDetail> findUserDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from UserDetail as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UserDetail findUserDetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserDetailCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from UserDetail as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
