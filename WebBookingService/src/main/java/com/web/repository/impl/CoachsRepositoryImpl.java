/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Coachs;
import com.web.repository.CoachsRepository;
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
public class CoachsRepositoryImpl implements CoachsRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;
    @Autowired
    private Environment env;

    public List<Coachs> getCoachs(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Coachs> q = b.createQuery(Coachs.class);
        Root root = q.from(Coachs.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("numberCoach").as(String.class), String.format("%%%s%%", kw)));
            }

            String capacity = params.get("capacity");
            if (capacity != null && !capacity.isEmpty()) {
                int capacityValue = Integer.parseInt(capacity);
                predicates.add(b.lessThanOrEqualTo(root.get("capacity"), capacityValue));
            }

            String typeOfCoach = params.get("typeOfCoach");
            if (typeOfCoach != null && !typeOfCoach.isEmpty()) {
                predicates.add(b.equal(root.get("typeOfCoach"), Integer.parseInt(typeOfCoach)));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idCoach")));

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
    public int countCoachs() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Coachs");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateCoach(Coachs c) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            boolean shouldSaveOrUpdate = false;

            // Kiểm tra xem liệu numberCoach đã tồn tại trong cơ sở dữ liệu 
            Coachs existingCoach = (Coachs) s.createQuery("FROM Coachs WHERE numberCoach = :numberCoach").setParameter("numberCoach", c.getNumberCoach()).uniqueResult();

            // Nếu một bản ghi với cùng numberCoach tồn tại và không phải là bản ghi hiện tại đang được cập nhật
            if (existingCoach != null && !existingCoach.getIdCoach().equals(c.getIdCoach())) {
                return false;
            }

            if (c.getIdCoach() == null) {
                if (c.getNumberCoach() != null && c.getCapacity() != null && c.getTypeOfCoach() != null) {
                    shouldSaveOrUpdate = true;
                }
            } else {
                if (c.getNumberCoach() != null && c.getCapacity() != null && c.getTypeOfCoach() != null) {
                    shouldSaveOrUpdate = true;
                }
            }

            if (shouldSaveOrUpdate) {
                if (c.getIdCoach() == null) {
                    s.save(c);
                } else {
                    s.clear();
                    s.update(c);
                }
                return true;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Coachs getCoachById(int idCoach) {
        Session s = this.Factory.getObject().getCurrentSession();

        return s.get(Coachs.class,
                idCoach);
    }

    @Override
    public boolean deleteCoach(int idCoach) {
        Session s = this.Factory.getObject().getCurrentSession();
        Coachs c = this.getCoachById(idCoach);
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
    public List<Coachs> getAllCoachs() {
        return em.createQuery("SELECT c FROM Coachs c", Coachs.class).getResultList();
    }
}
