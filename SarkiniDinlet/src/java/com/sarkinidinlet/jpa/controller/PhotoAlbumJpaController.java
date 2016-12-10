/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.IllegalOrphanException;
import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.PhotoAlbum;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Stats;
import com.sarkinidinlet.jpa.entity.Photo;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Özkan SARI
 */
public class PhotoAlbumJpaController {

    public PhotoAlbumJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PhotoAlbum photoAlbum) {
        if (photoAlbum.getPhotoCollection() == null) {
            photoAlbum.setPhotoCollection(new ArrayList<Photo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Stats idStatsFk = photoAlbum.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk = em.getReference(idStatsFk.getClass(), idStatsFk.getIdStats());
                photoAlbum.setIdStatsFk(idStatsFk);
            }
            Collection<Photo> attachedPhotoCollection = new ArrayList<Photo>();
            for (Photo photoCollectionPhotoToAttach : photoAlbum.getPhotoCollection()) {
                photoCollectionPhotoToAttach = em.getReference(photoCollectionPhotoToAttach.getClass(), photoCollectionPhotoToAttach.getIdPhoto());
                attachedPhotoCollection.add(photoCollectionPhotoToAttach);
            }
            photoAlbum.setPhotoCollection(attachedPhotoCollection);
            em.persist(photoAlbum);
            if (idStatsFk != null) {
                idStatsFk.getPhotoAlbumCollection().add(photoAlbum);
                idStatsFk = em.merge(idStatsFk);
            }
            for (Photo photoCollectionPhoto : photoAlbum.getPhotoCollection()) {
                PhotoAlbum oldIdPhotoAlbumFkOfPhotoCollectionPhoto = photoCollectionPhoto.getIdPhotoAlbumFk();
                photoCollectionPhoto.setIdPhotoAlbumFk(photoAlbum);
                photoCollectionPhoto = em.merge(photoCollectionPhoto);
                if (oldIdPhotoAlbumFkOfPhotoCollectionPhoto != null) {
                    oldIdPhotoAlbumFkOfPhotoCollectionPhoto.getPhotoCollection().remove(photoCollectionPhoto);
                    oldIdPhotoAlbumFkOfPhotoCollectionPhoto = em.merge(oldIdPhotoAlbumFkOfPhotoCollectionPhoto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PhotoAlbum photoAlbum) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PhotoAlbum persistentPhotoAlbum = em.find(PhotoAlbum.class, photoAlbum.getIdPhotoAlbum());
            Stats idStatsFkOld = persistentPhotoAlbum.getIdStatsFk();
            Stats idStatsFkNew = photoAlbum.getIdStatsFk();
            Collection<Photo> photoCollectionOld = persistentPhotoAlbum.getPhotoCollection();
            Collection<Photo> photoCollectionNew = photoAlbum.getPhotoCollection();
            List<String> illegalOrphanMessages = null;
            for (Photo photoCollectionOldPhoto : photoCollectionOld) {
                if (!photoCollectionNew.contains(photoCollectionOldPhoto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Photo " + photoCollectionOldPhoto + " since its idPhotoAlbumFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idStatsFkNew != null) {
                idStatsFkNew = em.getReference(idStatsFkNew.getClass(), idStatsFkNew.getIdStats());
                photoAlbum.setIdStatsFk(idStatsFkNew);
            }
            Collection<Photo> attachedPhotoCollectionNew = new ArrayList<Photo>();
            for (Photo photoCollectionNewPhotoToAttach : photoCollectionNew) {
                photoCollectionNewPhotoToAttach = em.getReference(photoCollectionNewPhotoToAttach.getClass(), photoCollectionNewPhotoToAttach.getIdPhoto());
                attachedPhotoCollectionNew.add(photoCollectionNewPhotoToAttach);
            }
            photoCollectionNew = attachedPhotoCollectionNew;
            photoAlbum.setPhotoCollection(photoCollectionNew);
            photoAlbum = em.merge(photoAlbum);
            if (idStatsFkOld != null && !idStatsFkOld.equals(idStatsFkNew)) {
                idStatsFkOld.getPhotoAlbumCollection().remove(photoAlbum);
                idStatsFkOld = em.merge(idStatsFkOld);
            }
            if (idStatsFkNew != null && !idStatsFkNew.equals(idStatsFkOld)) {
                idStatsFkNew.getPhotoAlbumCollection().add(photoAlbum);
                idStatsFkNew = em.merge(idStatsFkNew);
            }
            for (Photo photoCollectionNewPhoto : photoCollectionNew) {
                if (!photoCollectionOld.contains(photoCollectionNewPhoto)) {
                    PhotoAlbum oldIdPhotoAlbumFkOfPhotoCollectionNewPhoto = photoCollectionNewPhoto.getIdPhotoAlbumFk();
                    photoCollectionNewPhoto.setIdPhotoAlbumFk(photoAlbum);
                    photoCollectionNewPhoto = em.merge(photoCollectionNewPhoto);
                    if (oldIdPhotoAlbumFkOfPhotoCollectionNewPhoto != null && !oldIdPhotoAlbumFkOfPhotoCollectionNewPhoto.equals(photoAlbum)) {
                        oldIdPhotoAlbumFkOfPhotoCollectionNewPhoto.getPhotoCollection().remove(photoCollectionNewPhoto);
                        oldIdPhotoAlbumFkOfPhotoCollectionNewPhoto = em.merge(oldIdPhotoAlbumFkOfPhotoCollectionNewPhoto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = photoAlbum.getIdPhotoAlbum();
                if (findPhotoAlbum(id) == null) {
                    throw new NonexistentEntityException("The photoAlbum with id " + id + " no longer exists.");
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
            PhotoAlbum photoAlbum;
            try {
                photoAlbum = em.getReference(PhotoAlbum.class, id);
                photoAlbum.getIdPhotoAlbum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The photoAlbum with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Photo> photoCollectionOrphanCheck = photoAlbum.getPhotoCollection();
            for (Photo photoCollectionOrphanCheckPhoto : photoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PhotoAlbum (" + photoAlbum + ") cannot be destroyed since the Photo " + photoCollectionOrphanCheckPhoto + " in its photoCollection field has a non-nullable idPhotoAlbumFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Stats idStatsFk = photoAlbum.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk.getPhotoAlbumCollection().remove(photoAlbum);
                idStatsFk = em.merge(idStatsFk);
            }
            em.remove(photoAlbum);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PhotoAlbum> findPhotoAlbumEntities() {
        return findPhotoAlbumEntities(true, -1, -1);
    }

    public List<PhotoAlbum> findPhotoAlbumEntities(int maxResults, int firstResult) {
        return findPhotoAlbumEntities(false, maxResults, firstResult);
    }

    private List<PhotoAlbum> findPhotoAlbumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PhotoAlbum as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PhotoAlbum findPhotoAlbum(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PhotoAlbum.class, id);
        } finally {
            em.close();
        }
    }

    public int getPhotoAlbumCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from PhotoAlbum as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
