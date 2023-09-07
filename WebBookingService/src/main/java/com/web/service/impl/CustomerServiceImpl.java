/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Customer;
import com.web.repository.CustomerRepository;
import com.web.service.CustomerService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@Service("cusUserDetailsService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository cusRepo;

    @Override
    public List<Customer> getCustomer(Map<String, String> params) {
        return this.cusRepo.getCustomer(params);
    }

    @Override
    public int countCustomer() {
        return this.cusRepo.countCustomer();
    }

    @Override
    public boolean addOrUpdateCustomer(Customer cus) {
        return this.cusRepo.addOrUpdateCustomer(cus);
    }

    @Override
    public Customer getCustomerById(int idCustomer) {
        return this.cusRepo.getCustomerById(idCustomer);
    }

    @Override
    public boolean deleteCustomer(int idCustomer) {
        return this.cusRepo.deleteCustomer(idCustomer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return this.cusRepo.getAllCustomer();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer cus = this.cusRepo.getCustomerByUserName(userName);
        if (cus == null) {
            throw new UsernameNotFoundException("Invalid user!");
        }

        // Tạo một danh sách các quyền trống cho tài khoản khách hàng
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("CUSTOMER"));

        return new org.springframework.security.core.userdetails.User(
                cus.getUserName(), cus.getPassWord(), authorities);
    }

    @Override
    public boolean isCustomerUser(String userName) {
        Customer customerUser = cusRepo.getCustomerByUserName(userName);
        return customerUser != null;
    }

    @Override
    public Customer findCustomerByName(String name) {
        return this.cusRepo.findCustomerByName(name);
    }

    @Override
    public Customer getCurrentCustomer(String username) {
        return this.cusRepo.getCurrentCustomer(username);
    }

    @Override
    public List<Customer> getCustomer(String username) {
        return this.cusRepo.getCustomer(username);
    }

    @Override
    public boolean existUsername(String username) {
        return this.cusRepo.existUsername(username);
    }

    @Override
    public boolean authCustomer(String username, String password) {
        return this.cusRepo.authCustomer(username, password);
    }

}
