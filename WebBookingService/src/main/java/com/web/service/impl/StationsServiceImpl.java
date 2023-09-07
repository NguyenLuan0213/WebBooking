/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Stations;
import com.web.repository.StationsRepository;
import com.web.service.StationsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StationsServiceImpl implements StationsService {

    @Autowired
    private StationsRepository stationsRepository;

    @Override
    public List<Stations> getStations(Map<String, String> params) {
        return this.stationsRepository.getStations(params);
    }

    @Override
    public int countStations() {
        return this.stationsRepository.countStations();
    }

    @Override
    public boolean addOrUpdateStation(Stations sta) {
        return this.stationsRepository.addOrUpdateStation(sta);
    }

    @Override
    public Stations getStationById(int idStations) {
        return this.stationsRepository.getStationById(idStations);
    }

    @Override
    public boolean deleteStation(int idStations) {
        return this.stationsRepository.deleteStation(idStations);
    }

    @Override
    public List<Stations> getAllStations() {
        return this.stationsRepository.getAllStations();
    }

}
