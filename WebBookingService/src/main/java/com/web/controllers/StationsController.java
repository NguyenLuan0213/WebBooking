/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Stations;
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
@RequestMapping("/viewStation")
public class StationsController {

    @Autowired
    private StationsService stationsService;

    @GetMapping("/admin/stations")
    public String list(Model model) {
        model.addAttribute("stations", new Stations());
        return "stations";
    }
    

    @GetMapping("/admin/stations/{idStations}")
    public String update(Model model, @PathVariable(value = "idStations") int idStations) {
        Stations stationData = this.stationsService.getStationById(idStations);

        if (stationData != null) {
            model.addAttribute("stations", stationData);
            model.addAttribute("isUpdate", true);
        } else {
            model.addAttribute("stations", new Stations());
            model.addAttribute("isUpdate", false);
        }

        return "stations";
    }

    @PostMapping("/admin/stations")
    public String add(@ModelAttribute(value = "stations") @Valid Stations idStations,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.stationsService.addOrUpdateStation(idStations) == true) {
                return "redirect:/viewStation";
            }
        }

        return "stations";
    }

    @DeleteMapping(value = "/admin/stations/{idStations}")
    public ResponseEntity<Long> deletePost(@PathVariable int idStations) {
        this.stationsService.deleteStation(idStations);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
