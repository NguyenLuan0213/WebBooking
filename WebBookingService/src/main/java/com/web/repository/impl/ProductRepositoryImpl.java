/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Orderships;
import com.web.pojo.Product;
import com.web.pojo.Transporttruck;
import com.web.repository.ProductRepsitory;
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
public class ProductRepositoryImpl implements ProductRepsitory {

    @Autowired
    private LocalSessionFactoryBean Factory;

    @Autowired
    private Environment env;

    @Override
    public List<Product> getProduct(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = b.createQuery(Product.class);
        Root<Product> root = q.from(Product.class);
        Join<Product, Orderships> odershipsJoin = root.join("idOrderShip", JoinType.INNER);
        Join<Orderships, Transporttruck> transJoin = odershipsJoin.join("idTrans", JoinType.INNER);

        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate infoPredicate = b.like(root.get("infor").as(String.class), String.format("%%%s%%", kw));
                predicates.add(b.or(infoPredicate ));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idProduct")));

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
    public int countProduct() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Product");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateProduct(Product pro) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (pro.getIdProduct() == null) {
                s.save(pro);
            } else {
                s.update(pro);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getProductById(int idProduct) {
        Session s = this.Factory.getObject().getCurrentSession();
        return s.get(Product.class, idProduct);
    }

    @Override
    public boolean deleteProduct(int idProduct) {
        Session s = this.Factory.getObject().getCurrentSession();
        Product pro = this.getProductById(idProduct);
        try {
            s.delete(pro);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> getAllProduct() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

}
