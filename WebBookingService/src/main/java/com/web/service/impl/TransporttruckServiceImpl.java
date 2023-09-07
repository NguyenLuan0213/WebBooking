/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Transporttruck;
import com.web.repository.TransporttruckRepository;
import com.web.service.TransporttruckService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class TransporttruckServiceImpl implements TransporttruckService {

    @Autowired
    private TransporttruckRepository transRepo;

    @Override
    public List<Transporttruck> getTransporttruck(Map<String, String> params) {
        return this.transRepo.getTransporttruck(params);
    }

    @Override
    public int countTransporttruck() {
        return this.transRepo.countTransporttruck();
    }

    @Override
    public boolean addOrUpdateTransporttruck(Transporttruck tst) {
        return this.transRepo.addOrUpdateTransporttruck(tst);
    }

    @Override
    public Transporttruck getTransporttruckById(int idTrans) {
        return this.transRepo.getTransporttruckById(idTrans);
    }

    @Override
    public boolean deleteTransporttruck(int idTrans) {
        return this.transRepo.deleteTransporttruck(idTrans);
    }

    @Override
    public List<Transporttruck> getAllTransporttruck() {
        return this.transRepo.getAllTransporttruck();
    }

}
