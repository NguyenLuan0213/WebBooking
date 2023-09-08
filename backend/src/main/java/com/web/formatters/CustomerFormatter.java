/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.formatters;

import com.web.pojo.Customer;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class CustomerFormatter implements Formatter<Customer> {

    @Override
    public String print(Customer object, Locale locale) {
        return object.toString();
    }

    @Override
    public Customer parse(String text, Locale locale) throws ParseException {
        return new Customer(Integer.valueOf(text));
    }
    
}
