/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.repository;

import com.web.pojo.Staff;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Admin
 */
public interface StaffRepository {
    List<Staff> getStaffs(Map<String, String>params);
    int countStaff();
    boolean addOrUpdateStaff(Staff staf);
    Staff getStaffById(int idStaff);
    boolean deleteStaff(int idStaff);
    Staff getStaffByUserName(String userName);  
    boolean isPhoneAlreadyExists(String phone, Integer idStaff);
    List<Staff> getAllStaff();
    
    Staff getCurrentStaff(String username);
    List<Staff> getStaff(String username);
    List<Object[]> getUserByStaffRole(String staffRole);
    boolean existUsername(String username);
    boolean authUser(String username, String password);
    List<Object[]> getUserByUserRoleAndName(String userRole, String name);
}
