/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sarkinidinlet.jpa.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Özkan SARI
 */
@Entity
@Table(name = "clip")
@NamedQueries({@NamedQuery(name = "Clip.findAll", query = "SELECT c FROM Clip c"), @NamedQuery(name = "Clip.findByIdClip", query = "SELECT c FROM Clip c WHERE c.idClip = :idClip"), @NamedQuery(name = "Clip.findByInfo", query = "SELECT c FROM Clip c WHERE c.info = :info"), @NamedQuery(name = "Clip.findByDateCreated", query = "SELECT c FROM Clip c WHERE c.dateCreated = :dateCreated"), @NamedQuery(name = "Clip.findByIsApproved", query = "SELECT c FROM Clip c WHERE c.isApproved = :isApproved"), @NamedQuery(name = "Clip.findByFileSrc", query = "SELECT c FROM Clip c WHERE c.fileSrc = :fileSrc")})
public class Clip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clip")
    private Integer idClip;
    @Column(name = "info")
    private String info;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "is_approved")
    private boolean isApproved;
    @Basic(optional = false)
    @Column(name = "file_src")
    private String fileSrc;
    @JoinColumn(name = "id_track_fk", referencedColumnName = "id_track")
    @ManyToOne(optional = false)
    private Track idTrackFk;
    @JoinColumn(name = "id_stats_fk", referencedColumnName = "id_stats")
    @ManyToOne
    private Stats idStatsFk;
    @JoinColumn(name = "id_artist_fk", referencedColumnName = "id_artist")
    @ManyToOne(optional = false)
    private Artist idArtistFk;

    public Clip() {
    }

    public Clip(Integer idClip) {
        this.idClip = idClip;
    }

    public Clip(Integer idClip, Date dateCreated, boolean isApproved, String fileSrc) {
        this.idClip = idClip;
        this.dateCreated = dateCreated;
        this.isApproved = isApproved;
        this.fileSrc = fileSrc;
    }

    public Integer getIdClip() {
        return idClip;
    }

    public void setIdClip(Integer idClip) {
        this.idClip = idClip;
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

    public String getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc;
    }

    public Track getIdTrackFk() {
        return idTrackFk;
    }

    public void setIdTrackFk(Track idTrackFk) {
        this.idTrackFk = idTrackFk;
    }

    public Stats getIdStatsFk() {
        return idStatsFk;
    }

    public void setIdStatsFk(Stats idStatsFk) {
        this.idStatsFk = idStatsFk;
    }

    public Artist getIdArtistFk() {
        return idArtistFk;
    }

    public void setIdArtistFk(Artist idArtistFk) {
        this.idArtistFk = idArtistFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClip != null ? idClip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clip)) {
            return false;
        }
        Clip other = (Clip) object;
        if ((this.idClip == null && other.idClip != null) || (this.idClip != null && !this.idClip.equals(other.idClip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Clip[idClip=" + idClip + "]";
    }

}
