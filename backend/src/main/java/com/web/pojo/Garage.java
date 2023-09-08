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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "garage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Garage.findAll", query = "SELECT g FROM Garage g"),
    @NamedQuery(name = "Garage.findByIdGarage", query = "SELECT g FROM Garage g WHERE g.idGarage = :idGarage"),
    @NamedQuery(name = "Garage.findByNameGara", query = "SELECT g FROM Garage g WHERE g.nameGara = :nameGara"),
    @NamedQuery(name = "Garage.findByAddressGarage", query = "SELECT g FROM Garage g WHERE g.addressGarage = :addressGarage")})
public class Garage implements Serializable {

    @Size(max = 255)
    @Column(name = "nameGara")
    private String nameGara;
    @Size(max = 255)
    @Column(name = "addressGarage")
    private String addressGarage;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGarage")
    private Integer idGarage;
    @OneToMany(mappedBy = "idGarage")
    @JsonIgnore
    private Set<Coachs> coachsSet;
    @OneToMany(mappedBy = "idGarage")
    @JsonIgnore
    private Set<Comments> commentsSet;
    @OneToMany(mappedBy = "idGarage")
    @JsonIgnore
    private Set<Garagetakestrips> garagetakestripsSet;
    @JoinColumn(name = "idStaff", referencedColumnName = "idStaff")
    @ManyToOne
    @JsonIgnore
    private Staff idStaff;
    @OneToMany(mappedBy = "idGarage")
    @JsonIgnore
    private Set<Transporttruck> transporttruckSet;

    public Garage() {
    }

    public Garage(Integer idGarage) {
        this.idGarage = idGarage;
    }

    public Integer getIdGarage() {
        return idGarage;
    }

    public void setIdGarage(Integer idGarage) {
        this.idGarage = idGarage;
    }

    @XmlTransient
    public Set<Coachs> getCoachsSet() {
        return coachsSet;
    }

    public void setCoachsSet(Set<Coachs> coachsSet) {
        this.coachsSet = coachsSet;
    }

    @XmlTransient
    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }

    @XmlTransient
    public Set<Garagetakestrips> getGaragetakestripsSet() {
        return garagetakestripsSet;
    }

    public void setGaragetakestripsSet(Set<Garagetakestrips> garagetakestripsSet) {
        this.garagetakestripsSet = garagetakestripsSet;
    }

    public Staff getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(Staff idStaff) {
        this.idStaff = idStaff;
    }

    @XmlTransient
    public Set<Transporttruck> getTransporttruckSet() {
        return transporttruckSet;
    }

    public void setTransporttruckSet(Set<Transporttruck> transporttruckSet) {
        this.transporttruckSet = transporttruckSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGarage != null ? idGarage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Garage)) {
            return false;
        }
        Garage other = (Garage) object;
        if ((this.idGarage == null && other.idGarage != null) || (this.idGarage != null && !this.idGarage.equals(other.idGarage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Garage[ idGarage=" + idGarage + " ]";
    }

    public String getNameGara() {
        return nameGara;
    }

    public void setNameGara(String nameGara) {
        this.nameGara = nameGara;
    }

    public String getAddressGarage() {
        return addressGarage;
    }

    public void setAddressGarage(String addressGarage) {
        this.addressGarage = addressGarage;
    }

}
