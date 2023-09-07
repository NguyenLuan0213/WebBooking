/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Orderships;
import com.web.service.CustomerService;
import com.web.service.OrderShipsService;
import com.web.service.StaffService;
import com.web.service.StationsService;
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
@RequestMapping("/viewOrderShip")
public class OrderShipsController {

    @Autowired
    private OrderShipsService orderService;

    @Autowired
    private StationsService stationService;

    @Autowired
    private CustomerService cusService;

    @Autowired
    private TransporttruckService transService;

    @Autowired
    private StaffService staffService;

    @GetMapping("/admin/orderships")
    public String list(Model model) {
        model.addAttribute("orderships", new Orderships());
        model.addAttribute("stations", this.stationService.getAllStations());
        model.addAttribute("customers", this.cusService.getAllCustomer());
        model.addAttribute("trans", this.transService.getAllTransporttruck());
        model.addAttribute("staffs", this.staffService.getAllStaff());

        return "orderships";
    }

    @GetMapping("/admin/orderships/{idOrderships}")
    public String update(Model model, @PathVariable(value = "idOrderships") int idOrderships) {
        Orderships orderData = this.orderService.getOrdershipsById(idOrderships);

        if (orderData != null) {
            model.addAttribute("orderships", orderData);
            model.addAttribute("isUpdate", true);

        } else {
            model.addAttribute("orderships", new Orderships());
            model.addAttribute("isUpdate", false);
        }
        model.addAttribute("stations", this.stationService.getAllStations());
        model.addAttribute("customers", this.cusService.getAllCustomer());
        model.addAttribute("trans", this.transService.getAllTransporttruck());
        model.addAttribute("staffs", this.staffService.getAllStaff());
        return "orderships";
    }

    @PostMapping("/admin/orderships")
    public String add(@ModelAttribute(value = "orderships") @Valid Orderships or,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                Date dateOrder = dateFormat.parse(or.getFormattedDateOrder());

                or.setDateOrder(dateOrder);

                if (this.orderService.addOrUpdateOrderships(or)) {
                    return "redirect:/viewOrderShip";
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // Xử lý ngoại lệ do lỗi khi parse ngày tháng, nếu cần
            }
        }

        return "orderships";
    }

    @DeleteMapping(value = "/admin/orderships/{idOrderships}")
    public ResponseEntity<Long> deletePost(@PathVariable int idOrderships) {
        this.orderService.deleteOrderships(idOrderships);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
