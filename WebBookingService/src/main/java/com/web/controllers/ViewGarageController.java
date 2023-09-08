/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.service.GarageService;
import com.web.service.StaffService;
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
public class ViewGarageController {
    @Autowired
    private Environment env;

    @Autowired
    private GarageService garageService;
    
    @Autowired
    private StaffService staffService;
    

    @RequestMapping("/viewGarage")
    public String ViewGarageController(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("garages", this.garageService.getGarage(params));
        model.addAttribute("staffs", this.staffService.getAllStaff());
        
        
        int count = this.garageService.countGarage();
        int pageSize = Integer.parseInt(env.getProperty("PAGE_SIZE").toString());
        model.addAttribute("pages", Math.ceil(count * 1.0 / pageSize));

        return "viewGarage";
    }
}
