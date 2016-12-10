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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ozkansari
 */
@Entity
@Table(name = "constant_type", catalog = "javafun", schema = "")
@NamedQueries({
    @NamedQuery(name = "ConstantType.findAll", query = "SELECT c FROM ConstantType c"),
    @NamedQuery(name = "ConstantType.findByIdConstantType", query = "SELECT c FROM ConstantType c WHERE c.idConstantType = :idConstantType"),
    @NamedQuery(name = "ConstantType.findByTypeName", query = "SELECT c FROM ConstantType c WHERE c.typeName = :typeName")})
public class ConstantType implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @Id
    private Integer idConstantType;
    
    @NotNull
    @Size(min = 1, max = 45)
    private String typeName;
        
    private List<ConstantValue> constantValueList;

    public ConstantType() {
    }

    public ConstantType(Integer idConstantType) {
        this.idConstantType = idConstantType;
    }

    public ConstantType(Integer idConstantType, String typeName) {
        this.idConstantType = idConstantType;
        this.typeName = typeName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_constant_type", nullable = false)
    public Integer getIdConstantType() {
        return idConstantType;
    }

    public void setIdConstantType(Integer idConstantType) {
        this.idConstantType = idConstantType;
    }

    @Basic(optional = false)
    @Column(name = "type_name", nullable = false, length = 45)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeFk", fetch = FetchType.LAZY)
    public List<ConstantValue> getConstantValueList() {
        return constantValueList;
    }

    public void setConstantValueList(List<ConstantValue> constantValueList) {
        this.constantValueList = constantValueList;
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
        return "net.javafun.modal.jpa.entity.ConstantType[ idConstantType=" + idConstantType + " ]";
    }
    
}
