/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.handlers;

import com.web.pojo.Customer;
import com.web.pojo.Staff;
import com.web.service.CustomerService;
import com.web.service.StaffService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class LoginHandler implements AuthenticationSuccessHandler {

    @Autowired
    private StaffService staffService;
    @Autowired
    private CustomerService customerService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        Staff currentStaff = this.staffService.getCurrentStaff(username);
        Customer currentCustomer = this.customerService.getCurrentCustomer(username);

        // Kiểm tra và lưu vào session dựa vào loại người dùng
        if (currentStaff != null) {
            request.getSession().setAttribute("currentStaff", currentStaff);
        } else if (currentCustomer != null) {
            request.getSession().setAttribute("currentCustomer", currentCustomer);
        }

        response.sendRedirect("/WebBookingServer/");
    }
}
