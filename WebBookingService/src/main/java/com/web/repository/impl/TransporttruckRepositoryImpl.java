/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Coachs;
import com.web.pojo.Coachstrips;
import com.web.pojo.Garage;
import com.web.pojo.Orderships;
import com.web.pojo.Transporttruck;
import com.web.repository.TransporttruckRepository;
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
public class TransporttruckRepositoryImpl implements TransporttruckRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;

    @Autowired
    private Environment env;

    @Override
    public List<Transporttruck> getTransporttruck(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Transporttruck> q = b.createQuery(Transporttruck.class);
        Root<Transporttruck> root = q.from(Transporttruck.class);
        Join<Transporttruck, Coachs> coachsJoin = root.join("idCoach", JoinType.INNER);
        Join<Transporttruck, Coachstrips> coachStripsJoin = root.join("idCoachStrips", JoinType.INNER);
        Join<Transporttruck, Garage> garaJoin = root.join("idGarage", JoinType.INNER);

        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate namePredicate = b.like(root.get("name").as(String.class), String.format("%%%s%%", kw));
                Predicate nameCSPredicate = b.like(coachStripsJoin.get("nameCS").as(String.class), String.format("%%%s%%", kw));
                Predicate numberCoachPredicate = b.like(coachsJoin.get("numberCoach").as(String.class), String.format("%%%s%%", kw));
                Predicate nameGaraPredicate = b.like(garaJoin.get("nameGara").as(String.class), String.format("%%%s%%", kw));
                Predicate dateTransPredicate = b.like(root.get("dateTrans").as(String.class), String.format("%%%s%%", kw));

                
                predicates.add(b.or(namePredicate, nameCSPredicate, numberCoachPredicate, nameGaraPredicate, dateTransPredicate));
            }

            if (params.containsKey("formattedDateTrans")) {
                String formattedDateTrans = params.get("formattedDateTrans");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date formattedDate = dateFormat.parse(formattedDateTrans);
                    predicates.add(b.equal(root.get("dateTrans"), formattedDate));
                } catch (ParseException e) {
                    // Handle date format error
                }
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idTrans")));

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
    public int countTransporttruck() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Transporttruck");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateTransporttruck(Transporttruck tst) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (tst.getIdTrans() == null) {
                s.save(tst);
            } else {
                s.update(tst);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Transporttruck getTransporttruckById(int idTrans) {
        Session s = this.Factory.getObject().getCurrentSession();
        return s.get(Transporttruck.class, idTrans);
    }

    @Override
    public boolean deleteTransporttruck(int idTrans) {
        Session s = this.Factory.getObject().getCurrentSession();
        Transporttruck tst = this.getTransporttruckById(idTrans);
        try {
            s.delete(tst);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Transporttruck> getAllTransporttruck() {
        return em.createQuery("SELECT ts FROM Transporttruck ts", Transporttruck.class).getResultList();
    }

}
