/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "coachstrips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coachstrips.findAll", query = "SELECT c FROM Coachstrips c"),
    @NamedQuery(name = "Coachstrips.findByIdCoachStrips", query = "SELECT c FROM Coachstrips c WHERE c.idCoachStrips = :idCoachStrips"),
    @NamedQuery(name = "Coachstrips.findByDistance", query = "SELECT c FROM Coachstrips c WHERE c.distance = :distance")})
public class Coachstrips implements Serializable {

    @Size(max = 50)
    @Column(name = "nameCS")
    private String nameCS;

    @OneToMany(mappedBy = "idCoachStrips")
    private Collection<Garagetakestrips> garagetakestripsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCoachStrips")
    private Integer idCoachStrips;
    @Column(name = "distance")
    private Integer distance;
    @OneToMany(mappedBy = "idCoachStrips")
    @JsonIgnore
    private Set<Coachstripcoachseat> coachstripcoachseatSet;
    @OneToMany(mappedBy = "idCoachStrips")
    @JsonIgnore
    private Set<Transporttruck> transporttruckSet;
    @JoinColumn(name = "idStationsStart", referencedColumnName = "idStations")
    @ManyToOne
    @JsonIgnore
    private Stations idStationsStart;
    @JoinColumn(name = "idStationsEnd", referencedColumnName = "idStations")
    @ManyToOne
    @JsonIgnore
    private Stations idStationsEnd;

    public Coachstrips() {
    }

    public Coachstrips(Integer idCoachStrips) {
        this.idCoachStrips = idCoachStrips;
    }

    public Integer getIdCoachStrips() {
        return idCoachStrips;
    }

    public void setIdCoachStrips(Integer idCoachStrips) {
        this.idCoachStrips = idCoachStrips;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @XmlTransient
    public Set<Coachstripcoachseat> getCoachstripcoachseatSet() {
        return coachstripcoachseatSet;
    }

    public void setCoachstripcoachseatSet(Set<Coachstripcoachseat> coachstripcoachseatSet) {
        this.coachstripcoachseatSet = coachstripcoachseatSet;
    }

    @XmlTransient
    public Set<Transporttruck> getTransporttruckSet() {
        return transporttruckSet;
    }

    public void setTransporttruckSet(Set<Transporttruck> transporttruckSet) {
        this.transporttruckSet = transporttruckSet;
    }

    public Stations getIdStationsStart() {
        return idStationsStart;
    }

    public void setIdStationsStart(Stations idStationsStart) {
        this.idStationsStart = idStationsStart;
    }

    public Stations getIdStationsEnd() {
        return idStationsEnd;
    }

    public void setIdStationsEnd(Stations idStationsEnd) {
        this.idStationsEnd = idStationsEnd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoachStrips != null ? idCoachStrips.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coachstrips)) {
            return false;
        }
        Coachstrips other = (Coachstrips) object;
        if ((this.idCoachStrips == null && other.idCoachStrips != null) || (this.idCoachStrips != null && !this.idCoachStrips.equals(other.idCoachStrips))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Coachstrips[ idCoachStrips=" + idCoachStrips + " ]";
    }

    public String getNameCS() {
        return nameCS;
    }

    public void setNameCS(String nameCS) {
        this.nameCS = nameCS;
    }

 
    
}
