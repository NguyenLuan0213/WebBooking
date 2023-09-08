/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.repository;

import com.web.pojo.Garage;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface GarageRepository {
    List<Garage> getGarage(Map<String, String>params);
    int countGarage();
    boolean addOrUpdateGarage(Garage gara);
    Garage getGarageById(int idGarage);
    boolean deleteGarage(int idGarage);
    List<Garage> getAllGarage();
}
