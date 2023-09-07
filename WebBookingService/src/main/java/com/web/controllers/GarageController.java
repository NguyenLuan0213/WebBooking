/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Garage;
import com.web.pojo.Staff;
import com.web.service.GarageService;
import com.web.service.StaffService;
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
@RequestMapping("/viewGarage")
public class GarageController {

    @Autowired
    private GarageService garageService;

    @Autowired
    private StaffService staffService;

    @GetMapping("/admin/garages")
    public String list(Model model) {
        model.addAttribute("garages", new Garage());
        model.addAttribute("staffs", this.staffService.getAllStaff());
        return "garages";
    }

    @GetMapping("/admin/garages/{idGarage}")
    public String update(Model model, @PathVariable(value = "idGarage") int idGarage) {
        Garage garageData = this.garageService.getGarageById(idGarage);

        if (garageData != null) {
            model.addAttribute("garages", garageData);
            model.addAttribute("isUpdate", true);
            model.addAttribute("staffs", this.staffService.getAllStaff());
        } else {
            model.addAttribute("garages", new Garage());
            model.addAttribute("isUpdate", false);
        }

        return "garages";
    }

    @PostMapping("/admin/garages")
    public String addOrUpdateGarage(@ModelAttribute(value = "garages") @Valid Garage garage,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "garages";
        }

        if (garage.getNameGara().trim().isEmpty() || garage.getAddressGarage().trim().isEmpty()) {
            // Nếu tên nhà xe hoặc địa chỉ để trống
            rs.rejectValue("nameGara", "field.required", "Tên nhà xe không được để trống");
            rs.rejectValue("addressGarage", "field.required", "Địa chỉ không được để trống");
            return "garages";
        }

        if (garage.getIdGarage() == null) {
            // Thêm mới Garage
            if (garage.getIdStaff() == null) {
                Staff newStaff = new Staff();
                // Đặt thông tin cho Staff mới từ form
                newStaff.setNameStaff("Tên Staff mới");
                // ... Đặt các thông tin khác từ form

                staffService.addOrUpdateStaff(newStaff); // Lưu Staff mới vào cơ sở dữ liệu

                garage.setIdStaff(newStaff); // Gán Staff cho Garage
            }
            garageService.addOrUpdateGarage(garage); // Lưu Garage cùng với Staff vào cơ sở dữ liệu
        } else {
            // Cập nhật Garage
            Garage existingGarage = garageService.getGarageById(garage.getIdGarage());
            if (existingGarage != null) {
                // Lấy thông tin Staff hiện tại từ cơ sở dữ liệu
                Staff existingStaff = existingGarage.getIdStaff();
                if (existingStaff != null) {
                    // Cập nhật thông tin Staff nếu tên đã thay đổi
                    if (!existingStaff.getNameStaff().equals(garage.getIdStaff().getNameStaff())) {
                        existingStaff.setNameStaff(garage.getIdStaff().getNameStaff());
                        staffService.addOrUpdateStaff(existingStaff);
                    }
                    // Gán Staff đã có cho Garage
                    garage.setIdStaff(existingStaff);
                }
                garageService.addOrUpdateGarage(garage); // Lưu Garage cùng với Staff vào cơ sở dữ liệu
            }
        }

        return "redirect:/viewGarage";
    }

    @DeleteMapping(value = "/admin/garages/{idGarage}")
    public ResponseEntity<Long> deletePost(@PathVariable int idGarage) {
        this.garageService.deleteGarage(idGarage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
