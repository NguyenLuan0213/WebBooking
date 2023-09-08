package com.web.controllers;

import com.web.service.RevenueService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
@Controller
public class IndexController {

    @Autowired
    private Environment env;

    @Autowired
    private RevenueService revenueServive;

    @RequestMapping("/")
    public String index(@RequestParam Map<String, String> paramsm, Model model) {
        LocalDate currentDate = LocalDate.now();
        List<Object[]> revenues = this.revenueServive.RevenueStatistics(currentDate, 0);
        List<Object[]> revenuesorder = this.revenueServive.RevenueOrder(currentDate, 0);

        model.addAttribute("revenues", revenues);
        model.addAttribute("revenuesorder", revenuesorder);

        int subTotal = this.revenueServive.totalRevenue();
        int totalAmountSent = this.revenueServive.totalRevenueOrder();
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("totalAmountSent", totalAmountSent);

        return "index";
    }

}
