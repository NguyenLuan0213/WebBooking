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
@Table(name = "stations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stations.findAll", query = "SELECT s FROM Stations s"),
    @NamedQuery(name = "Stations.findByIdStations", query = "SELECT s FROM Stations s WHERE s.idStations = :idStations"),
    @NamedQuery(name = "Stations.findByName", query = "SELECT s FROM Stations s WHERE s.name = :name"),
    @NamedQuery(name = "Stations.findByAddress", query = "SELECT s FROM Stations s WHERE s.address = :address")})
public class Stations implements Serializable {

    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "idStationBuy")
    private Set<Orderships> ordershipsSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStations")
    private Integer idStations;
    @OneToMany(mappedBy = "idStationBuy")
    @JsonIgnore
    private Set<Ticket> ticketSet;
    @OneToMany(mappedBy = "idStationsStart")
    @JsonIgnore
    private Set<Coachstrips> coachstripsSet;
    @OneToMany(mappedBy = "idStationsEnd")
    @JsonIgnore
    private Set<Coachstrips> coachstripsSet1;

    public Stations() {
    }

    public Stations(Integer idStations) {
        this.idStations = idStations;
    }

    public Integer getIdStations() {
        return idStations;
    }

    public void setIdStations(Integer idStations) {
        this.idStations = idStations;
    }

    @XmlTransient
    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }

    @XmlTransient
    public Set<Coachstrips> getCoachstripsSet() {
        return coachstripsSet;
    }

    public void setCoachstripsSet(Set<Coachstrips> coachstripsSet) {
        this.coachstripsSet = coachstripsSet;
    }

    @XmlTransient
    public Set<Coachstrips> getCoachstripsSet1() {
        return coachstripsSet1;
    }

    public void setCoachstripsSet1(Set<Coachstrips> coachstripsSet1) {
        this.coachstripsSet1 = coachstripsSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStations != null ? idStations.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stations)) {
            return false;
        }
        Stations other = (Stations) object;
        if ((this.idStations == null && other.idStations != null) || (this.idStations != null && !this.idStations.equals(other.idStations))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Stations[ idStations=" + idStations + " ]";
    }


    @XmlTransient
    public Set<Orderships> getOrdershipsSet() {
        return ordershipsSet;
    }

    public void setOrdershipsSet(Set<Orderships> ordershipsSet) {
        this.ordershipsSet = ordershipsSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
