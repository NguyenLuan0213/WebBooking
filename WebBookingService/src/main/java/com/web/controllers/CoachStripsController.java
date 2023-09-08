/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Coachstrips;
import com.web.service.CoachStripsService;
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
@RequestMapping("/viewCoachStrip")
public class CoachStripsController {

    @Autowired
    private CoachStripsService coachStripsService;

    @Autowired
    private StationsService stationsService;

    @GetMapping("/admin/coachStrips")
    public String list(Model model) {
        model.addAttribute("coachStrips", new Coachstrips());
        model.addAttribute("stations",this.stationsService.getAllStations());
        return "coachStrips";
    }

    @GetMapping("/admin/coachStrips/{idCoachStrips}")
    public String update(Model model, @PathVariable(value = "idCoachStrips") int idCoachStrips) {
        // get the data
        Coachstrips coachStripsData = this.coachStripsService.getCoachStripsById(idCoachStrips);

        // add attributes to the model
        if (coachStripsData != null) {
            model.addAttribute("coachStrips", coachStripsData);
            model.addAttribute("isUpdate", true);
            model.addAttribute("stations", this.stationsService.getAllStations()); // Thêm dòng này để truyền danh sách bến xe vào model
        } else {
            model.addAttribute("coachStrips", new Coachstrips());
            model.addAttribute("isUpdate", false);
        }

        // return view name
        return "coachStrips";
    }

    @PostMapping("/admin/coachStrips")
    public String add(@ModelAttribute(value = "coachStrips") @Valid Coachstrips c,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.coachStripsService.addOrUpdateCoachStrips(c) == true) {
                return "redirect:/viewCoachStrip";
            }
        }

        return "coachStrips";
    }

    @DeleteMapping(value = "/admin/coachStrips/{idCoachStrips}")
    public ResponseEntity<Long> deletePost(@PathVariable int idCoachStrips) {
        this.coachStripsService.deleteCoachStrips(idCoachStrips);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
