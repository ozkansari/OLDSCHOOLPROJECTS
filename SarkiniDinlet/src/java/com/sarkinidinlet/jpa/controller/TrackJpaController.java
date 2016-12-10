/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.IllegalOrphanException;
import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Track;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Record;
import com.sarkinidinlet.jpa.entity.Artist;
import com.sarkinidinlet.jpa.entity.Stats;
import com.sarkinidinlet.jpa.entity.Clip;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Özkan SARI
 */
public class TrackJpaController {

    public TrackJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Track track) {
        if (track.getClipCollection() == null) {
            track.setClipCollection(new ArrayList<Clip>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Record idRecordFk = track.getIdRecordFk();
            if (idRecordFk != null) {
                idRecordFk = em.getReference(idRecordFk.getClass(), idRecordFk.getIdRecord());
                track.setIdRecordFk(idRecordFk);
            }
            Artist idArtistFk = track.getIdArtistFk();
            if (idArtistFk != null) {
                idArtistFk = em.getReference(idArtistFk.getClass(), idArtistFk.getIdArtist());
                track.setIdArtistFk(idArtistFk);
            }
            Stats idStatsFk = track.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk = em.getReference(idStatsFk.getClass(), idStatsFk.getIdStats());
                track.setIdStatsFk(idStatsFk);
            }
            Collection<Clip> attachedClipCollection = new ArrayList<Clip>();
            for (Clip clipCollectionClipToAttach : track.getClipCollection()) {
                clipCollectionClipToAttach = em.getReference(clipCollectionClipToAttach.getClass(), clipCollectionClipToAttach.getIdClip());
                attachedClipCollection.add(clipCollectionClipToAttach);
            }
            track.setClipCollection(attachedClipCollection);
            em.persist(track);
            if (idRecordFk != null) {
                idRecordFk.getTrackCollection().add(track);
                idRecordFk = em.merge(idRecordFk);
            }
            if (idArtistFk != null) {
                idArtistFk.getTrackCollection().add(track);
                idArtistFk = em.merge(idArtistFk);
            }
            if (idStatsFk != null) {
                idStatsFk.getTrackCollection().add(track);
                idStatsFk = em.merge(idStatsFk);
            }
            for (Clip clipCollectionClip : track.getClipCollection()) {
                Track oldIdTrackFkOfClipCollectionClip = clipCollectionClip.getIdTrackFk();
                clipCollectionClip.setIdTrackFk(track);
                clipCollectionClip = em.merge(clipCollectionClip);
                if (oldIdTrackFkOfClipCollectionClip != null) {
                    oldIdTrackFkOfClipCollectionClip.getClipCollection().remove(clipCollectionClip);
                    oldIdTrackFkOfClipCollectionClip = em.merge(oldIdTrackFkOfClipCollectionClip);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Track track) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Track persistentTrack = em.find(Track.class, track.getIdTrack());
            Record idRecordFkOld = persistentTrack.getIdRecordFk();
            Record idRecordFkNew = track.getIdRecordFk();
            Artist idArtistFkOld = persistentTrack.getIdArtistFk();
            Artist idArtistFkNew = track.getIdArtistFk();
            Stats idStatsFkOld = persistentTrack.getIdStatsFk();
            Stats idStatsFkNew = track.getIdStatsFk();
            Collection<Clip> clipCollectionOld = persistentTrack.getClipCollection();
            Collection<Clip> clipCollectionNew = track.getClipCollection();
            List<String> illegalOrphanMessages = null;
            for (Clip clipCollectionOldClip : clipCollectionOld) {
                if (!clipCollectionNew.contains(clipCollectionOldClip)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Clip " + clipCollectionOldClip + " since its idTrackFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRecordFkNew != null) {
                idRecordFkNew = em.getReference(idRecordFkNew.getClass(), idRecordFkNew.getIdRecord());
                track.setIdRecordFk(idRecordFkNew);
            }
            if (idArtistFkNew != null) {
                idArtistFkNew = em.getReference(idArtistFkNew.getClass(), idArtistFkNew.getIdArtist());
                track.setIdArtistFk(idArtistFkNew);
            }
            if (idStatsFkNew != null) {
                idStatsFkNew = em.getReference(idStatsFkNew.getClass(), idStatsFkNew.getIdStats());
                track.setIdStatsFk(idStatsFkNew);
            }
            Collection<Clip> attachedClipCollectionNew = new ArrayList<Clip>();
            for (Clip clipCollectionNewClipToAttach : clipCollectionNew) {
                clipCollectionNewClipToAttach = em.getReference(clipCollectionNewClipToAttach.getClass(), clipCollectionNewClipToAttach.getIdClip());
                attachedClipCollectionNew.add(clipCollectionNewClipToAttach);
            }
            clipCollectionNew = attachedClipCollectionNew;
            track.setClipCollection(clipCollectionNew);
            track = em.merge(track);
            if (idRecordFkOld != null && !idRecordFkOld.equals(idRecordFkNew)) {
                idRecordFkOld.getTrackCollection().remove(track);
                idRecordFkOld = em.merge(idRecordFkOld);
            }
            if (idRecordFkNew != null && !idRecordFkNew.equals(idRecordFkOld)) {
                idRecordFkNew.getTrackCollection().add(track);
                idRecordFkNew = em.merge(idRecordFkNew);
            }
            if (idArtistFkOld != null && !idArtistFkOld.equals(idArtistFkNew)) {
                idArtistFkOld.getTrackCollection().remove(track);
                idArtistFkOld = em.merge(idArtistFkOld);
            }
            if (idArtistFkNew != null && !idArtistFkNew.equals(idArtistFkOld)) {
                idArtistFkNew.getTrackCollection().add(track);
                idArtistFkNew = em.merge(idArtistFkNew);
            }
            if (idStatsFkOld != null && !idStatsFkOld.equals(idStatsFkNew)) {
                idStatsFkOld.getTrackCollection().remove(track);
                idStatsFkOld = em.merge(idStatsFkOld);
            }
            if (idStatsFkNew != null && !idStatsFkNew.equals(idStatsFkOld)) {
                idStatsFkNew.getTrackCollection().add(track);
                idStatsFkNew = em.merge(idStatsFkNew);
            }
            for (Clip clipCollectionNewClip : clipCollectionNew) {
                if (!clipCollectionOld.contains(clipCollectionNewClip)) {
                    Track oldIdTrackFkOfClipCollectionNewClip = clipCollectionNewClip.getIdTrackFk();
                    clipCollectionNewClip.setIdTrackFk(track);
                    clipCollectionNewClip = em.merge(clipCollectionNewClip);
                    if (oldIdTrackFkOfClipCollectionNewClip != null && !oldIdTrackFkOfClipCollectionNewClip.equals(track)) {
                        oldIdTrackFkOfClipCollectionNewClip.getClipCollection().remove(clipCollectionNewClip);
                        oldIdTrackFkOfClipCollectionNewClip = em.merge(oldIdTrackFkOfClipCollectionNewClip);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = track.getIdTrack();
                if (findTrack(id) == null) {
                    throw new NonexistentEntityException("The track with id " + id + " no longer exists.");
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
            Track track;
            try {
                track = em.getReference(Track.class, id);
                track.getIdTrack();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The track with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Clip> clipCollectionOrphanCheck = track.getClipCollection();
            for (Clip clipCollectionOrphanCheckClip : clipCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Track (" + track + ") cannot be destroyed since the Clip " + clipCollectionOrphanCheckClip + " in its clipCollection field has a non-nullable idTrackFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Record idRecordFk = track.getIdRecordFk();
            if (idRecordFk != null) {
                idRecordFk.getTrackCollection().remove(track);
                idRecordFk = em.merge(idRecordFk);
            }
            Artist idArtistFk = track.getIdArtistFk();
            if (idArtistFk != null) {
                idArtistFk.getTrackCollection().remove(track);
                idArtistFk = em.merge(idArtistFk);
            }
            Stats idStatsFk = track.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk.getTrackCollection().remove(track);
                idStatsFk = em.merge(idStatsFk);
            }
            em.remove(track);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Track> findTrackEntities() {
        return findTrackEntities(true, -1, -1);
    }

    public List<Track> findTrackEntities(int maxResults, int firstResult) {
        return findTrackEntities(false, maxResults, firstResult);
    }

    private List<Track> findTrackEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Track as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Track findTrack(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Track.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrackCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Track as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
