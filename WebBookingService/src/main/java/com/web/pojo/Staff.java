/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "staff",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"phone"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findByIdStaff", query = "SELECT s FROM Staff s WHERE s.idStaff = :idStaff"),
    @NamedQuery(name = "Staff.findByPassWord", query = "SELECT s FROM Staff s WHERE s.passWord = :passWord"),
    @NamedQuery(name = "Staff.findByUserName", query = "SELECT s FROM Staff s WHERE s.userName = :userName"),
    @NamedQuery(name = "Staff.findByAddressUser", query = "SELECT s FROM Staff s WHERE s.addressUser = :addressUser"),
    @NamedQuery(name = "Staff.findByNameStaff", query = "SELECT s FROM Staff s WHERE s.nameStaff = :nameStaff"),
    @NamedQuery(name = "Staff.findByGender", query = "SELECT s FROM Staff s WHERE s.gender = :gender"),
    @NamedQuery(name = "Staff.findByPhone", query = "SELECT s FROM Staff s WHERE s.phone = :phone"),
    @NamedQuery(name = "Staff.findByBrithStaff", query = "SELECT s FROM Staff s WHERE s.brithStaff = :brithStaff")})
public class Staff implements Serializable {

    @Size(max = 255)
    @Column(name = "passWord")
    private String passWord;
    @Size(max = 255)
    @Column(name = "userName")
    private String userName;
    @Size(max = 255)
    @Column(name = "addressUser")
    private String addressUser;
    @Size(max = 255)
    @Column(name = "nameStaff")
    private String nameStaff;
    @Size(max = 255)
    @Column(name = "gender")
    private String gender;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 250)
    @Column(name = "imgStaff")
    private String imgStaff;
    @JoinColumn(name = "roles", referencedColumnName = "nameRoles")
    @ManyToOne
    @JsonIgnore
    private Roles roles;

    @OneToMany(mappedBy = "idStaffDrive")
    @JsonIgnore
    private List<Transporttruck> transporttruckList;
    @OneToMany(mappedBy = "idStaff")
    @JsonIgnore
    private Set<Orderships> ordershipsSet;
    @OneToMany(mappedBy = "idStaff")
    @JsonIgnore
    private Collection<Garage> garageCollection;

    private static final long serialVersionUID = 1L;
    public static final String ADMIN = "ADMIN";
    public static final String STAFF = "STAFF";
    public static final String OWNER = "OWNER";
    public static final String DRIVER = "DRIVER";
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStaff")
    private Integer idStaff;
    @Column(name = "brithStaff")
    @Temporal(TemporalType.DATE)
    private Date brithStaff;
    @OneToMany(mappedBy = "idStaff")
    @JsonIgnore
    private Set<Ticket> ticketSet;
    @OneToMany(mappedBy = "idStaff")
    @JsonIgnore
    private Set<Coachstripcoachseat> coachstripcoachseatSet;
    @Transient
    private String formattedBrithStaff;
    @Transient
    private boolean isValidUsername;
    @Transient
    private MultipartFile file;

    public Staff() {
    }

    public String getFormattedBrithStaff() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (brithStaff != null) {
            return dateFormat.format(brithStaff);
        }
        return "";
    }

    public boolean getIsValidUsername() {
        return isValidUsername;
    }

    public void setIsValidUsername(boolean isValidUsername) {
        this.isValidUsername = isValidUsername;
    }

    public void setFormattedBrithStaff(String formattedBrithStaff) {
        this.formattedBrithStaff = formattedBrithStaff;
    }

    public Staff(Integer idStaff) {
        this.idStaff = idStaff;
    }

    public Integer getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(Integer idStaff) {
        this.idStaff = idStaff;
    }

    public Date getBrithStaff() {
        return brithStaff;
    }

    public void setBrithStaff(Date brithStaff) {
        this.brithStaff = brithStaff;
    }

    @XmlTransient
    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }



    @XmlTransient
    public Set<Coachstripcoachseat> getCoachstripcoachseatSet() {
        return coachstripcoachseatSet;
    }

    public void setCoachstripcoachseatSet(Set<Coachstripcoachseat> coachstripcoachseatSet) {
        this.coachstripcoachseatSet = coachstripcoachseatSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStaff != null ? idStaff.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.idStaff == null && other.idStaff != null) || (this.idStaff != null && !this.idStaff.equals(other.idStaff))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.web.pojo.Staff[ idStaff=" + idStaff + " ]";
    }

    @XmlTransient
    public Collection<Garage> getGarageCollection() {
        return garageCollection;
    }

    public void setGarageCollection(Collection<Garage> garageCollection) {
        this.garageCollection = garageCollection;
    }
    
    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    @XmlTransient
    public Set<Orderships> getOrdershipsSet() {
        return ordershipsSet;
    }
    public void setOrdershipsSet(Set<Orderships> ordershipsSet) {
        this.ordershipsSet = ordershipsSet;
    }
    @XmlTransient
    public List<Transporttruck> getTransporttruckList() {
        return transporttruckList;
    }
    public void setTransporttruckList(List<Transporttruck> transporttruckList) {
        this.transporttruckList = transporttruckList;
    }


    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImgStaff() {
        return imgStaff;
    }

    public void setImgStaff(String imgStaff) {
        this.imgStaff = imgStaff;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

}
