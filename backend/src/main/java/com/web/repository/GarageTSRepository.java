/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.repository;


import com.web.pojo.Garagetakestrips;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface GarageTSRepository {
    List<Garagetakestrips> getGaragetakestrips(Map<String, String>params);
    int countGaragetakestrips();
    boolean addOrUpdateGaragetakestrips(Garagetakestrips gara);
    Garagetakestrips getGaragetakestripsById(int idGTS);
    boolean deleteGaragetakestrips(int idGTS);
    List<Garagetakestrips> getAllGaragetakestrips();
}
