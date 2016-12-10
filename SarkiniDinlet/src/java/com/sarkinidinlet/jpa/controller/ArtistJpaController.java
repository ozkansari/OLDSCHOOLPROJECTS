/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.IllegalOrphanException;
import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Artist;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Constant;
import com.sarkinidinlet.jpa.entity.Photo;
import com.sarkinidinlet.jpa.entity.Clip;
import java.util.ArrayList;
import java.util.Collection;
import com.sarkinidinlet.jpa.entity.Track;
import com.sarkinidinlet.jpa.entity.Record;
import com.sarkinidinlet.jpa.entity.User;

/**
 *
 * @author Özkan SARI
 */
public class ArtistJpaController {

    public ArtistJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artist artist) {
        if (artist.getClipCollection() == null) {
            artist.setClipCollection(new ArrayList<Clip>());
        }
        if (artist.getTrackCollection() == null) {
            artist.setTrackCollection(new ArrayList<Track>());
        }
        if (artist.getRecordCollection() == null) {
            artist.setRecordCollection(new ArrayList<Record>());
        }
        if (artist.getUserCollection() == null) {
            artist.setUserCollection(new ArrayList<User>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Constant musicTypeFk = artist.getMusicTypeFk();
            if (musicTypeFk != null) {
                musicTypeFk = em.getReference(musicTypeFk.getClass(), musicTypeFk.getIdConstant());
                artist.setMusicTypeFk(musicTypeFk);
            }
            Photo idPhotoFk = artist.getIdPhotoFk();
            if (idPhotoFk != null) {
                idPhotoFk = em.getReference(idPhotoFk.getClass(), idPhotoFk.getIdPhoto());
                artist.setIdPhotoFk(idPhotoFk);
            }
            Collection<Clip> attachedClipCollection = new ArrayList<Clip>();
            for (Clip clipCollectionClipToAttach : artist.getClipCollection()) {
                clipCollectionClipToAttach = em.getReference(clipCollectionClipToAttach.getClass(), clipCollectionClipToAttach.getIdClip());
                attachedClipCollection.add(clipCollectionClipToAttach);
            }
            artist.setClipCollection(attachedClipCollection);
            Collection<Track> attachedTrackCollection = new ArrayList<Track>();
            for (Track trackCollectionTrackToAttach : artist.getTrackCollection()) {
                trackCollectionTrackToAttach = em.getReference(trackCollectionTrackToAttach.getClass(), trackCollectionTrackToAttach.getIdTrack());
                attachedTrackCollection.add(trackCollectionTrackToAttach);
            }
            artist.setTrackCollection(attachedTrackCollection);
            Collection<Record> attachedRecordCollection = new ArrayList<Record>();
            for (Record recordCollectionRecordToAttach : artist.getRecordCollection()) {
                recordCollectionRecordToAttach = em.getReference(recordCollectionRecordToAttach.getClass(), recordCollectionRecordToAttach.getIdRecord());
                attachedRecordCollection.add(recordCollectionRecordToAttach);
            }
            artist.setRecordCollection(attachedRecordCollection);
            Collection<User> attachedUserCollection = new ArrayList<User>();
            for (User userCollectionUserToAttach : artist.getUserCollection()) {
                userCollectionUserToAttach = em.getReference(userCollectionUserToAttach.getClass(), userCollectionUserToAttach.getIdUser());
                attachedUserCollection.add(userCollectionUserToAttach);
            }
            artist.setUserCollection(attachedUserCollection);
            em.persist(artist);
            if (musicTypeFk != null) {
                musicTypeFk.getArtistCollection().add(artist);
                musicTypeFk = em.merge(musicTypeFk);
            }
            if (idPhotoFk != null) {
                idPhotoFk.getArtistCollection().add(artist);
                idPhotoFk = em.merge(idPhotoFk);
            }
            for (Clip clipCollectionClip : artist.getClipCollection()) {
                Artist oldIdArtistFkOfClipCollectionClip = clipCollectionClip.getIdArtistFk();
                clipCollectionClip.setIdArtistFk(artist);
                clipCollectionClip = em.merge(clipCollectionClip);
                if (oldIdArtistFkOfClipCollectionClip != null) {
                    oldIdArtistFkOfClipCollectionClip.getClipCollection().remove(clipCollectionClip);
                    oldIdArtistFkOfClipCollectionClip = em.merge(oldIdArtistFkOfClipCollectionClip);
                }
            }
            for (Track trackCollectionTrack : artist.getTrackCollection()) {
                Artist oldIdArtistFkOfTrackCollectionTrack = trackCollectionTrack.getIdArtistFk();
                trackCollectionTrack.setIdArtistFk(artist);
                trackCollectionTrack = em.merge(trackCollectionTrack);
                if (oldIdArtistFkOfTrackCollectionTrack != null) {
                    oldIdArtistFkOfTrackCollectionTrack.getTrackCollection().remove(trackCollectionTrack);
                    oldIdArtistFkOfTrackCollectionTrack = em.merge(oldIdArtistFkOfTrackCollectionTrack);
                }
            }
            for (Record recordCollectionRecord : artist.getRecordCollection()) {
                Artist oldIdArtistFkOfRecordCollectionRecord = recordCollectionRecord.getIdArtistFk();
                recordCollectionRecord.setIdArtistFk(artist);
                recordCollectionRecord = em.merge(recordCollectionRecord);
                if (oldIdArtistFkOfRecordCollectionRecord != null) {
                    oldIdArtistFkOfRecordCollectionRecord.getRecordCollection().remove(recordCollectionRecord);
                    oldIdArtistFkOfRecordCollectionRecord = em.merge(oldIdArtistFkOfRecordCollectionRecord);
                }
            }
            for (User userCollectionUser : artist.getUserCollection()) {
                Artist oldIdArtistFkOfUserCollectionUser = userCollectionUser.getIdArtistFk();
                userCollectionUser.setIdArtistFk(artist);
                userCollectionUser = em.merge(userCollectionUser);
                if (oldIdArtistFkOfUserCollectionUser != null) {
                    oldIdArtistFkOfUserCollectionUser.getUserCollection().remove(userCollectionUser);
                    oldIdArtistFkOfUserCollectionUser = em.merge(oldIdArtistFkOfUserCollectionUser);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artist artist) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artist persistentArtist = em.find(Artist.class, artist.getIdArtist());
            Constant musicTypeFkOld = persistentArtist.getMusicTypeFk();
            Constant musicTypeFkNew = artist.getMusicTypeFk();
            Photo idPhotoFkOld = persistentArtist.getIdPhotoFk();
            Photo idPhotoFkNew = artist.getIdPhotoFk();
            Collection<Clip> clipCollectionOld = persistentArtist.getClipCollection();
            Collection<Clip> clipCollectionNew = artist.getClipCollection();
            Collection<Track> trackCollectionOld = persistentArtist.getTrackCollection();
            Collection<Track> trackCollectionNew = artist.getTrackCollection();
            Collection<Record> recordCollectionOld = persistentArtist.getRecordCollection();
            Collection<Record> recordCollectionNew = artist.getRecordCollection();
            Collection<User> userCollectionOld = persistentArtist.getUserCollection();
            Collection<User> userCollectionNew = artist.getUserCollection();
            List<String> illegalOrphanMessages = null;
            for (Clip clipCollectionOldClip : clipCollectionOld) {
                if (!clipCollectionNew.contains(clipCollectionOldClip)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Clip " + clipCollectionOldClip + " since its idArtistFk field is not nullable.");
                }
            }
            for (Track trackCollectionOldTrack : trackCollectionOld) {
                if (!trackCollectionNew.contains(trackCollectionOldTrack)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Track " + trackCollectionOldTrack + " since its idArtistFk field is not nullable.");
                }
            }
            for (Record recordCollectionOldRecord : recordCollectionOld) {
                if (!recordCollectionNew.contains(recordCollectionOldRecord)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Record " + recordCollectionOldRecord + " since its idArtistFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (musicTypeFkNew != null) {
                musicTypeFkNew = em.getReference(musicTypeFkNew.getClass(), musicTypeFkNew.getIdConstant());
                artist.setMusicTypeFk(musicTypeFkNew);
            }
            if (idPhotoFkNew != null) {
                idPhotoFkNew = em.getReference(idPhotoFkNew.getClass(), idPhotoFkNew.getIdPhoto());
                artist.setIdPhotoFk(idPhotoFkNew);
            }
            Collection<Clip> attachedClipCollectionNew = new ArrayList<Clip>();
            for (Clip clipCollectionNewClipToAttach : clipCollectionNew) {
                clipCollectionNewClipToAttach = em.getReference(clipCollectionNewClipToAttach.getClass(), clipCollectionNewClipToAttach.getIdClip());
                attachedClipCollectionNew.add(clipCollectionNewClipToAttach);
            }
            clipCollectionNew = attachedClipCollectionNew;
            artist.setClipCollection(clipCollectionNew);
            Collection<Track> attachedTrackCollectionNew = new ArrayList<Track>();
            for (Track trackCollectionNewTrackToAttach : trackCollectionNew) {
                trackCollectionNewTrackToAttach = em.getReference(trackCollectionNewTrackToAttach.getClass(), trackCollectionNewTrackToAttach.getIdTrack());
                attachedTrackCollectionNew.add(trackCollectionNewTrackToAttach);
            }
            trackCollectionNew = attachedTrackCollectionNew;
            artist.setTrackCollection(trackCollectionNew);
            Collection<Record> attachedRecordCollectionNew = new ArrayList<Record>();
            for (Record recordCollectionNewRecordToAttach : recordCollectionNew) {
                recordCollectionNewRecordToAttach = em.getReference(recordCollectionNewRecordToAttach.getClass(), recordCollectionNewRecordToAttach.getIdRecord());
                attachedRecordCollectionNew.add(recordCollectionNewRecordToAttach);
            }
            recordCollectionNew = attachedRecordCollectionNew;
            artist.setRecordCollection(recordCollectionNew);
            Collection<User> attachedUserCollectionNew = new ArrayList<User>();
            for (User userCollectionNewUserToAttach : userCollectionNew) {
                userCollectionNewUserToAttach = em.getReference(userCollectionNewUserToAttach.getClass(), userCollectionNewUserToAttach.getIdUser());
                attachedUserCollectionNew.add(userCollectionNewUserToAttach);
            }
            userCollectionNew = attachedUserCollectionNew;
            artist.setUserCollection(userCollectionNew);
            artist = em.merge(artist);
            if (musicTypeFkOld != null && !musicTypeFkOld.equals(musicTypeFkNew)) {
                musicTypeFkOld.getArtistCollection().remove(artist);
                musicTypeFkOld = em.merge(musicTypeFkOld);
            }
            if (musicTypeFkNew != null && !musicTypeFkNew.equals(musicTypeFkOld)) {
                musicTypeFkNew.getArtistCollection().add(artist);
                musicTypeFkNew = em.merge(musicTypeFkNew);
            }
            if (idPhotoFkOld != null && !idPhotoFkOld.equals(idPhotoFkNew)) {
                idPhotoFkOld.getArtistCollection().remove(artist);
                idPhotoFkOld = em.merge(idPhotoFkOld);
            }
            if (idPhotoFkNew != null && !idPhotoFkNew.equals(idPhotoFkOld)) {
                idPhotoFkNew.getArtistCollection().add(artist);
                idPhotoFkNew = em.merge(idPhotoFkNew);
            }
            for (Clip clipCollectionNewClip : clipCollectionNew) {
                if (!clipCollectionOld.contains(clipCollectionNewClip)) {
                    Artist oldIdArtistFkOfClipCollectionNewClip = clipCollectionNewClip.getIdArtistFk();
                    clipCollectionNewClip.setIdArtistFk(artist);
                    clipCollectionNewClip = em.merge(clipCollectionNewClip);
                    if (oldIdArtistFkOfClipCollectionNewClip != null && !oldIdArtistFkOfClipCollectionNewClip.equals(artist)) {
                        oldIdArtistFkOfClipCollectionNewClip.getClipCollection().remove(clipCollectionNewClip);
                        oldIdArtistFkOfClipCollectionNewClip = em.merge(oldIdArtistFkOfClipCollectionNewClip);
                    }
                }
            }
            for (Track trackCollectionNewTrack : trackCollectionNew) {
                if (!trackCollectionOld.contains(trackCollectionNewTrack)) {
                    Artist oldIdArtistFkOfTrackCollectionNewTrack = trackCollectionNewTrack.getIdArtistFk();
                    trackCollectionNewTrack.setIdArtistFk(artist);
                    trackCollectionNewTrack = em.merge(trackCollectionNewTrack);
                    if (oldIdArtistFkOfTrackCollectionNewTrack != null && !oldIdArtistFkOfTrackCollectionNewTrack.equals(artist)) {
                        oldIdArtistFkOfTrackCollectionNewTrack.getTrackCollection().remove(trackCollectionNewTrack);
                        oldIdArtistFkOfTrackCollectionNewTrack = em.merge(oldIdArtistFkOfTrackCollectionNewTrack);
                    }
                }
            }
            for (Record recordCollectionNewRecord : recordCollectionNew) {
                if (!recordCollectionOld.contains(recordCollectionNewRecord)) {
                    Artist oldIdArtistFkOfRecordCollectionNewRecord = recordCollectionNewRecord.getIdArtistFk();
                    recordCollectionNewRecord.setIdArtistFk(artist);
                    recordCollectionNewRecord = em.merge(recordCollectionNewRecord);
                    if (oldIdArtistFkOfRecordCollectionNewRecord != null && !oldIdArtistFkOfRecordCollectionNewRecord.equals(artist)) {
                        oldIdArtistFkOfRecordCollectionNewRecord.getRecordCollection().remove(recordCollectionNewRecord);
                        oldIdArtistFkOfRecordCollectionNewRecord = em.merge(oldIdArtistFkOfRecordCollectionNewRecord);
                    }
                }
            }
            for (User userCollectionOldUser : userCollectionOld) {
                if (!userCollectionNew.contains(userCollectionOldUser)) {
                    userCollectionOldUser.setIdArtistFk(null);
                    userCollectionOldUser = em.merge(userCollectionOldUser);
                }
            }
            for (User userCollectionNewUser : userCollectionNew) {
                if (!userCollectionOld.contains(userCollectionNewUser)) {
                    Artist oldIdArtistFkOfUserCollectionNewUser = userCollectionNewUser.getIdArtistFk();
                    userCollectionNewUser.setIdArtistFk(artist);
                    userCollectionNewUser = em.merge(userCollectionNewUser);
                    if (oldIdArtistFkOfUserCollectionNewUser != null && !oldIdArtistFkOfUserCollectionNewUser.equals(artist)) {
                        oldIdArtistFkOfUserCollectionNewUser.getUserCollection().remove(userCollectionNewUser);
                        oldIdArtistFkOfUserCollectionNewUser = em.merge(oldIdArtistFkOfUserCollectionNewUser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = artist.getIdArtist();
                if (findArtist(id) == null) {
                    throw new NonexistentEntityException("The artist with id " + id + " no longer exists.");
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
            Artist artist;
            try {
                artist = em.getReference(Artist.class, id);
                artist.getIdArtist();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artist with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Clip> clipCollectionOrphanCheck = artist.getClipCollection();
            for (Clip clipCollectionOrphanCheckClip : clipCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Artist (" + artist + ") cannot be destroyed since the Clip " + clipCollectionOrphanCheckClip + " in its clipCollection field has a non-nullable idArtistFk field.");
            }
            Collection<Track> trackCollectionOrphanCheck = artist.getTrackCollection();
            for (Track trackCollectionOrphanCheckTrack : trackCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Artist (" + artist + ") cannot be destroyed since the Track " + trackCollectionOrphanCheckTrack + " in its trackCollection field has a non-nullable idArtistFk field.");
            }
            Collection<Record> recordCollectionOrphanCheck = artist.getRecordCollection();
            for (Record recordCollectionOrphanCheckRecord : recordCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Artist (" + artist + ") cannot be destroyed since the Record " + recordCollectionOrphanCheckRecord + " in its recordCollection field has a non-nullable idArtistFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Constant musicTypeFk = artist.getMusicTypeFk();
            if (musicTypeFk != null) {
                musicTypeFk.getArtistCollection().remove(artist);
                musicTypeFk = em.merge(musicTypeFk);
            }
            Photo idPhotoFk = artist.getIdPhotoFk();
            if (idPhotoFk != null) {
                idPhotoFk.getArtistCollection().remove(artist);
                idPhotoFk = em.merge(idPhotoFk);
            }
            Collection<User> userCollection = artist.getUserCollection();
            for (User userCollectionUser : userCollection) {
                userCollectionUser.setIdArtistFk(null);
                userCollectionUser = em.merge(userCollectionUser);
            }
            em.remove(artist);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Artist> findArtistEntities() {
        return findArtistEntities(true, -1, -1);
    }

    public List<Artist> findArtistEntities(int maxResults, int firstResult) {
        return findArtistEntities(false, maxResults, firstResult);
    }

    private List<Artist> findArtistEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Artist as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Artist findArtist(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artist.class, id);
        } finally {
            em.close();
        }
    }

    public int getArtistCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Artist as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
