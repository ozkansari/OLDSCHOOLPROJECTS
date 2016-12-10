/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javafun.modal.jpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author ozkansari
 */
@Entity
@Table(name = "user_info", catalog = "javafun", schema = "")
@NamedQueries({
    @NamedQuery(name = "UserInfo.findAll", query = "SELECT u FROM UserInfo u"),
    @NamedQuery(name = "UserInfo.findByUserId", query = "SELECT u FROM UserInfo u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserInfo.findByUsername", query = "SELECT u FROM UserInfo u WHERE u.username = :username"),
    @NamedQuery(name = "UserInfo.findByPwd", query = "SELECT u FROM UserInfo u WHERE u.pwd = :pwd"),
    @NamedQuery(name = "UserInfo.findByEmail", query = "SELECT u FROM UserInfo u WHERE u.email = :email")})
public class UserInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @Id
    private Integer userId;
    
    @NotNull
    @Size(min = 1, max = 64)
    private String username;
    
    @NotNull
    @Size(min = 1, max = 64)
    private String pwd;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")
    @NotNull
    @Size(min = 1, max = 45)
    private String email;
    
    private List<Blog> blogList;
    
    private List<Link> linksList;
    
    private UserDetail idUserDetailFk;

    public UserInfo() {
    }

    public UserInfo(Integer userId) {
        this.userId = userId;
    }

    public UserInfo(String username, String pwd, String email) {
        this.username = username;
        this.pwd = pwd;
        this.email = email;
    }
    
    public UserInfo(Integer userId, String username, String pwd, String email) {
        this.userId = userId;
        this.username = username;
        this.pwd = pwd;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 64)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic(optional = false)
    @Column(name = "pwd", nullable = false, length = 64)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo", fetch = FetchType.LAZY)
	public List<Link> getLinks() {
		return this.linksList;
	}

	public void setLinks(List<Link> linksList) {
		this.linksList = linksList;
	}

    @JoinColumn(name = "id_user_detail_fk", referencedColumnName = "id_user_detail")
    @ManyToOne(fetch = FetchType.LAZY)
    public UserDetail getIdUserDetailFk() {
        return idUserDetailFk;
    }

    public void setIdUserDetailFk(UserDetail idUserDetailFk) {
        this.idUserDetailFk = idUserDetailFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInfo)) {
            return false;
        }
        UserInfo other = (UserInfo) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.javafun.modal.jpa.entity.UserInfo[ userId=" + userId + " ]";
    }
    
}
