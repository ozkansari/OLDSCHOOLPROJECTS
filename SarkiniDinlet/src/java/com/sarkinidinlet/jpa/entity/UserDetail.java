/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Özkan SARI
 */
@Entity
@Table(name = "user_detail")
@NamedQueries({@NamedQuery(name = "UserDetail.findAll", query = "SELECT u FROM UserDetail u"), @NamedQuery(name = "UserDetail.findByIdUserDetail", query = "SELECT u FROM UserDetail u WHERE u.idUserDetail = :idUserDetail"), @NamedQuery(name = "UserDetail.findByEmail1", query = "SELECT u FROM UserDetail u WHERE u.email1 = :email1"), @NamedQuery(name = "UserDetail.findByEmail2", query = "SELECT u FROM UserDetail u WHERE u.email2 = :email2"), @NamedQuery(name = "UserDetail.findByAddress", query = "SELECT u FROM UserDetail u WHERE u.address = :address"), @NamedQuery(name = "UserDetail.findByBirthDate", query = "SELECT u FROM UserDetail u WHERE u.birthDate = :birthDate"), @NamedQuery(name = "UserDetail.findByDateCreated", query = "SELECT u FROM UserDetail u WHERE u.dateCreated = :dateCreated"), @NamedQuery(name = "UserDetail.findByLastLogin", query = "SELECT u FROM UserDetail u WHERE u.lastLogin = :lastLogin"), @NamedQuery(name = "UserDetail.findByPoint", query = "SELECT u FROM UserDetail u WHERE u.point = :point")})
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_detail")
    private Integer idUserDetail;
    @Column(name = "email1")
    private String email1;
    @Column(name = "email2")
    private String email2;
    @Column(name = "address")
    private String address;
    @Column(name = "birth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "point")
    private Integer point;
    @JoinColumn(name = "id_city_fk", referencedColumnName = "id_constant")
    @ManyToOne
    private Constant idCityFk;
    @JoinColumn(name = "id_country_fk", referencedColumnName = "id_constant")
    @ManyToOne
    private Constant idCountryFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserDetailFk")
    private Collection<User> userCollection;

    public UserDetail() {
    }

    public UserDetail(Integer idUserDetail) {
        this.idUserDetail = idUserDetail;
    }

    public UserDetail(Integer idUserDetail, Date dateCreated, Date lastLogin) {
        this.idUserDetail = idUserDetail;
        this.dateCreated = dateCreated;
        this.lastLogin = lastLogin;
    }

    public Integer getIdUserDetail() {
        return idUserDetail;
    }

    public void setIdUserDetail(Integer idUserDetail) {
        this.idUserDetail = idUserDetail;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Constant getIdCityFk() {
        return idCityFk;
    }

    public void setIdCityFk(Constant idCityFk) {
        this.idCityFk = idCityFk;
    }

    public Constant getIdCountryFk() {
        return idCountryFk;
    }

    public void setIdCountryFk(Constant idCountryFk) {
        this.idCountryFk = idCountryFk;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserDetail != null ? idUserDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDetail)) {
            return false;
        }
        UserDetail other = (UserDetail) object;
        if ((this.idUserDetail == null && other.idUserDetail != null) || (this.idUserDetail != null && !this.idUserDetail.equals(other.idUserDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.UserDetail[idUserDetail=" + idUserDetail + "]";
    }

}
