/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;


import com.web.service.CoachsService;
import com.web.service.GarageService;
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
public class ViewCoachController {

    @Autowired
    private Environment env;
    
    @Autowired
    private CoachsService coachsService;
    
    @Autowired
    private GarageService garageService;
    

    @RequestMapping("/viewCoach")
    public String viewCoach(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("coachs", this.coachsService.getCoachs(params));
        model.addAttribute("garages", this.garageService.getAllGarage());
        

        int count = this.coachsService.countCoachs();
        int pageSize = Integer.parseInt(env.getProperty("PAGE_SIZE").toString());
        model.addAttribute("pageCoach", Math.ceil(count * 1.0 / pageSize));

        return "viewCoach";
    }
}
