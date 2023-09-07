/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "transporttruck")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transporttruck.findAll", query = "SELECT t FROM Transporttruck t"),
    @NamedQuery(name = "Transporttruck.findByIdTrans", query = "SELECT t FROM Transporttruck t WHERE t.idTrans = :idTrans"),
    @NamedQuery(name = "Transporttruck.findByDateTrans", query = "SELECT t FROM Transporttruck t WHERE t.dateTrans = :dateTrans"),
    @NamedQuery(name = "Transporttruck.findByAccpectSent", query = "SELECT t FROM Transporttruck t WHERE t.accpectSent = :accpectSent"),
    @NamedQuery(name = "Transporttruck.findByPriceTran", query = "SELECT t FROM Transporttruck t WHERE t.priceTran = :priceTran")})
public class Transporttruck implements Serializable {

    @JoinColumn(name = "idStaffDrive", referencedColumnName = "idStaff")
    @ManyToOne
    private Staff idStaffDrive;
    @Column(name = "dateTrans")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Ngày vận chuyển không được bỏ trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateTrans;
    @Column(name = "accpectSent")
    @NotNull(message = "không được bỏ trống")
    private Integer accpectSent;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "priceTran")
    @NotNull(message = "không được bỏ trống")
    private BigDecimal priceTran;

    @JoinColumn(name = "idGarage", referencedColumnName = "idGarage")
    @ManyToOne
    private Garage idGarage;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTrans")
    private Integer idTrans;
    @OneToMany(mappedBy = "idTrans",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Orderships> ordershipsSet;
    @JoinColumn(name = "idCoach", referencedColumnName = "idCoach")
    @ManyToOne
    @JsonIgnore
    private Coachs idCoach;
    @JoinColumn(name = "idCoachStrips", referencedColumnName = "idCoachStrips")
    @ManyToOne
    @JsonIgnore
    private Coachstrips idCoachStrips;
    @Transient
    private String formattedDateTrans;

    public Transporttruck() {
    }

    public Transporttruck(Integer idTrans) {
        this.idTrans = idTrans;
    }

    public Integer getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(Integer idTrans) {
        this.idTrans = idTrans;
    }


    @XmlTransient
    public Set<Orderships> getOrdershipsSet() {
        return ordershipsSet;
    }

    public void setOrdershipsSet(Set<Orderships> ordershipsSet) {
        this.ordershipsSet = ordershipsSet;
    }

    public Coachs getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(Coachs idCoach) {
        this.idCoach = idCoach;
    }

    public Coachstrips getIdCoachStrips() {
        return idCoachStrips;
    }

    public void setIdCoachStrips(Coachstrips idCoachStrips) {
        this.idCoachStrips = idCoachStrips;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrans != null ? idTrans.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transporttruck)) {
            return false;
        }
        Transporttruck other = (Transporttruck) object;
        if ((this.idTrans == null && other.idTrans != null) || (this.idTrans != null && !this.idTrans.equals(other.idTrans))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Transporttruck[ idTrans=" + idTrans + " ]";
    }

    public Garage getIdGarage() {
        return idGarage;
    }

    public void setIdGarage(Garage idGarage) {
        this.idGarage = idGarage;
    }


    /**
     * @return the formattedDateTrans
     */
    public String getFormattedDateTrans() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (dateTrans != null) {
            return dateFormat.format(dateTrans);
        }
        return "";
    }

    /**
     * @param formattedDateTrans the formattedDateTrans to set
     */
    public void setFormattedDateTrans(String formattedDateTrans) {
        this.formattedDateTrans = formattedDateTrans;
    }


    public Date getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans(Date dateTrans) {
        this.dateTrans = dateTrans;
    }

    public Integer getAccpectSent() {
        return accpectSent;
    }

    public void setAccpectSent(Integer accpectSent) {
        this.accpectSent = accpectSent;
    }

    public BigDecimal getPriceTran() {
        return priceTran;
    }

    public void setPriceTran(BigDecimal priceTran) {
        this.priceTran = priceTran;
    }


    public Staff getIdStaffDrive() {
        return idStaffDrive;
    }

    public void setIdStaffDrive(Staff idStaffDrive) {
        this.idStaffDrive = idStaffDrive;
    }

}
