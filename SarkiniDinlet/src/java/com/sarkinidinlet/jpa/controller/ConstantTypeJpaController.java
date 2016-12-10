/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.IllegalOrphanException;
import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.ConstantType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Constant;
import java.util.ArrayList;
import java.util.Collection;
import com.sarkinidinlet.jpa.entity.Article;
import com.sarkinidinlet.jpa.entity.Comment;

/**
 *
 * @author Özkan SARI
 */
public class ConstantTypeJpaController {

    public ConstantTypeJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ConstantType constantType) {
        if (constantType.getConstantCollection() == null) {
            constantType.setConstantCollection(new ArrayList<Constant>());
        }
        if (constantType.getArticleCollection() == null) {
            constantType.setArticleCollection(new ArrayList<Article>());
        }
        if (constantType.getCommentCollection() == null) {
            constantType.setCommentCollection(new ArrayList<Comment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Constant> attachedConstantCollection = new ArrayList<Constant>();
            for (Constant constantCollectionConstantToAttach : constantType.getConstantCollection()) {
                constantCollectionConstantToAttach = em.getReference(constantCollectionConstantToAttach.getClass(), constantCollectionConstantToAttach.getIdConstant());
                attachedConstantCollection.add(constantCollectionConstantToAttach);
            }
            constantType.setConstantCollection(attachedConstantCollection);
            Collection<Article> attachedArticleCollection = new ArrayList<Article>();
            for (Article articleCollectionArticleToAttach : constantType.getArticleCollection()) {
                articleCollectionArticleToAttach = em.getReference(articleCollectionArticleToAttach.getClass(), articleCollectionArticleToAttach.getIdArticle());
                attachedArticleCollection.add(articleCollectionArticleToAttach);
            }
            constantType.setArticleCollection(attachedArticleCollection);
            Collection<Comment> attachedCommentCollection = new ArrayList<Comment>();
            for (Comment commentCollectionCommentToAttach : constantType.getCommentCollection()) {
                commentCollectionCommentToAttach = em.getReference(commentCollectionCommentToAttach.getClass(), commentCollectionCommentToAttach.getIdComment());
                attachedCommentCollection.add(commentCollectionCommentToAttach);
            }
            constantType.setCommentCollection(attachedCommentCollection);
            em.persist(constantType);
            for (Constant constantCollectionConstant : constantType.getConstantCollection()) {
                ConstantType oldIdTypeFkOfConstantCollectionConstant = constantCollectionConstant.getIdTypeFk();
                constantCollectionConstant.setIdTypeFk(constantType);
                constantCollectionConstant = em.merge(constantCollectionConstant);
                if (oldIdTypeFkOfConstantCollectionConstant != null) {
                    oldIdTypeFkOfConstantCollectionConstant.getConstantCollection().remove(constantCollectionConstant);
                    oldIdTypeFkOfConstantCollectionConstant = em.merge(oldIdTypeFkOfConstantCollectionConstant);
                }
            }
            for (Article articleCollectionArticle : constantType.getArticleCollection()) {
                ConstantType oldIdTypeFkOfArticleCollectionArticle = articleCollectionArticle.getIdTypeFk();
                articleCollectionArticle.setIdTypeFk(constantType);
                articleCollectionArticle = em.merge(articleCollectionArticle);
                if (oldIdTypeFkOfArticleCollectionArticle != null) {
                    oldIdTypeFkOfArticleCollectionArticle.getArticleCollection().remove(articleCollectionArticle);
                    oldIdTypeFkOfArticleCollectionArticle = em.merge(oldIdTypeFkOfArticleCollectionArticle);
                }
            }
            for (Comment commentCollectionComment : constantType.getCommentCollection()) {
                ConstantType oldIdTypeFkOfCommentCollectionComment = commentCollectionComment.getIdTypeFk();
                commentCollectionComment.setIdTypeFk(constantType);
                commentCollectionComment = em.merge(commentCollectionComment);
                if (oldIdTypeFkOfCommentCollectionComment != null) {
                    oldIdTypeFkOfCommentCollectionComment.getCommentCollection().remove(commentCollectionComment);
                    oldIdTypeFkOfCommentCollectionComment = em.merge(oldIdTypeFkOfCommentCollectionComment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConstantType constantType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ConstantType persistentConstantType = em.find(ConstantType.class, constantType.getIdConstantType());
            Collection<Constant> constantCollectionOld = persistentConstantType.getConstantCollection();
            Collection<Constant> constantCollectionNew = constantType.getConstantCollection();
            Collection<Article> articleCollectionOld = persistentConstantType.getArticleCollection();
            Collection<Article> articleCollectionNew = constantType.getArticleCollection();
            Collection<Comment> commentCollectionOld = persistentConstantType.getCommentCollection();
            Collection<Comment> commentCollectionNew = constantType.getCommentCollection();
            List<String> illegalOrphanMessages = null;
            for (Constant constantCollectionOldConstant : constantCollectionOld) {
                if (!constantCollectionNew.contains(constantCollectionOldConstant)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Constant " + constantCollectionOldConstant + " since its idTypeFk field is not nullable.");
                }
            }
            for (Article articleCollectionOldArticle : articleCollectionOld) {
                if (!articleCollectionNew.contains(articleCollectionOldArticle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Article " + articleCollectionOldArticle + " since its idTypeFk field is not nullable.");
                }
            }
            for (Comment commentCollectionOldComment : commentCollectionOld) {
                if (!commentCollectionNew.contains(commentCollectionOldComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comment " + commentCollectionOldComment + " since its idTypeFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Constant> attachedConstantCollectionNew = new ArrayList<Constant>();
            for (Constant constantCollectionNewConstantToAttach : constantCollectionNew) {
                constantCollectionNewConstantToAttach = em.getReference(constantCollectionNewConstantToAttach.getClass(), constantCollectionNewConstantToAttach.getIdConstant());
                attachedConstantCollectionNew.add(constantCollectionNewConstantToAttach);
            }
            constantCollectionNew = attachedConstantCollectionNew;
            constantType.setConstantCollection(constantCollectionNew);
            Collection<Article> attachedArticleCollectionNew = new ArrayList<Article>();
            for (Article articleCollectionNewArticleToAttach : articleCollectionNew) {
                articleCollectionNewArticleToAttach = em.getReference(articleCollectionNewArticleToAttach.getClass(), articleCollectionNewArticleToAttach.getIdArticle());
                attachedArticleCollectionNew.add(articleCollectionNewArticleToAttach);
            }
            articleCollectionNew = attachedArticleCollectionNew;
            constantType.setArticleCollection(articleCollectionNew);
            Collection<Comment> attachedCommentCollectionNew = new ArrayList<Comment>();
            for (Comment commentCollectionNewCommentToAttach : commentCollectionNew) {
                commentCollectionNewCommentToAttach = em.getReference(commentCollectionNewCommentToAttach.getClass(), commentCollectionNewCommentToAttach.getIdComment());
                attachedCommentCollectionNew.add(commentCollectionNewCommentToAttach);
            }
            commentCollectionNew = attachedCommentCollectionNew;
            constantType.setCommentCollection(commentCollectionNew);
            constantType = em.merge(constantType);
            for (Constant constantCollectionNewConstant : constantCollectionNew) {
                if (!constantCollectionOld.contains(constantCollectionNewConstant)) {
                    ConstantType oldIdTypeFkOfConstantCollectionNewConstant = constantCollectionNewConstant.getIdTypeFk();
                    constantCollectionNewConstant.setIdTypeFk(constantType);
                    constantCollectionNewConstant = em.merge(constantCollectionNewConstant);
                    if (oldIdTypeFkOfConstantCollectionNewConstant != null && !oldIdTypeFkOfConstantCollectionNewConstant.equals(constantType)) {
                        oldIdTypeFkOfConstantCollectionNewConstant.getConstantCollection().remove(constantCollectionNewConstant);
                        oldIdTypeFkOfConstantCollectionNewConstant = em.merge(oldIdTypeFkOfConstantCollectionNewConstant);
                    }
                }
            }
            for (Article articleCollectionNewArticle : articleCollectionNew) {
                if (!articleCollectionOld.contains(articleCollectionNewArticle)) {
                    ConstantType oldIdTypeFkOfArticleCollectionNewArticle = articleCollectionNewArticle.getIdTypeFk();
                    articleCollectionNewArticle.setIdTypeFk(constantType);
                    articleCollectionNewArticle = em.merge(articleCollectionNewArticle);
                    if (oldIdTypeFkOfArticleCollectionNewArticle != null && !oldIdTypeFkOfArticleCollectionNewArticle.equals(constantType)) {
                        oldIdTypeFkOfArticleCollectionNewArticle.getArticleCollection().remove(articleCollectionNewArticle);
                        oldIdTypeFkOfArticleCollectionNewArticle = em.merge(oldIdTypeFkOfArticleCollectionNewArticle);
                    }
                }
            }
            for (Comment commentCollectionNewComment : commentCollectionNew) {
                if (!commentCollectionOld.contains(commentCollectionNewComment)) {
                    ConstantType oldIdTypeFkOfCommentCollectionNewComment = commentCollectionNewComment.getIdTypeFk();
                    commentCollectionNewComment.setIdTypeFk(constantType);
                    commentCollectionNewComment = em.merge(commentCollectionNewComment);
                    if (oldIdTypeFkOfCommentCollectionNewComment != null && !oldIdTypeFkOfCommentCollectionNewComment.equals(constantType)) {
                        oldIdTypeFkOfCommentCollectionNewComment.getCommentCollection().remove(commentCollectionNewComment);
                        oldIdTypeFkOfCommentCollectionNewComment = em.merge(oldIdTypeFkOfCommentCollectionNewComment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = constantType.getIdConstantType();
                if (findConstantType(id) == null) {
                    throw new NonexistentEntityException("The constantType with id " + id + " no longer exists.");
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
            ConstantType constantType;
            try {
                constantType = em.getReference(ConstantType.class, id);
                constantType.getIdConstantType();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The constantType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Constant> constantCollectionOrphanCheck = constantType.getConstantCollection();
            for (Constant constantCollectionOrphanCheckConstant : constantCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ConstantType (" + constantType + ") cannot be destroyed since the Constant " + constantCollectionOrphanCheckConstant + " in its constantCollection field has a non-nullable idTypeFk field.");
            }
            Collection<Article> articleCollectionOrphanCheck = constantType.getArticleCollection();
            for (Article articleCollectionOrphanCheckArticle : articleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ConstantType (" + constantType + ") cannot be destroyed since the Article " + articleCollectionOrphanCheckArticle + " in its articleCollection field has a non-nullable idTypeFk field.");
            }
            Collection<Comment> commentCollectionOrphanCheck = constantType.getCommentCollection();
            for (Comment commentCollectionOrphanCheckComment : commentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ConstantType (" + constantType + ") cannot be destroyed since the Comment " + commentCollectionOrphanCheckComment + " in its commentCollection field has a non-nullable idTypeFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(constantType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConstantType> findConstantTypeEntities() {
        return findConstantTypeEntities(true, -1, -1);
    }

    public List<ConstantType> findConstantTypeEntities(int maxResults, int firstResult) {
        return findConstantTypeEntities(false, maxResults, firstResult);
    }

    private List<ConstantType> findConstantTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ConstantType as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ConstantType findConstantType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConstantType.class, id);
        } finally {
            em.close();
        }
    }

    public int getConstantTypeCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from ConstantType as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
