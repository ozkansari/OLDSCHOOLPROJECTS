/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Özkan SARI
 */
@Entity
@Table(name = "constant_type")
@NamedQueries({@NamedQuery(name = "ConstantType.findAll", query = "SELECT c FROM ConstantType c"), @NamedQuery(name = "ConstantType.findByIdConstantType", query = "SELECT c FROM ConstantType c WHERE c.idConstantType = :idConstantType"), @NamedQuery(name = "ConstantType.findByTypeName", query = "SELECT c FROM ConstantType c WHERE c.typeName = :typeName")})
public class ConstantType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_constant_type")
    private Integer idConstantType;
    @Column(name = "type_name")
    private String typeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeFk")
    private Collection<Constant> constantCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeFk")
    private Collection<Article> articleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeFk")
    private Collection<Comment> commentCollection;

    public ConstantType() {
    }

    public ConstantType(Integer idConstantType) {
        this.idConstantType = idConstantType;
    }

    public Integer getIdConstantType() {
        return idConstantType;
    }

    public void setIdConstantType(Integer idConstantType) {
        this.idConstantType = idConstantType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Collection<Constant> getConstantCollection() {
        return constantCollection;
    }

    public void setConstantCollection(Collection<Constant> constantCollection) {
        this.constantCollection = constantCollection;
    }

    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConstantType != null ? idConstantType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConstantType)) {
            return false;
        }
        ConstantType other = (ConstantType) object;
        if ((this.idConstantType == null && other.idConstantType != null) || (this.idConstantType != null && !this.idConstantType.equals(other.idConstantType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.ConstantType[idConstantType=" + idConstantType + "]";
    }

}
