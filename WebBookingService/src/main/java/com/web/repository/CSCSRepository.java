/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.repository;


import com.web.pojo.Coachstripcoachseat;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface CSCSRepository {
    List<Coachstripcoachseat> getCoachstripcoachseat(Map<String, String>params);
    int countCoachstripcoachseat();
    boolean addOrUpdateCoachstripcoachseat(Coachstripcoachseat cscs);
    Coachstripcoachseat getCoachstripcoachseatById(int idCSCS);
    boolean deleteCoachstripcoachseat(int idCSCS); 
    List<Coachstripcoachseat> getAllCoachstripcoachseat();

//    boolean updateCoachstripcoachseatData();

}
