/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javafun.modal.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ozkansari
 */
@Entity
@Table(name = "blog", catalog = "javafun", schema = "")
@NamedQueries({
    @NamedQuery(name = "Blog.findAll", query = "SELECT b FROM Blog b"),
    @NamedQuery(name = "Blog.findByBlogId", query = "SELECT b FROM Blog b WHERE b.blogId = :blogId"),
    @NamedQuery(name = "Blog.findByTitle", query = "SELECT b FROM Blog b WHERE b.title = :title"),
    @NamedQuery(name = "Blog.findByDescription", query = "SELECT b FROM Blog b WHERE b.description = :description"),
    @NamedQuery(name = "Blog.findByCreated", query = "SELECT b FROM Blog b WHERE b.created = :created")})
public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @Id
    private Integer blogId;
    
    @NotNull
    @Size(min = 1, max = 128)
    private String title;
    
    @NotNull
    @Size(min = 1, max = 1024)
    private String description;
    
    @NotNull
    private Date created;
        
    private List<Article> articleList;
        
    private UserInfo userId;

    public Blog() {
    }

    public Blog(Integer blogId) {
        this.blogId = blogId;
    }

    public Blog(Integer blogId, String title, String description, Date created) {
        this.blogId = blogId;
        this.title = title;
        this.description = description;
        this.created = created;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "blog_id", nullable = false)
    @Id
    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 128)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic(optional = false)
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blogId", fetch = FetchType.LAZY)
    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public UserInfo getUserId() {
        return userId;
    }

    public void setUserId(UserInfo userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blogId != null ? blogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blog)) {
            return false;
        }
        Blog other = (Blog) object;
        if ((this.blogId == null && other.blogId != null) || (this.blogId != null && !this.blogId.equals(other.blogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.javafun.modal.jpa.entity.Blog[ blogId=" + blogId + " ]";
    }
    
}
