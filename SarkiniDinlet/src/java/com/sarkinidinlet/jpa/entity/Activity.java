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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Özkan SARI
 */
@Entity
@Table(name = "activity")
@NamedQueries({@NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"), @NamedQuery(name = "Activity.findByIdActivity", query = "SELECT a FROM Activity a WHERE a.idActivity = :idActivity"), @NamedQuery(name = "Activity.findByType", query = "SELECT a FROM Activity a WHERE a.type = :type"), @NamedQuery(name = "Activity.findByValue", query = "SELECT a FROM Activity a WHERE a.value = :value")})
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_activity")
    private Integer idActivity;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "value")
    private int value;

    public Activity() {
    }

    public Activity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public Activity(Integer idActivity, String type, int value) {
        this.idActivity = idActivity;
        this.type = type;
        this.value = value;
    }

    public Integer getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivity != null ? idActivity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.idActivity == null && other.idActivity != null) || (this.idActivity != null && !this.idActivity.equals(other.idActivity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Activity[idActivity=" + idActivity + "]";
    }

}
