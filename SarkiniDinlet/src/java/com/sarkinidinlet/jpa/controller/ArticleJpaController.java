/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.Article;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Stats;
import com.sarkinidinlet.jpa.entity.User;
import com.sarkinidinlet.jpa.entity.ConstantType;

/**
 *
 * @author Özkan SARI
 */
public class ArticleJpaController {

    public ArticleJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Article article) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Stats idStatsFk = article.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk = em.getReference(idStatsFk.getClass(), idStatsFk.getIdStats());
                article.setIdStatsFk(idStatsFk);
            }
            User idCreatorFk = article.getIdCreatorFk();
            if (idCreatorFk != null) {
                idCreatorFk = em.getReference(idCreatorFk.getClass(), idCreatorFk.getIdUser());
                article.setIdCreatorFk(idCreatorFk);
            }
            ConstantType idTypeFk = article.getIdTypeFk();
            if (idTypeFk != null) {
                idTypeFk = em.getReference(idTypeFk.getClass(), idTypeFk.getIdConstantType());
                article.setIdTypeFk(idTypeFk);
            }
            em.persist(article);
            if (idStatsFk != null) {
                idStatsFk.getArticleCollection().add(article);
                idStatsFk = em.merge(idStatsFk);
            }
            if (idCreatorFk != null) {
                idCreatorFk.getArticleCollection().add(article);
                idCreatorFk = em.merge(idCreatorFk);
            }
            if (idTypeFk != null) {
                idTypeFk.getArticleCollection().add(article);
                idTypeFk = em.merge(idTypeFk);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Article article) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Article persistentArticle = em.find(Article.class, article.getIdArticle());
            Stats idStatsFkOld = persistentArticle.getIdStatsFk();
            Stats idStatsFkNew = article.getIdStatsFk();
            User idCreatorFkOld = persistentArticle.getIdCreatorFk();
            User idCreatorFkNew = article.getIdCreatorFk();
            ConstantType idTypeFkOld = persistentArticle.getIdTypeFk();
            ConstantType idTypeFkNew = article.getIdTypeFk();
            if (idStatsFkNew != null) {
                idStatsFkNew = em.getReference(idStatsFkNew.getClass(), idStatsFkNew.getIdStats());
                article.setIdStatsFk(idStatsFkNew);
            }
            if (idCreatorFkNew != null) {
                idCreatorFkNew = em.getReference(idCreatorFkNew.getClass(), idCreatorFkNew.getIdUser());
                article.setIdCreatorFk(idCreatorFkNew);
            }
            if (idTypeFkNew != null) {
                idTypeFkNew = em.getReference(idTypeFkNew.getClass(), idTypeFkNew.getIdConstantType());
                article.setIdTypeFk(idTypeFkNew);
            }
            article = em.merge(article);
            if (idStatsFkOld != null && !idStatsFkOld.equals(idStatsFkNew)) {
                idStatsFkOld.getArticleCollection().remove(article);
                idStatsFkOld = em.merge(idStatsFkOld);
            }
            if (idStatsFkNew != null && !idStatsFkNew.equals(idStatsFkOld)) {
                idStatsFkNew.getArticleCollection().add(article);
                idStatsFkNew = em.merge(idStatsFkNew);
            }
            if (idCreatorFkOld != null && !idCreatorFkOld.equals(idCreatorFkNew)) {
                idCreatorFkOld.getArticleCollection().remove(article);
                idCreatorFkOld = em.merge(idCreatorFkOld);
            }
            if (idCreatorFkNew != null && !idCreatorFkNew.equals(idCreatorFkOld)) {
                idCreatorFkNew.getArticleCollection().add(article);
                idCreatorFkNew = em.merge(idCreatorFkNew);
            }
            if (idTypeFkOld != null && !idTypeFkOld.equals(idTypeFkNew)) {
                idTypeFkOld.getArticleCollection().remove(article);
                idTypeFkOld = em.merge(idTypeFkOld);
            }
            if (idTypeFkNew != null && !idTypeFkNew.equals(idTypeFkOld)) {
                idTypeFkNew.getArticleCollection().add(article);
                idTypeFkNew = em.merge(idTypeFkNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = article.getIdArticle();
                if (findArticle(id) == null) {
                    throw new NonexistentEntityException("The article with id " + id + " no longer exists.");
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
            Article article;
            try {
                article = em.getReference(Article.class, id);
                article.getIdArticle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The article with id " + id + " no longer exists.", enfe);
            }
            Stats idStatsFk = article.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk.getArticleCollection().remove(article);
                idStatsFk = em.merge(idStatsFk);
            }
            User idCreatorFk = article.getIdCreatorFk();
            if (idCreatorFk != null) {
                idCreatorFk.getArticleCollection().remove(article);
                idCreatorFk = em.merge(idCreatorFk);
            }
            ConstantType idTypeFk = article.getIdTypeFk();
            if (idTypeFk != null) {
                idTypeFk.getArticleCollection().remove(article);
                idTypeFk = em.merge(idTypeFk);
            }
            em.remove(article);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Article> findArticleEntities() {
        return findArticleEntities(true, -1, -1);
    }

    public List<Article> findArticleEntities(int maxResults, int firstResult) {
        return findArticleEntities(false, maxResults, firstResult);
    }

    private List<Article> findArticleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Article as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Article findArticle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Article.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticleCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Article as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
