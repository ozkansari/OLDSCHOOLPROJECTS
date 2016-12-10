/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.IllegalOrphanException;
import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Stats;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Clip;
import java.util.ArrayList;
import java.util.Collection;
import com.sarkinidinlet.jpa.entity.Track;
import com.sarkinidinlet.jpa.entity.Photo;
import com.sarkinidinlet.jpa.entity.Record;
import com.sarkinidinlet.jpa.entity.Article;
import com.sarkinidinlet.jpa.entity.PhotoAlbum;
import com.sarkinidinlet.jpa.entity.User;

/**
 *
 * @author Özkan SARI
 */
public class StatsJpaController {

    public StatsJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Stats stats) {
        if (stats.getClipCollection() == null) {
            stats.setClipCollection(new ArrayList<Clip>());
        }
        if (stats.getTrackCollection() == null) {
            stats.setTrackCollection(new ArrayList<Track>());
        }
        if (stats.getPhotoCollection() == null) {
            stats.setPhotoCollection(new ArrayList<Photo>());
        }
        if (stats.getRecordCollection() == null) {
            stats.setRecordCollection(new ArrayList<Record>());
        }
        if (stats.getArticleCollection() == null) {
            stats.setArticleCollection(new ArrayList<Article>());
        }
        if (stats.getPhotoAlbumCollection() == null) {
            stats.setPhotoAlbumCollection(new ArrayList<PhotoAlbum>());
        }
        if (stats.getUserCollection() == null) {
            stats.setUserCollection(new ArrayList<User>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Clip> attachedClipCollection = new ArrayList<Clip>();
            for (Clip clipCollectionClipToAttach : stats.getClipCollection()) {
                clipCollectionClipToAttach = em.getReference(clipCollectionClipToAttach.getClass(), clipCollectionClipToAttach.getIdClip());
                attachedClipCollection.add(clipCollectionClipToAttach);
            }
            stats.setClipCollection(attachedClipCollection);
            Collection<Track> attachedTrackCollection = new ArrayList<Track>();
            for (Track trackCollectionTrackToAttach : stats.getTrackCollection()) {
                trackCollectionTrackToAttach = em.getReference(trackCollectionTrackToAttach.getClass(), trackCollectionTrackToAttach.getIdTrack());
                attachedTrackCollection.add(trackCollectionTrackToAttach);
            }
            stats.setTrackCollection(attachedTrackCollection);
            Collection<Photo> attachedPhotoCollection = new ArrayList<Photo>();
            for (Photo photoCollectionPhotoToAttach : stats.getPhotoCollection()) {
                photoCollectionPhotoToAttach = em.getReference(photoCollectionPhotoToAttach.getClass(), photoCollectionPhotoToAttach.getIdPhoto());
                attachedPhotoCollection.add(photoCollectionPhotoToAttach);
            }
            stats.setPhotoCollection(attachedPhotoCollection);
            Collection<Record> attachedRecordCollection = new ArrayList<Record>();
            for (Record recordCollectionRecordToAttach : stats.getRecordCollection()) {
                recordCollectionRecordToAttach = em.getReference(recordCollectionRecordToAttach.getClass(), recordCollectionRecordToAttach.getIdRecord());
                attachedRecordCollection.add(recordCollectionRecordToAttach);
            }
            stats.setRecordCollection(attachedRecordCollection);
            Collection<Article> attachedArticleCollection = new ArrayList<Article>();
            for (Article articleCollectionArticleToAttach : stats.getArticleCollection()) {
                articleCollectionArticleToAttach = em.getReference(articleCollectionArticleToAttach.getClass(), articleCollectionArticleToAttach.getIdArticle());
                attachedArticleCollection.add(articleCollectionArticleToAttach);
            }
            stats.setArticleCollection(attachedArticleCollection);
            Collection<PhotoAlbum> attachedPhotoAlbumCollection = new ArrayList<PhotoAlbum>();
            for (PhotoAlbum photoAlbumCollectionPhotoAlbumToAttach : stats.getPhotoAlbumCollection()) {
                photoAlbumCollectionPhotoAlbumToAttach = em.getReference(photoAlbumCollectionPhotoAlbumToAttach.getClass(), photoAlbumCollectionPhotoAlbumToAttach.getIdPhotoAlbum());
                attachedPhotoAlbumCollection.add(photoAlbumCollectionPhotoAlbumToAttach);
            }
            stats.setPhotoAlbumCollection(attachedPhotoAlbumCollection);
            Collection<User> attachedUserCollection = new ArrayList<User>();
            for (User userCollectionUserToAttach : stats.getUserCollection()) {
                userCollectionUserToAttach = em.getReference(userCollectionUserToAttach.getClass(), userCollectionUserToAttach.getIdUser());
                attachedUserCollection.add(userCollectionUserToAttach);
            }
            stats.setUserCollection(attachedUserCollection);
            em.persist(stats);
            for (Clip clipCollectionClip : stats.getClipCollection()) {
                Stats oldIdStatsFkOfClipCollectionClip = clipCollectionClip.getIdStatsFk();
                clipCollectionClip.setIdStatsFk(stats);
                clipCollectionClip = em.merge(clipCollectionClip);
                if (oldIdStatsFkOfClipCollectionClip != null) {
                    oldIdStatsFkOfClipCollectionClip.getClipCollection().remove(clipCollectionClip);
                    oldIdStatsFkOfClipCollectionClip = em.merge(oldIdStatsFkOfClipCollectionClip);
                }
            }
            for (Track trackCollectionTrack : stats.getTrackCollection()) {
                Stats oldIdStatsFkOfTrackCollectionTrack = trackCollectionTrack.getIdStatsFk();
                trackCollectionTrack.setIdStatsFk(stats);
                trackCollectionTrack = em.merge(trackCollectionTrack);
                if (oldIdStatsFkOfTrackCollectionTrack != null) {
                    oldIdStatsFkOfTrackCollectionTrack.getTrackCollection().remove(trackCollectionTrack);
                    oldIdStatsFkOfTrackCollectionTrack = em.merge(oldIdStatsFkOfTrackCollectionTrack);
                }
            }
            for (Photo photoCollectionPhoto : stats.getPhotoCollection()) {
                Stats oldIdStatsFkOfPhotoCollectionPhoto = photoCollectionPhoto.getIdStatsFk();
                photoCollectionPhoto.setIdStatsFk(stats);
                photoCollectionPhoto = em.merge(photoCollectionPhoto);
                if (oldIdStatsFkOfPhotoCollectionPhoto != null) {
                    oldIdStatsFkOfPhotoCollectionPhoto.getPhotoCollection().remove(photoCollectionPhoto);
                    oldIdStatsFkOfPhotoCollectionPhoto = em.merge(oldIdStatsFkOfPhotoCollectionPhoto);
                }
            }
            for (Record recordCollectionRecord : stats.getRecordCollection()) {
                Stats oldIdStatsFkOfRecordCollectionRecord = recordCollectionRecord.getIdStatsFk();
                recordCollectionRecord.setIdStatsFk(stats);
                recordCollectionRecord = em.merge(recordCollectionRecord);
                if (oldIdStatsFkOfRecordCollectionRecord != null) {
                    oldIdStatsFkOfRecordCollectionRecord.getRecordCollection().remove(recordCollectionRecord);
                    oldIdStatsFkOfRecordCollectionRecord = em.merge(oldIdStatsFkOfRecordCollectionRecord);
                }
            }
            for (Article articleCollectionArticle : stats.getArticleCollection()) {
                Stats oldIdStatsFkOfArticleCollectionArticle = articleCollectionArticle.getIdStatsFk();
                articleCollectionArticle.setIdStatsFk(stats);
                articleCollectionArticle = em.merge(articleCollectionArticle);
                if (oldIdStatsFkOfArticleCollectionArticle != null) {
                    oldIdStatsFkOfArticleCollectionArticle.getArticleCollection().remove(articleCollectionArticle);
                    oldIdStatsFkOfArticleCollectionArticle = em.merge(oldIdStatsFkOfArticleCollectionArticle);
                }
            }
            for (PhotoAlbum photoAlbumCollectionPhotoAlbum : stats.getPhotoAlbumCollection()) {
                Stats oldIdStatsFkOfPhotoAlbumCollectionPhotoAlbum = photoAlbumCollectionPhotoAlbum.getIdStatsFk();
                photoAlbumCollectionPhotoAlbum.setIdStatsFk(stats);
                photoAlbumCollectionPhotoAlbum = em.merge(photoAlbumCollectionPhotoAlbum);
                if (oldIdStatsFkOfPhotoAlbumCollectionPhotoAlbum != null) {
                    oldIdStatsFkOfPhotoAlbumCollectionPhotoAlbum.getPhotoAlbumCollection().remove(photoAlbumCollectionPhotoAlbum);
                    oldIdStatsFkOfPhotoAlbumCollectionPhotoAlbum = em.merge(oldIdStatsFkOfPhotoAlbumCollectionPhotoAlbum);
                }
            }
            for (User userCollectionUser : stats.getUserCollection()) {
                Stats oldIdStatsFkOfUserCollectionUser = userCollectionUser.getIdStatsFk();
                userCollectionUser.setIdStatsFk(stats);
                userCollectionUser = em.merge(userCollectionUser);
                if (oldIdStatsFkOfUserCollectionUser != null) {
                    oldIdStatsFkOfUserCollectionUser.getUserCollection().remove(userCollectionUser);
                    oldIdStatsFkOfUserCollectionUser = em.merge(oldIdStatsFkOfUserCollectionUser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Stats stats) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Stats persistentStats = em.find(Stats.class, stats.getIdStats());
            Collection<Clip> clipCollectionOld = persistentStats.getClipCollection();
            Collection<Clip> clipCollectionNew = stats.getClipCollection();
            Collection<Track> trackCollectionOld = persistentStats.getTrackCollection();
            Collection<Track> trackCollectionNew = stats.getTrackCollection();
            Collection<Photo> photoCollectionOld = persistentStats.getPhotoCollection();
            Collection<Photo> photoCollectionNew = stats.getPhotoCollection();
            Collection<Record> recordCollectionOld = persistentStats.getRecordCollection();
            Collection<Record> recordCollectionNew = stats.getRecordCollection();
            Collection<Article> articleCollectionOld = persistentStats.getArticleCollection();
            Collection<Article> articleCollectionNew = stats.getArticleCollection();
            Collection<PhotoAlbum> photoAlbumCollectionOld = persistentStats.getPhotoAlbumCollection();
            Collection<PhotoAlbum> photoAlbumCollectionNew = stats.getPhotoAlbumCollection();
            Collection<User> userCollectionOld = persistentStats.getUserCollection();
            Collection<User> userCollectionNew = stats.getUserCollection();
            List<String> illegalOrphanMessages = null;
            for (Article articleCollectionOldArticle : articleCollectionOld) {
                if (!articleCollectionNew.contains(articleCollectionOldArticle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Article " + articleCollectionOldArticle + " since its idStatsFk field is not nullable.");
                }
            }
            for (PhotoAlbum photoAlbumCollectionOldPhotoAlbum : photoAlbumCollectionOld) {
                if (!photoAlbumCollectionNew.contains(photoAlbumCollectionOldPhotoAlbum)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PhotoAlbum " + photoAlbumCollectionOldPhotoAlbum + " since its idStatsFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Clip> attachedClipCollectionNew = new ArrayList<Clip>();
            for (Clip clipCollectionNewClipToAttach : clipCollectionNew) {
                clipCollectionNewClipToAttach = em.getReference(clipCollectionNewClipToAttach.getClass(), clipCollectionNewClipToAttach.getIdClip());
                attachedClipCollectionNew.add(clipCollectionNewClipToAttach);
            }
            clipCollectionNew = attachedClipCollectionNew;
            stats.setClipCollection(clipCollectionNew);
            Collection<Track> attachedTrackCollectionNew = new ArrayList<Track>();
            for (Track trackCollectionNewTrackToAttach : trackCollectionNew) {
                trackCollectionNewTrackToAttach = em.getReference(trackCollectionNewTrackToAttach.getClass(), trackCollectionNewTrackToAttach.getIdTrack());
                attachedTrackCollectionNew.add(trackCollectionNewTrackToAttach);
            }
            trackCollectionNew = attachedTrackCollectionNew;
            stats.setTrackCollection(trackCollectionNew);
            Collection<Photo> attachedPhotoCollectionNew = new ArrayList<Photo>();
            for (Photo photoCollectionNewPhotoToAttach : photoCollectionNew) {
                photoCollectionNewPhotoToAttach = em.getReference(photoCollectionNewPhotoToAttach.getClass(), photoCollectionNewPhotoToAttach.getIdPhoto());
                attachedPhotoCollectionNew.add(photoCollectionNewPhotoToAttach);
            }
            photoCollectionNew = attachedPhotoCollectionNew;
            stats.setPhotoCollection(photoCollectionNew);
            Collection<Record> attachedRecordCollectionNew = new ArrayList<Record>();
            for (Record recordCollectionNewRecordToAttach : recordCollectionNew) {
                recordCollectionNewRecordToAttach = em.getReference(recordCollectionNewRecordToAttach.getClass(), recordCollectionNewRecordToAttach.getIdRecord());
                attachedRecordCollectionNew.add(recordCollectionNewRecordToAttach);
            }
            recordCollectionNew = attachedRecordCollectionNew;
            stats.setRecordCollection(recordCollectionNew);
            Collection<Article> attachedArticleCollectionNew = new ArrayList<Article>();
            for (Article articleCollectionNewArticleToAttach : articleCollectionNew) {
                articleCollectionNewArticleToAttach = em.getReference(articleCollectionNewArticleToAttach.getClass(), articleCollectionNewArticleToAttach.getIdArticle());
                attachedArticleCollectionNew.add(articleCollectionNewArticleToAttach);
            }
            articleCollectionNew = attachedArticleCollectionNew;
            stats.setArticleCollection(articleCollectionNew);
            Collection<PhotoAlbum> attachedPhotoAlbumCollectionNew = new ArrayList<PhotoAlbum>();
            for (PhotoAlbum photoAlbumCollectionNewPhotoAlbumToAttach : photoAlbumCollectionNew) {
                photoAlbumCollectionNewPhotoAlbumToAttach = em.getReference(photoAlbumCollectionNewPhotoAlbumToAttach.getClass(), photoAlbumCollectionNewPhotoAlbumToAttach.getIdPhotoAlbum());
                attachedPhotoAlbumCollectionNew.add(photoAlbumCollectionNewPhotoAlbumToAttach);
            }
            photoAlbumCollectionNew = attachedPhotoAlbumCollectionNew;
            stats.setPhotoAlbumCollection(photoAlbumCollectionNew);
            Collection<User> attachedUserCollectionNew = new ArrayList<User>();
            for (User userCollectionNewUserToAttach : userCollectionNew) {
                userCollectionNewUserToAttach = em.getReference(userCollectionNewUserToAttach.getClass(), userCollectionNewUserToAttach.getIdUser());
                attachedUserCollectionNew.add(userCollectionNewUserToAttach);
            }
            userCollectionNew = attachedUserCollectionNew;
            stats.setUserCollection(userCollectionNew);
            stats = em.merge(stats);
            for (Clip clipCollectionOldClip : clipCollectionOld) {
                if (!clipCollectionNew.contains(clipCollectionOldClip)) {
                    clipCollectionOldClip.setIdStatsFk(null);
                    clipCollectionOldClip = em.merge(clipCollectionOldClip);
                }
            }
            for (Clip clipCollectionNewClip : clipCollectionNew) {
                if (!clipCollectionOld.contains(clipCollectionNewClip)) {
                    Stats oldIdStatsFkOfClipCollectionNewClip = clipCollectionNewClip.getIdStatsFk();
                    clipCollectionNewClip.setIdStatsFk(stats);
                    clipCollectionNewClip = em.merge(clipCollectionNewClip);
                    if (oldIdStatsFkOfClipCollectionNewClip != null && !oldIdStatsFkOfClipCollectionNewClip.equals(stats)) {
                        oldIdStatsFkOfClipCollectionNewClip.getClipCollection().remove(clipCollectionNewClip);
                        oldIdStatsFkOfClipCollectionNewClip = em.merge(oldIdStatsFkOfClipCollectionNewClip);
                    }
                }
            }
            for (Track trackCollectionOldTrack : trackCollectionOld) {
                if (!trackCollectionNew.contains(trackCollectionOldTrack)) {
                    trackCollectionOldTrack.setIdStatsFk(null);
                    trackCollectionOldTrack = em.merge(trackCollectionOldTrack);
                }
            }
            for (Track trackCollectionNewTrack : trackCollectionNew) {
                if (!trackCollectionOld.contains(trackCollectionNewTrack)) {
                    Stats oldIdStatsFkOfTrackCollectionNewTrack = trackCollectionNewTrack.getIdStatsFk();
                    trackCollectionNewTrack.setIdStatsFk(stats);
                    trackCollectionNewTrack = em.merge(trackCollectionNewTrack);
                    if (oldIdStatsFkOfTrackCollectionNewTrack != null && !oldIdStatsFkOfTrackCollectionNewTrack.equals(stats)) {
                        oldIdStatsFkOfTrackCollectionNewTrack.getTrackCollection().remove(trackCollectionNewTrack);
                        oldIdStatsFkOfTrackCollectionNewTrack = em.merge(oldIdStatsFkOfTrackCollectionNewTrack);
                    }
                }
            }
            for (Photo photoCollectionOldPhoto : photoCollectionOld) {
                if (!photoCollectionNew.contains(photoCollectionOldPhoto)) {
                    photoCollectionOldPhoto.setIdStatsFk(null);
                    photoCollectionOldPhoto = em.merge(photoCollectionOldPhoto);
                }
            }
            for (Photo photoCollectionNewPhoto : photoCollectionNew) {
                if (!photoCollectionOld.contains(photoCollectionNewPhoto)) {
                    Stats oldIdStatsFkOfPhotoCollectionNewPhoto = photoCollectionNewPhoto.getIdStatsFk();
                    photoCollectionNewPhoto.setIdStatsFk(stats);
                    photoCollectionNewPhoto = em.merge(photoCollectionNewPhoto);
                    if (oldIdStatsFkOfPhotoCollectionNewPhoto != null && !oldIdStatsFkOfPhotoCollectionNewPhoto.equals(stats)) {
                        oldIdStatsFkOfPhotoCollectionNewPhoto.getPhotoCollection().remove(photoCollectionNewPhoto);
                        oldIdStatsFkOfPhotoCollectionNewPhoto = em.merge(oldIdStatsFkOfPhotoCollectionNewPhoto);
                    }
                }
            }
            for (Record recordCollectionOldRecord : recordCollectionOld) {
                if (!recordCollectionNew.contains(recordCollectionOldRecord)) {
                    recordCollectionOldRecord.setIdStatsFk(null);
                    recordCollectionOldRecord = em.merge(recordCollectionOldRecord);
                }
            }
            for (Record recordCollectionNewRecord : recordCollectionNew) {
                if (!recordCollectionOld.contains(recordCollectionNewRecord)) {
                    Stats oldIdStatsFkOfRecordCollectionNewRecord = recordCollectionNewRecord.getIdStatsFk();
                    recordCollectionNewRecord.setIdStatsFk(stats);
                    recordCollectionNewRecord = em.merge(recordCollectionNewRecord);
                    if (oldIdStatsFkOfRecordCollectionNewRecord != null && !oldIdStatsFkOfRecordCollectionNewRecord.equals(stats)) {
                        oldIdStatsFkOfRecordCollectionNewRecord.getRecordCollection().remove(recordCollectionNewRecord);
                        oldIdStatsFkOfRecordCollectionNewRecord = em.merge(oldIdStatsFkOfRecordCollectionNewRecord);
                    }
                }
            }
            for (Article articleCollectionNewArticle : articleCollectionNew) {
                if (!articleCollectionOld.contains(articleCollectionNewArticle)) {
                    Stats oldIdStatsFkOfArticleCollectionNewArticle = articleCollectionNewArticle.getIdStatsFk();
                    articleCollectionNewArticle.setIdStatsFk(stats);
                    articleCollectionNewArticle = em.merge(articleCollectionNewArticle);
                    if (oldIdStatsFkOfArticleCollectionNewArticle != null && !oldIdStatsFkOfArticleCollectionNewArticle.equals(stats)) {
                        oldIdStatsFkOfArticleCollectionNewArticle.getArticleCollection().remove(articleCollectionNewArticle);
                        oldIdStatsFkOfArticleCollectionNewArticle = em.merge(oldIdStatsFkOfArticleCollectionNewArticle);
                    }
                }
            }
            for (PhotoAlbum photoAlbumCollectionNewPhotoAlbum : photoAlbumCollectionNew) {
                if (!photoAlbumCollectionOld.contains(photoAlbumCollectionNewPhotoAlbum)) {
                    Stats oldIdStatsFkOfPhotoAlbumCollectionNewPhotoAlbum = photoAlbumCollectionNewPhotoAlbum.getIdStatsFk();
                    photoAlbumCollectionNewPhotoAlbum.setIdStatsFk(stats);
                    photoAlbumCollectionNewPhotoAlbum = em.merge(photoAlbumCollectionNewPhotoAlbum);
                    if (oldIdStatsFkOfPhotoAlbumCollectionNewPhotoAlbum != null && !oldIdStatsFkOfPhotoAlbumCollectionNewPhotoAlbum.equals(stats)) {
                        oldIdStatsFkOfPhotoAlbumCollectionNewPhotoAlbum.getPhotoAlbumCollection().remove(photoAlbumCollectionNewPhotoAlbum);
                        oldIdStatsFkOfPhotoAlbumCollectionNewPhotoAlbum = em.merge(oldIdStatsFkOfPhotoAlbumCollectionNewPhotoAlbum);
                    }
                }
            }
            for (User userCollectionOldUser : userCollectionOld) {
                if (!userCollectionNew.contains(userCollectionOldUser)) {
                    userCollectionOldUser.setIdStatsFk(null);
                    userCollectionOldUser = em.merge(userCollectionOldUser);
                }
            }
            for (User userCollectionNewUser : userCollectionNew) {
                if (!userCollectionOld.contains(userCollectionNewUser)) {
                    Stats oldIdStatsFkOfUserCollectionNewUser = userCollectionNewUser.getIdStatsFk();
                    userCollectionNewUser.setIdStatsFk(stats);
                    userCollectionNewUser = em.merge(userCollectionNewUser);
                    if (oldIdStatsFkOfUserCollectionNewUser != null && !oldIdStatsFkOfUserCollectionNewUser.equals(stats)) {
                        oldIdStatsFkOfUserCollectionNewUser.getUserCollection().remove(userCollectionNewUser);
                        oldIdStatsFkOfUserCollectionNewUser = em.merge(oldIdStatsFkOfUserCollectionNewUser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = stats.getIdStats();
                if (findStats(id) == null) {
                    throw new NonexistentEntityException("The stats with id " + id + " no longer exists.");
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
            Stats stats;
            try {
                stats = em.getReference(Stats.class, id);
                stats.getIdStats();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The stats with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Article> articleCollectionOrphanCheck = stats.getArticleCollection();
            for (Article articleCollectionOrphanCheckArticle : articleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Stats (" + stats + ") cannot be destroyed since the Article " + articleCollectionOrphanCheckArticle + " in its articleCollection field has a non-nullable idStatsFk field.");
            }
            Collection<PhotoAlbum> photoAlbumCollectionOrphanCheck = stats.getPhotoAlbumCollection();
            for (PhotoAlbum photoAlbumCollectionOrphanCheckPhotoAlbum : photoAlbumCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Stats (" + stats + ") cannot be destroyed since the PhotoAlbum " + photoAlbumCollectionOrphanCheckPhotoAlbum + " in its photoAlbumCollection field has a non-nullable idStatsFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Clip> clipCollection = stats.getClipCollection();
            for (Clip clipCollectionClip : clipCollection) {
                clipCollectionClip.setIdStatsFk(null);
                clipCollectionClip = em.merge(clipCollectionClip);
            }
            Collection<Track> trackCollection = stats.getTrackCollection();
            for (Track trackCollectionTrack : trackCollection) {
                trackCollectionTrack.setIdStatsFk(null);
                trackCollectionTrack = em.merge(trackCollectionTrack);
            }
            Collection<Photo> photoCollection = stats.getPhotoCollection();
            for (Photo photoCollectionPhoto : photoCollection) {
                photoCollectionPhoto.setIdStatsFk(null);
                photoCollectionPhoto = em.merge(photoCollectionPhoto);
            }
            Collection<Record> recordCollection = stats.getRecordCollection();
            for (Record recordCollectionRecord : recordCollection) {
                recordCollectionRecord.setIdStatsFk(null);
                recordCollectionRecord = em.merge(recordCollectionRecord);
            }
            Collection<User> userCollection = stats.getUserCollection();
            for (User userCollectionUser : userCollection) {
                userCollectionUser.setIdStatsFk(null);
                userCollectionUser = em.merge(userCollectionUser);
            }
            em.remove(stats);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Stats> findStatsEntities() {
        return findStatsEntities(true, -1, -1);
    }

    public List<Stats> findStatsEntities(int maxResults, int firstResult) {
        return findStatsEntities(false, maxResults, firstResult);
    }

    private List<Stats> findStatsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Stats as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Stats findStats(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Stats.class, id);
        } finally {
            em.close();
        }
    }

    public int getStatsCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Stats as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
