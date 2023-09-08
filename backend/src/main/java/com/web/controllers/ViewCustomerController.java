/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;


import com.web.service.CustomerService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@ControllerAdvice
public class ViewCustomerController {
    
    @Autowired
    private Environment env;

    @Autowired
    private CustomerService cusService;

    @RequestMapping("/viewCustomer")
    public String viewCustomerController(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("customers", this.cusService.getCustomer(params));

        int count = this.cusService.countCustomer();
        int pageSize = Integer.parseInt(env.getProperty("PAGECUS_SIZE").toString());
        model.addAttribute("pages", Math.ceil(count * 1.0 / pageSize));

        return "viewCustomer";
    }
}
