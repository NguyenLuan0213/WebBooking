/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Orderships;
import com.web.repository.OrdershipsRepository;
import com.web.service.OrderShipsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class OrderShipsServiceImpl implements OrderShipsService {

    @Autowired
    private OrdershipsRepository orderRepo;

    @Override
    public List<Orderships> getOrderships(Map<String, String> params) {
        return this.orderRepo.getOrderships(params);
    }

    @Override
    public int countOrderships() {
        return this.orderRepo.countOrderships();
    }

    @Override
    public boolean addOrUpdateOrderships(Orderships or) {
        return this.orderRepo.addOrUpdateOrderships(or);
    }

    @Override
    public Orderships getOrdershipsById(int idOrderShips) {
        return this.orderRepo.getOrdershipsById(idOrderShips);
    }

    @Override
    public boolean deleteOrderships(int idOrderShips) {
        return this.orderRepo.deleteOrderships(idOrderShips);
    }

    @Override
    public List<Orderships> getAllOrderships() {
        return this.orderRepo.getAllOrderships();
    }

}
