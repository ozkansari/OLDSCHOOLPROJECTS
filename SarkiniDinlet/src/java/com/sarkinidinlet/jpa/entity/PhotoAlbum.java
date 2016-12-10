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
@Table(name = "photo_album")
@NamedQueries({@NamedQuery(name = "PhotoAlbum.findAll", query = "SELECT p FROM PhotoAlbum p"), @NamedQuery(name = "PhotoAlbum.findByIdPhotoAlbum", query = "SELECT p FROM PhotoAlbum p WHERE p.idPhotoAlbum = :idPhotoAlbum"), @NamedQuery(name = "PhotoAlbum.findByTitle", query = "SELECT p FROM PhotoAlbum p WHERE p.title = :title"), @NamedQuery(name = "PhotoAlbum.findByInfo", query = "SELECT p FROM PhotoAlbum p WHERE p.info = :info"), @NamedQuery(name = "PhotoAlbum.findByDateCreated", query = "SELECT p FROM PhotoAlbum p WHERE p.dateCreated = :dateCreated"), @NamedQuery(name = "PhotoAlbum.findByIsApproved", query = "SELECT p FROM PhotoAlbum p WHERE p.isApproved = :isApproved")})
public class PhotoAlbum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_photo_album")
    private Integer idPhotoAlbum;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "info")
    private String info;
    @Column(name = "dateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "is_approved")
    private boolean isApproved;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPhotoAlbumFk")
    private Collection<Photo> photoCollection;
    @JoinColumn(name = "id_stats_fk", referencedColumnName = "id_stats")
    @ManyToOne(optional = false)
    private Stats idStatsFk;

    public PhotoAlbum() {
    }

    public PhotoAlbum(Integer idPhotoAlbum) {
        this.idPhotoAlbum = idPhotoAlbum;
    }

    public PhotoAlbum(Integer idPhotoAlbum, String title, boolean isApproved) {
        this.idPhotoAlbum = idPhotoAlbum;
        this.title = title;
        this.isApproved = isApproved;
    }

    public Integer getIdPhotoAlbum() {
        return idPhotoAlbum;
    }

    public void setIdPhotoAlbum(Integer idPhotoAlbum) {
        this.idPhotoAlbum = idPhotoAlbum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Collection<Photo> getPhotoCollection() {
        return photoCollection;
    }

    public void setPhotoCollection(Collection<Photo> photoCollection) {
        this.photoCollection = photoCollection;
    }

    public Stats getIdStatsFk() {
        return idStatsFk;
    }

    public void setIdStatsFk(Stats idStatsFk) {
        this.idStatsFk = idStatsFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhotoAlbum != null ? idPhotoAlbum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhotoAlbum)) {
            return false;
        }
        PhotoAlbum other = (PhotoAlbum) object;
        if ((this.idPhotoAlbum == null && other.idPhotoAlbum != null) || (this.idPhotoAlbum != null && !this.idPhotoAlbum.equals(other.idPhotoAlbum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.PhotoAlbum[idPhotoAlbum=" + idPhotoAlbum + "]";
    }

}
