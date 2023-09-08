/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Ticket;
import com.web.service.CSCSService;
import com.web.service.CustomerService;
import com.web.service.StaffService;
import com.web.service.StationsService;
import com.web.service.TicketService;
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
@RequestMapping("/viewTicket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StationsService stationService;

    @Autowired
    private CustomerService cusService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private CSCSService cscsService;

    @GetMapping("/admin/tickets")
    public String list(Model model) {
        model.addAttribute("tickets", new Ticket());
        model.addAttribute("stations", this.stationService.getAllStations());
        model.addAttribute("customers", this.cusService.getAllCustomer());
        model.addAttribute("staffs", this.staffService.getAllStaff());
        model.addAttribute("cscs", this.cscsService.getAllCoachstripcoachseat());

        return "tickets";
    }

    @GetMapping("/admin/tickets/{idTicket}")
    public String update(Model model, @PathVariable(value = "idTicket") int idTicket) {
        Ticket ticketData = this.ticketService.getTicketById(idTicket);

        if (ticketData != null) {
            model.addAttribute("tickets", ticketData);
            model.addAttribute("isUpdate", true);

        } else {
            model.addAttribute("tickets", new Ticket());
            model.addAttribute("isUpdate", false);
        }
        model.addAttribute("stations", this.stationService.getAllStations());
        model.addAttribute("customers", this.cusService.getAllCustomer());
        model.addAttribute("staffs", this.staffService.getAllStaff());
        model.addAttribute("cscs", this.cscsService.getAllCoachstripcoachseat());
        return "tickets";
    }

    @PostMapping("/admin/tickets")
    public String add(@ModelAttribute(value = "tickets") @Valid Ticket or,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                Date dateOrder = dateFormat.parse(or.getFormattedBookingDate());

                or.setBookingDate(dateOrder);

                if (this.ticketService.addOrUpdateTicket(or)) {
                    return "redirect:/viewTicket";
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // Xử lý ngoại lệ do lỗi khi parse ngày tháng, nếu cần
            }
        }

        return "tickets";
    }

    @DeleteMapping(value = "/admin/tickets/{idTicket}")
    public ResponseEntity<Long> deletePost(@PathVariable int idTicket) {
        this.ticketService.deleteTicket(idTicket);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
