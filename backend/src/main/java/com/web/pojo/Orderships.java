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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "orderships")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderships.findAll", query = "SELECT o FROM Orderships o"),
    @NamedQuery(name = "Orderships.findByIdOrderShips", query = "SELECT o FROM Orderships o WHERE o.idOrderShips = :idOrderShips"),
    @NamedQuery(name = "Orderships.findByIdStaff", query = "SELECT o FROM Orderships o WHERE o.idStaff = :idStaff"),
    @NamedQuery(name = "Orderships.findByDateOrder", query = "SELECT o FROM Orderships o WHERE o.dateOrder = :dateOrder"),
    @NamedQuery(name = "Orderships.findByStatusOrder", query = "SELECT o FROM Orderships o WHERE o.statusOrder = :statusOrder"),
    @NamedQuery(name = "Orderships.findByAmount", query = "SELECT o FROM Orderships o WHERE o.amount = :amount"),
    @NamedQuery(name = "Orderships.findByAmountSent", query = "SELECT o FROM Orderships o WHERE o.amountSent = :amountSent")})
public class Orderships implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrderShips")
    private Integer idOrderShips;
    @JoinColumn(name = "idStaff", referencedColumnName = "idStaff")
    @ManyToOne
    @JsonIgnore
    private Staff idStaff;
    @Column(name = "statusOrder")
    @NotNull(message = "Không được bỏ trống")
    private Integer statusOrder;
    @Column(name = "amount")
    @NotNull(message = "Không được bỏ trống")
    @Min(value = 1, message = "Phải lớn hơn 0")
    @Max(value = 4, message = "Phải bé hơn 5")
    private Integer amount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amountSent")
    @NotNull(message = "Không được bỏ trống")
    private BigDecimal amountSent;
    @OneToMany(mappedBy = "idOrderShip")
    @JsonIgnore
    private Set<Product> productSet;
    @JoinColumn(name = "idCusSender", referencedColumnName = "idCustomer")
    @ManyToOne
    @JsonIgnore
    private Customer idCusSender;
    @JoinColumn(name = "idCusAccpect", referencedColumnName = "idCustomer")
    @ManyToOne
    @JsonIgnore
    private Customer idCusAccpect;
    @JoinColumn(name = "idTrans", referencedColumnName = "idTrans")
    @ManyToOne
    @JsonIgnore
    private Transporttruck idTrans;
    @Column(name = "dateOrder")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Ngày vận chuyển không được bỏ trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOrder;
    @JoinColumn(name = "idStationBuy", referencedColumnName = "idStations")
    @ManyToOne
    @JsonIgnore
    private Stations idStationBuy;

//    private BigDecimal priceTran;
    @Transient
    private String formattedDateOrder;

    public Orderships() {
    }

    public Orderships(Integer idOrderShips) {
        this.idOrderShips = idOrderShips;
    }

    public Integer getIdOrderShips() {
        return idOrderShips;
    }

    public void setIdOrderShips(Integer idOrderShips) {
        this.idOrderShips = idOrderShips;
    }

//    public Integer getIdStaff() {
//        return idStaff;
//    }
//
//    public void setIdStaff(Integer idStaff) {
//        this.idStaff = idStaff;
//    }
    public Integer getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(Integer statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(BigDecimal amountSent) {
        this.amountSent = amountSent;
    }

    @XmlTransient
    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public Customer getIdCusSender() {
        return idCusSender;
    }

    public void setIdCusSender(Customer idCusSender) {
        this.idCusSender = idCusSender;
    }

    public Customer getIdCusAccpect() {
        return idCusAccpect;
    }

    public void setIdCusAccpect(Customer idCusAccpect) {
        this.idCusAccpect = idCusAccpect;
    }

    public Transporttruck getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(Transporttruck idTrans) {
        this.idTrans = idTrans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrderShips != null ? idOrderShips.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderships)) {
            return false;
        }
        Orderships other = (Orderships) object;
        if ((this.idOrderShips == null && other.idOrderShips != null) || (this.idOrderShips != null && !this.idOrderShips.equals(other.idOrderShips))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Orderships[ idOrderShips=" + idOrderShips + " ]";
    }

    /**
     * @return the formattedDateOrder
     */
    public String getFormattedDateOrder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (dateOrder != null) {
            return dateFormat.format(dateOrder);
        }
        return "";
    }

    /**
     * @param formattedDateOrder the formattedDateOrder to set
     */
    public void setFormattedDateOrder(String formattedDateOrder) {
        this.formattedDateOrder = formattedDateOrder;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Stations getIdStationBuy() {
        return idStationBuy;
    }

    public void setIdStationBuy(Stations idStationBuy) {
        this.idStationBuy = idStationBuy;
    }

    /**
     * @return the idStaff
     */
    public Staff getIdStaff() {
        return idStaff;
    }

    /**
     * @param idStaff the idStaff to set
     */
    public void setIdStaff(Staff idStaff) {
        this.idStaff = idStaff;
    }

}
