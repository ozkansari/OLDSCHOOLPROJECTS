package net.javafun.modal.jpa.entity;

// Generated Apr 11, 2011 2:19:11 AM by Hibernate Tools 3.3.0.GA

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Link generated by hbm2java
 */
@Entity
@Table(name = "link", catalog = "javafun")
public class Link implements java.io.Serializable {

	private static final long serialVersionUID = -3884616638605512728L;
	
	@Id
	@NotNull
	private Integer linkId;

	@NotNull
    @Size(min = 1, max = 256)
	private String description;
	
	@NotNull
    @Size(min = 1, max = 256)
	private String linkUrl;
	
	private UserInfo userInfo;

	public Link() {
	}

	public Link(UserInfo userInfo, String description, String linkUrl) {
		this.userInfo = userInfo;
		this.description = description;
		this.linkUrl = linkUrl;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Basic(optional = false)
	@Column(name = "link_id", unique = true, nullable = false)
	public Integer getLinkId() {
		return this.linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user_fk", referencedColumnName = "user_id", nullable = false)
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Basic(optional = false)
	@Column(name = "description", nullable = false, length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic(optional = false)
	@Column(name = "link_url", nullable = false, length = 256)
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (linkId != null ? linkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.linkId == null && other.linkId != null) || (this.linkId != null && !this.linkId.equals(other.linkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.javafun.modal.jpa.entity.Link[ linkId=" + linkId + " ]";
    }

}