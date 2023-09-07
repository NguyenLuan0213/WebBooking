/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.repository;

import com.web.pojo.Transporttruck;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface TransporttruckRepository {
    List<Transporttruck> getTransporttruck(Map<String, String>params);
    int countTransporttruck();
    boolean addOrUpdateTransporttruck(Transporttruck tst);
    Transporttruck getTransporttruckById(int idTrans);
    boolean deleteTransporttruck(int idTrans);
    List<Transporttruck> getAllTransporttruck();
}
