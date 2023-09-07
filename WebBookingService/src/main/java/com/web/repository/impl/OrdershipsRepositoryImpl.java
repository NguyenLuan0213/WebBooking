/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Coachs;
import com.web.pojo.Coachstrips;
import com.web.pojo.Customer;
import com.web.pojo.Garage;
import com.web.pojo.Orderships;
import com.web.pojo.Staff;
import com.web.pojo.Stations;
import com.web.pojo.Transporttruck;
import com.web.repository.OrdershipsRepository;
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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
public class OrdershipsRepositoryImpl implements OrdershipsRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;

    @Autowired
    private Environment env;

    @Override
    public List<Orderships> getOrderships(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Orderships> q = b.createQuery(Orderships.class);
        Root<Orderships> root = q.from(Orderships.class);
        Join<Orderships, Transporttruck> transJoin = root.join("idTrans", JoinType.INNER);
        Join<Orderships, Staff> staffJoin = root.join("idStaff", JoinType.INNER);
        Join<Orderships, Stations> stationsJoin = root.join("idStationBuy", JoinType.INNER);
        Join<Orderships, Customer> cusSenJoin = root.join("idCusSender", JoinType.INNER);
        Join<Orderships, Customer> cusAccJoin = root.join("idCusAccpect", JoinType.INNER);
        Join<Transporttruck, Coachs> coachsJoin = transJoin.join("idCoach", JoinType.INNER);
        Join<Transporttruck, Garage> garageJoin = transJoin.join("idGarage", JoinType.INNER);
        Join<Transporttruck, Coachstrips> coachStripsJoin = transJoin.join("idCoachStrips", JoinType.INNER);

        q.multiselect(
                root.get("idOrderShips"),
                root.get("idTrans"),
                root.get("idStationBuy"),
                root.get("idCusSender"),
                root.get("idCusAccpect"),
                root.get("dateOrder"),
                staffJoin.get("nameStaff"),
                coachsJoin.get("numberCoach"),
                garageJoin.get("nameGara"),
                coachStripsJoin.get("nameCS")
        );

        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate nameStaffPredicate = b.like(staffJoin.get("nameStaff").as(String.class), String.format("%%%s%%", kw));
                Predicate nameCSPredicate = b.like(coachStripsJoin.get("nameCS").as(String.class), String.format("%%%s%%", kw));
                Predicate numberCoachPredicate = b.like(coachsJoin.get("numberCoach").as(String.class), String.format("%%%s%%", kw));
                Predicate nameGaraPredicate = b.like(garageJoin.get("nameGara").as(String.class), String.format("%%%s%%", kw));

                predicates.add(b.or(nameStaffPredicate, nameCSPredicate, numberCoachPredicate, nameGaraPredicate));
            }

            if (params.containsKey("formattedDateOrder")) {
                String formattedDateOrder = params.get("formattedDateOrder");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date formattedDate = dateFormat.parse(formattedDateOrder);
                    predicates.add(b.equal(root.get("dateOrder"), formattedDate));
                } catch (ParseException e) {
                    // Handle date format error
                }
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idOrderShips")));

        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null) {
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public int countOrderships() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Orderships");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateOrderships(Orderships or) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (or.getIdOrderShips() == null) {
                s.save(or);
            } else {
                s.update(or);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Orderships getOrdershipsById(int idOrderships) {
        Session s = this.Factory.getObject().getCurrentSession();
        return s.get(Orderships.class, idOrderships);
    }

    @Override
    public boolean deleteOrderships(int idOrderships) {
        Session s = this.Factory.getObject().getCurrentSession();
        Orderships or = this.getOrdershipsById(idOrderships);
        try {
            s.delete(or);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Orderships> getAllOrderships() {
        return em.createQuery("SELECT ts FROM Orderships ts", Orderships.class).getResultList();
    }

}
