/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "photo")
@NamedQueries({@NamedQuery(name = "Photo.findAll", query = "SELECT p FROM Photo p"), @NamedQuery(name = "Photo.findByIdPhoto", query = "SELECT p FROM Photo p WHERE p.idPhoto = :idPhoto"), @NamedQuery(name = "Photo.findByTitle", query = "SELECT p FROM Photo p WHERE p.title = :title"), @NamedQuery(name = "Photo.findByInfo", query = "SELECT p FROM Photo p WHERE p.info = :info"), @NamedQuery(name = "Photo.findByDateCreated", query = "SELECT p FROM Photo p WHERE p.dateCreated = :dateCreated"), @NamedQuery(name = "Photo.findByIsApproved", query = "SELECT p FROM Photo p WHERE p.isApproved = :isApproved")})
public class Photo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_photo")
    private Integer idPhoto;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "info")
    private String info;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "is_approved")
    private boolean isApproved;
    @JoinColumn(name = "id_stats_fk", referencedColumnName = "id_stats")
    @ManyToOne
    private Stats idStatsFk;
    @JoinColumn(name = "id_photo_album_fk", referencedColumnName = "id_photo_album")
    @ManyToOne(optional = false)
    private PhotoAlbum idPhotoAlbumFk;
    @OneToMany(mappedBy = "idPhotoFk")
    private Collection<Record> recordCollection;
    @OneToMany(mappedBy = "idPhotoFk")
    private Collection<Artist> artistCollection;

    public Photo() {
    }

    public Photo(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    public Photo(Integer idPhoto, String title, Date dateCreated, boolean isApproved) {
        this.idPhoto = idPhoto;
        this.title = title;
        this.dateCreated = dateCreated;
        this.isApproved = isApproved;
    }

    public Integer getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
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

    public Stats getIdStatsFk() {
        return idStatsFk;
    }

    public void setIdStatsFk(Stats idStatsFk) {
        this.idStatsFk = idStatsFk;
    }

    public PhotoAlbum getIdPhotoAlbumFk() {
        return idPhotoAlbumFk;
    }

    public void setIdPhotoAlbumFk(PhotoAlbum idPhotoAlbumFk) {
        this.idPhotoAlbumFk = idPhotoAlbumFk;
    }

    public Collection<Record> getRecordCollection() {
        return recordCollection;
    }

    public void setRecordCollection(Collection<Record> recordCollection) {
        this.recordCollection = recordCollection;
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
        hash += (idPhoto != null ? idPhoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Photo)) {
            return false;
        }
        Photo other = (Photo) object;
        if ((this.idPhoto == null && other.idPhoto != null) || (this.idPhoto != null && !this.idPhoto.equals(other.idPhoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Photo[idPhoto=" + idPhoto + "]";
    }

}
