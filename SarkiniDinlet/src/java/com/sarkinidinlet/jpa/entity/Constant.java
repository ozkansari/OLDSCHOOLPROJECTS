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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Özkan SARI
 */
@Entity
@Table(name = "constant")
@NamedQueries({@NamedQuery(name = "Constant.findAll", query = "SELECT c FROM Constant c"), @NamedQuery(name = "Constant.findByIdConstant", query = "SELECT c FROM Constant c WHERE c.idConstant = :idConstant"), @NamedQuery(name = "Constant.findByValue", query = "SELECT c FROM Constant c WHERE c.value = :value"), @NamedQuery(name = "Constant.findByText", query = "SELECT c FROM Constant c WHERE c.text = :text")})
public class Constant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_constant")
    private Integer idConstant;
    @Basic(optional = false)
    @Column(name = "value")
    private short value;
    @Basic(optional = false)
    @Column(name = "text")
    private String text;
    @OneToMany(mappedBy = "idCityFk")
    private Collection<UserDetail> userDetailCollection;
    @OneToMany(mappedBy = "idCountryFk")
    private Collection<UserDetail> userDetailCollection1;
    @JoinColumn(name = "id_type_fk", referencedColumnName = "id_constant_type")
    @ManyToOne(optional = false)
    private ConstantType idTypeFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFavTypeFk")
    private Collection<Favorite> favoriteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "musicTypeFk")
    private Collection<Artist> artistCollection;

    public Constant() {
    }

    public Constant(Integer idConstant) {
        this.idConstant = idConstant;
    }

    public Constant(Integer idConstant, short value, String text) {
        this.idConstant = idConstant;
        this.value = value;
        this.text = text;
    }

    public Integer getIdConstant() {
        return idConstant;
    }

    public void setIdConstant(Integer idConstant) {
        this.idConstant = idConstant;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Collection<UserDetail> getUserDetailCollection() {
        return userDetailCollection;
    }

    public void setUserDetailCollection(Collection<UserDetail> userDetailCollection) {
        this.userDetailCollection = userDetailCollection;
    }

    public Collection<UserDetail> getUserDetailCollection1() {
        return userDetailCollection1;
    }

    public void setUserDetailCollection1(Collection<UserDetail> userDetailCollection1) {
        this.userDetailCollection1 = userDetailCollection1;
    }

    public ConstantType getIdTypeFk() {
        return idTypeFk;
    }

    public void setIdTypeFk(ConstantType idTypeFk) {
        this.idTypeFk = idTypeFk;
    }

    public Collection<Favorite> getFavoriteCollection() {
        return favoriteCollection;
    }

    public void setFavoriteCollection(Collection<Favorite> favoriteCollection) {
        this.favoriteCollection = favoriteCollection;
    }

    public Collection<Artist> getArtistCollection() {
        return artistCollection;
    }

    public void setArtistCollection(Collection<Artist> artistCollection) {
        this.artistCollection = artistCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConstant != null ? idConstant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Constant)) {
            return false;
        }
        Constant other = (Constant) object;
        if ((this.idConstant == null && other.idConstant != null) || (this.idConstant != null && !this.idConstant.equals(other.idConstant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Constant[idConstant=" + idConstant + "]";
    }

}
