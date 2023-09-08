/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers;

import com.web.pojo.Product;
import com.web.service.OrderShipsService;
import com.web.service.ProductService;
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
@RequestMapping("/viewProduct")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderShipsService ordService;

    @GetMapping("/admin/products")
    public String list(Model model) {
        model.addAttribute("products", new Product());
        model.addAttribute("orderships", this.ordService.getAllOrderships());
        return "products";
    }

    @GetMapping("/admin/products/{idProduct}")
    public String update(Model model, @PathVariable(value = "idProduct") int idProduct) {
        Product proData = this.productService.getProductById(idProduct);

        if (proData != null) {
            model.addAttribute("products", proData);
            model.addAttribute("isUpdate", true);
        } else {
            model.addAttribute("products", new Product());
            model.addAttribute("isUpdate", false);
        }
        model.addAttribute("orderships", this.ordService.getAllOrderships());

        return "products";
    }

    @PostMapping("/admin/products")
    public String add(@ModelAttribute(value = "products") @Valid Product idProduct,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.productService.addOrUpdateProduct(idProduct) == true) {
                return "redirect:/viewProduct";
            }
        }

        return "products";
    }

    @DeleteMapping(value = "/admin/products/{idProduct}")
    public ResponseEntity<Long> deletePost(@PathVariable int idProduct) {
        this.productService.deleteProduct(idProduct);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
