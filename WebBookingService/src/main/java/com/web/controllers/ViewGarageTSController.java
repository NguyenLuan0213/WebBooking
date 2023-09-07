/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.service.CoachStripsService;
import com.web.service.GarageService;
import com.web.service.GarageTSService;
import com.web.service.StationsService;
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
public class ViewGarageTSController {
    @Autowired
    private Environment env;

    @Autowired
    private GarageService garageService;
    
    @Autowired
    private GarageTSService GarageTSService;
    
    @Autowired
    private StationsService stationsService;
    
    @Autowired
    private CoachStripsService coachStripsService;
    
    @RequestMapping("/viewGarageTS")
    public String ViewGarageTSController(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("garagetakestrips", this.GarageTSService.getGaragetakestrips(params));
        model.addAttribute("garages", this.garageService.getAllGarage());
        model.addAttribute("stations",this.stationsService.getAllStations());
        model.addAttribute("coachstrips",this.coachStripsService.getAllCoachStrips());
        
        
        int count = this.GarageTSService.countGaragetakestrips();
        int pageSize = Integer.parseInt(env.getProperty("PAGE_SIZE").toString());
        model.addAttribute("pages", Math.ceil(count * 1.0 / pageSize));

        return "viewGarageTS";
    }
}
