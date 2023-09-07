/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Ticket;
import com.web.repository.TicketRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@PropertySource("classpath:configs.properties")
@Transactional
public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;

    @Autowired
    private Environment env;

    @Override
    public List<Ticket> getTicket(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Ticket> q = b.createQuery(Ticket.class);
        Root<Ticket> root = q.from(Ticket.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
//                Predicate nameSeatPredicate = b.like(root.get("idCoachStripCoachSeat.nameSeat").as(String.class), String.format("%%%s%%", kw));
                Predicate bookingDatePredicate = b.like(root.get("bookingDate").as(String.class), String.format("%%%s%%", kw));

                predicates.add(b.or( bookingDatePredicate));
            }
            
            if (params.containsKey("formattedBookingDate")) {
                String formattedBookingDate = params.get("formattedBookingDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date formattedDate = dateFormat.parse(formattedBookingDate);
                    predicates.add(b.equal(root.get("bookingDate"), formattedDate));
                } catch (ParseException e) {
                    // Handle date format error
                }
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idTicket")));

        Query query = s.createQuery(q);

         if (params != null) {
            String page = params.get("page");
            if (page != null) {
                int pageSize = Integer.parseInt(this.env.getProperty("PAGECSCS_SIZE"));
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public int countTicket() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Ticket");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateTicket(Ticket tk) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (tk.getIdTicket() == null) {
                s.save(tk);
            } else {
                s.update(tk);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Ticket getTicketById(int idTicket) {
        Session s = this.Factory.getObject().getCurrentSession();
        return s.get(Ticket.class, idTicket);
    }

    @Override
    public boolean deleteTicket(int idTicket) {
        Session s = this.Factory.getObject().getCurrentSession();
        Ticket tk = this.getTicketById(idTicket);
        try {
            s.delete(tk);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Ticket> getAllTicket() {
        return em.createQuery("SELECT tk FROM Ticket tk", Ticket.class).getResultList();
    }

}
