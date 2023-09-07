/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;


import com.web.pojo.Coachstrips;
import com.web.repository.CoachStripRepository;
import com.web.service.CoachStripsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CoachStripsServiceImpl implements CoachStripsService {

    @Autowired
    private CoachStripRepository coachStripsRepository;
    
    

    @Override
    public List<Coachstrips> getCoachStrips(Map<String, String> params) {
        return this.coachStripsRepository.getCoachStrips(params);
    }

    @Override
    public int countCoachStrips() {
        return this.coachStripsRepository.countCoachStrips();
    }

    @Override
    public Coachstrips getCoachStripsById(int idCoachStrips) {
        return this.coachStripsRepository.getCoachStripsById(idCoachStrips);
    }

    @Override
    public boolean deleteCoachStrips(int idCoachStrips) {
        return this.coachStripsRepository.deleteCoachStrips(idCoachStrips);
    }

    @Override
    public boolean addOrUpdateCoachStrips(Coachstrips cstrip) {
        return this.coachStripsRepository.addOrUpdateCoachStrips(cstrip);
    }

    @Override
    public List<Coachstrips> getAllCoachStrips() {
        return this.coachStripsRepository.getAllCoachStrips();
    }

}
