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
@Table(name = "favorite")
@NamedQueries({@NamedQuery(name = "Favorite.findAll", query = "SELECT f FROM Favorite f"), @NamedQuery(name = "Favorite.findByIdFavorite", query = "SELECT f FROM Favorite f WHERE f.idFavorite = :idFavorite"), @NamedQuery(name = "Favorite.findByIdParentFk", query = "SELECT f FROM Favorite f WHERE f.idParentFk = :idParentFk")})
public class Favorite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_favorite")
    private Integer idFavorite;
    @Column(name = "id_parent_fk")
    private Integer idParentFk;
    @JoinColumn(name = "id_creator_fk", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idCreatorFk;
    @JoinColumn(name = "id_fav_type_fk", referencedColumnName = "id_constant")
    @ManyToOne(optional = false)
    private Constant idFavTypeFk;

    public Favorite() {
    }

    public Favorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public Integer getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Integer idFavorite) {
        this.idFavorite = idFavorite;
    }

    public Integer getIdParentFk() {
        return idParentFk;
    }

    public void setIdParentFk(Integer idParentFk) {
        this.idParentFk = idParentFk;
    }

    public User getIdCreatorFk() {
        return idCreatorFk;
    }

    public void setIdCreatorFk(User idCreatorFk) {
        this.idCreatorFk = idCreatorFk;
    }

    public Constant getIdFavTypeFk() {
        return idFavTypeFk;
    }

    public void setIdFavTypeFk(Constant idFavTypeFk) {
        this.idFavTypeFk = idFavTypeFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFavorite != null ? idFavorite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorite)) {
            return false;
        }
        Favorite other = (Favorite) object;
        if ((this.idFavorite == null && other.idFavorite != null) || (this.idFavorite != null && !this.idFavorite.equals(other.idFavorite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Favorite[idFavorite=" + idFavorite + "]";
    }

}
