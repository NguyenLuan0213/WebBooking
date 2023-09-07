/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Coachstripcoachseat;
import com.web.repository.CSCSRepository;
import com.web.service.CSCSService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CSCSServiceImpl implements CSCSService {

    @Autowired
    private CSCSRepository CSCSRepository;

    @Override
    public List<Coachstripcoachseat> getCoachstripcoachseat(Map<String, String> params) {
        return this.CSCSRepository.getCoachstripcoachseat(params);
    }

    @Override
    public int countCoachstripcoachseat() {
        return this.CSCSRepository.countCoachstripcoachseat();
    }

    @Override
    public boolean addOrUpdateCoachstripcoachseat(Coachstripcoachseat cscs) {
        return this.CSCSRepository.addOrUpdateCoachstripcoachseat(cscs);
    }

    @Override
    public Coachstripcoachseat getCoachstripcoachseatById(int idCSCS) {
        return this.CSCSRepository.getCoachstripcoachseatById(idCSCS);
    }

    @Override
    public boolean deleteCoachstripcoachseat(int idCSCS) {
        return this.CSCSRepository.deleteCoachstripcoachseat(idCSCS);
    }

    @Override
    public List<Coachstripcoachseat> getAllCoachstripcoachseat() {
        return this.CSCSRepository.getAllCoachstripcoachseat();
    }

//    @Override
//    public boolean updateCoachstripcoachseatData() {
//        return this.CSCSRepository.updateCoachstripcoachseatData();
    //    }

}
