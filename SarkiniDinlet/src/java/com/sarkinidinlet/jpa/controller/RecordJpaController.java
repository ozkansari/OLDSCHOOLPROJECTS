/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Record;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Artist;
import com.sarkinidinlet.jpa.entity.Stats;
import com.sarkinidinlet.jpa.entity.Photo;
import com.sarkinidinlet.jpa.entity.Track;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Özkan SARI
 */
public class RecordJpaController {

    public RecordJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Record record) {
        if (record.getTrackCollection() == null) {
            record.setTrackCollection(new ArrayList<Track>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artist idArtistFk = record.getIdArtistFk();
            if (idArtistFk != null) {
                idArtistFk = em.getReference(idArtistFk.getClass(), idArtistFk.getIdArtist());
                record.setIdArtistFk(idArtistFk);
            }
            Stats idStatsFk = record.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk = em.getReference(idStatsFk.getClass(), idStatsFk.getIdStats());
                record.setIdStatsFk(idStatsFk);
            }
            Photo idPhotoFk = record.getIdPhotoFk();
            if (idPhotoFk != null) {
                idPhotoFk = em.getReference(idPhotoFk.getClass(), idPhotoFk.getIdPhoto());
                record.setIdPhotoFk(idPhotoFk);
            }
            Collection<Track> attachedTrackCollection = new ArrayList<Track>();
            for (Track trackCollectionTrackToAttach : record.getTrackCollection()) {
                trackCollectionTrackToAttach = em.getReference(trackCollectionTrackToAttach.getClass(), trackCollectionTrackToAttach.getIdTrack());
                attachedTrackCollection.add(trackCollectionTrackToAttach);
            }
            record.setTrackCollection(attachedTrackCollection);
            em.persist(record);
            if (idArtistFk != null) {
                idArtistFk.getRecordCollection().add(record);
                idArtistFk = em.merge(idArtistFk);
            }
            if (idStatsFk != null) {
                idStatsFk.getRecordCollection().add(record);
                idStatsFk = em.merge(idStatsFk);
            }
            if (idPhotoFk != null) {
                idPhotoFk.getRecordCollection().add(record);
                idPhotoFk = em.merge(idPhotoFk);
            }
            for (Track trackCollectionTrack : record.getTrackCollection()) {
                Record oldIdRecordFkOfTrackCollectionTrack = trackCollectionTrack.getIdRecordFk();
                trackCollectionTrack.setIdRecordFk(record);
                trackCollectionTrack = em.merge(trackCollectionTrack);
                if (oldIdRecordFkOfTrackCollectionTrack != null) {
                    oldIdRecordFkOfTrackCollectionTrack.getTrackCollection().remove(trackCollectionTrack);
                    oldIdRecordFkOfTrackCollectionTrack = em.merge(oldIdRecordFkOfTrackCollectionTrack);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Record record) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Record persistentRecord = em.find(Record.class, record.getIdRecord());
            Artist idArtistFkOld = persistentRecord.getIdArtistFk();
            Artist idArtistFkNew = record.getIdArtistFk();
            Stats idStatsFkOld = persistentRecord.getIdStatsFk();
            Stats idStatsFkNew = record.getIdStatsFk();
            Photo idPhotoFkOld = persistentRecord.getIdPhotoFk();
            Photo idPhotoFkNew = record.getIdPhotoFk();
            Collection<Track> trackCollectionOld = persistentRecord.getTrackCollection();
            Collection<Track> trackCollectionNew = record.getTrackCollection();
            if (idArtistFkNew != null) {
                idArtistFkNew = em.getReference(idArtistFkNew.getClass(), idArtistFkNew.getIdArtist());
                record.setIdArtistFk(idArtistFkNew);
            }
            if (idStatsFkNew != null) {
                idStatsFkNew = em.getReference(idStatsFkNew.getClass(), idStatsFkNew.getIdStats());
                record.setIdStatsFk(idStatsFkNew);
            }
            if (idPhotoFkNew != null) {
                idPhotoFkNew = em.getReference(idPhotoFkNew.getClass(), idPhotoFkNew.getIdPhoto());
                record.setIdPhotoFk(idPhotoFkNew);
            }
            Collection<Track> attachedTrackCollectionNew = new ArrayList<Track>();
            for (Track trackCollectionNewTrackToAttach : trackCollectionNew) {
                trackCollectionNewTrackToAttach = em.getReference(trackCollectionNewTrackToAttach.getClass(), trackCollectionNewTrackToAttach.getIdTrack());
                attachedTrackCollectionNew.add(trackCollectionNewTrackToAttach);
            }
            trackCollectionNew = attachedTrackCollectionNew;
            record.setTrackCollection(trackCollectionNew);
            record = em.merge(record);
            if (idArtistFkOld != null && !idArtistFkOld.equals(idArtistFkNew)) {
                idArtistFkOld.getRecordCollection().remove(record);
                idArtistFkOld = em.merge(idArtistFkOld);
            }
            if (idArtistFkNew != null && !idArtistFkNew.equals(idArtistFkOld)) {
                idArtistFkNew.getRecordCollection().add(record);
                idArtistFkNew = em.merge(idArtistFkNew);
            }
            if (idStatsFkOld != null && !idStatsFkOld.equals(idStatsFkNew)) {
                idStatsFkOld.getRecordCollection().remove(record);
                idStatsFkOld = em.merge(idStatsFkOld);
            }
            if (idStatsFkNew != null && !idStatsFkNew.equals(idStatsFkOld)) {
                idStatsFkNew.getRecordCollection().add(record);
                idStatsFkNew = em.merge(idStatsFkNew);
            }
            if (idPhotoFkOld != null && !idPhotoFkOld.equals(idPhotoFkNew)) {
                idPhotoFkOld.getRecordCollection().remove(record);
                idPhotoFkOld = em.merge(idPhotoFkOld);
            }
            if (idPhotoFkNew != null && !idPhotoFkNew.equals(idPhotoFkOld)) {
                idPhotoFkNew.getRecordCollection().add(record);
                idPhotoFkNew = em.merge(idPhotoFkNew);
            }
            for (Track trackCollectionOldTrack : trackCollectionOld) {
                if (!trackCollectionNew.contains(trackCollectionOldTrack)) {
                    trackCollectionOldTrack.setIdRecordFk(null);
                    trackCollectionOldTrack = em.merge(trackCollectionOldTrack);
                }
            }
            for (Track trackCollectionNewTrack : trackCollectionNew) {
                if (!trackCollectionOld.contains(trackCollectionNewTrack)) {
                    Record oldIdRecordFkOfTrackCollectionNewTrack = trackCollectionNewTrack.getIdRecordFk();
                    trackCollectionNewTrack.setIdRecordFk(record);
                    trackCollectionNewTrack = em.merge(trackCollectionNewTrack);
                    if (oldIdRecordFkOfTrackCollectionNewTrack != null && !oldIdRecordFkOfTrackCollectionNewTrack.equals(record)) {
                        oldIdRecordFkOfTrackCollectionNewTrack.getTrackCollection().remove(trackCollectionNewTrack);
                        oldIdRecordFkOfTrackCollectionNewTrack = em.merge(oldIdRecordFkOfTrackCollectionNewTrack);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = record.getIdRecord();
                if (findRecord(id) == null) {
                    throw new NonexistentEntityException("The record with id " + id + " no longer exists.");
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
            Record record;
            try {
                record = em.getReference(Record.class, id);
                record.getIdRecord();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The record with id " + id + " no longer exists.", enfe);
            }
            Artist idArtistFk = record.getIdArtistFk();
            if (idArtistFk != null) {
                idArtistFk.getRecordCollection().remove(record);
                idArtistFk = em.merge(idArtistFk);
            }
            Stats idStatsFk = record.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk.getRecordCollection().remove(record);
                idStatsFk = em.merge(idStatsFk);
            }
            Photo idPhotoFk = record.getIdPhotoFk();
            if (idPhotoFk != null) {
                idPhotoFk.getRecordCollection().remove(record);
                idPhotoFk = em.merge(idPhotoFk);
            }
            Collection<Track> trackCollection = record.getTrackCollection();
            for (Track trackCollectionTrack : trackCollection) {
                trackCollectionTrack.setIdRecordFk(null);
                trackCollectionTrack = em.merge(trackCollectionTrack);
            }
            em.remove(record);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Record> findRecordEntities() {
        return findRecordEntities(true, -1, -1);
    }

    public List<Record> findRecordEntities(int maxResults, int firstResult) {
        return findRecordEntities(false, maxResults, firstResult);
    }

    private List<Record> findRecordEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Record as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Record findRecord(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Record.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecordCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Record as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
