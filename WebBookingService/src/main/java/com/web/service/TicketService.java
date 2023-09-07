/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.web.service;

import com.web.pojo.Ticket;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface TicketService {
    List<Ticket> getTicket(Map<String, String>params);
    int countTicket();
    boolean addOrUpdateTicket(Ticket tk);
    Ticket getTicketById(int idTicket);
    boolean deleteTicket(int idTicket);
    List<Ticket> getAllTicket();
}
