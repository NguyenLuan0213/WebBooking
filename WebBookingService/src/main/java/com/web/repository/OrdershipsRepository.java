/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.repository;

import com.web.pojo.Orderships;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface OrdershipsRepository {
    List<Orderships> getOrderships(Map<String, String>params);
    int countOrderships();
    boolean addOrUpdateOrderships(Orderships or);
    Orderships getOrdershipsById(int idOrderShips);
    boolean deleteOrderships(int idOrderShips);
    List<Orderships> getAllOrderships();
}
