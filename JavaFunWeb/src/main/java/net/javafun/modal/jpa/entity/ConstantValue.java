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
import javax.validation.constraints.Size;

/**
 *
 * @author ozkansari
 */
@Entity
@Table(name = "constant_value", catalog = "javafun", schema = "")
@NamedQueries({
    @NamedQuery(name = "ConstantValue.findAll", query = "SELECT c FROM ConstantValue c"),
    @NamedQuery(name = "ConstantValue.findByIdConstantValue", query = "SELECT c FROM ConstantValue c WHERE c.idConstantValue = :idConstantValue"),
    @NamedQuery(name = "ConstantValue.findByValueInt", query = "SELECT c FROM ConstantValue c WHERE c.valueInt = :valueInt"),
    @NamedQuery(name = "ConstantValue.findByValueText", query = "SELECT c FROM ConstantValue c WHERE c.valueText = :valueText")})
public class ConstantValue implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @NotNull
    @Id
    private Integer idConstantValue;
    
    @NotNull
    private int valueInt;
    
    @NotNull
    @Size(min = 1, max = 45)
    private String valueText;
    
    private List<UserDetail> userDetailListByCountry;
    private List<UserDetail> userDetailListByCity;
    
    private ConstantType idTypeFk;

    public ConstantValue() {
    }

    public ConstantValue(Integer idConstantValue) {
        this.idConstantValue = idConstantValue;
    }

    public ConstantValue(Integer idConstantValue, int valueInt, String valueText) {
        this.idConstantValue = idConstantValue;
        this.valueInt = valueInt;
        this.valueText = valueText;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_constant_value", nullable = false)
    public Integer getIdConstantValue() {
        return idConstantValue;
    }

    public void setIdConstantValue(Integer idConstantValue) {
        this.idConstantValue = idConstantValue;
    }

    @Basic(optional = false)
    @Column(name = "valueInt", nullable = false)
    public int getValueInt() {
        return valueInt;
    }

    public void setValueInt(int valueInt) {
        this.valueInt = valueInt;
    }

    @Basic(optional = false)
    @Column(name = "valueText", nullable = false, length = 45)
    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCountryFk", fetch = FetchType.LAZY)
    public List<UserDetail> getUserDetailListByCountry() {
        return userDetailListByCountry;
    }

    public void setUserDetailListByCountry(List<UserDetail> userDetailListByCountry) {
        this.userDetailListByCountry = userDetailListByCountry;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCityFk", fetch = FetchType.LAZY)
    public List<UserDetail> getUserDetailListByCity() {
        return userDetailListByCity;
    }

    public void setUserDetailListByCity(List<UserDetail> userDetailListByCity) {
        this.userDetailListByCity = userDetailListByCity;
    }

    @JoinColumn(name = "id_type_fk", referencedColumnName = "id_constant_type", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public ConstantType getIdTypeFk() {
        return idTypeFk;
    }

    public void setIdTypeFk(ConstantType idTypeFk) {
        this.idTypeFk = idTypeFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConstantValue != null ? idConstantValue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConstantValue)) {
            return false;
        }
        ConstantValue other = (ConstantValue) object;
        if ((this.idConstantValue == null && other.idConstantValue != null) || (this.idConstantValue != null && !this.idConstantValue.equals(other.idConstantValue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.javafun.modal.jpa.entity.ConstantValue[ idConstantValue=" + idConstantValue + " ]";
    }
    
}
