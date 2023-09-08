/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Coachs;
import com.web.service.CoachsService;
import com.web.service.GarageService;
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
@RequestMapping("/viewCoach")
public class CoachController {

    @Autowired
    private CoachsService coachsService;

    @Autowired
    private GarageService garageService;

    @GetMapping("/admin/coachs")
    public String list(Model model) {
        model.addAttribute("coachs", new Coachs());
        model.addAttribute("garages", this.garageService.getAllGarage());
        return "coachs";
    }

    @GetMapping("/admin/coachs/{idCoach}")
    public String update(Model model, @PathVariable(value = "idCoach") int idCoach) {
        Coachs coachsData = this.coachsService.getCoachById(idCoach);

        if (coachsData != null) {
            model.addAttribute("coachs", coachsData);
            model.addAttribute("isUpdate", true);
            model.addAttribute("garages", this.garageService.getAllGarage()); // Thêm dòng này để truyền danh sách nhà xe vào model
        } else {
            model.addAttribute("coachs", new Coachs());
            model.addAttribute("isUpdate", false);
        }
        return "coachs";
    }

    @PostMapping("/admin/coachs")
    public String add(@ModelAttribute(value = "coachs") @Valid Coachs c,
            BindingResult rs, Model model) {
        if (!rs.hasErrors()) {
            if (this.coachsService.addOrUpdateCoach(c) == true) {
                return "redirect:/viewCoach";
            }
        }

        // Nếu có lỗi, đặt thông báo lỗi vào model và quay lại trang nhập liệu
        model.addAttribute("error", "Có lỗi xảy ra khi thêm/chỉnh sửa coach");
        return "coachs";
    }

    @DeleteMapping(value = "/admin/coachs/{idCoach}")
    public ResponseEntity<Long> deletePost(@PathVariable int idCoach) {
        this.coachsService.deleteCoach(idCoach);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
