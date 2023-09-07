/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Garagetakestrips;
import com.web.repository.GarageTSRepository;
import com.web.service.GarageTSService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class GarageTSServiceImpl implements GarageTSService {

    @Autowired
    private GarageTSRepository GarageTSRepository;

    @Override
    public List<Garagetakestrips> getGaragetakestrips(Map<String, String> params) {
        return this.GarageTSRepository.getGaragetakestrips(params);
    }

    @Override
    public int countGaragetakestrips() {
        return this.GarageTSRepository.countGaragetakestrips();
    }

    @Override
    public boolean addOrUpdateGaragetakestrips(Garagetakestrips gara) {
        return this.GarageTSRepository.addOrUpdateGaragetakestrips(gara);
    }

    @Override
    public Garagetakestrips getGaragetakestripsById(int idGTS) {
        return this.GarageTSRepository.getGaragetakestripsById(idGTS);
    }

    @Override
    public boolean deleteGaragetakestrips(int idGTS) {
        return this.GarageTSRepository.deleteGaragetakestrips(idGTS);
    }

    @Override
    public List<Garagetakestrips> getAllGaragetakestrips() {
        return this.GarageTSRepository.getAllGaragetakestrips();
    }

}
