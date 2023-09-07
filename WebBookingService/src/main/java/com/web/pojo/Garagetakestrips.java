/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "garagetakestrips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Garagetakestrips.findAll", query = "SELECT g FROM Garagetakestrips g"),
    @NamedQuery(name = "Garagetakestrips.findByIdGTS", query = "SELECT g FROM Garagetakestrips g WHERE g.idGTS = :idGTS"),
    @NamedQuery(name = "Garagetakestrips.findByInfo", query = "SELECT g FROM Garagetakestrips g WHERE g.info = :info")})
public class Garagetakestrips implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGTS")
    private Integer idGTS;
    @Size(max = 255)
    @Column(name = "info")
    private String info;
    @JoinColumn(name = "idCoachStrips", referencedColumnName = "idCoachStrips")
    @ManyToOne
    @JsonIgnore
    private Coachstrips idCoachStrips;
    @JoinColumn(name = "idGarage", referencedColumnName = "idGarage")
    @ManyToOne
    @JsonIgnore
    private Garage idGarage;

    public Garagetakestrips() {
    }

    public Garagetakestrips(Integer idGTS) {
        this.idGTS = idGTS;
    }

    public Integer getIdGTS() {
        return idGTS;
    }

    public void setIdGTS(Integer idGTS) {
        this.idGTS = idGTS;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Coachstrips getIdCoachStrips() {
        return idCoachStrips;
    }

    public void setIdCoachStrips(Coachstrips idCoachStrips) {
        this.idCoachStrips = idCoachStrips;
    }

    public Garage getIdGarage() {
        return idGarage;
    }

    public void setIdGarage(Garage idGarage) {
        this.idGarage = idGarage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGTS != null ? idGTS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Garagetakestrips)) {
            return false;
        }
        Garagetakestrips other = (Garagetakestrips) object;
        if ((this.idGTS == null && other.idGTS != null) || (this.idGTS != null && !this.idGTS.equals(other.idGTS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Garagetakestrips[ idGTS=" + idGTS + " ]";
    }
    
}
