/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Ticket.findByBookingDate", query = "SELECT t FROM Ticket t WHERE t.bookingDate = :bookingDate"),
    @NamedQuery(name = "Ticket.findByStatus", query = "SELECT t FROM Ticket t WHERE t.status = :status")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTicket")
    private Integer idTicket;
    @Column(name = "bookingDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Ngày đi không được bỏ trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bookingDate;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "idCoachStripCoachSeat", referencedColumnName = "idCSCS")
    @ManyToOne
    @JsonIgnore
    private Coachstripcoachseat idCoachStripCoachSeat;
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer")
    @ManyToOne
    @JsonIgnore
    private Customer idCustomer;
    @JoinColumn(name = "idStaff", referencedColumnName = "idStaff")
    @ManyToOne
    @JsonIgnore
    private Staff idStaff;
    @JoinColumn(name = "idStationBuy", referencedColumnName = "idStations")
    @ManyToOne
    @JsonIgnore
    private Stations idStationBuy;

    @Transient
    private String formattedBookingDate;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Coachstripcoachseat getIdCoachStripCoachSeat() {
        return idCoachStripCoachSeat;
    }

    public void setIdCoachStripCoachSeat(Coachstripcoachseat idCoachStripCoachSeat) {
        this.idCoachStripCoachSeat = idCoachStripCoachSeat;
    }

    public Customer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Customer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Staff getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(Staff idStaff) {
        this.idStaff = idStaff;
    }

    public Stations getIdStationBuy() {
        return idStationBuy;
    }

    public void setIdStationBuy(Stations idStationBuy) {
        this.idStationBuy = idStationBuy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Ticket[ idTicket=" + idTicket + " ]";
    }

    /**
     * @return the formattedBookingDate
     */
    public String getFormattedBookingDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (bookingDate != null) {
            return dateFormat.format(bookingDate);
        }
        return "";
    }

    /**
     * @param formattedBookingDate the formattedBookingDate to set
     */
    public void setFormattedBookingDate(String formattedBookingDate) {
        this.formattedBookingDate = formattedBookingDate;
    }

}
