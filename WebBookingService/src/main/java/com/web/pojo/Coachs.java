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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "coachs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coachs.findAll", query = "SELECT c FROM Coachs c"),
    @NamedQuery(name = "Coachs.findByIdCoach", query = "SELECT c FROM Coachs c WHERE c.idCoach = :idCoach"),
    @NamedQuery(name = "Coachs.findByNumberCoach", query = "SELECT c FROM Coachs c WHERE c.numberCoach = :numberCoach"),
    @NamedQuery(name = "Coachs.findByImgCoach", query = "SELECT c FROM Coachs c WHERE c.imgCoach = :imgCoach"),
    @NamedQuery(name = "Coachs.findByCapacity", query = "SELECT c FROM Coachs c WHERE c.capacity = :capacity")})
public class Coachs implements Serializable {

    @Size(max = 255)
    @Column(name = "imgCoach")
    private String imgCoach;
    @JoinColumn(name = "idGarage", referencedColumnName = "idGarage")
    @ManyToOne
    @JsonIgnore
    private Garage idGarage;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCoach")
    private Integer idCoach;
    @Column(name = "numberCoach")
    private Integer numberCoach;
    @Column(name = "capacity")
    private Integer capacity;
    @JoinColumn(name = "typeOfCoach", referencedColumnName = "nameTypeOfCoach")
    @ManyToOne
    @JsonIgnore
    private Typeofcoach typeOfCoach;
    @OneToMany(mappedBy = "idCoach")
    @JsonIgnore
    private Set<Coachstripcoachseat> coachstripcoachseatSet;
    @OneToMany(mappedBy = "idCoach")
    @JsonIgnore
    private Set<Transporttruck> transporttruckSet;
    @Transient
    private MultipartFile file;

    public Coachs() {
    }

    public Coachs(Integer idCoach) {
        this.idCoach = idCoach;
    }

    public Integer getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Integer idCoach) {
        this.idCoach = idCoach;
    }

    public Integer getNumberCoach() {
        return numberCoach;
    }

    public void setNumberCoach(Integer numberCoach) {
        this.numberCoach = numberCoach;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Typeofcoach getTypeOfCoach() {
        return typeOfCoach;
    }

    public void setTypeOfCoach(Typeofcoach typeOfCoach) {
        this.typeOfCoach = typeOfCoach;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoach != null ? idCoach.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coachs)) {
            return false;
        }
        Coachs other = (Coachs) object;
        if ((this.idCoach == null && other.idCoach != null) || (this.idCoach != null && !this.idCoach.equals(other.idCoach))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Coachs[ idCoach=" + idCoach + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Garage getIdGarage() {
        return idGarage;
    }

    public void setIdGarage(Garage idGarage) {
        this.idGarage = idGarage;
    }

    public String getImgCoach() {
        return imgCoach;
    }

    public void setImgCoach(String imgCoach) {
        this.imgCoach = imgCoach;
    }

}
