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
@Table(name = "comment")
@NamedQueries({@NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"), @NamedQuery(name = "Comment.findByIdComment", query = "SELECT c FROM Comment c WHERE c.idComment = :idComment"), @NamedQuery(name = "Comment.findByIdObjectFk", query = "SELECT c FROM Comment c WHERE c.idObjectFk = :idObjectFk"), @NamedQuery(name = "Comment.findByText", query = "SELECT c FROM Comment c WHERE c.text = :text"), @NamedQuery(name = "Comment.findByDateCreated", query = "SELECT c FROM Comment c WHERE c.dateCreated = :dateCreated"), @NamedQuery(name = "Comment.findByIsApproved", query = "SELECT c FROM Comment c WHERE c.isApproved = :isApproved")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comment")
    private Integer idComment;
    @Column(name = "id_object_fk")
    private Integer idObjectFk;
    @Basic(optional = false)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "is_approved")
    private boolean isApproved;
    @JoinColumn(name = "id_creator_fk", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idCreatorFk;
    @JoinColumn(name = "id_type_fk", referencedColumnName = "id_constant_type")
    @ManyToOne(optional = false)
    private ConstantType idTypeFk;

    public Comment() {
    }

    public Comment(Integer idComment) {
        this.idComment = idComment;
    }

    public Comment(Integer idComment, String text, Date dateCreated, boolean isApproved) {
        this.idComment = idComment;
        this.text = text;
        this.dateCreated = dateCreated;
        this.isApproved = isApproved;
    }

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public Integer getIdObjectFk() {
        return idObjectFk;
    }

    public void setIdObjectFk(Integer idObjectFk) {
        this.idObjectFk = idObjectFk;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public User getIdCreatorFk() {
        return idCreatorFk;
    }

    public void setIdCreatorFk(User idCreatorFk) {
        this.idCreatorFk = idCreatorFk;
    }

    public ConstantType getIdTypeFk() {
        return idTypeFk;
    }

    public void setIdTypeFk(ConstantType idTypeFk) {
        this.idTypeFk = idTypeFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sarkinidinlet.jpa.entity.Comment[idComment=" + idComment + "]";
    }

}
