/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.repository.RevenueRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.service.RevenueService;

/**
 *
 * @author Admin
 */
@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    @Override
    public int totalRevenue() {
        return this.revenueRepository.totalRevenue();
    }

    @Override
    public List<Object[]> RevenueStatistics(LocalDate date, int typeStat) {
        return this.revenueRepository.RevenueStatistics(date, typeStat);
    }

    @Override
    public int totalRevenueOrder() {
        return this.revenueRepository.totalRevenueOrder();
    }

    @Override
    public List<Object[]> RevenueOrder(LocalDate date, int typeStat) {
        return this.revenueRepository.RevenueOrder(date, typeStat);
    }

}
