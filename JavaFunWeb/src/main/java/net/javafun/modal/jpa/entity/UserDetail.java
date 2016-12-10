/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javafun.modal.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
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
@Table(name = "user_detail", catalog = "javafun", schema = "")
@NamedQueries({
    @NamedQuery(name = "UserDetail.findAll", query = "SELECT u FROM UserDetail u"),
    @NamedQuery(name = "UserDetail.findByIdUserDetail", query = "SELECT u FROM UserDetail u WHERE u.idUserDetail = :idUserDetail"),
    @NamedQuery(name = "UserDetail.findByEmailPersonal", query = "SELECT u FROM UserDetail u WHERE u.emailPersonal = :emailPersonal"),
    @NamedQuery(name = "UserDetail.findByEmailWork", query = "SELECT u FROM UserDetail u WHERE u.emailWork = :emailWork"),
    @NamedQuery(name = "UserDetail.findByCreated", query = "SELECT u FROM UserDetail u WHERE u.created = :created"),
    @NamedQuery(name = "UserDetail.findByUpdated", query = "SELECT u FROM UserDetail u WHERE u.updated = :updated"),
    @NamedQuery(name = "UserDetail.findByLastLogin", query = "SELECT u FROM UserDetail u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "UserDetail.findByGender", query = "SELECT u FROM UserDetail u WHERE u.gender = :gender")})
public class UserDetail implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @Id
    private Integer idUserDetail;
    
    @NotNull
    @Size(min = 1, max = 45)
    private String emailPersonal;
    
    @NotNull
    @Size(min = 1, max = 45)
    private String emailWork;
    
    @NotNull
    private Date created;
    
    @NotNull
    private Date updated;
    
    @NotNull
    private Date lastLogin;
    
    @NotNull
    private char gender;
    
    private ConstantValue idCountryFk;
    
    private ConstantValue idCityFk;
    
    private List<UserInfo> userList;

    public UserDetail() {
    }

    public UserDetail(Integer idUserDetail) {
        this.idUserDetail = idUserDetail;
    }

    public UserDetail(Integer idUserDetail, String emailPersonal, String emailWork, Date created, Date updated, Date lastLogin, char gender) {
        this.idUserDetail = idUserDetail;
        this.emailPersonal = emailPersonal;
        this.emailWork = emailWork;
        this.created = created;
        this.updated = updated;
        this.lastLogin = lastLogin;
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_detail", nullable = false)
    public Integer getIdUserDetail() {
        return idUserDetail;
    }

    public void setIdUserDetail(Integer idUserDetail) {
        this.idUserDetail = idUserDetail;
    }

    @Basic(optional = false)
    @Column(name = "email_personal", nullable = false, length = 45)
    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    @Basic(optional = false)
    @Column(name = "email_work", nullable = false, length = 45)
    public String getEmailWork() {
        return emailWork;
    }

    public void setEmailWork(String emailWork) {
        this.emailWork = emailWork;
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
    @Column(name = "last_login", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic(optional = false)
    @Column(name = "gender", nullable = false)
    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @JoinColumn(name = "id_country_fk", referencedColumnName = "id_constant_value", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public ConstantValue getIdCountryFk() {
        return idCountryFk;
    }

    public void setIdCountryFk(ConstantValue idCountryFk) {
        this.idCountryFk = idCountryFk;
    }

    @JoinColumn(name = "id_city_fk", referencedColumnName = "id_constant_value", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public ConstantValue getIdCityFk() {
        return idCityFk;
    }

    public void setIdCityFk(ConstantValue idCityFk) {
        this.idCityFk = idCityFk;
    }

    @OneToMany(mappedBy = "idUserDetailFk", fetch = FetchType.LAZY)
    public List<UserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
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
        return "net.javafun.modal.jpa.entity.UserDetail[ idUserDetail=" + idUserDetail + " ]";
    }
    
}
