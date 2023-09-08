/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.web.pojo.Coachs;
import com.web.repository.CoachsRepository;
import com.web.service.CoachsService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CoachsServiceImpl implements CoachsService {

    @Autowired
    private CoachsRepository coachsRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Coachs> getCoachs(Map<String, String> params) {
        return this.coachsRepository.getCoachs(params);
    }

    @Override
    public int countCoachs() {
        return this.coachsRepository.countCoachs();
    }

    @Override
    public boolean addOrUpdateCoach(Coachs c) {
        if (!c.getFile().isEmpty()) {

            try {
                Map res = this.cloudinary.uploader().upload(c.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                c.setImgCoach(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(CoachsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.coachsRepository.addOrUpdateCoach(c);
    }

    @Override
    public Coachs getCoachById(int idCoach) {
        return this.coachsRepository.getCoachById(idCoach);
    }

    @Override
    public boolean deleteCoach(int idCoach) {
        return this.coachsRepository.deleteCoach(idCoach);
    }

    @Override
    public List<Coachs> getAllCoachs() {
        return this.coachsRepository.getAllCoachs();
    }

}
