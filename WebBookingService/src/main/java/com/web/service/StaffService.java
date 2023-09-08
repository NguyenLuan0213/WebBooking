/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.service;

import com.web.pojo.Staff;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 *
 * @author Admin
 */
public interface StaffService extends UserDetailsService{
    List<Staff> getStaffs(Map<String, String>params);
    int countStaff();
    boolean addOrUpdateStaff(Staff staf);
    Staff getStaffById(int idStaff);
    boolean deleteStaff(int idStaff);
    boolean isPhoneAlreadyExists(String phone, Integer idStaff);
    List<Staff> getAllStaff();
    boolean isStaffUser(String userName);
    
    
    Staff getCurrentStaff(String username);
    List<Staff> getStaff(String username);
    List<Object[]> getUserByStaffRole(String staffRole);
    boolean existUsername(String username);
    boolean authUser(String username, String password);
    List<Object[]> getUserByUserRoleAndName(String userRole, String name);
}
