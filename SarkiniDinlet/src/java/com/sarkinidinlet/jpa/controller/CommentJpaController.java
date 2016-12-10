/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Comment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.User;
import com.sarkinidinlet.jpa.entity.ConstantType;

/**
 *
 * @author Özkan SARI
 */
public class CommentJpaController {

    public CommentJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comment comment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User idCreatorFk = comment.getIdCreatorFk();
            if (idCreatorFk != null) {
                idCreatorFk = em.getReference(idCreatorFk.getClass(), idCreatorFk.getIdUser());
                comment.setIdCreatorFk(idCreatorFk);
            }
            ConstantType idTypeFk = comment.getIdTypeFk();
            if (idTypeFk != null) {
                idTypeFk = em.getReference(idTypeFk.getClass(), idTypeFk.getIdConstantType());
                comment.setIdTypeFk(idTypeFk);
            }
            em.persist(comment);
            if (idCreatorFk != null) {
                idCreatorFk.getCommentCollection().add(comment);
                idCreatorFk = em.merge(idCreatorFk);
            }
            if (idTypeFk != null) {
                idTypeFk.getCommentCollection().add(comment);
                idTypeFk = em.merge(idTypeFk);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comment comment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comment persistentComment = em.find(Comment.class, comment.getIdComment());
            User idCreatorFkOld = persistentComment.getIdCreatorFk();
            User idCreatorFkNew = comment.getIdCreatorFk();
            ConstantType idTypeFkOld = persistentComment.getIdTypeFk();
            ConstantType idTypeFkNew = comment.getIdTypeFk();
            if (idCreatorFkNew != null) {
                idCreatorFkNew = em.getReference(idCreatorFkNew.getClass(), idCreatorFkNew.getIdUser());
                comment.setIdCreatorFk(idCreatorFkNew);
            }
            if (idTypeFkNew != null) {
                idTypeFkNew = em.getReference(idTypeFkNew.getClass(), idTypeFkNew.getIdConstantType());
                comment.setIdTypeFk(idTypeFkNew);
            }
            comment = em.merge(comment);
            if (idCreatorFkOld != null && !idCreatorFkOld.equals(idCreatorFkNew)) {
                idCreatorFkOld.getCommentCollection().remove(comment);
                idCreatorFkOld = em.merge(idCreatorFkOld);
            }
            if (idCreatorFkNew != null && !idCreatorFkNew.equals(idCreatorFkOld)) {
                idCreatorFkNew.getCommentCollection().add(comment);
                idCreatorFkNew = em.merge(idCreatorFkNew);
            }
            if (idTypeFkOld != null && !idTypeFkOld.equals(idTypeFkNew)) {
                idTypeFkOld.getCommentCollection().remove(comment);
                idTypeFkOld = em.merge(idTypeFkOld);
            }
            if (idTypeFkNew != null && !idTypeFkNew.equals(idTypeFkOld)) {
                idTypeFkNew.getCommentCollection().add(comment);
                idTypeFkNew = em.merge(idTypeFkNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comment.getIdComment();
                if (findComment(id) == null) {
                    throw new NonexistentEntityException("The comment with id " + id + " no longer exists.");
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
            Comment comment;
            try {
                comment = em.getReference(Comment.class, id);
                comment.getIdComment();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comment with id " + id + " no longer exists.", enfe);
            }
            User idCreatorFk = comment.getIdCreatorFk();
            if (idCreatorFk != null) {
                idCreatorFk.getCommentCollection().remove(comment);
                idCreatorFk = em.merge(idCreatorFk);
            }
            ConstantType idTypeFk = comment.getIdTypeFk();
            if (idTypeFk != null) {
                idTypeFk.getCommentCollection().remove(comment);
                idTypeFk = em.merge(idTypeFk);
            }
            em.remove(comment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comment> findCommentEntities() {
        return findCommentEntities(true, -1, -1);
    }

    public List<Comment> findCommentEntities(int maxResults, int firstResult) {
        return findCommentEntities(false, maxResults, firstResult);
    }

    private List<Comment> findCommentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Comment as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Comment findComment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comment.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommentCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Comment as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
