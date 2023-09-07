/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.repository;

import com.web.pojo.Customer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface CustomerRepository {
    List<Customer> getCustomer(Map<String, String>params);
    int countCustomer();
    boolean addOrUpdateCustomer(Customer cus);
    Customer getCustomerById(int idCustomer);
    boolean deleteCustomer(int idCustomer);
    Customer getCustomerByUserName(String userName);
    List<Customer> getAllCustomer();
    Customer findCustomerByName(String name);
    
    Customer getCurrentCustomer(String username);
    List<Customer> getCustomer(String username);
    boolean existUsername(String username);
    boolean authCustomer(String username, String password);

}
