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
@Table(name = "user")
@NamedQueries({@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"), @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"), @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"), @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"), @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"), @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"), @NamedQuery(name = "User.findByIsApproved", query = "SELECT u FROM User u WHERE u.isApproved = :isApproved")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "is_approved")
    private boolean isApproved;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCreatorFk")
    private Collection<Favorite> favoriteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCreatorFk")
    private Collection<Article> articleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCreatorFk")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "id_stats_fk", referencedColumnName = "id_stats")
    @ManyToOne
    private Stats idStatsFk;
    @JoinColumn(name = "id_artist_fk", referencedColumnName = "id_artist")
    @ManyToOne
    private Artist idArtistFk;
    @JoinColumn(name = "id_role_fk", referencedColumnName = "id_user_role")
    @ManyToOne(optional = false)
    private UserRole idRoleFk;
    @JoinColumn(name = "id_user_detail_fk", referencedColumnName = "id_user_detail")
    @ManyToOne(optional = false)
    private UserDetail idUserDetailFk;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(Integer idUser, String password, String userName, String firstName, String lastName, boolean isApproved) {
        this.idUser = idUser;
        this.password = password;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isApproved = isApproved;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Collection<Favorite> getFavoriteCollection() {
        return favoriteCollection;
    }

    public void setFavoriteCollection(Collection<Favorite> favoriteCollection) {
        this.favoriteCollection = favoriteCollection;
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

    public Stats getIdStatsFk() {
        return idStatsFk;
    }

    public void setIdStatsFk(Stats idStatsFk) {
        this.idStatsFk = idStatsFk;
    }

    public Artist getIdArtistFk() {
        return idArtistFk;
    }

    public void setIdArtistFk(Artist idArtistFk) {
        this.idArtistFk = idArtistFk;
    }

    public UserRole getIdRoleFk() {
        return idRoleFk;
    }

    public void setIdRoleFk(UserRole idRoleFk) {
        this.idRoleFk = idRoleFk;
    }

    public UserDetail getIdUserDetailFk() {
        return idUserDetailFk;
    }

    public void setIdUserDetailFk(UserDetail idUserDetailFk) {
        this.idUserDetailFk = idUserDetailFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.User[idUser=" + idUser + "]";
    }

}
