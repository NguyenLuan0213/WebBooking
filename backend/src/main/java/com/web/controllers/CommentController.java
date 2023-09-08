/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Comments;
import com.web.service.CommentService;
import com.web.service.CustomerService;
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
@RequestMapping("/viewComment")
public class CommentController {

    @Autowired
    private GarageService garageService;
    @Autowired
    private CommentService comService;
    @Autowired
    private CustomerService cusService;

    @GetMapping("/admin/comments")
    public String list(Model model) {
        model.addAttribute("comments", new Comments());
        model.addAttribute("garages", this.garageService.getAllGarage());
        model.addAttribute("customers", this.cusService.getAllCustomer());
        
        return "comments";
    }

    @GetMapping("/admin/comments/{idComment}")
    public String update(Model model, @PathVariable(value = "idComment") int idComment) {
        Comments commentsData = this.comService.getCommentById(idComment);

        if (commentsData != null) {
            model.addAttribute("comments", commentsData);
            model.addAttribute("isUpdate", true);
            model.addAttribute("garages", this.garageService.getAllGarage());
        } else {
            model.addAttribute("comments", new Comments());
            model.addAttribute("isUpdate", false);
        }

        return "comments";
    }

    @PostMapping("/admin/comments")
    public String add(@Valid @ModelAttribute(value = "comments") Comments comments,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "comments";
        }

        if (comments.getIdCustomer() == null) {
            bindingResult.rejectValue("idCustomer", "error.comments", "Vui lòng chọn khách hàng và bến xe.");
            return "comments";
        }

        if (!comService.addOrUpdateComment(comments)) {
            bindingResult.rejectValue("comments", "error.comments", "Lỗi khi lưu bình luận.");
            return "comments";
        }

        // Sau khi thêm/cập nhật thành công, cập nhật lại danh sách bình luận và danh sách bến
        model.addAttribute("comments", this.comService.getComments(null)); // Để lấy danh sách bình luận mới
        return "redirect:/viewComment";
    }

    @DeleteMapping(value = "/admin/comments/{idComment}")
    public ResponseEntity<Long> deletePost(@PathVariable int idComment) {
        this.comService.deleteComment(idComment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
