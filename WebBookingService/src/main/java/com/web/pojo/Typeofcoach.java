/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
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
@Table(name = "typeofcoach")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeofcoach.findAll", query = "SELECT t FROM Typeofcoach t"),
    @NamedQuery(name = "Typeofcoach.findByNameTypeOfCoach", query = "SELECT t FROM Typeofcoach t WHERE t.nameTypeOfCoach = :nameTypeOfCoach")})
public class Typeofcoach implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nameTypeOfCoach")
    private String nameTypeOfCoach;
    @OneToMany(mappedBy = "typeOfCoach")
    @JsonIgnore
    private Set<Coachs> coachsSet;

    public Typeofcoach() {
    }

    public Typeofcoach(String nameTypeOfCoach) {
        this.nameTypeOfCoach = nameTypeOfCoach;
    }

    public String getNameTypeOfCoach() {
        return nameTypeOfCoach;
    }

    public void setNameTypeOfCoach(String nameTypeOfCoach) {
        this.nameTypeOfCoach = nameTypeOfCoach;
    }

    @XmlTransient
    public Set<Coachs> getCoachsSet() {
        return coachsSet;
    }

    public void setCoachsSet(Set<Coachs> coachsSet) {
        this.coachsSet = coachsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nameTypeOfCoach != null ? nameTypeOfCoach.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeofcoach)) {
            return false;
        }
        Typeofcoach other = (Typeofcoach) object;
        if ((this.nameTypeOfCoach == null && other.nameTypeOfCoach != null) || (this.nameTypeOfCoach != null && !this.nameTypeOfCoach.equals(other.nameTypeOfCoach))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Typeofcoach[ nameTypeOfCoach=" + nameTypeOfCoach + " ]";
    }
    
}
