/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Garage;
import com.web.repository.GarageRepository;
import com.web.repository.StaffRepository;
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
public class GarageRepositoryImpl implements GarageRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;
    
    @Autowired
    private StaffRepository staffRepo;

    @Autowired
    private Environment env;

    @Override
    public List<Garage> getGarage(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Garage> q = b.createQuery(Garage.class);
        Root root = q.from(Garage.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate addressPredicate = b.like(root.get("addressGarage").as(String.class), String.format("%%%s%%", kw));
                Predicate nameGaraPredicate = b.like(root.get("nameGara").as(String.class), String.format("%%%s%%", kw));
                predicates.add(b.or(addressPredicate, nameGaraPredicate));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idGarage")));

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
    public int countGarage() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Garage");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateGarage(Garage gara) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (gara.getIdGarage() == null) {
                // Lưu Staff trước nếu có
                if (gara.getIdStaff() != null && gara.getIdStaff().getIdStaff() == null) {
                    staffRepo.addOrUpdateStaff(gara.getIdStaff());
                }
                s.save(gara);
            } else {
                // Lưu Staff trước nếu có
                if (gara.getIdStaff() != null && gara.getIdStaff().getIdStaff() == null) {
                    staffRepo.addOrUpdateStaff(gara.getIdStaff());
                }
                s.update(gara);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Garage getGarageById(int idGarage) {
        Session s = this.Factory.getObject().getCurrentSession();
        return s.get(Garage.class, idGarage);
    }

    @Override
    public boolean deleteGarage(int idGarage) {
        Session s = this.Factory.getObject().getCurrentSession();
        Garage gara = this.getGarageById(idGarage);
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
    public List<Garage> getAllGarage() {
        return em.createQuery("SELECT ga FROM Garage ga", Garage.class).getResultList();
    }

}
