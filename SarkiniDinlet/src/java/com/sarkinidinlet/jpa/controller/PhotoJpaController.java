/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Photo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Stats;
import com.sarkinidinlet.jpa.entity.PhotoAlbum;
import com.sarkinidinlet.jpa.entity.Record;
import java.util.ArrayList;
import java.util.Collection;
import com.sarkinidinlet.jpa.entity.Artist;

/**
 *
 * @author Özkan SARI
 */
public class PhotoJpaController {

    public PhotoJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Photo photo) {
        if (photo.getRecordCollection() == null) {
            photo.setRecordCollection(new ArrayList<Record>());
        }
        if (photo.getArtistCollection() == null) {
            photo.setArtistCollection(new ArrayList<Artist>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Stats idStatsFk = photo.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk = em.getReference(idStatsFk.getClass(), idStatsFk.getIdStats());
                photo.setIdStatsFk(idStatsFk);
            }
            PhotoAlbum idPhotoAlbumFk = photo.getIdPhotoAlbumFk();
            if (idPhotoAlbumFk != null) {
                idPhotoAlbumFk = em.getReference(idPhotoAlbumFk.getClass(), idPhotoAlbumFk.getIdPhotoAlbum());
                photo.setIdPhotoAlbumFk(idPhotoAlbumFk);
            }
            Collection<Record> attachedRecordCollection = new ArrayList<Record>();
            for (Record recordCollectionRecordToAttach : photo.getRecordCollection()) {
                recordCollectionRecordToAttach = em.getReference(recordCollectionRecordToAttach.getClass(), recordCollectionRecordToAttach.getIdRecord());
                attachedRecordCollection.add(recordCollectionRecordToAttach);
            }
            photo.setRecordCollection(attachedRecordCollection);
            Collection<Artist> attachedArtistCollection = new ArrayList<Artist>();
            for (Artist artistCollectionArtistToAttach : photo.getArtistCollection()) {
                artistCollectionArtistToAttach = em.getReference(artistCollectionArtistToAttach.getClass(), artistCollectionArtistToAttach.getIdArtist());
                attachedArtistCollection.add(artistCollectionArtistToAttach);
            }
            photo.setArtistCollection(attachedArtistCollection);
            em.persist(photo);
            if (idStatsFk != null) {
                idStatsFk.getPhotoCollection().add(photo);
                idStatsFk = em.merge(idStatsFk);
            }
            if (idPhotoAlbumFk != null) {
                idPhotoAlbumFk.getPhotoCollection().add(photo);
                idPhotoAlbumFk = em.merge(idPhotoAlbumFk);
            }
            for (Record recordCollectionRecord : photo.getRecordCollection()) {
                Photo oldIdPhotoFkOfRecordCollectionRecord = recordCollectionRecord.getIdPhotoFk();
                recordCollectionRecord.setIdPhotoFk(photo);
                recordCollectionRecord = em.merge(recordCollectionRecord);
                if (oldIdPhotoFkOfRecordCollectionRecord != null) {
                    oldIdPhotoFkOfRecordCollectionRecord.getRecordCollection().remove(recordCollectionRecord);
                    oldIdPhotoFkOfRecordCollectionRecord = em.merge(oldIdPhotoFkOfRecordCollectionRecord);
                }
            }
            for (Artist artistCollectionArtist : photo.getArtistCollection()) {
                Photo oldIdPhotoFkOfArtistCollectionArtist = artistCollectionArtist.getIdPhotoFk();
                artistCollectionArtist.setIdPhotoFk(photo);
                artistCollectionArtist = em.merge(artistCollectionArtist);
                if (oldIdPhotoFkOfArtistCollectionArtist != null) {
                    oldIdPhotoFkOfArtistCollectionArtist.getArtistCollection().remove(artistCollectionArtist);
                    oldIdPhotoFkOfArtistCollectionArtist = em.merge(oldIdPhotoFkOfArtistCollectionArtist);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Photo photo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Photo persistentPhoto = em.find(Photo.class, photo.getIdPhoto());
            Stats idStatsFkOld = persistentPhoto.getIdStatsFk();
            Stats idStatsFkNew = photo.getIdStatsFk();
            PhotoAlbum idPhotoAlbumFkOld = persistentPhoto.getIdPhotoAlbumFk();
            PhotoAlbum idPhotoAlbumFkNew = photo.getIdPhotoAlbumFk();
            Collection<Record> recordCollectionOld = persistentPhoto.getRecordCollection();
            Collection<Record> recordCollectionNew = photo.getRecordCollection();
            Collection<Artist> artistCollectionOld = persistentPhoto.getArtistCollection();
            Collection<Artist> artistCollectionNew = photo.getArtistCollection();
            if (idStatsFkNew != null) {
                idStatsFkNew = em.getReference(idStatsFkNew.getClass(), idStatsFkNew.getIdStats());
                photo.setIdStatsFk(idStatsFkNew);
            }
            if (idPhotoAlbumFkNew != null) {
                idPhotoAlbumFkNew = em.getReference(idPhotoAlbumFkNew.getClass(), idPhotoAlbumFkNew.getIdPhotoAlbum());
                photo.setIdPhotoAlbumFk(idPhotoAlbumFkNew);
            }
            Collection<Record> attachedRecordCollectionNew = new ArrayList<Record>();
            for (Record recordCollectionNewRecordToAttach : recordCollectionNew) {
                recordCollectionNewRecordToAttach = em.getReference(recordCollectionNewRecordToAttach.getClass(), recordCollectionNewRecordToAttach.getIdRecord());
                attachedRecordCollectionNew.add(recordCollectionNewRecordToAttach);
            }
            recordCollectionNew = attachedRecordCollectionNew;
            photo.setRecordCollection(recordCollectionNew);
            Collection<Artist> attachedArtistCollectionNew = new ArrayList<Artist>();
            for (Artist artistCollectionNewArtistToAttach : artistCollectionNew) {
                artistCollectionNewArtistToAttach = em.getReference(artistCollectionNewArtistToAttach.getClass(), artistCollectionNewArtistToAttach.getIdArtist());
                attachedArtistCollectionNew.add(artistCollectionNewArtistToAttach);
            }
            artistCollectionNew = attachedArtistCollectionNew;
            photo.setArtistCollection(artistCollectionNew);
            photo = em.merge(photo);
            if (idStatsFkOld != null && !idStatsFkOld.equals(idStatsFkNew)) {
                idStatsFkOld.getPhotoCollection().remove(photo);
                idStatsFkOld = em.merge(idStatsFkOld);
            }
            if (idStatsFkNew != null && !idStatsFkNew.equals(idStatsFkOld)) {
                idStatsFkNew.getPhotoCollection().add(photo);
                idStatsFkNew = em.merge(idStatsFkNew);
            }
            if (idPhotoAlbumFkOld != null && !idPhotoAlbumFkOld.equals(idPhotoAlbumFkNew)) {
                idPhotoAlbumFkOld.getPhotoCollection().remove(photo);
                idPhotoAlbumFkOld = em.merge(idPhotoAlbumFkOld);
            }
            if (idPhotoAlbumFkNew != null && !idPhotoAlbumFkNew.equals(idPhotoAlbumFkOld)) {
                idPhotoAlbumFkNew.getPhotoCollection().add(photo);
                idPhotoAlbumFkNew = em.merge(idPhotoAlbumFkNew);
            }
            for (Record recordCollectionOldRecord : recordCollectionOld) {
                if (!recordCollectionNew.contains(recordCollectionOldRecord)) {
                    recordCollectionOldRecord.setIdPhotoFk(null);
                    recordCollectionOldRecord = em.merge(recordCollectionOldRecord);
                }
            }
            for (Record recordCollectionNewRecord : recordCollectionNew) {
                if (!recordCollectionOld.contains(recordCollectionNewRecord)) {
                    Photo oldIdPhotoFkOfRecordCollectionNewRecord = recordCollectionNewRecord.getIdPhotoFk();
                    recordCollectionNewRecord.setIdPhotoFk(photo);
                    recordCollectionNewRecord = em.merge(recordCollectionNewRecord);
                    if (oldIdPhotoFkOfRecordCollectionNewRecord != null && !oldIdPhotoFkOfRecordCollectionNewRecord.equals(photo)) {
                        oldIdPhotoFkOfRecordCollectionNewRecord.getRecordCollection().remove(recordCollectionNewRecord);
                        oldIdPhotoFkOfRecordCollectionNewRecord = em.merge(oldIdPhotoFkOfRecordCollectionNewRecord);
                    }
                }
            }
            for (Artist artistCollectionOldArtist : artistCollectionOld) {
                if (!artistCollectionNew.contains(artistCollectionOldArtist)) {
                    artistCollectionOldArtist.setIdPhotoFk(null);
                    artistCollectionOldArtist = em.merge(artistCollectionOldArtist);
                }
            }
            for (Artist artistCollectionNewArtist : artistCollectionNew) {
                if (!artistCollectionOld.contains(artistCollectionNewArtist)) {
                    Photo oldIdPhotoFkOfArtistCollectionNewArtist = artistCollectionNewArtist.getIdPhotoFk();
                    artistCollectionNewArtist.setIdPhotoFk(photo);
                    artistCollectionNewArtist = em.merge(artistCollectionNewArtist);
                    if (oldIdPhotoFkOfArtistCollectionNewArtist != null && !oldIdPhotoFkOfArtistCollectionNewArtist.equals(photo)) {
                        oldIdPhotoFkOfArtistCollectionNewArtist.getArtistCollection().remove(artistCollectionNewArtist);
                        oldIdPhotoFkOfArtistCollectionNewArtist = em.merge(oldIdPhotoFkOfArtistCollectionNewArtist);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = photo.getIdPhoto();
                if (findPhoto(id) == null) {
                    throw new NonexistentEntityException("The photo with id " + id + " no longer exists.");
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
            Photo photo;
            try {
                photo = em.getReference(Photo.class, id);
                photo.getIdPhoto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The photo with id " + id + " no longer exists.", enfe);
            }
            Stats idStatsFk = photo.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk.getPhotoCollection().remove(photo);
                idStatsFk = em.merge(idStatsFk);
            }
            PhotoAlbum idPhotoAlbumFk = photo.getIdPhotoAlbumFk();
            if (idPhotoAlbumFk != null) {
                idPhotoAlbumFk.getPhotoCollection().remove(photo);
                idPhotoAlbumFk = em.merge(idPhotoAlbumFk);
            }
            Collection<Record> recordCollection = photo.getRecordCollection();
            for (Record recordCollectionRecord : recordCollection) {
                recordCollectionRecord.setIdPhotoFk(null);
                recordCollectionRecord = em.merge(recordCollectionRecord);
            }
            Collection<Artist> artistCollection = photo.getArtistCollection();
            for (Artist artistCollectionArtist : artistCollection) {
                artistCollectionArtist.setIdPhotoFk(null);
                artistCollectionArtist = em.merge(artistCollectionArtist);
            }
            em.remove(photo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Photo> findPhotoEntities() {
        return findPhotoEntities(true, -1, -1);
    }

    public List<Photo> findPhotoEntities(int maxResults, int firstResult) {
        return findPhotoEntities(false, maxResults, firstResult);
    }

    private List<Photo> findPhotoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Photo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Photo findPhoto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Photo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPhotoCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Photo as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
