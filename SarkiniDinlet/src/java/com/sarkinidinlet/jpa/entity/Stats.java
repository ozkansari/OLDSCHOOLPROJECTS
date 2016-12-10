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
@Table(name = "stats")
@NamedQueries({@NamedQuery(name = "Stats.findAll", query = "SELECT s FROM Stats s"), @NamedQuery(name = "Stats.findByIdStats", query = "SELECT s FROM Stats s WHERE s.idStats = :idStats"), @NamedQuery(name = "Stats.findByViewCount", query = "SELECT s FROM Stats s WHERE s.viewCount = :viewCount"), @NamedQuery(name = "Stats.findByPointCount", query = "SELECT s FROM Stats s WHERE s.pointCount = :pointCount"), @NamedQuery(name = "Stats.findByVoterCount", query = "SELECT s FROM Stats s WHERE s.voterCount = :voterCount"), @NamedQuery(name = "Stats.findByCommentCount", query = "SELECT s FROM Stats s WHERE s.commentCount = :commentCount"), @NamedQuery(name = "Stats.findByEditorRating", query = "SELECT s FROM Stats s WHERE s.editorRating = :editorRating")})
public class Stats implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stats")
    private Integer idStats;
    @Column(name = "view_count")
    private Integer viewCount;
    @Column(name = "point_count")
    private Integer pointCount;
    @Column(name = "voter_count")
    private Integer voterCount;
    @Column(name = "comment_count")
    private Integer commentCount;
    @Column(name = "editor_rating")
    private Integer editorRating;
    @OneToMany(mappedBy = "idStatsFk")
    private Collection<Clip> clipCollection;
    @OneToMany(mappedBy = "idStatsFk")
    private Collection<Track> trackCollection;
    @OneToMany(mappedBy = "idStatsFk")
    private Collection<Photo> photoCollection;
    @OneToMany(mappedBy = "idStatsFk")
    private Collection<Record> recordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatsFk")
    private Collection<Article> articleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatsFk")
    private Collection<PhotoAlbum> photoAlbumCollection;
    @OneToMany(mappedBy = "idStatsFk")
    private Collection<User> userCollection;

    public Stats() {
    }

    public Stats(Integer idStats) {
        this.idStats = idStats;
    }

    public Integer getIdStats() {
        return idStats;
    }

    public void setIdStats(Integer idStats) {
        this.idStats = idStats;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getPointCount() {
        return pointCount;
    }

    public void setPointCount(Integer pointCount) {
        this.pointCount = pointCount;
    }

    public Integer getVoterCount() {
        return voterCount;
    }

    public void setVoterCount(Integer voterCount) {
        this.voterCount = voterCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getEditorRating() {
        return editorRating;
    }

    public void setEditorRating(Integer editorRating) {
        this.editorRating = editorRating;
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

    public Collection<Photo> getPhotoCollection() {
        return photoCollection;
    }

    public void setPhotoCollection(Collection<Photo> photoCollection) {
        this.photoCollection = photoCollection;
    }

    public Collection<Record> getRecordCollection() {
        return recordCollection;
    }

    public void setRecordCollection(Collection<Record> recordCollection) {
        this.recordCollection = recordCollection;
    }

    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    public Collection<PhotoAlbum> getPhotoAlbumCollection() {
        return photoAlbumCollection;
    }

    public void setPhotoAlbumCollection(Collection<PhotoAlbum> photoAlbumCollection) {
        this.photoAlbumCollection = photoAlbumCollection;
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
        hash += (idStats != null ? idStats.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stats)) {
            return false;
        }
        Stats other = (Stats) object;
        if ((this.idStats == null && other.idStats != null) || (this.idStats != null && !this.idStats.equals(other.idStats))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Stats[idStats=" + idStats + "]";
    }

}
