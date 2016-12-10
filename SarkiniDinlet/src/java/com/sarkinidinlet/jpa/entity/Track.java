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
@Table(name = "track")
@NamedQueries({@NamedQuery(name = "Track.findAll", query = "SELECT t FROM Track t"), @NamedQuery(name = "Track.findByIdTrack", query = "SELECT t FROM Track t WHERE t.idTrack = :idTrack"), @NamedQuery(name = "Track.findByDateCreated", query = "SELECT t FROM Track t WHERE t.dateCreated = :dateCreated"), @NamedQuery(name = "Track.findByTitle", query = "SELECT t FROM Track t WHERE t.title = :title"), @NamedQuery(name = "Track.findByInfo", query = "SELECT t FROM Track t WHERE t.info = :info"), @NamedQuery(name = "Track.findByIsApproved", query = "SELECT t FROM Track t WHERE t.isApproved = :isApproved"), @NamedQuery(name = "Track.findByFileSrc", query = "SELECT t FROM Track t WHERE t.fileSrc = :fileSrc")})
public class Track implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_track")
    private Integer idTrack;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "info")
    private String info;
    @Basic(optional = false)
    @Column(name = "is_approved")
    private boolean isApproved;
    @Basic(optional = false)
    @Column(name = "file_src")
    private String fileSrc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTrackFk")
    private Collection<Clip> clipCollection;
    @JoinColumn(name = "id_record_fk", referencedColumnName = "id_record")
    @ManyToOne
    private Record idRecordFk;
    @JoinColumn(name = "id_artist_fk", referencedColumnName = "id_artist")
    @ManyToOne(optional = false)
    private Artist idArtistFk;
    @JoinColumn(name = "id_stats_fk", referencedColumnName = "id_stats")
    @ManyToOne
    private Stats idStatsFk;

    public Track() {
    }

    public Track(Integer idTrack) {
        this.idTrack = idTrack;
    }

    public Track(Integer idTrack, Date dateCreated, String title, boolean isApproved, String fileSrc) {
        this.idTrack = idTrack;
        this.dateCreated = dateCreated;
        this.title = title;
        this.isApproved = isApproved;
        this.fileSrc = fileSrc;
    }

    public Integer getIdTrack() {
        return idTrack;
    }

    public void setIdTrack(Integer idTrack) {
        this.idTrack = idTrack;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc;
    }

    public Collection<Clip> getClipCollection() {
        return clipCollection;
    }

    public void setClipCollection(Collection<Clip> clipCollection) {
        this.clipCollection = clipCollection;
    }

    public Record getIdRecordFk() {
        return idRecordFk;
    }

    public void setIdRecordFk(Record idRecordFk) {
        this.idRecordFk = idRecordFk;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrack != null ? idTrack.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Track)) {
            return false;
        }
        Track other = (Track) object;
        if ((this.idTrack == null && other.idTrack != null) || (this.idTrack != null && !this.idTrack.equals(other.idTrack))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Track[idTrack=" + idTrack + "]";
    }

}
