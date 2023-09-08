/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Garage;
import com.web.pojo.Staff;
import com.web.repository.GarageRepository;
import com.web.repository.StaffRepository;
import com.web.service.GarageService;
import com.web.service.StaffService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepo;

    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private StaffService staffService;

    @Override
    public List<Garage> getGarage(Map<String, String> params) {
        return this.garageRepo.getGarage(params);
    }

    @Override
    public int countGarage() {
        return this.garageRepo.countGarage();
    }

    @Override
    public Garage getGarageById(int idGarage) {
        return this.garageRepo.getGarageById(idGarage);
    }

    @Override
    public boolean deleteGarage(int idGarage) {
        return this.garageRepo.deleteGarage(idGarage);
    }

    @Override
    public List<Garage> getAllGarage() {
        return this.garageRepo.getAllGarage();
    }

    @Override
    public boolean addOrUpdateGarage(Garage garage) {
        if (garage.getIdGarage() == null) {
            addNewGarage(garage);
        } else {
            updateExistingGarage(garage);
        }
        return true; // Cập nhật trạng thái thành công/không thành công
    }

    private boolean addNewGarage(Garage garage) {
        Staff staff = garage.getIdStaff();
        if (staff == null) {
            staff = new Staff(); // Tạo một đối tượng Staff mới
            // Đặt thông tin cho staff mới từ form
            staff.setNameStaff("Tên Staff mới");
            // ... Đặt các thông tin khác từ form
            if (!staffService.addOrUpdateStaff(staff)) {
                return false; // Xử lý lỗi nếu không thể lưu Staff mới
            }
            garage.setIdStaff(staff); // Gán Staff cho Garage
        }
        return garageRepo.addOrUpdateGarage(garage); // Lưu Garage vào cơ sở dữ liệu
    }

    private boolean updateExistingGarage(Garage garage) {
        Garage existingGarage = garageRepo.getGarageById(garage.getIdGarage());
        if (existingGarage != null) {
            Staff existingStaff = existingGarage.getIdStaff();
            if (existingStaff != null && !existingStaff.getNameStaff().equals(garage.getIdStaff().getNameStaff())) {
                existingStaff.setNameStaff(garage.getIdStaff().getNameStaff());
                if (!staffService.addOrUpdateStaff(existingStaff)) {
                    return false; // Xử lý lỗi nếu không thể cập nhật Staff
                }
            }
            garage.setIdStaff(existingStaff);
            return garageRepo.addOrUpdateGarage(garage); // Cập nhật Garage trong cơ sở dữ liệu
        }
        return false;
    }
    
    public void addGarageWithNewStaff(String nameGara, String addressGarage) {
        Staff newStaff = new Staff();
        // Đặt thông tin cho Staff mới
        newStaff.setNameStaff("Tên Staff mới");
        // ... Đặt các thông tin khác

        staffRepository.addOrUpdateStaff(newStaff); // Lưu Staff mới vào cơ sở dữ liệu

        Garage newGarage = new Garage();
        newGarage.setNameGara(nameGara);
        newGarage.setAddressGarage(addressGarage);
        
        // Gán Staff đã được lưu vào Garage
        newGarage.setIdStaff(newStaff);

        garageRepo.addOrUpdateGarage(newGarage); // Lưu Garage vào cơ sở dữ liệu
    }

}
