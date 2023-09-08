/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Customer;
import com.web.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@PropertySource("classpath:configs.properties")
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;

    @Autowired
    private Environment env;

    @Lazy
    @Autowired
    private BCryptPasswordEncoder bcpasswordEncode;

    @Override
    public List<Customer> getCustomer(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Customer> q = b.createQuery(Customer.class);
        Root root = q.from(Customer.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));
                predicates.add(b.equal(root.get("phoneNumber").as(String.class), String.format("%%%s%%", kw)));
                predicates.add(b.equal(root.get("addressCus").as(String.class), String.format("%%%s%%", kw)));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idCustomer")));

        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null) {
                int pageSize = Integer.parseInt(this.env.getProperty("PAGECUS_SIZE"));
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public int countCustomer() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Customer");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateCustomer(Customer cus) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (cus.getIdCustomer() == null) {
                s.save(cus);
            } else {
                s.update(cus);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Customer getCustomerById(int idCustomer) {
        Session s = this.Factory.getObject().getCurrentSession();

        return s.get(Customer.class,
                idCustomer);
    }

    @Override
    public boolean deleteCustomer(int idCustomer) {
        Session s = this.Factory.getObject().getCurrentSession();
        Customer cus = this.getCustomerById(idCustomer);
        try {
            s.delete(cus);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> getAllCustomer() {
        return em.createQuery("SELECT cs FROM Customer cs", Customer.class).getResultList();
    }

//    @Override
//    public Customer getCustomerByUserName(String userName) {
//        Session s = this.Factory.getObject().getCurrentSession();
//        Query q = s.createQuery("FROM Customer WHERE userName = :un");
//        q.setParameter("un", userName);
//
//        return (Customer) q.getSingleResult();
//    }
    @Override
    public Customer getCustomerByUserName(String userName) {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Customer WHERE userName = :un");
        q.setParameter("un", userName);

        try {
            return (Customer) q.getSingleResult();
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy khách hàng
        }
    }

    @Override
    public Customer findCustomerByName(String name) {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Customer WHERE name = :name");
        q.setParameter("name", name);

        try {
            return (Customer) q.getSingleResult();
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy khách hàng
        }
    }

    @Override
    public Customer getCurrentCustomer(String username) {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Customer s WHERE s.userName = :customer");
        q.setParameter("customer", username);

        List<Customer> customerList = q.getResultList();
        if (!customerList.isEmpty()) {
            return customerList.get(0);
        }
        return null; // Hoặc xử lý trường hợp không tìm thấy khách hàng ở đây
    }

    @Override
    public List<Customer> getCustomer(String username) {
        Session s = this.Factory.getObject().getCurrentSession();
        String query = "FROM Customer s ";
        if (!username.isEmpty()) {
            query += "WHERE s.userName =: customer";
            Query q = s.createQuery(query);
            q.setParameter("customer", username);
            return q.getResultList();
        }
        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean existUsername(String username) {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User u");
        List<Customer> customers = q.getResultList();
        for (Customer customer : customers) {
            if (customer.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean authCustomer(String username, String password) {
        Customer customer = this.getCustomerByUserName(username);
        return this.bcpasswordEncode.matches(password, customer.getPassWord());
    }
}
