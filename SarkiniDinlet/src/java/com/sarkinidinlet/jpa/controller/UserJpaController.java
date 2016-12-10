/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.controller;

import com.sarkinidinlet.jpa.controller.exceptions.IllegalOrphanException;
import com.sarkinidinlet.jpa.controller.exceptions.NonexistentEntityException;
import com.sarkinidinlet.jpa.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.sarkinidinlet.jpa.entity.Stats;
import com.sarkinidinlet.jpa.entity.Artist;
import com.sarkinidinlet.jpa.entity.UserRole;
import com.sarkinidinlet.jpa.entity.UserDetail;
import com.sarkinidinlet.jpa.entity.Favorite;
import java.util.ArrayList;
import java.util.Collection;
import com.sarkinidinlet.jpa.entity.Article;
import com.sarkinidinlet.jpa.entity.Comment;

/**
 *
 * @author Özkan SARI
 */
public class UserJpaController {

    public UserJpaController() {
        emf = Persistence.createEntityManagerFactory("SarkiniDinletPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getFavoriteCollection() == null) {
            user.setFavoriteCollection(new ArrayList<Favorite>());
        }
        if (user.getArticleCollection() == null) {
            user.setArticleCollection(new ArrayList<Article>());
        }
        if (user.getCommentCollection() == null) {
            user.setCommentCollection(new ArrayList<Comment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Stats idStatsFk = user.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk = em.getReference(idStatsFk.getClass(), idStatsFk.getIdStats());
                user.setIdStatsFk(idStatsFk);
            }
            Artist idArtistFk = user.getIdArtistFk();
            if (idArtistFk != null) {
                idArtistFk = em.getReference(idArtistFk.getClass(), idArtistFk.getIdArtist());
                user.setIdArtistFk(idArtistFk);
            }
            UserRole idRoleFk = user.getIdRoleFk();
            if (idRoleFk != null) {
                idRoleFk = em.getReference(idRoleFk.getClass(), idRoleFk.getIdUserRole());
                user.setIdRoleFk(idRoleFk);
            }
            UserDetail idUserDetailFk = user.getIdUserDetailFk();
            if (idUserDetailFk != null) {
                idUserDetailFk = em.getReference(idUserDetailFk.getClass(), idUserDetailFk.getIdUserDetail());
                user.setIdUserDetailFk(idUserDetailFk);
            }
            Collection<Favorite> attachedFavoriteCollection = new ArrayList<Favorite>();
            for (Favorite favoriteCollectionFavoriteToAttach : user.getFavoriteCollection()) {
                favoriteCollectionFavoriteToAttach = em.getReference(favoriteCollectionFavoriteToAttach.getClass(), favoriteCollectionFavoriteToAttach.getIdFavorite());
                attachedFavoriteCollection.add(favoriteCollectionFavoriteToAttach);
            }
            user.setFavoriteCollection(attachedFavoriteCollection);
            Collection<Article> attachedArticleCollection = new ArrayList<Article>();
            for (Article articleCollectionArticleToAttach : user.getArticleCollection()) {
                articleCollectionArticleToAttach = em.getReference(articleCollectionArticleToAttach.getClass(), articleCollectionArticleToAttach.getIdArticle());
                attachedArticleCollection.add(articleCollectionArticleToAttach);
            }
            user.setArticleCollection(attachedArticleCollection);
            Collection<Comment> attachedCommentCollection = new ArrayList<Comment>();
            for (Comment commentCollectionCommentToAttach : user.getCommentCollection()) {
                commentCollectionCommentToAttach = em.getReference(commentCollectionCommentToAttach.getClass(), commentCollectionCommentToAttach.getIdComment());
                attachedCommentCollection.add(commentCollectionCommentToAttach);
            }
            user.setCommentCollection(attachedCommentCollection);
            em.persist(user);
            if (idStatsFk != null) {
                idStatsFk.getUserCollection().add(user);
                idStatsFk = em.merge(idStatsFk);
            }
            if (idArtistFk != null) {
                idArtistFk.getUserCollection().add(user);
                idArtistFk = em.merge(idArtistFk);
            }
            if (idRoleFk != null) {
                idRoleFk.getUserCollection().add(user);
                idRoleFk = em.merge(idRoleFk);
            }
            if (idUserDetailFk != null) {
                idUserDetailFk.getUserCollection().add(user);
                idUserDetailFk = em.merge(idUserDetailFk);
            }
            for (Favorite favoriteCollectionFavorite : user.getFavoriteCollection()) {
                User oldIdCreatorFkOfFavoriteCollectionFavorite = favoriteCollectionFavorite.getIdCreatorFk();
                favoriteCollectionFavorite.setIdCreatorFk(user);
                favoriteCollectionFavorite = em.merge(favoriteCollectionFavorite);
                if (oldIdCreatorFkOfFavoriteCollectionFavorite != null) {
                    oldIdCreatorFkOfFavoriteCollectionFavorite.getFavoriteCollection().remove(favoriteCollectionFavorite);
                    oldIdCreatorFkOfFavoriteCollectionFavorite = em.merge(oldIdCreatorFkOfFavoriteCollectionFavorite);
                }
            }
            for (Article articleCollectionArticle : user.getArticleCollection()) {
                User oldIdCreatorFkOfArticleCollectionArticle = articleCollectionArticle.getIdCreatorFk();
                articleCollectionArticle.setIdCreatorFk(user);
                articleCollectionArticle = em.merge(articleCollectionArticle);
                if (oldIdCreatorFkOfArticleCollectionArticle != null) {
                    oldIdCreatorFkOfArticleCollectionArticle.getArticleCollection().remove(articleCollectionArticle);
                    oldIdCreatorFkOfArticleCollectionArticle = em.merge(oldIdCreatorFkOfArticleCollectionArticle);
                }
            }
            for (Comment commentCollectionComment : user.getCommentCollection()) {
                User oldIdCreatorFkOfCommentCollectionComment = commentCollectionComment.getIdCreatorFk();
                commentCollectionComment.setIdCreatorFk(user);
                commentCollectionComment = em.merge(commentCollectionComment);
                if (oldIdCreatorFkOfCommentCollectionComment != null) {
                    oldIdCreatorFkOfCommentCollectionComment.getCommentCollection().remove(commentCollectionComment);
                    oldIdCreatorFkOfCommentCollectionComment = em.merge(oldIdCreatorFkOfCommentCollectionComment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getIdUser());
            Stats idStatsFkOld = persistentUser.getIdStatsFk();
            Stats idStatsFkNew = user.getIdStatsFk();
            Artist idArtistFkOld = persistentUser.getIdArtistFk();
            Artist idArtistFkNew = user.getIdArtistFk();
            UserRole idRoleFkOld = persistentUser.getIdRoleFk();
            UserRole idRoleFkNew = user.getIdRoleFk();
            UserDetail idUserDetailFkOld = persistentUser.getIdUserDetailFk();
            UserDetail idUserDetailFkNew = user.getIdUserDetailFk();
            Collection<Favorite> favoriteCollectionOld = persistentUser.getFavoriteCollection();
            Collection<Favorite> favoriteCollectionNew = user.getFavoriteCollection();
            Collection<Article> articleCollectionOld = persistentUser.getArticleCollection();
            Collection<Article> articleCollectionNew = user.getArticleCollection();
            Collection<Comment> commentCollectionOld = persistentUser.getCommentCollection();
            Collection<Comment> commentCollectionNew = user.getCommentCollection();
            List<String> illegalOrphanMessages = null;
            for (Favorite favoriteCollectionOldFavorite : favoriteCollectionOld) {
                if (!favoriteCollectionNew.contains(favoriteCollectionOldFavorite)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Favorite " + favoriteCollectionOldFavorite + " since its idCreatorFk field is not nullable.");
                }
            }
            for (Article articleCollectionOldArticle : articleCollectionOld) {
                if (!articleCollectionNew.contains(articleCollectionOldArticle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Article " + articleCollectionOldArticle + " since its idCreatorFk field is not nullable.");
                }
            }
            for (Comment commentCollectionOldComment : commentCollectionOld) {
                if (!commentCollectionNew.contains(commentCollectionOldComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Comment " + commentCollectionOldComment + " since its idCreatorFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idStatsFkNew != null) {
                idStatsFkNew = em.getReference(idStatsFkNew.getClass(), idStatsFkNew.getIdStats());
                user.setIdStatsFk(idStatsFkNew);
            }
            if (idArtistFkNew != null) {
                idArtistFkNew = em.getReference(idArtistFkNew.getClass(), idArtistFkNew.getIdArtist());
                user.setIdArtistFk(idArtistFkNew);
            }
            if (idRoleFkNew != null) {
                idRoleFkNew = em.getReference(idRoleFkNew.getClass(), idRoleFkNew.getIdUserRole());
                user.setIdRoleFk(idRoleFkNew);
            }
            if (idUserDetailFkNew != null) {
                idUserDetailFkNew = em.getReference(idUserDetailFkNew.getClass(), idUserDetailFkNew.getIdUserDetail());
                user.setIdUserDetailFk(idUserDetailFkNew);
            }
            Collection<Favorite> attachedFavoriteCollectionNew = new ArrayList<Favorite>();
            for (Favorite favoriteCollectionNewFavoriteToAttach : favoriteCollectionNew) {
                favoriteCollectionNewFavoriteToAttach = em.getReference(favoriteCollectionNewFavoriteToAttach.getClass(), favoriteCollectionNewFavoriteToAttach.getIdFavorite());
                attachedFavoriteCollectionNew.add(favoriteCollectionNewFavoriteToAttach);
            }
            favoriteCollectionNew = attachedFavoriteCollectionNew;
            user.setFavoriteCollection(favoriteCollectionNew);
            Collection<Article> attachedArticleCollectionNew = new ArrayList<Article>();
            for (Article articleCollectionNewArticleToAttach : articleCollectionNew) {
                articleCollectionNewArticleToAttach = em.getReference(articleCollectionNewArticleToAttach.getClass(), articleCollectionNewArticleToAttach.getIdArticle());
                attachedArticleCollectionNew.add(articleCollectionNewArticleToAttach);
            }
            articleCollectionNew = attachedArticleCollectionNew;
            user.setArticleCollection(articleCollectionNew);
            Collection<Comment> attachedCommentCollectionNew = new ArrayList<Comment>();
            for (Comment commentCollectionNewCommentToAttach : commentCollectionNew) {
                commentCollectionNewCommentToAttach = em.getReference(commentCollectionNewCommentToAttach.getClass(), commentCollectionNewCommentToAttach.getIdComment());
                attachedCommentCollectionNew.add(commentCollectionNewCommentToAttach);
            }
            commentCollectionNew = attachedCommentCollectionNew;
            user.setCommentCollection(commentCollectionNew);
            user = em.merge(user);
            if (idStatsFkOld != null && !idStatsFkOld.equals(idStatsFkNew)) {
                idStatsFkOld.getUserCollection().remove(user);
                idStatsFkOld = em.merge(idStatsFkOld);
            }
            if (idStatsFkNew != null && !idStatsFkNew.equals(idStatsFkOld)) {
                idStatsFkNew.getUserCollection().add(user);
                idStatsFkNew = em.merge(idStatsFkNew);
            }
            if (idArtistFkOld != null && !idArtistFkOld.equals(idArtistFkNew)) {
                idArtistFkOld.getUserCollection().remove(user);
                idArtistFkOld = em.merge(idArtistFkOld);
            }
            if (idArtistFkNew != null && !idArtistFkNew.equals(idArtistFkOld)) {
                idArtistFkNew.getUserCollection().add(user);
                idArtistFkNew = em.merge(idArtistFkNew);
            }
            if (idRoleFkOld != null && !idRoleFkOld.equals(idRoleFkNew)) {
                idRoleFkOld.getUserCollection().remove(user);
                idRoleFkOld = em.merge(idRoleFkOld);
            }
            if (idRoleFkNew != null && !idRoleFkNew.equals(idRoleFkOld)) {
                idRoleFkNew.getUserCollection().add(user);
                idRoleFkNew = em.merge(idRoleFkNew);
            }
            if (idUserDetailFkOld != null && !idUserDetailFkOld.equals(idUserDetailFkNew)) {
                idUserDetailFkOld.getUserCollection().remove(user);
                idUserDetailFkOld = em.merge(idUserDetailFkOld);
            }
            if (idUserDetailFkNew != null && !idUserDetailFkNew.equals(idUserDetailFkOld)) {
                idUserDetailFkNew.getUserCollection().add(user);
                idUserDetailFkNew = em.merge(idUserDetailFkNew);
            }
            for (Favorite favoriteCollectionNewFavorite : favoriteCollectionNew) {
                if (!favoriteCollectionOld.contains(favoriteCollectionNewFavorite)) {
                    User oldIdCreatorFkOfFavoriteCollectionNewFavorite = favoriteCollectionNewFavorite.getIdCreatorFk();
                    favoriteCollectionNewFavorite.setIdCreatorFk(user);
                    favoriteCollectionNewFavorite = em.merge(favoriteCollectionNewFavorite);
                    if (oldIdCreatorFkOfFavoriteCollectionNewFavorite != null && !oldIdCreatorFkOfFavoriteCollectionNewFavorite.equals(user)) {
                        oldIdCreatorFkOfFavoriteCollectionNewFavorite.getFavoriteCollection().remove(favoriteCollectionNewFavorite);
                        oldIdCreatorFkOfFavoriteCollectionNewFavorite = em.merge(oldIdCreatorFkOfFavoriteCollectionNewFavorite);
                    }
                }
            }
            for (Article articleCollectionNewArticle : articleCollectionNew) {
                if (!articleCollectionOld.contains(articleCollectionNewArticle)) {
                    User oldIdCreatorFkOfArticleCollectionNewArticle = articleCollectionNewArticle.getIdCreatorFk();
                    articleCollectionNewArticle.setIdCreatorFk(user);
                    articleCollectionNewArticle = em.merge(articleCollectionNewArticle);
                    if (oldIdCreatorFkOfArticleCollectionNewArticle != null && !oldIdCreatorFkOfArticleCollectionNewArticle.equals(user)) {
                        oldIdCreatorFkOfArticleCollectionNewArticle.getArticleCollection().remove(articleCollectionNewArticle);
                        oldIdCreatorFkOfArticleCollectionNewArticle = em.merge(oldIdCreatorFkOfArticleCollectionNewArticle);
                    }
                }
            }
            for (Comment commentCollectionNewComment : commentCollectionNew) {
                if (!commentCollectionOld.contains(commentCollectionNewComment)) {
                    User oldIdCreatorFkOfCommentCollectionNewComment = commentCollectionNewComment.getIdCreatorFk();
                    commentCollectionNewComment.setIdCreatorFk(user);
                    commentCollectionNewComment = em.merge(commentCollectionNewComment);
                    if (oldIdCreatorFkOfCommentCollectionNewComment != null && !oldIdCreatorFkOfCommentCollectionNewComment.equals(user)) {
                        oldIdCreatorFkOfCommentCollectionNewComment.getCommentCollection().remove(commentCollectionNewComment);
                        oldIdCreatorFkOfCommentCollectionNewComment = em.merge(oldIdCreatorFkOfCommentCollectionNewComment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getIdUser();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Favorite> favoriteCollectionOrphanCheck = user.getFavoriteCollection();
            for (Favorite favoriteCollectionOrphanCheckFavorite : favoriteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Favorite " + favoriteCollectionOrphanCheckFavorite + " in its favoriteCollection field has a non-nullable idCreatorFk field.");
            }
            Collection<Article> articleCollectionOrphanCheck = user.getArticleCollection();
            for (Article articleCollectionOrphanCheckArticle : articleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Article " + articleCollectionOrphanCheckArticle + " in its articleCollection field has a non-nullable idCreatorFk field.");
            }
            Collection<Comment> commentCollectionOrphanCheck = user.getCommentCollection();
            for (Comment commentCollectionOrphanCheckComment : commentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Comment " + commentCollectionOrphanCheckComment + " in its commentCollection field has a non-nullable idCreatorFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Stats idStatsFk = user.getIdStatsFk();
            if (idStatsFk != null) {
                idStatsFk.getUserCollection().remove(user);
                idStatsFk = em.merge(idStatsFk);
            }
            Artist idArtistFk = user.getIdArtistFk();
            if (idArtistFk != null) {
                idArtistFk.getUserCollection().remove(user);
                idArtistFk = em.merge(idArtistFk);
            }
            UserRole idRoleFk = user.getIdRoleFk();
            if (idRoleFk != null) {
                idRoleFk.getUserCollection().remove(user);
                idRoleFk = em.merge(idRoleFk);
            }
            UserDetail idUserDetailFk = user.getIdUserDetailFk();
            if (idUserDetailFk != null) {
                idUserDetailFk.getUserCollection().remove(user);
                idUserDetailFk = em.merge(idUserDetailFk);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from User as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from User as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
