/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.web.service.RevenueService;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @RequestMapping("/stas")
    public String stat(Model model, @RequestParam Map<String, String> params) {
        LocalDate currentDate = LocalDate.now();
        List<Object[]> revenues = this.revenueService.RevenueStatistics(currentDate, 0);
        List<Object[]> revenuesorder = this.revenueService.RevenueOrder(currentDate, 0);

        model.addAttribute("revenues", revenues);
        model.addAttribute("revenuesorder", revenuesorder);

        int subTotal = this.revenueService.totalRevenue();
        int totalAmountSent = this.revenueService.totalRevenueOrder();
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("totalAmountSent", totalAmountSent);

        return "stas";
    }

    @RequestMapping("/stas/admin/filter")
    public String statFilter(Model model,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam(value = "typeStat") String type) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Object[]> revenues = this.revenueService.RevenueStatistics(localDate, Integer.parseInt(type));
        List<Object[]> revenuesorder = this.revenueService.RevenueOrder(localDate, Integer.parseInt(type));

        model.addAttribute("revenues", revenues);
        model.addAttribute("revenuesorder", revenuesorder);
        model.addAttribute("dateIn", localDate);
        model.addAttribute("subTotal", this.revenueService.totalRevenue());
        model.addAttribute("totalAmountSent", this.revenueService.totalRevenueOrder());

        return "stas";
    }

}
