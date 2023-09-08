/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Customer;
import com.web.service.CustomerService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/viewCustomer")
public class CustomerController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerService cusService;

    @GetMapping("/admin/customers")
    public String list(Model model) {
        model.addAttribute("customers", new Customer());
        return "customers";
    }

    private boolean isValidUsername(String username) {
        // Điều kiện: ít nhất 5 ký tự, chứa ít nhất 1 chữ thường và 1 số.
        return username.length() >= 5 && username.matches(".*[a-z]+.*") && username.matches(".*\\d+.*");
    }

    @GetMapping("/admin/customers/{idCustomer}")
    public String update(Model model, @PathVariable(value = "idCustomer") int idCustomer) {
        Customer customerData = this.cusService.getCustomerById(idCustomer);

        if (customerData != null) {
            model.addAttribute("customers", customerData);
            model.addAttribute("isUpdate", true);
        } else {
            model.addAttribute("customers", new Customer());
            model.addAttribute("isUpdate", false);
        }

        return "customers";
    }

    @PostMapping("/admin/customers")
    public String addOrUpdateCustomer(@ModelAttribute(value = "customers") @Valid Customer customer,
            BindingResult bindingResult) {

        String rawPassword = customer.getPassWord();
        if (rawPassword.length() > 16) {
            rawPassword = rawPassword.substring(0, 16);
        }

        String hashedPassword = passwordEncoder.encode(rawPassword);
        customer.setPassWord(hashedPassword);

        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().length() != 10) {
            bindingResult.rejectValue("phoneNumber", "phoneNumber.invalid", "Số điện thoại phải có 10 ký tự");
        }

        if (!bindingResult.hasErrors()) {
            if (isValidUsername(customer.getUserName())) {
                customer.setIsValidUsername(true);
            } else {
                customer.setIsValidUsername(false);
            }

            if (cusService.addOrUpdateCustomer(customer)) {
                return "redirect:/viewCustomer";
            }
        }

        return "customers"; // Trả về trang lỗi nếu có lỗi xảy ra
    }

    @DeleteMapping(value = "/admin/customers/{idCustomer}")
    public ResponseEntity<Long> deletePost(@PathVariable int idCustomer) {
        this.cusService.deleteCustomer(idCustomer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
