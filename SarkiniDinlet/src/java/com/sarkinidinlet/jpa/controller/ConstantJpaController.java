/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.IllegalOrphanException;
import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Constant;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.ConstantType;
import com.sarkinidinlet.jpa.entity.UserDetail;
import java.util.ArrayList;
import java.util.Collection;
import com.sarkinidinlet.jpa.entity.Favorite;
import com.sarkinidinlet.jpa.entity.Artist;

/**
 *
 * @author Özkan SARI
 */
public class ConstantJpaController {

    public ConstantJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Constant constant) {
        if (constant.getUserDetailCollection() == null) {
            constant.setUserDetailCollection(new ArrayList<UserDetail>());
        }
        if (constant.getUserDetailCollection1() == null) {
            constant.setUserDetailCollection1(new ArrayList<UserDetail>());
        }
        if (constant.getFavoriteCollection() == null) {
            constant.setFavoriteCollection(new ArrayList<Favorite>());
        }
        if (constant.getArtistCollection() == null) {
            constant.setArtistCollection(new ArrayList<Artist>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConstantType idTypeFk = constant.getIdTypeFk();
            if (idTypeFk != null) {
                idTypeFk = em.getReference(idTypeFk.getClass(), idTypeFk.getIdConstantType());
                constant.setIdTypeFk(idTypeFk);
            }
            Collection<UserDetail> attachedUserDetailCollection = new ArrayList<UserDetail>();
            for (UserDetail userDetailCollectionUserDetailToAttach : constant.getUserDetailCollection()) {
                userDetailCollectionUserDetailToAttach = em.getReference(userDetailCollectionUserDetailToAttach.getClass(), userDetailCollectionUserDetailToAttach.getIdUserDetail());
                attachedUserDetailCollection.add(userDetailCollectionUserDetailToAttach);
            }
            constant.setUserDetailCollection(attachedUserDetailCollection);
            Collection<UserDetail> attachedUserDetailCollection1 = new ArrayList<UserDetail>();
            for (UserDetail userDetailCollection1UserDetailToAttach : constant.getUserDetailCollection1()) {
                userDetailCollection1UserDetailToAttach = em.getReference(userDetailCollection1UserDetailToAttach.getClass(), userDetailCollection1UserDetailToAttach.getIdUserDetail());
                attachedUserDetailCollection1.add(userDetailCollection1UserDetailToAttach);
            }
            constant.setUserDetailCollection1(attachedUserDetailCollection1);
            Collection<Favorite> attachedFavoriteCollection = new ArrayList<Favorite>();
            for (Favorite favoriteCollectionFavoriteToAttach : constant.getFavoriteCollection()) {
                favoriteCollectionFavoriteToAttach = em.getReference(favoriteCollectionFavoriteToAttach.getClass(), favoriteCollectionFavoriteToAttach.getIdFavorite());
                attachedFavoriteCollection.add(favoriteCollectionFavoriteToAttach);
            }
            constant.setFavoriteCollection(attachedFavoriteCollection);
            Collection<Artist> attachedArtistCollection = new ArrayList<Artist>();
            for (Artist artistCollectionArtistToAttach : constant.getArtistCollection()) {
                artistCollectionArtistToAttach = em.getReference(artistCollectionArtistToAttach.getClass(), artistCollectionArtistToAttach.getIdArtist());
                attachedArtistCollection.add(artistCollectionArtistToAttach);
            }
            constant.setArtistCollection(attachedArtistCollection);
            em.persist(constant);
            if (idTypeFk != null) {
                idTypeFk.getConstantCollection().add(constant);
                idTypeFk = em.merge(idTypeFk);
            }
            for (UserDetail userDetailCollectionUserDetail : constant.getUserDetailCollection()) {
                Constant oldIdCityFkOfUserDetailCollectionUserDetail = userDetailCollectionUserDetail.getIdCityFk();
                userDetailCollectionUserDetail.setIdCityFk(constant);
                userDetailCollectionUserDetail = em.merge(userDetailCollectionUserDetail);
                if (oldIdCityFkOfUserDetailCollectionUserDetail != null) {
                    oldIdCityFkOfUserDetailCollectionUserDetail.getUserDetailCollection().remove(userDetailCollectionUserDetail);
                    oldIdCityFkOfUserDetailCollectionUserDetail = em.merge(oldIdCityFkOfUserDetailCollectionUserDetail);
                }
            }
            for (UserDetail userDetailCollection1UserDetail : constant.getUserDetailCollection1()) {
                Constant oldIdCountryFkOfUserDetailCollection1UserDetail = userDetailCollection1UserDetail.getIdCountryFk();
                userDetailCollection1UserDetail.setIdCountryFk(constant);
                userDetailCollection1UserDetail = em.merge(userDetailCollection1UserDetail);
                if (oldIdCountryFkOfUserDetailCollection1UserDetail != null) {
                    oldIdCountryFkOfUserDetailCollection1UserDetail.getUserDetailCollection1().remove(userDetailCollection1UserDetail);
                    oldIdCountryFkOfUserDetailCollection1UserDetail = em.merge(oldIdCountryFkOfUserDetailCollection1UserDetail);
                }
            }
            for (Favorite favoriteCollectionFavorite : constant.getFavoriteCollection()) {
                Constant oldIdFavTypeFkOfFavoriteCollectionFavorite = favoriteCollectionFavorite.getIdFavTypeFk();
                favoriteCollectionFavorite.setIdFavTypeFk(constant);
                favoriteCollectionFavorite = em.merge(favoriteCollectionFavorite);
                if (oldIdFavTypeFkOfFavoriteCollectionFavorite != null) {
                    oldIdFavTypeFkOfFavoriteCollectionFavorite.getFavoriteCollection().remove(favoriteCollectionFavorite);
                    oldIdFavTypeFkOfFavoriteCollectionFavorite = em.merge(oldIdFavTypeFkOfFavoriteCollectionFavorite);
                }
            }
            for (Artist artistCollectionArtist : constant.getArtistCollection()) {
                Constant oldMusicTypeFkOfArtistCollectionArtist = artistCollectionArtist.getMusicTypeFk();
                artistCollectionArtist.setMusicTypeFk(constant);
                artistCollectionArtist = em.merge(artistCollectionArtist);
                if (oldMusicTypeFkOfArtistCollectionArtist != null) {
                    oldMusicTypeFkOfArtistCollectionArtist.getArtistCollection().remove(artistCollectionArtist);
                    oldMusicTypeFkOfArtistCollectionArtist = em.merge(oldMusicTypeFkOfArtistCollectionArtist);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Constant constant) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Constant persistentConstant = em.find(Constant.class, constant.getIdConstant());
            ConstantType idTypeFkOld = persistentConstant.getIdTypeFk();
            ConstantType idTypeFkNew = constant.getIdTypeFk();
            Collection<UserDetail> userDetailCollectionOld = persistentConstant.getUserDetailCollection();
            Collection<UserDetail> userDetailCollectionNew = constant.getUserDetailCollection();
            Collection<UserDetail> userDetailCollection1Old = persistentConstant.getUserDetailCollection1();
            Collection<UserDetail> userDetailCollection1New = constant.getUserDetailCollection1();
            Collection<Favorite> favoriteCollectionOld = persistentConstant.getFavoriteCollection();
            Collection<Favorite> favoriteCollectionNew = constant.getFavoriteCollection();
            Collection<Artist> artistCollectionOld = persistentConstant.getArtistCollection();
            Collection<Artist> artistCollectionNew = constant.getArtistCollection();
            List<String> illegalOrphanMessages = null;
            for (Favorite favoriteCollectionOldFavorite : favoriteCollectionOld) {
                if (!favoriteCollectionNew.contains(favoriteCollectionOldFavorite)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Favorite " + favoriteCollectionOldFavorite + " since its idFavTypeFk field is not nullable.");
                }
            }
            for (Artist artistCollectionOldArtist : artistCollectionOld) {
                if (!artistCollectionNew.contains(artistCollectionOldArtist)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Artist " + artistCollectionOldArtist + " since its musicTypeFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTypeFkNew != null) {
                idTypeFkNew = em.getReference(idTypeFkNew.getClass(), idTypeFkNew.getIdConstantType());
                constant.setIdTypeFk(idTypeFkNew);
            }
            Collection<UserDetail> attachedUserDetailCollectionNew = new ArrayList<UserDetail>();
            for (UserDetail userDetailCollectionNewUserDetailToAttach : userDetailCollectionNew) {
                userDetailCollectionNewUserDetailToAttach = em.getReference(userDetailCollectionNewUserDetailToAttach.getClass(), userDetailCollectionNewUserDetailToAttach.getIdUserDetail());
                attachedUserDetailCollectionNew.add(userDetailCollectionNewUserDetailToAttach);
            }
            userDetailCollectionNew = attachedUserDetailCollectionNew;
            constant.setUserDetailCollection(userDetailCollectionNew);
            Collection<UserDetail> attachedUserDetailCollection1New = new ArrayList<UserDetail>();
            for (UserDetail userDetailCollection1NewUserDetailToAttach : userDetailCollection1New) {
                userDetailCollection1NewUserDetailToAttach = em.getReference(userDetailCollection1NewUserDetailToAttach.getClass(), userDetailCollection1NewUserDetailToAttach.getIdUserDetail());
                attachedUserDetailCollection1New.add(userDetailCollection1NewUserDetailToAttach);
            }
            userDetailCollection1New = attachedUserDetailCollection1New;
            constant.setUserDetailCollection1(userDetailCollection1New);
            Collection<Favorite> attachedFavoriteCollectionNew = new ArrayList<Favorite>();
            for (Favorite favoriteCollectionNewFavoriteToAttach : favoriteCollectionNew) {
                favoriteCollectionNewFavoriteToAttach = em.getReference(favoriteCollectionNewFavoriteToAttach.getClass(), favoriteCollectionNewFavoriteToAttach.getIdFavorite());
                attachedFavoriteCollectionNew.add(favoriteCollectionNewFavoriteToAttach);
            }
            favoriteCollectionNew = attachedFavoriteCollectionNew;
            constant.setFavoriteCollection(favoriteCollectionNew);
            Collection<Artist> attachedArtistCollectionNew = new ArrayList<Artist>();
            for (Artist artistCollectionNewArtistToAttach : artistCollectionNew) {
                artistCollectionNewArtistToAttach = em.getReference(artistCollectionNewArtistToAttach.getClass(), artistCollectionNewArtistToAttach.getIdArtist());
                attachedArtistCollectionNew.add(artistCollectionNewArtistToAttach);
            }
            artistCollectionNew = attachedArtistCollectionNew;
            constant.setArtistCollection(artistCollectionNew);
            constant = em.merge(constant);
            if (idTypeFkOld != null && !idTypeFkOld.equals(idTypeFkNew)) {
                idTypeFkOld.getConstantCollection().remove(constant);
                idTypeFkOld = em.merge(idTypeFkOld);
            }
            if (idTypeFkNew != null && !idTypeFkNew.equals(idTypeFkOld)) {
                idTypeFkNew.getConstantCollection().add(constant);
                idTypeFkNew = em.merge(idTypeFkNew);
            }
            for (UserDetail userDetailCollectionOldUserDetail : userDetailCollectionOld) {
                if (!userDetailCollectionNew.contains(userDetailCollectionOldUserDetail)) {
                    userDetailCollectionOldUserDetail.setIdCityFk(null);
                    userDetailCollectionOldUserDetail = em.merge(userDetailCollectionOldUserDetail);
                }
            }
            for (UserDetail userDetailCollectionNewUserDetail : userDetailCollectionNew) {
                if (!userDetailCollectionOld.contains(userDetailCollectionNewUserDetail)) {
                    Constant oldIdCityFkOfUserDetailCollectionNewUserDetail = userDetailCollectionNewUserDetail.getIdCityFk();
                    userDetailCollectionNewUserDetail.setIdCityFk(constant);
                    userDetailCollectionNewUserDetail = em.merge(userDetailCollectionNewUserDetail);
                    if (oldIdCityFkOfUserDetailCollectionNewUserDetail != null && !oldIdCityFkOfUserDetailCollectionNewUserDetail.equals(constant)) {
                        oldIdCityFkOfUserDetailCollectionNewUserDetail.getUserDetailCollection().remove(userDetailCollectionNewUserDetail);
                        oldIdCityFkOfUserDetailCollectionNewUserDetail = em.merge(oldIdCityFkOfUserDetailCollectionNewUserDetail);
                    }
                }
            }
            for (UserDetail userDetailCollection1OldUserDetail : userDetailCollection1Old) {
                if (!userDetailCollection1New.contains(userDetailCollection1OldUserDetail)) {
                    userDetailCollection1OldUserDetail.setIdCountryFk(null);
                    userDetailCollection1OldUserDetail = em.merge(userDetailCollection1OldUserDetail);
                }
            }
            for (UserDetail userDetailCollection1NewUserDetail : userDetailCollection1New) {
                if (!userDetailCollection1Old.contains(userDetailCollection1NewUserDetail)) {
                    Constant oldIdCountryFkOfUserDetailCollection1NewUserDetail = userDetailCollection1NewUserDetail.getIdCountryFk();
                    userDetailCollection1NewUserDetail.setIdCountryFk(constant);
                    userDetailCollection1NewUserDetail = em.merge(userDetailCollection1NewUserDetail);
                    if (oldIdCountryFkOfUserDetailCollection1NewUserDetail != null && !oldIdCountryFkOfUserDetailCollection1NewUserDetail.equals(constant)) {
                        oldIdCountryFkOfUserDetailCollection1NewUserDetail.getUserDetailCollection1().remove(userDetailCollection1NewUserDetail);
                        oldIdCountryFkOfUserDetailCollection1NewUserDetail = em.merge(oldIdCountryFkOfUserDetailCollection1NewUserDetail);
                    }
                }
            }
            for (Favorite favoriteCollectionNewFavorite : favoriteCollectionNew) {
                if (!favoriteCollectionOld.contains(favoriteCollectionNewFavorite)) {
                    Constant oldIdFavTypeFkOfFavoriteCollectionNewFavorite = favoriteCollectionNewFavorite.getIdFavTypeFk();
                    favoriteCollectionNewFavorite.setIdFavTypeFk(constant);
                    favoriteCollectionNewFavorite = em.merge(favoriteCollectionNewFavorite);
                    if (oldIdFavTypeFkOfFavoriteCollectionNewFavorite != null && !oldIdFavTypeFkOfFavoriteCollectionNewFavorite.equals(constant)) {
                        oldIdFavTypeFkOfFavoriteCollectionNewFavorite.getFavoriteCollection().remove(favoriteCollectionNewFavorite);
                        oldIdFavTypeFkOfFavoriteCollectionNewFavorite = em.merge(oldIdFavTypeFkOfFavoriteCollectionNewFavorite);
                    }
                }
            }
            for (Artist artistCollectionNewArtist : artistCollectionNew) {
                if (!artistCollectionOld.contains(artistCollectionNewArtist)) {
                    Constant oldMusicTypeFkOfArtistCollectionNewArtist = artistCollectionNewArtist.getMusicTypeFk();
                    artistCollectionNewArtist.setMusicTypeFk(constant);
                    artistCollectionNewArtist = em.merge(artistCollectionNewArtist);
                    if (oldMusicTypeFkOfArtistCollectionNewArtist != null && !oldMusicTypeFkOfArtistCollectionNewArtist.equals(constant)) {
                        oldMusicTypeFkOfArtistCollectionNewArtist.getArtistCollection().remove(artistCollectionNewArtist);
                        oldMusicTypeFkOfArtistCollectionNewArtist = em.merge(oldMusicTypeFkOfArtistCollectionNewArtist);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = constant.getIdConstant();
                if (findConstant(id) == null) {
                    throw new NonexistentEntityException("The constant with id " + id + " no longer exists.");
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
            Constant constant;
            try {
                constant = em.getReference(Constant.class, id);
                constant.getIdConstant();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The constant with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Favorite> favoriteCollectionOrphanCheck = constant.getFavoriteCollection();
            for (Favorite favoriteCollectionOrphanCheckFavorite : favoriteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Constant (" + constant + ") cannot be destroyed since the Favorite " + favoriteCollectionOrphanCheckFavorite + " in its favoriteCollection field has a non-nullable idFavTypeFk field.");
            }
            Collection<Artist> artistCollectionOrphanCheck = constant.getArtistCollection();
            for (Artist artistCollectionOrphanCheckArtist : artistCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Constant (" + constant + ") cannot be destroyed since the Artist " + artistCollectionOrphanCheckArtist + " in its artistCollection field has a non-nullable musicTypeFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ConstantType idTypeFk = constant.getIdTypeFk();
            if (idTypeFk != null) {
                idTypeFk.getConstantCollection().remove(constant);
                idTypeFk = em.merge(idTypeFk);
            }
            Collection<UserDetail> userDetailCollection = constant.getUserDetailCollection();
            for (UserDetail userDetailCollectionUserDetail : userDetailCollection) {
                userDetailCollectionUserDetail.setIdCityFk(null);
                userDetailCollectionUserDetail = em.merge(userDetailCollectionUserDetail);
            }
            Collection<UserDetail> userDetailCollection1 = constant.getUserDetailCollection1();
            for (UserDetail userDetailCollection1UserDetail : userDetailCollection1) {
                userDetailCollection1UserDetail.setIdCountryFk(null);
                userDetailCollection1UserDetail = em.merge(userDetailCollection1UserDetail);
            }
            em.remove(constant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Constant> findConstantEntities() {
        return findConstantEntities(true, -1, -1);
    }

    public List<Constant> findConstantEntities(int maxResults, int firstResult) {
        return findConstantEntities(false, maxResults, firstResult);
    }

    private List<Constant> findConstantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Constant as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Constant findConstant(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Constant.class, id);
        } finally {
            em.close();
        }
    }

    public int getConstantCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Constant as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
