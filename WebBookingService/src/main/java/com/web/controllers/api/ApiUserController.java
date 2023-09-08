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

    @RequestMapping(value = "/api/user/{id}/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Staff> getUserById(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.staffService.getStaffById(id),
                HttpStatus.OK);
    }

//    @RequestMapping(value = "/api/current-user/", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<UserDTO> getCurrentUserAndRoles(Principal user) {
//        UserDTO userDTO = new UserDTO();
//
//        Staff staff = this.staffService.getCurrentStaff(user.getName());
//        if (staff != null) {
//            userDTO.setStaff(staff);
//            userDTO.setRoles(staff.getRoles()); // Bổ sung vai trò vào DTO
//        }
//
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }

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

}
