/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Özkan SARI
 */
@Entity
@Table(name = "article")
@NamedQueries({@NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"), @NamedQuery(name = "Article.findByIdArticle", query = "SELECT a FROM Article a WHERE a.idArticle = :idArticle"), @NamedQuery(name = "Article.findByIdParentFk", query = "SELECT a FROM Article a WHERE a.idParentFk = :idParentFk"), @NamedQuery(name = "Article.findByTitle", query = "SELECT a FROM Article a WHERE a.title = :title"), @NamedQuery(name = "Article.findByBody", query = "SELECT a FROM Article a WHERE a.body = :body"), @NamedQuery(name = "Article.findByDateCreated", query = "SELECT a FROM Article a WHERE a.dateCreated = :dateCreated"), @NamedQuery(name = "Article.findByIsApproved", query = "SELECT a FROM Article a WHERE a.isApproved = :isApproved")})
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_article")
    private Integer idArticle;
    @Column(name = "id_parent_fk")
    private Integer idParentFk;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @Basic(optional = false)
    @Column(name = "date_created")
    private int dateCreated;
    @Basic(optional = false)
    @Column(name = "is_approved")
    private boolean isApproved;
    @JoinColumn(name = "id_stats_fk", referencedColumnName = "id_stats")
    @ManyToOne(optional = false)
    private Stats idStatsFk;
    @JoinColumn(name = "id_creator_fk", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idCreatorFk;
    @JoinColumn(name = "id_type_fk", referencedColumnName = "id_constant_type")
    @ManyToOne(optional = false)
    private ConstantType idTypeFk;

    public Article() {
    }

    public Article(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Article(Integer idArticle, String title, int dateCreated, boolean isApproved) {
        this.idArticle = idArticle;
        this.title = title;
        this.dateCreated = dateCreated;
        this.isApproved = isApproved;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getIdParentFk() {
        return idParentFk;
    }

    public void setIdParentFk(Integer idParentFk) {
        this.idParentFk = idParentFk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Stats getIdStatsFk() {
        return idStatsFk;
    }

    public void setIdStatsFk(Stats idStatsFk) {
        this.idStatsFk = idStatsFk;
    }

    public User getIdCreatorFk() {
        return idCreatorFk;
    }

    public void setIdCreatorFk(User idCreatorFk) {
        this.idCreatorFk = idCreatorFk;
    }

    public ConstantType getIdTypeFk() {
        return idTypeFk;
    }

    public void setIdTypeFk(ConstantType idTypeFk) {
        this.idTypeFk = idTypeFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticle != null ? idArticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.idArticle == null && other.idArticle != null) || (this.idArticle != null && !this.idArticle.equals(other.idArticle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Article[idArticle=" + idArticle + "]";
    }

}
