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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "coachstripcoachseat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coachstripcoachseat.findAll", query = "SELECT c FROM Coachstripcoachseat c"),
    @NamedQuery(name = "Coachstripcoachseat.findByIdCSCS", query = "SELECT c FROM Coachstripcoachseat c WHERE c.idCSCS = :idCSCS"),
    @NamedQuery(name = "Coachstripcoachseat.findByPrice", query = "SELECT c FROM Coachstripcoachseat c WHERE c.price = :price"),
    @NamedQuery(name = "Coachstripcoachseat.findByDepartureTime", query = "SELECT c FROM Coachstripcoachseat c WHERE c.departureTime = :departureTime"),
    @NamedQuery(name = "Coachstripcoachseat.findByStatusSeat", query = "SELECT c FROM Coachstripcoachseat c WHERE c.statusSeat = :statusSeat"),
    @NamedQuery(name = "Coachstripcoachseat.findByNameSeat", query = "SELECT c FROM Coachstripcoachseat c WHERE c.nameSeat = :nameSeat")})
public class Coachstripcoachseat implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "departureTime")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Ngày chạy không được bỏ trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departureTime;
    @Column(name = "statusSeat")
    @NotNull(message = "Không dược bỏ trống")
    private Integer statusSeat;
    @Column(name = "nameSeat")
    @NotNull(message = "Không dược bỏ trống")
    private Integer nameSeat;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCSCS")
    private Integer idCSCS;
    @OneToMany(mappedBy = "idCoachStripCoachSeat")
    @JsonIgnore
    private Set<Ticket> ticketSet;
    @JoinColumn(name = "idCoach", referencedColumnName = "idCoach")
    @ManyToOne
    private Coachs idCoach;
    @JoinColumn(name = "idCoachStrips", referencedColumnName = "idCoachStrips")
    @ManyToOne
    private Coachstrips idCoachStrips;
    @JoinColumn(name = "idStaff", referencedColumnName = "idStaff")
    @ManyToOne
//    @JsonIgnore
//    
    private Staff idStaff;
    @Transient
    private String formattedDepartureTime;

    public Coachstripcoachseat() {
    }

    //tạo định dạng 
    public String getFormattedDepartureTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (departureTime != null) {
            return dateFormat.format(departureTime);
        }
        return "";
    }

    public void setFormattedDepartureTime(String formattedDepartureTime) {
        this.formattedDepartureTime = formattedDepartureTime;
    }

    public Coachstripcoachseat(Integer idCSCS) {
        this.idCSCS = idCSCS;
    }

    public Integer getIdCSCS() {
        return idCSCS;
    }

    public void setIdCSCS(Integer idCSCS) {
        this.idCSCS = idCSCS;
    }


    @XmlTransient
    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
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

    public Staff getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(Staff idStaff) {
        this.idStaff = idStaff;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCSCS != null ? idCSCS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coachstripcoachseat)) {
            return false;
        }
        Coachstripcoachseat other = (Coachstripcoachseat) object;
        if ((this.idCSCS == null && other.idCSCS != null) || (this.idCSCS != null && !this.idCSCS.equals(other.idCSCS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Coachstripcoachseat[ idCSCS=" + idCSCS + " ]";
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getStatusSeat() {
        return statusSeat;
    }

    public void setStatusSeat(Integer statusSeat) {
        this.statusSeat = statusSeat;
    }

    public Integer getNameSeat() {
        return nameSeat;
    }

    public void setNameSeat(Integer nameSeat) {
        this.nameSeat = nameSeat;
    }

    /**
     * @param formattedDepartureTime the formattedDepartureTime to set
     */
}
