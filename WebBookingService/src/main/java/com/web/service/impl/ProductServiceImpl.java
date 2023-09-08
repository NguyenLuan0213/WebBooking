/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Product;
import com.web.repository.ProductRepsitory;
import com.web.service.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepsitory proRepo;

    @Override
    public List<Product> getProduct(Map<String, String> params) {
        return this.proRepo.getProduct(params);
    }

    @Override
    public int countProduct() {
        return this.proRepo.countProduct();
    }

    @Override
    public boolean addOrUpdateProduct(Product pro) {
        return this.proRepo.addOrUpdateProduct(pro);
    }

    @Override
    public Product getProductById(int idProduct) {
        return this.proRepo.getProductById(idProduct);
    }

    @Override
    public boolean deleteProduct(int idProduct) {
        return this.proRepo.deleteProduct(idProduct);
    }

    @Override
    public List<Product> getAllProduct() {
        return this.proRepo.getAllProduct();
    }

}
