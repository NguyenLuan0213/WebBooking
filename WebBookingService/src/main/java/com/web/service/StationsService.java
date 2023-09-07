/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.service;


import com.web.pojo.Stations;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface StationsService {
    List<Stations> getStations(Map<String, String>params);
    int countStations();
    boolean addOrUpdateStation(Stations sta);
    Stations getStationById(int idStations);
    boolean deleteStation(int idStations);
    List<Stations> getAllStations();
}
