/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javafun.modal.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ozkansari
 */
@Entity
@Table(name = "project", catalog = "javafun", schema = "")
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByIdProject", query = "SELECT p FROM Project p WHERE p.idProject = :idProject"),
    @NamedQuery(name = "Project.findByProjectName", query = "SELECT p FROM Project p WHERE p.projectName = :projectName"),
    @NamedQuery(name = "Project.findByDescription", query = "SELECT p FROM Project p WHERE p.description = :description")})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @Id
    private Integer idProject;
    
    @NotNull
    @Size(min = 1, max = 128)
    private String projectName;
    
    @NotNull
    @Size(min = 1, max = 1024)
    private String description;

    public Project() {
    }

    public Project(Integer idProject) {
        this.idProject = idProject;
    }

    public Project(Integer idProject, String projectName, String description) {
        this.idProject = idProject;
        this.projectName = projectName;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_project", nullable = false)
    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    @Basic(optional = false)
    @Column(name = "project_name", nullable = false, length = 128)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProject != null ? idProject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.idProject == null && other.idProject != null) || (this.idProject != null && !this.idProject.equals(other.idProject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.javafun.modal.jpa.entity.Project[ idProject=" + idProject + " ]";
    }
    
}
