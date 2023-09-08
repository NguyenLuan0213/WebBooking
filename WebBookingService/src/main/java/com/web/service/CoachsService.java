/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.service;

import com.web.pojo.Coachs;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface CoachsService {
    List<Coachs> getCoachs(Map<String, String> params);
    int countCoachs();
    boolean addOrUpdateCoach(Coachs c);
    Coachs getCoachById(int idCoach);
    boolean deleteCoach(int idCoach);
    List<Coachs> getAllCoachs();
}
