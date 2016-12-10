/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javafun.modal.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ozkansari
 */
@Entity
@Table(name = "article", catalog = "javafun", schema = "")
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByArticleId", query = "SELECT a FROM Article a WHERE a.articleId = :articleId"),
    @NamedQuery(name = "Article.findByCreated", query = "SELECT a FROM Article a WHERE a.created = :created"),
    @NamedQuery(name = "Article.findByUpdated", query = "SELECT a FROM Article a WHERE a.updated = :updated"),
    @NamedQuery(name = "Article.findByTitle", query = "SELECT a FROM Article a WHERE a.title = :title"),
    @NamedQuery(name = "Article.findByBody", query = "SELECT a FROM Article a WHERE a.body = :body")})
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @Id
    private Integer articleId;
    
    @NotNull
    private Date created;
    
    @NotNull
    private Date updated;
    
    @NotNull
    @Size(min = 1, max = 256)
    private String title;
    
    @NotNull
    @Size(min = 1, max = 10240)
    private String body;
    
    private Blog blogId;

    public Article() {
    }

    public Article(Integer articleId) {
        this.articleId = articleId;
    }

    public Article(Integer articleId, Date created, Date updated, String title, String body) {
        this.articleId = articleId;
        this.created = created;
        this.updated = updated;
        this.title = title;
        this.body = body;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "article_id", nullable = false)
    @Id
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Basic(optional = false)
    @Column(name = "updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 256)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic(optional = false)
    @Column(name = "body", nullable = false, length = 10240)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JoinColumn(name = "blog_id", referencedColumnName = "blog_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Blog getBlogId() {
        return blogId;
    }

    public void setBlogId(Blog blogId) {
        this.blogId = blogId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleId != null ? articleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.articleId == null && other.articleId != null) || (this.articleId != null && !this.articleId.equals(other.articleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.javafun.modal.jpa.entity.Article[ articleId=" + articleId + " ]";
    }
    
}
