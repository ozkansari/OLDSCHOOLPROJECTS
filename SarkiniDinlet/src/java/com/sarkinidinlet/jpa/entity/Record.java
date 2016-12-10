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
@Table(name = "record")
@NamedQueries({@NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r"), @NamedQuery(name = "Record.findByIdRecord", query = "SELECT r FROM Record r WHERE r.idRecord = :idRecord"), @NamedQuery(name = "Record.findByDateCreated", query = "SELECT r FROM Record r WHERE r.dateCreated = :dateCreated"), @NamedQuery(name = "Record.findByAlbumTitle", query = "SELECT r FROM Record r WHERE r.albumTitle = :albumTitle"), @NamedQuery(name = "Record.findBySoldCount", query = "SELECT r FROM Record r WHERE r.soldCount = :soldCount"), @NamedQuery(name = "Record.findByIsApproved", query = "SELECT r FROM Record r WHERE r.isApproved = :isApproved")})
public class Record implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_record")
    private Integer idRecord;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "album_title")
    private String albumTitle;
    @Column(name = "sold_count")
    private Integer soldCount;
    @Basic(optional = false)
    @Column(name = "is_approved")
    private boolean isApproved;
    @OneToMany(mappedBy = "idRecordFk")
    private Collection<Track> trackCollection;
    @JoinColumn(name = "id_artist_fk", referencedColumnName = "id_artist")
    @ManyToOne(optional = false)
    private Artist idArtistFk;
    @JoinColumn(name = "id_stats_fk", referencedColumnName = "id_stats")
    @ManyToOne
    private Stats idStatsFk;
    @JoinColumn(name = "id_photo_fk", referencedColumnName = "id_photo")
    @ManyToOne
    private Photo idPhotoFk;

    public Record() {
    }

    public Record(Integer idRecord) {
        this.idRecord = idRecord;
    }

    public Record(Integer idRecord, Date dateCreated, String albumTitle, boolean isApproved) {
        this.idRecord = idRecord;
        this.dateCreated = dateCreated;
        this.albumTitle = albumTitle;
        this.isApproved = isApproved;
    }

    public Integer getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(Integer idRecord) {
        this.idRecord = idRecord;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Integer getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Collection<Track> getTrackCollection() {
        return trackCollection;
    }

    public void setTrackCollection(Collection<Track> trackCollection) {
        this.trackCollection = trackCollection;
    }

    public Artist getIdArtistFk() {
        return idArtistFk;
    }

    public void setIdArtistFk(Artist idArtistFk) {
        this.idArtistFk = idArtistFk;
    }

    public Stats getIdStatsFk() {
        return idStatsFk;
    }

    public void setIdStatsFk(Stats idStatsFk) {
        this.idStatsFk = idStatsFk;
    }

    public Photo getIdPhotoFk() {
        return idPhotoFk;
    }

    public void setIdPhotoFk(Photo idPhotoFk) {
        this.idPhotoFk = idPhotoFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecord != null ? idRecord.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Record)) {
            return false;
        }
        Record other = (Record) object;
        if ((this.idRecord == null && other.idRecord != null) || (this.idRecord != null && !this.idRecord.equals(other.idRecord))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Record[idRecord=" + idRecord + "]";
    }

}
