/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.service.impl;

import com.web.pojo.Ticket;
import com.web.repository.TicketRepository;
import com.web.service.TicketService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepo;
    
    @Override
    public List<Ticket> getTicket(Map<String, String> params) {
        return this.ticketRepo.getTicket(params);
    }

    @Override
    public int countTicket() {
        return this.ticketRepo.countTicket();
    }

    @Override
    public boolean addOrUpdateTicket(Ticket tk) {
        return this.ticketRepo.addOrUpdateTicket(tk);
    }

    @Override
    public Ticket getTicketById(int idTicket) {
        return this.ticketRepo.getTicketById(idTicket);
    }

    @Override
    public boolean deleteTicket(int idTicket) {
        return this.ticketRepo.deleteTicket(idTicket);
    }

    @Override
    public List<Ticket> getAllTicket() {
        return this.ticketRepo.getAllTicket();
    }
     
}
