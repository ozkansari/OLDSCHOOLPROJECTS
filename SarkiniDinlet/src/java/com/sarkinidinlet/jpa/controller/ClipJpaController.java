/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Clip;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Track;
import com.sarkinidinlet.jpa.entity.Stats;
import com.sarkinidinlet.jpa.entity.Artist;

/**
 *
 * @author Özkan SARI
 */
public class ClipJpaController {

    public ClipJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clip clip) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Track idTrackFk = clip.getIdTrackFk();
            if (idTrackFk != null) {
                idTrackFk = em.getReference(idTrackFk.getClass(), idTrackFk.getIdTrack());
                clip.setIdTrackFk(idTrackFk);
            }
            Stats idStatsFk = clip.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk = em.getReference(idStatsFk.getClass(), idStatsFk.getIdStats());
                clip.setIdStatsFk(idStatsFk);
            }
            Artist idArtistFk = clip.getIdArtistFk();
            if (idArtistFk != null) {
                idArtistFk = em.getReference(idArtistFk.getClass(), idArtistFk.getIdArtist());
                clip.setIdArtistFk(idArtistFk);
            }
            em.persist(clip);
            if (idTrackFk != null) {
                idTrackFk.getClipCollection().add(clip);
                idTrackFk = em.merge(idTrackFk);
            }
            if (idStatsFk != null) {
                idStatsFk.getClipCollection().add(clip);
                idStatsFk = em.merge(idStatsFk);
            }
            if (idArtistFk != null) {
                idArtistFk.getClipCollection().add(clip);
                idArtistFk = em.merge(idArtistFk);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clip clip) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clip persistentClip = em.find(Clip.class, clip.getIdClip());
            Track idTrackFkOld = persistentClip.getIdTrackFk();
            Track idTrackFkNew = clip.getIdTrackFk();
            Stats idStatsFkOld = persistentClip.getIdStatsFk();
            Stats idStatsFkNew = clip.getIdStatsFk();
            Artist idArtistFkOld = persistentClip.getIdArtistFk();
            Artist idArtistFkNew = clip.getIdArtistFk();
            if (idTrackFkNew != null) {
                idTrackFkNew = em.getReference(idTrackFkNew.getClass(), idTrackFkNew.getIdTrack());
                clip.setIdTrackFk(idTrackFkNew);
            }
            if (idStatsFkNew != null) {
                idStatsFkNew = em.getReference(idStatsFkNew.getClass(), idStatsFkNew.getIdStats());
                clip.setIdStatsFk(idStatsFkNew);
            }
            if (idArtistFkNew != null) {
                idArtistFkNew = em.getReference(idArtistFkNew.getClass(), idArtistFkNew.getIdArtist());
                clip.setIdArtistFk(idArtistFkNew);
            }
            clip = em.merge(clip);
            if (idTrackFkOld != null && !idTrackFkOld.equals(idTrackFkNew)) {
                idTrackFkOld.getClipCollection().remove(clip);
                idTrackFkOld = em.merge(idTrackFkOld);
            }
            if (idTrackFkNew != null && !idTrackFkNew.equals(idTrackFkOld)) {
                idTrackFkNew.getClipCollection().add(clip);
                idTrackFkNew = em.merge(idTrackFkNew);
            }
            if (idStatsFkOld != null && !idStatsFkOld.equals(idStatsFkNew)) {
                idStatsFkOld.getClipCollection().remove(clip);
                idStatsFkOld = em.merge(idStatsFkOld);
            }
            if (idStatsFkNew != null && !idStatsFkNew.equals(idStatsFkOld)) {
                idStatsFkNew.getClipCollection().add(clip);
                idStatsFkNew = em.merge(idStatsFkNew);
            }
            if (idArtistFkOld != null && !idArtistFkOld.equals(idArtistFkNew)) {
                idArtistFkOld.getClipCollection().remove(clip);
                idArtistFkOld = em.merge(idArtistFkOld);
            }
            if (idArtistFkNew != null && !idArtistFkNew.equals(idArtistFkOld)) {
                idArtistFkNew.getClipCollection().add(clip);
                idArtistFkNew = em.merge(idArtistFkNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clip.getIdClip();
                if (findClip(id) == null) {
                    throw new NonexistentEntityException("The clip with id " + id + " no longer exists.");
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
            Clip clip;
            try {
                clip = em.getReference(Clip.class, id);
                clip.getIdClip();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clip with id " + id + " no longer exists.", enfe);
            }
            Track idTrackFk = clip.getIdTrackFk();
            if (idTrackFk != null) {
                idTrackFk.getClipCollection().remove(clip);
                idTrackFk = em.merge(idTrackFk);
            }
            Stats idStatsFk = clip.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk.getClipCollection().remove(clip);
                idStatsFk = em.merge(idStatsFk);
            }
            Artist idArtistFk = clip.getIdArtistFk();
            if (idArtistFk != null) {
                idArtistFk.getClipCollection().remove(clip);
                idArtistFk = em.merge(idArtistFk);
            }
            em.remove(clip);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clip> findClipEntities() {
        return findClipEntities(true, -1, -1);
    }

    public List<Clip> findClipEntities(int maxResults, int firstResult) {
        return findClipEntities(false, maxResults, firstResult);
    }

    private List<Clip> findClipEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Clip as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Clip findClip(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clip.class, id);
        } finally {
            em.close();
        }
    }

    public int getClipCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Clip as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
