/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers.api;

import com.cloudinary.Cloudinary;
import com.web.components.JWTService;
import com.web.pojo.Customer;
import com.web.pojo.Staff;
import com.web.service.CustomerService;
import com.web.service.StaffService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class ApiUserController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private JWTService jWTService;
    @Autowired
    private Cloudinary cloud;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/api/login/staff", method = RequestMethod.POST)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public ResponseEntity<String> staffLogin(@RequestBody Staff staff) {
        if (this.staffService.authUser(staff.getUserName(), staff.getPassWord())) {
            String token = this.jWTService.genarateTokenLogin(staff.getUserName());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/api/login/customer", method = RequestMethod.POST)
    @CrossOrigin(origins = {"http://localhost:3000/"})
    public ResponseEntity<String> customerLogin(@RequestBody Customer customer) {
        if (this.customerService.authCustomer(customer.getUserName(), customer.getPassWord())) {
            String token = this.jWTService.genarateTokenLogin(customer.getUserName());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CrossOrigin(origins = {"http://localhost:3000/"})
    @RequestMapping(value = "/api/user/{id}/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Staff> getStaffById(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.staffService.getStaffById(id),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/api/current-user/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> currentUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user instanceof Staff) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else if (user instanceof Customer) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>("Unknown user type", HttpStatus.UNAUTHORIZED);
    }

}
