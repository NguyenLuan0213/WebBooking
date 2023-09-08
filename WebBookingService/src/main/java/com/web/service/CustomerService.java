/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.service;

import com.web.pojo.Customer;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface CustomerService extends UserDetailsService{
    List<Customer> getCustomer(Map<String, String>params);
    int countCustomer();
    boolean addOrUpdateCustomer(Customer cus);
    Customer getCustomerById(int idCustomer);
    boolean deleteCustomer(int idCustomer);
    List<Customer> getAllCustomer();
    boolean isCustomerUser(String userName);
    Customer findCustomerByName(String name);
    
    Customer getCurrentCustomer(String username);
    List<Customer> getCustomer(String username);
    boolean existUsername(String username);
    boolean authCustomer(String username, String password);
}
