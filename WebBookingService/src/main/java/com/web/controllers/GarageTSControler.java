/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Garagetakestrips;
import com.web.service.CoachStripsService;
import com.web.service.GarageService;
import com.web.service.GarageTSService;
import com.web.service.StationsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/viewGarageTS")
public class GarageTSControler {

    @Autowired
    private GarageService garageService;

    @Autowired
    private GarageTSService GarageTSService;

    @Autowired
    private StationsService stationsService;

    @Autowired
    private CoachStripsService coachStripsService;

    @GetMapping("/admin/garagetakestrips")
    public String list(Model model) {
        model.addAttribute("garagetakestrips", new Garagetakestrips());
        model.addAttribute("garages", this.garageService.getAllGarage());
        model.addAttribute("stations", this.stationsService.getAllStations());
        model.addAttribute("coachstrips", this.coachStripsService.getAllCoachStrips());

        return "garagetakestrips";
    }

    @GetMapping("/admin/garagetakestrips/{idGTS}")
    public String update(Model model, @PathVariable(value = "idGTS") int idGTS) {
        Garagetakestrips garageTSData = this.GarageTSService.getGaragetakestripsById(idGTS);

        if (garageTSData != null) {
            model.addAttribute("garagetakestrips", garageTSData);
            model.addAttribute("isUpdate", true);
            model.addAttribute("garages", this.garageService.getAllGarage());
            model.addAttribute("coachstrips", this.coachStripsService.getAllCoachStrips());
        } else {
            model.addAttribute("garagetakestrips", new Garagetakestrips());
            model.addAttribute("isUpdate", false);
        }

        return "garagetakestrips";
    }

    @PostMapping("/admin/garagetakestrips")
    public String add(@ModelAttribute(value = "garagetakestrips") @Valid Garagetakestrips idGTS,
            BindingResult rs, Model model) {
        if (!rs.hasErrors()) {
            if (this.GarageTSService.addOrUpdateGaragetakestrips(idGTS)) {
                return "redirect:/viewGarageTS";
            }
        }

        model.addAttribute("garages", this.garageService.getAllGarage());
        model.addAttribute("stations", this.stationsService.getAllStations());
        model.addAttribute("coachstrips", this.coachStripsService.getAllCoachStrips());

        return "garagetakestrips";
    }

    @DeleteMapping(value = "/admin/garagetakestrips/{idGTS}")
    public ResponseEntity<Long> deletePost(@PathVariable int idGTS) {
        this.GarageTSService.deleteGaragetakestrips(idGTS);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
