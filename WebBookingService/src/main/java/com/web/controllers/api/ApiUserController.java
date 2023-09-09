/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers.api;

import com.cloudinary.Cloudinary;
import com.web.components.JWTService;
import com.web.pojo.Customer;
import com.web.pojo.LoginRequest;
import com.web.pojo.Staff;
import com.web.pojo.UserDTO;
import com.web.service.CustomerService;
import com.web.service.StaffService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000/"})
public class ApiUserController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private JWTService jWTService;
    @Autowired
    private Cloudinary cloud;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Xác định loại người dùng dựa trên thông tin đăng nhập
        if (this.staffService.authUser(username, password) || this.customerService.authCustomer(username, password)) {
            String token = this.jWTService.genarateTokenLogin(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/api/current-user/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Staff> currentUser(Principal user) {
        Staff sta = this.staffService.getCurrentStaff(user.getName());
        return new ResponseEntity<>(sta, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/current-customer/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> currentCustomer(Principal user) {
        Customer cus = this.customerService.getCurrentCustomer(user.getName());
        return new ResponseEntity<>(cus, HttpStatus.OK);
    }

    // API để lấy danh sách nhân viên với tùy chọn tìm kiếm và phân trang
    @RequestMapping(value = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Staff>> getStaffs(@RequestParam Map<String, String> params) {
        return new ResponseEntity<List<Staff>>(staffService.getAllStaff(), HttpStatus.OK);
    }

    // API để lấy số lượng tổng cộng của nhân viên
    @RequestMapping(value = "/api/user/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> countStaff() {
        int count = staffService.countStaff();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // API để thêm hoặc cập nhật thông tin nhân viên
    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity<String> addOrUpdateStaff(@RequestBody Staff staff) {
        boolean success = staffService.addOrUpdateStaff(staff);
        if (success) {
            return new ResponseEntity<>("Staff added/updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add/update Staff", HttpStatus.BAD_REQUEST);
        }
    }

    // API để lấy thông tin nhân viên theo ID
    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.POST)
    public ResponseEntity<Staff> getStaffById(@PathVariable(value = "id") int idStaff) {
        Staff staff = staffService.getStaffById(idStaff);
        if (staff != null) {
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // API để xóa nhân viên theo ID
    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserAPI(@PathVariable(value = "id") int id) {
        boolean isDeleted = this.staffService.deleteStaff(id);
        if (isDeleted) {
            return new ResponseEntity<>("DELETE USER SUCCESS", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("DELETE USER FAILED", HttpStatus.BAD_REQUEST);
    }

}
