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
@Table(name = "user_role")
@NamedQueries({@NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u"), @NamedQuery(name = "UserRole.findByIdUserRole", query = "SELECT u FROM UserRole u WHERE u.idUserRole = :idUserRole"), @NamedQuery(name = "UserRole.findByRoleName", query = "SELECT u FROM UserRole u WHERE u.roleName = :roleName"), @NamedQuery(name = "UserRole.findByRoleDefinition", query = "SELECT u FROM UserRole u WHERE u.roleDefinition = :roleDefinition")})
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_role")
    private Integer idUserRole;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_definition")
    private String roleDefinition;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRoleFk")
    private Collection<User> userCollection;

    public UserRole() {
    }

    public UserRole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    public Integer getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDefinition() {
        return roleDefinition;
    }

    public void setRoleDefinition(String roleDefinition) {
        this.roleDefinition = roleDefinition;
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
        hash += (idUserRole != null ? idUserRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRole)) {
            return false;
        }
        UserRole other = (UserRole) object;
        if ((this.idUserRole == null && other.idUserRole != null) || (this.idUserRole != null && !this.idUserRole.equals(other.idUserRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.UserRole[idUserRole=" + idUserRole + "]";
    }

}
