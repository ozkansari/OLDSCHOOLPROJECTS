/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.IllegalOrphanException;
import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.UserRole;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.User;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Özkan SARI
 */
public class UserRoleJpaController {

    public UserRoleJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserRole userRole) {
        if (userRole.getUserCollection() == null) {
            userRole.setUserCollection(new ArrayList<User>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<User> attachedUserCollection = new ArrayList<User>();
            for (User userCollectionUserToAttach : userRole.getUserCollection()) {
                userCollectionUserToAttach = em.getReference(userCollectionUserToAttach.getClass(), userCollectionUserToAttach.getIdUser());
                attachedUserCollection.add(userCollectionUserToAttach);
            }
            userRole.setUserCollection(attachedUserCollection);
            em.persist(userRole);
            for (User userCollectionUser : userRole.getUserCollection()) {
                UserRole oldIdRoleFkOfUserCollectionUser = userCollectionUser.getIdRoleFk();
                userCollectionUser.setIdRoleFk(userRole);
                userCollectionUser = em.merge(userCollectionUser);
                if (oldIdRoleFkOfUserCollectionUser != null) {
                    oldIdRoleFkOfUserCollectionUser.getUserCollection().remove(userCollectionUser);
                    oldIdRoleFkOfUserCollectionUser = em.merge(oldIdRoleFkOfUserCollectionUser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserRole userRole) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserRole persistentUserRole = em.find(UserRole.class, userRole.getIdUserRole());
            Collection<User> userCollectionOld = persistentUserRole.getUserCollection();
            Collection<User> userCollectionNew = userRole.getUserCollection();
            List<String> illegalOrphanMessages = null;
            for (User userCollectionOldUser : userCollectionOld) {
                if (!userCollectionNew.contains(userCollectionOldUser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain User " + userCollectionOldUser + " since its idRoleFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<User> attachedUserCollectionNew = new ArrayList<User>();
            for (User userCollectionNewUserToAttach : userCollectionNew) {
                userCollectionNewUserToAttach = em.getReference(userCollectionNewUserToAttach.getClass(), userCollectionNewUserToAttach.getIdUser());
                attachedUserCollectionNew.add(userCollectionNewUserToAttach);
            }
            userCollectionNew = attachedUserCollectionNew;
            userRole.setUserCollection(userCollectionNew);
            userRole = em.merge(userRole);
            for (User userCollectionNewUser : userCollectionNew) {
                if (!userCollectionOld.contains(userCollectionNewUser)) {
                    UserRole oldIdRoleFkOfUserCollectionNewUser = userCollectionNewUser.getIdRoleFk();
                    userCollectionNewUser.setIdRoleFk(userRole);
                    userCollectionNewUser = em.merge(userCollectionNewUser);
                    if (oldIdRoleFkOfUserCollectionNewUser != null && !oldIdRoleFkOfUserCollectionNewUser.equals(userRole)) {
                        oldIdRoleFkOfUserCollectionNewUser.getUserCollection().remove(userCollectionNewUser);
                        oldIdRoleFkOfUserCollectionNewUser = em.merge(oldIdRoleFkOfUserCollectionNewUser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userRole.getIdUserRole();
                if (findUserRole(id) == null) {
                    throw new NonexistentEntityException("The userRole with id " + id + " no longer exists.");
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
            UserRole userRole;
            try {
                userRole = em.getReference(UserRole.class, id);
                userRole.getIdUserRole();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userRole with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<User> userCollectionOrphanCheck = userRole.getUserCollection();
            for (User userCollectionOrphanCheckUser : userCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserRole (" + userRole + ") cannot be destroyed since the User " + userCollectionOrphanCheckUser + " in its userCollection field has a non-nullable idRoleFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(userRole);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserRole> findUserRoleEntities() {
        return findUserRoleEntities(true, -1, -1);
    }

    public List<UserRole> findUserRoleEntities(int maxResults, int firstResult) {
        return findUserRoleEntities(false, maxResults, firstResult);
    }

    private List<UserRole> findUserRoleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from UserRole as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UserRole findUserRole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserRole.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserRoleCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from UserRole as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
