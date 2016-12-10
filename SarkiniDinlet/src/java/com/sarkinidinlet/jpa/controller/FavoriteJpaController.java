/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Favorite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.User;
import com.sarkinidinlet.jpa.entity.Constant;

/**
 *
 * @author Özkan SARI
 */
public class FavoriteJpaController {

    public FavoriteJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Favorite favorite) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User idCreatorFk = favorite.getIdCreatorFk();
            if (idCreatorFk != null) {
                idCreatorFk = em.getReference(idCreatorFk.getClass(), idCreatorFk.getIdUser());
                favorite.setIdCreatorFk(idCreatorFk);
            }
            Constant idFavTypeFk = favorite.getIdFavTypeFk();
            if (idFavTypeFk != null) {
                idFavTypeFk = em.getReference(idFavTypeFk.getClass(), idFavTypeFk.getIdConstant());
                favorite.setIdFavTypeFk(idFavTypeFk);
            }
            em.persist(favorite);
            if (idCreatorFk != null) {
                idCreatorFk.getFavoriteCollection().add(favorite);
                idCreatorFk = em.merge(idCreatorFk);
            }
            if (idFavTypeFk != null) {
                idFavTypeFk.getFavoriteCollection().add(favorite);
                idFavTypeFk = em.merge(idFavTypeFk);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Favorite favorite) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favorite persistentFavorite = em.find(Favorite.class, favorite.getIdFavorite());
            User idCreatorFkOld = persistentFavorite.getIdCreatorFk();
            User idCreatorFkNew = favorite.getIdCreatorFk();
            Constant idFavTypeFkOld = persistentFavorite.getIdFavTypeFk();
            Constant idFavTypeFkNew = favorite.getIdFavTypeFk();
            if (idCreatorFkNew != null) {
                idCreatorFkNew = em.getReference(idCreatorFkNew.getClass(), idCreatorFkNew.getIdUser());
                favorite.setIdCreatorFk(idCreatorFkNew);
            }
            if (idFavTypeFkNew != null) {
                idFavTypeFkNew = em.getReference(idFavTypeFkNew.getClass(), idFavTypeFkNew.getIdConstant());
                favorite.setIdFavTypeFk(idFavTypeFkNew);
            }
            favorite = em.merge(favorite);
            if (idCreatorFkOld != null && !idCreatorFkOld.equals(idCreatorFkNew)) {
                idCreatorFkOld.getFavoriteCollection().remove(favorite);
                idCreatorFkOld = em.merge(idCreatorFkOld);
            }
            if (idCreatorFkNew != null && !idCreatorFkNew.equals(idCreatorFkOld)) {
                idCreatorFkNew.getFavoriteCollection().add(favorite);
                idCreatorFkNew = em.merge(idCreatorFkNew);
            }
            if (idFavTypeFkOld != null && !idFavTypeFkOld.equals(idFavTypeFkNew)) {
                idFavTypeFkOld.getFavoriteCollection().remove(favorite);
                idFavTypeFkOld = em.merge(idFavTypeFkOld);
            }
            if (idFavTypeFkNew != null && !idFavTypeFkNew.equals(idFavTypeFkOld)) {
                idFavTypeFkNew.getFavoriteCollection().add(favorite);
                idFavTypeFkNew = em.merge(idFavTypeFkNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = favorite.getIdFavorite();
                if (findFavorite(id) == null) {
                    throw new NonexistentEntityException("The favorite with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Favorite favorite;
            try {
                favorite = em.getReference(Favorite.class, id);
                favorite.getIdFavorite();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The favorite with id " + id + " no longer exists.", enfe);
            }
            User idCreatorFk = favorite.getIdCreatorFk();
            if (idCreatorFk != null) {
                idCreatorFk.getFavoriteCollection().remove(favorite);
                idCreatorFk = em.merge(idCreatorFk);
            }
            Constant idFavTypeFk = favorite.getIdFavTypeFk();
            if (idFavTypeFk != null) {
                idFavTypeFk.getFavoriteCollection().remove(favorite);
                idFavTypeFk = em.merge(idFavTypeFk);
            }
            em.remove(favorite);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Favorite> findFavoriteEntities() {
        return findFavoriteEntities(true, -1, -1);
    }

    public List<Favorite> findFavoriteEntities(int maxResults, int firstResult) {
        return findFavoriteEntities(false, maxResults, firstResult);
    }

    private List<Favorite> findFavoriteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Favorite as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Favorite findFavorite(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Favorite.class, id);
        } finally {
            em.close();
        }
    }

    public int getFavoriteCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Favorite as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
