/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.controllers.api;

/**
 *
 * @author Admin
 */
import com.web.pojo.Coachs;
import com.web.pojo.Coachstripcoachseat;
import com.web.pojo.Coachstrips;
import com.web.pojo.Staff;
import com.web.service.CSCSService;
import com.web.service.CoachStripsService;
import com.web.service.CoachsService;
import com.web.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cscs")
public class ApiCSCSController {

    @Autowired
    private CSCSService csccService;

    @Autowired
    private CoachsService coachService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private CoachStripsService csService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Coachstripcoachseat>> getCoachstripcoachseats(@RequestParam(required = false) Map<String, String> params) {
        List<Coachstripcoachseat> coachstripcoachseats = csccService.getCoachstripcoachseat(params);
        List<Staff> staff = staffService.getStaffs(params);
        List<Coachstrips> coachstrips = csService.getCoachStrips(params);
        List<Coachs> coachs = coachService.getCoachs(params);

        return new ResponseEntity<>(coachstripcoachseats, HttpStatus.OK);
    }

    @GetMapping("/{idCSCS}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Coachstripcoachseat> getCoachstripcoachseat(@PathVariable int idCSCS) {
        Coachstripcoachseat coachstripcoachseat = csccService.getCoachstripcoachseatById(idCSCS);
        if (coachstripcoachseat != null) {
            return new ResponseEntity<>(coachstripcoachseat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Coachstripcoachseat> addCoachstripcoachseat(@RequestBody Coachstripcoachseat coachstripcoachseat) {
        boolean result = csccService.addOrUpdateCoachstripcoachseat(coachstripcoachseat);
        if (result) {
            return new ResponseEntity<>(coachstripcoachseat, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idCSCS}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Coachstripcoachseat> updateCoachstripcoachseat(@PathVariable int idCSCS, @RequestBody Coachstripcoachseat coachstripcoachseat) {
        coachstripcoachseat.setIdCSCS(idCSCS);
        boolean result = csccService.addOrUpdateCoachstripcoachseat(coachstripcoachseat);
        if (result) {
            return new ResponseEntity<>(coachstripcoachseat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{idCSCS}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> deleteCoachstripcoachseat(@PathVariable int idCSCS) {
        boolean result = csccService.deleteCoachstripcoachseat(idCSCS);
        if (result) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
