/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.pojo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByNameRoles", query = "SELECT r FROM Roles r WHERE r.nameRoles = :nameRoles")})
public class Roles implements Serializable {

    @OneToMany(mappedBy = "roles")
    private Collection<Staff> staffCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nameRoles")
    private String nameRoles;

    public Roles() {
    }

    public Roles(String nameRoles) {
        this.nameRoles = nameRoles;
    }

    public String getNameRoles() {
        return nameRoles;
    }

    public void setNameRoles(String nameRoles) {
        this.nameRoles = nameRoles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nameRoles != null ? nameRoles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.nameRoles == null && other.nameRoles != null) || (this.nameRoles != null && !this.nameRoles.equals(other.nameRoles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Roles[ nameRoles=" + nameRoles + " ]";
    }

    @XmlTransient
    public Collection<Staff> getStaffCollection() {
        return staffCollection;
    }

    public void setStaffCollection(Collection<Staff> staffCollection) {
        this.staffCollection = staffCollection;
    }
    
}
