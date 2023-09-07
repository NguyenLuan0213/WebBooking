/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Stations;
import com.web.repository.StationsRepository;
import java.util.ArrayList;
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
public class StationsRepositoryImpl implements StationsRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;

    @Autowired
    private Environment env;

    @Override
    public List<Stations> getStations(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Stations> q = b.createQuery(Stations.class);
        Root root = q.from(Stations.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate addressPredicate = b.like(root.get("address").as(String.class), String.format("%%%s%%", kw));
                Predicate namePredicate = b.like(root.get("name").as(String.class), String.format("%%%s%%", kw));
                predicates.add(b.or(addressPredicate, namePredicate));
            }

            String name = params.get("name");
            if (name != null && !name.isEmpty()) {
                predicates.add(b.equal(root.get("name"), name));
            }
            
            String address = params.get("address");
            if (address != null && !address.isEmpty()) {
                predicates.add(b.equal(root.get("address"), name));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idStations")));

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
    public int countStations() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Stations");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateStation(Stations sta) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (sta.getIdStations() == null) {
                s.save(sta);
            } else {
                s.update(sta);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Stations getStationById(int idStations) {
        Session s = this.Factory.getObject().getCurrentSession();
        return s.get(Stations.class, idStations);
    }

    @Override
    public boolean deleteStation(int idStations) {
        Session s = this.Factory.getObject().getCurrentSession();
        Stations sta = this.getStationById(idStations);
        try {
            s.delete(sta);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Stations> getAllStations() {
        return em.createQuery("SELECT s FROM Stations s", Stations.class).getResultList();
    }
}
