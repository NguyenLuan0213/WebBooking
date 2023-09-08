/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.service;

import com.web.pojo.Coachstrips;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface CoachStripsService {
    List<Coachstrips> getCoachStrips(Map<String, String>params);
    int countCoachStrips();
    boolean addOrUpdateCoachStrips(Coachstrips cstrip);
    Coachstrips getCoachStripsById(int idCoachStrips);
    boolean deleteCoachStrips(int idCoachStrips);
    List<Coachstrips> getAllCoachStrips();
}
