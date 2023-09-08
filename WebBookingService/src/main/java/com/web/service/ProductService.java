/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.service;

import com.web.pojo.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface ProductService {
    List<Product> getProduct(Map<String, String>params);
    int countProduct();
    boolean addOrUpdateProduct(Product pro);
    Product getProductById(int idProduct);
    boolean deleteProduct(int idProduct);
    List<Product> getAllProduct();
}
