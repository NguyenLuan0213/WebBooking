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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByIdCustomer", query = "SELECT c FROM Customer c WHERE c.idCustomer = :idCustomer"),
    @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name"),
    @NamedQuery(name = "Customer.findByPhoneNumber", query = "SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Customer.findByAddressCus", query = "SELECT c FROM Customer c WHERE c.addressCus = :addressCus")})
public class Customer implements Serializable {

    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Size(max = 255)
    @Column(name = "addressCus")
    private String addressCus;
    @Size(max = 255)
    @Column(name = "userName")
    private String userName;
    @Size(max = 255)
    @Column(name = "passWord")
    private String passWord;

    private static final long serialVersionUID = 1L;
    
    public static final String CUSTOMER = "CUSTOMER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCustomer")
    private Integer idCustomer;
    @OneToMany(mappedBy = "idCustomer")
    @JsonIgnore
    private Set<Comments> commentsSet;
    @OneToMany(mappedBy = "idCusSender")
    @JsonIgnore
    private Set<Orderships> ordershipsSet;
    @OneToMany(mappedBy = "idCusAccpect")
    @JsonIgnore
    private Set<Orderships> ordershipsSet1;
    @OneToMany(mappedBy = "idCustomer")
    @JsonIgnore
    private Set<Ticket> ticketSet;

    @Transient
    private boolean isValidUsername;

    public Customer() {
    }

    public Customer(Integer idCustomer, String name, String phoneNumber, String userName, String passWord, String addressCus) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.addressCus = addressCus;
    }

    public Customer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    @XmlTransient
    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }

    @XmlTransient
    public Set<Orderships> getOrdershipsSet() {
        return ordershipsSet;
    }

    public void setOrdershipsSet(Set<Orderships> ordershipsSet) {
        this.ordershipsSet = ordershipsSet;
    }

    @XmlTransient
    public Set<Orderships> getOrdershipsSet1() {
        return ordershipsSet1;
    }

    public void setOrdershipsSet1(Set<Orderships> ordershipsSet1) {
        this.ordershipsSet1 = ordershipsSet1;
    }

    @XmlTransient
    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCustomer != null ? idCustomer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.idCustomer == null && other.idCustomer != null) || (this.idCustomer != null && !this.idCustomer.equals(other.idCustomer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Customer[ idCustomer=" + idCustomer + " ]";
    }

    public boolean isIsValidUsername() {
        return isValidUsername;
    }

    public void setIsValidUsername(boolean isValidUsername) {
        this.isValidUsername = isValidUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressCus() {
        return addressCus;
    }

    public void setAddressCus(String addressCus) {
        this.addressCus = addressCus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
