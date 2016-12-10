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
@Table(name = "artist")
@NamedQueries({@NamedQuery(name = "Artist.findAll", query = "SELECT a FROM Artist a"), @NamedQuery(name = "Artist.findByIdArtist", query = "SELECT a FROM Artist a WHERE a.idArtist = :idArtist"), @NamedQuery(name = "Artist.findByName", query = "SELECT a FROM Artist a WHERE a.name = :name"), @NamedQuery(name = "Artist.findByInfo", query = "SELECT a FROM Artist a WHERE a.info = :info"), @NamedQuery(name = "Artist.findByDateCreated", query = "SELECT a FROM Artist a WHERE a.dateCreated = :dateCreated"), @NamedQuery(name = "Artist.findByIsApproved", query = "SELECT a FROM Artist a WHERE a.isApproved = :isApproved")})
public class Artist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_artist")
    private Integer idArtist;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "info")
    private String info;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "is_approved")
    private boolean isApproved;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtistFk")
    private Collection<Clip> clipCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtistFk")
    private Collection<Track> trackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArtistFk")
    private Collection<Record> recordCollection;
    @JoinColumn(name = "music_type_fk", referencedColumnName = "id_constant")
    @ManyToOne(optional = false)
    private Constant musicTypeFk;
    @JoinColumn(name = "id_photo_fk", referencedColumnName = "id_photo")
    @ManyToOne
    private Photo idPhotoFk;
    @OneToMany(mappedBy = "idArtistFk")
    private Collection<User> userCollection;

    public Artist() {
    }

    public Artist(Integer idArtist) {
        this.idArtist = idArtist;
    }

    public Artist(Integer idArtist, String name, Date dateCreated, boolean isApproved) {
        this.idArtist = idArtist;
        this.name = name;
        this.dateCreated = dateCreated;
        this.isApproved = isApproved;
    }

    public Integer getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(Integer idArtist) {
        this.idArtist = idArtist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Collection<Clip> getClipCollection() {
        return clipCollection;
    }

    public void setClipCollection(Collection<Clip> clipCollection) {
        this.clipCollection = clipCollection;
    }

    public Collection<Track> getTrackCollection() {
        return trackCollection;
    }

    public void setTrackCollection(Collection<Track> trackCollection) {
        this.trackCollection = trackCollection;
    }

    public Collection<Record> getRecordCollection() {
        return recordCollection;
    }

    public void setRecordCollection(Collection<Record> recordCollection) {
        this.recordCollection = recordCollection;
    }

    public Constant getMusicTypeFk() {
        return musicTypeFk;
    }

    public void setMusicTypeFk(Constant musicTypeFk) {
        this.musicTypeFk = musicTypeFk;
    }

    public Photo getIdPhotoFk() {
        return idPhotoFk;
    }

    public void setIdPhotoFk(Photo idPhotoFk) {
        this.idPhotoFk = idPhotoFk;
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
        hash += (idArtist != null ? idArtist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artist)) {
            return false;
        }
        Artist other = (Artist) object;
        if ((this.idArtist == null && other.idArtist != null) || (this.idArtist != null && !this.idArtist.equals(other.idArtist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Artist[idArtist=" + idArtist + "]";
    }

}
