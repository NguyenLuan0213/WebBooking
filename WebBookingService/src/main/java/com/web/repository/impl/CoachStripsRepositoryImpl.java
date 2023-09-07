/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Coachstrips;
import com.web.pojo.Stations;
import com.web.repository.CoachStripRepository;
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
public class CoachStripsRepositoryImpl implements CoachStripRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;
    @Autowired
    private Environment env;

    @Override
    public List<Coachstrips> getCoachStrips(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Coachstrips> q = b.createQuery(Coachstrips.class);
        Root root = q.from(Coachstrips.class);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Join<Coachstrips, Stations> stationsJoinEnd = root.join("idStationsEnd", JoinType.INNER);
                Join<Coachstrips, Stations> stationsJoinStart = root.join("idStationsStart", JoinType.INNER);
                Predicate kwPredicate = b.or(
                        b.like(stationsJoinStart.get("name").as(String.class), String.format("%%%s%%", kw)),
                        b.like(stationsJoinEnd.get("name").as(String.class), String.format("%%%s%%", kw))
                );
                predicates.add(kwPredicate);
            }

            String distance = params.get("distance");
            if (distance != null && !distance.isEmpty()) {
                int distanceValue = Integer.parseInt(distance);
                predicates.add(b.equal(root.get("distance"), distanceValue));
            }

            String idStationsStart = params.get("idStationsStart");
            if (idStationsStart != null && !idStationsStart.isEmpty()) {
                predicates.add(b.equal(root.get("idStationsStart"), Integer.parseInt(idStationsStart)));
            }

            String idStationsEnd = params.get("idStationsEnd");
            if (idStationsEnd != null && !idStationsEnd.isEmpty()) {
                predicates.add(b.equal(root.get("idStationsEnd"), Integer.parseInt(idStationsEnd)));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idCoachStrips")));

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
    public int countCoachStrips() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Coachstrips");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateCoachStrips(Coachstrips cstrip) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (cstrip.getIdCoachStrips() == null) {
                if (cstrip.getDistance() >= 1) { // Kiểm tra trường distance
                    s.save(cstrip);
                    return true;
                }
            } else if (cstrip.getIdCoachStrips() != null) {
                if (cstrip.getDistance() >= 1) {
                    s.update(cstrip);
                    return true;
                }
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Coachstrips getCoachStripsById(int idCoachStrips) {
        Session s = this.Factory.getObject().getCurrentSession();

        return s.get(Coachstrips.class,
                idCoachStrips);
    }

    @Override
    public boolean deleteCoachStrips(int idCoachStrips) {
        Session s = this.Factory.getObject().getCurrentSession();
        Coachstrips c = this.getCoachStripsById(idCoachStrips);
        try {
            s.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Coachstrips> getAllCoachStrips() {
        return em.createQuery("SELECT cs FROM Coachstrips cs", Coachstrips.class).getResultList();
    }

}
