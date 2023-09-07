/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.web.pojo.Staff;
import com.web.service.StaffService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/viewStaff")
public class StaffController {

    private boolean isValidPassword(String password) {
        // Thực hiện kiểm tra các yêu cầu về mật khẩu ở đây
        // Ví dụ: Kiểm tra chữ số, kí tự đặc biệt, độ dài, chữ thường và chữ in hoa
        return password.matches(".*[0-9].*")
                && password.matches(".*[!@#$%^&*()\\-_=+{}|?>.<,:;~`'\"/\\\\].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[A-Z].*")
                && password.length() >= 8;
    }

    private boolean isValidUsername(String username) {
        // Điều kiện: ít nhất 5 ký tự, chứa ít nhất 1 chữ thường và 1 số.
        return username.length() >= 5 && username.matches(".*[a-z]+.*") && username.matches(".*\\d+.*");
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StaffService staffService;

    @GetMapping("/admin/staffs")
    public String list(Model model) {
        model.addAttribute("staffs", new Staff());

        return "staffs";
    }

    @GetMapping("/admin/staffs/{idStaff}")
    public String update(Model model, @PathVariable(value = "idStaff") int idStaff) {
        // get the data
        Staff staffsData = this.staffService.getStaffById(idStaff);

        // add attributes to the model
        if (staffsData != null) {
            model.addAttribute("staffs", staffsData);
            model.addAttribute("isUpdate", true);
        } else {
            model.addAttribute("staffs", new Staff());
            model.addAttribute("isUpdate", false);
        }

        // return view name
        return "staffs";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/admin/staffs")
    public String addOrUpdate(Model model,@ModelAttribute("staffs") @Valid Staff staff,
            BindingResult bindingResult, HttpServletRequest request) {
        // Mã hóa mật khẩu
        String hashedPassword = passwordEncoder.encode(staff.getPassWord());
        staff.setPassWord(hashedPassword);

        String phone = staff.getPhone();
        if (phone != null && !phone.isEmpty()) {
            try {
                Long.parseLong(phone); // Kiểm tra xem có phải số hay không
                if (phone.length() != 10) {
                    bindingResult.rejectValue("phone", "error.staff", "Số điện thoại phải có đúng 10 chữ số");
                }
            } catch (NumberFormatException e) {
                bindingResult.rejectValue("phone", "error.staff", "Số điện thoại không hợp lệ");
            }
        } else {
            bindingResult.rejectValue("phone", "error.staff", "Số điện thoại không được bỏ trống");
        }
//        staff.setRoles(Staff.ADMIN);
        if (!bindingResult.hasErrors()) {
            if (isValidUsername(staff.getUserName())) {
                staff.setIsValidUsername(true);
                if (staffService.addOrUpdateStaff(staff)) {
                    return "redirect:/viewStaff";
                }
            } else {
                staff.setIsValidUsername(false);
            }
        }
        return "staffs";
    }

    @DeleteMapping(value = "/admin/staffs/{idStaff}")
    public ResponseEntity<Long> deletePost(@PathVariable int idStaff) {
        this.staffService.deleteStaff(idStaff);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("/infoStaff")
    public String infoUser(Model model) {
        Staff s = this.staffService.
                getCurrentStaff(
                        SecurityContextHolder.getContext()
                                .getAuthentication().getName());
        model.addAttribute("user", s);
        return "infoStaff";
    }
}
