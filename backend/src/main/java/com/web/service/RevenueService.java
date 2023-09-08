/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.service;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface RevenueService {

    int totalRevenue();
    List<Object[]> RevenueStatistics(LocalDate date, int typeStat);
    int totalRevenueOrder();
    List<Object[]> RevenueOrder(LocalDate date, int typeStat);
}
