/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Coachstrips;
import com.web.pojo.Garage;
import com.web.pojo.Garagetakestrips;
import com.web.pojo.Stations;
import com.web.repository.GarageTSRepository;
import java.util.ArrayList;
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
public class GarageTSRepositoryImpl implements GarageTSRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;

    @Autowired
    private Environment env;

    @Override
    public List<Garagetakestrips> getGaragetakestrips(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Garagetakestrips> q = b.createQuery(Garagetakestrips.class);
        Root<Garagetakestrips> root = q.from(Garagetakestrips.class);
        Join<Garagetakestrips, Coachstrips> coachStripsJoin = root.join("idCoachStrips", JoinType.INNER);
        Join<Coachstrips, Stations> stationsJoin = coachStripsJoin.join("idStationsStart", JoinType.INNER);
        Join<Garagetakestrips, Garage> garageJoin = root.join("idGarage", JoinType.INNER);

        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate nameGaraPredicate = b.like(garageJoin.get("nameGara").as(String.class), String.format("%%%s%%", kw));
                Predicate nameStationsPredicate = b.like(stationsJoin.get("name").as(String.class), String.format("%%%s%%", kw));
                predicates.add(b.or( nameGaraPredicate, nameStationsPredicate));
            }

            q.where(predicates.toArray(new Predicate[0]));
        }

        q.orderBy(b.desc(root.get("idGTS")));

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
    public int countGaragetakestrips() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Garagetakestrips");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateGaragetakestrips(Garagetakestrips gara) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (gara.getIdGTS() == null) {
                s.save(gara);
            } else {
                s.update(gara);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Garagetakestrips getGaragetakestripsById(int idGTS) {
        Session s = this.Factory.getObject().getCurrentSession();
        return s.get(Garagetakestrips.class, idGTS);
    }

    @Override
    public boolean deleteGaragetakestrips(int idGTS) {
        Session s = this.Factory.getObject().getCurrentSession();
        Garagetakestrips gara = this.getGaragetakestripsById(idGTS);
        try {
            s.delete(gara);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Garagetakestrips> getAllGaragetakestrips() {
        return em.createQuery("SELECT ga FROM Garagetakestrips ga", Garagetakestrips.class).getResultList();
    }

}
