 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.web.pojo.Staff;
import com.web.repository.StaffRepository;
import com.web.service.StaffService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service("userDetailsService")
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        List<Staff> staffs = this.getStaff(userName);
        if (staffs.isEmpty()) {
            throw new UsernameNotFoundException("Không tồn tại tại username");
        }

        Staff s = staffs.get(0);
        Set<GrantedAuthority> authoritys = new HashSet<>();
        authoritys.add(new SimpleGrantedAuthority(s.getRoles().getNameRoles()));
        return new org.springframework.security.core.userdetails
                .User(s.getUserName(), s.getPassWord(), authoritys);
    }

    @Override
    public List<Staff> getStaffs(Map<String, String> params) {
        return this.staffRepo.getStaffs(params);
    }

    @Override
    public int countStaff() {
        return this.staffRepo.countStaff();
    }

    @Override
    public boolean addOrUpdateStaff(Staff staf) {
        if (!staf.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(staf.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                staf.setImgStaff(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(StaffServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.staffRepo.addOrUpdateStaff(staf);
    }

    @Override
    public Staff getStaffById(int idStaff) {
        return this.staffRepo.getStaffById(idStaff);
    }

    @Override
    public boolean deleteStaff(int idStaff) {
        return this.staffRepo.deleteStaff(idStaff);
    }

    @Override
    public boolean isPhoneAlreadyExists(String phone, Integer idStaff) {
        return this.staffRepo.isPhoneAlreadyExists(phone, idStaff);
    }

    @Override
    public List<Staff> getAllStaff() {
        return this.staffRepo.getAllStaff();
    }

    @Override
    public boolean isStaffUser(String userName) {
        Staff staffUser = staffRepo.getStaffByUserName(userName);
        return staffUser != null;
    }

    @Override
    public List<Staff> getStaff(String username) {
        return this.staffRepo.getStaff(username);
    }

    @Override
    public List<Object[]> getUserByStaffRole(String staffRole) {
        return this.staffRepo.getUserByStaffRole(staffRole);
    }

    @Override
    public boolean existUsername(String username) {
        return this.staffRepo.existUsername(username);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.staffRepo.authUser(username, password);
    }

    @Override
    public List<Object[]> getUserByUserRoleAndName(String userRole, String name) {
        return this.staffRepo.getUserByUserRoleAndName(userRole, name);
    }

    @Override
    public Staff getCurrentStaff(String username) {
        return this.staffRepo.getCurrentStaff(username);
    }

}
