/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Coachstripcoachseat;
import com.web.service.CSCSService;
import com.web.service.CoachStripsService;
import com.web.service.CoachsService;
import com.web.service.StaffService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
@RequestMapping("/viewCSCS")
public class CSCSController {

    @Autowired
    private CoachsService coachService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private CoachStripsService csService;

    @Autowired
    private CSCSService cscsService;

    @GetMapping("/admin/cscs")
    public String list(Model model) {
        model.addAttribute("cscs", new Coachstripcoachseat());
        model.addAttribute("coachstrips", this.csService.getAllCoachStrips());
//        model.addAttribute("garages", this.garaService.getAllGarage());
        model.addAttribute("coachs", this.coachService.getAllCoachs());
        model.addAttribute("staffs", this.staffService.getAllStaff());
        return "cscs";
    }

    @GetMapping("/admin/cscs/{idCSCS}")
    public String update(Model model, @PathVariable(value = "idCSCS") int idCSCS) {
        Coachstripcoachseat cscsData = this.cscsService.getCoachstripcoachseatById(idCSCS);

        if (cscsData != null) {
            model.addAttribute("cscs", cscsData);
            model.addAttribute("isUpdate", true);
        } else {
            model.addAttribute("cscs", new Coachstripcoachseat());
            model.addAttribute("isUpdate", false);
        }
        model.addAttribute("staffs", this.staffService.getAllStaff());
        model.addAttribute("coachstrips", this.csService.getAllCoachStrips());
//        model.addAttribute("garages", this.garaService.getAllGarage());
        model.addAttribute("coachs", this.coachService.getAllCoachs());

        return "cscs";
    }

    @PostMapping("/admin/cscs")
    public String add(@ModelAttribute(value = "cscs") @Valid Coachstripcoachseat csc,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dateDepartureTime = dateFormat.parse(csc.getFormattedDepartureTime());
                csc.setDepartureTime(dateDepartureTime);

                System.out.println(csc.getDepartureTime());
                boolean canBook = canBookCoachstripcoachseat(csc);
                System.out.println(canBook);
                if (!canBook) {
                    String errorMessage = "Không thể đặt vé cho lịch trình này. Thời gian khởi hành đã qua hoặc gần đây.";
                    rs.rejectValue("departureTime", "error.cannotBook", errorMessage);
                    return "cscs";
                }

                if (this.cscsService.addOrUpdateCoachstripcoachseat(csc)) {
                    return "redirect:/viewCSCS";
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // Handle the parsing exception, if needed
            }
        }

        return "cscs";
    }

    @DeleteMapping(value = "/admin/cscs/{idCSCS}")
    public ResponseEntity<Long> deletePost(@PathVariable int idCSCS) {
        this.cscsService.deleteCoachstripcoachseat(idCSCS);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean canBookCoachstripcoachseat(Coachstripcoachseat csc) {
        if (csc == null || csc.getIdCSCS() == null) {
            return false;
        }

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime fiveMinutesAgo = currentTime.minusMinutes(5);
        
        System.out.println();
        Date departureTime = csc.getDepartureTime();

        // Kiểm tra xem departureTime đã được đặt hay chưa
        if (departureTime == null) {
            // Nếu departureTime chưa được đặt, bạn có thể xem đây là trường hợp không h0 aqợp lệ
            return false;
        }

        Instant instant = departureTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime departureLocalDateTime = instant.atZone(zoneId).toLocalDateTime();

        if (departureLocalDateTime.isBefore(currentTime) || departureLocalDateTime.isBefore(fiveMinutesAgo)) {
            // Thời gian khởi hành của bản ghi đã qua 5 phút so với thời gian hiện tại,
            // không cho phép đặt vé.
            return false;
        }

        // Bản ghi có thể được đặt vé.
        return true;
    }

}
