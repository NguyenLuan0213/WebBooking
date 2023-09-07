/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Transporttruck;
import com.web.service.CoachStripsService;
import com.web.service.CoachsService;
import com.web.service.GarageService;
import com.web.service.StaffService;
import com.web.service.TransporttruckService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/viewTransporttruck")
public class TransporttruckControler {

    @Autowired
    private TransporttruckService transService;

    @Autowired
    private CoachsService coachService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private CoachStripsService csService;

    @Autowired
    private GarageService garaService;

    @GetMapping("/admin/transts")
    public String list(Model model) {
        model.addAttribute("transts", new Transporttruck());
        model.addAttribute("coachstrips", this.csService.getAllCoachStrips());
        model.addAttribute("garages", this.garaService.getAllGarage());
        model.addAttribute("coachs", this.coachService.getAllCoachs());
        model.addAttribute("staffs", this.staffService.getAllStaff());

        return "transts";
    }

    @GetMapping("/admin/transts/{idTrans}")
    public String update(Model model, @PathVariable(value = "idTrans") int idTrans) {
        Transporttruck transData = this.transService.getTransporttruckById(idTrans);

        if (transData != null) {
            model.addAttribute("transts", transData);
            model.addAttribute("isUpdate", true);
        } else {
            model.addAttribute("transts", new Transporttruck());
            model.addAttribute("isUpdate", false);
        }
        model.addAttribute("staffs", this.staffService.getAllStaff());
        model.addAttribute("coachstrips", this.csService.getAllCoachStrips());
        model.addAttribute("garages", this.garaService.getAllGarage());
        model.addAttribute("coachs", this.coachService.getAllCoachs());

        return "transts";
    }

    @PostMapping("/admin/transts")
    public String add(@ModelAttribute(value = "transts") @Valid Transporttruck tst,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dateTrans = dateFormat.parse(tst.getFormattedDateTrans());

                tst.setDateTrans(dateTrans); // Set the parsed Date to the object

                if (this.transService.addOrUpdateTransporttruck(tst)) {
                    return "redirect:/viewTransporttruck";
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // Handle the parsing exception, if needed
            }
        }

        return "transts";
    }

    @DeleteMapping(value = "/admin/transts/{idTrans}")
    public ResponseEntity<Long> deletePost(@PathVariable int idTrans) {
        this.transService.deleteTransporttruck(idTrans);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
