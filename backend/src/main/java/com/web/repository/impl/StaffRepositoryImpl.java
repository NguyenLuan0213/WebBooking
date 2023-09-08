/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Staff;
import com.web.repository.StaffRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class StaffRepositoryImpl implements StaffRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;

    @Lazy
    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Autowired
    private Environment env;

    @Override
    public Staff getStaffByUserName(String userName) {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Staff WHERE userName = :un");
        q.setParameter("un", userName);

        try {
            return (Staff) q.getSingleResult();
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy nhân viên
        }
    }

    @Override
    public List<Staff> getStaffs(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Staff> q = b.createQuery(Staff.class);
        Root root = q.from(Staff.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate nameStaffPredicate = b.like(root.get("nameStaff").as(String.class), String.format("%%%s%%", kw));
                Predicate genderPredicate = b.like(root.get("gender").as(String.class), String.format("%%%s%%", kw));
                Predicate phonePredicate = b.equal(root.get("phone").as(String.class), String.format("%%%s%%", kw));

                predicates.add(b.or(nameStaffPredicate, genderPredicate, phonePredicate));
            }
             String roles = params.get("roles");
            if (roles != null && !roles.isEmpty()) {
                Predicate rolesPredicate = b.equal(root.get("roles"), roles);
                predicates.add(rolesPredicate);
            }

            if (params.containsKey("formattedBrithStaff")) {
                String formattedBrithStaff = params.get("formattedBrithStaff");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date formattedDate = dateFormat.parse(formattedBrithStaff);
                    predicates.add(b.equal(root.get("brithStaff"), formattedDate));
                } catch (ParseException e) {
                    // Handle date format error
                }
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        q.orderBy(b.desc(root.get("idStaff")));

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
    public int countStaff() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Staff");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateStaff(Staff staf) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (staf.getIdStaff() == null) {
                if (isPhoneAlreadyExists(staf.getPhone(), null)) {
                    // Số điện thoại đã tồn tại
                    return false;
                }
                s.save(staf); // Lưu Staff mới vào cơ sở dữ liệu
            } else {
                if (isPhoneAlreadyExists(staf.getPhone(), staf.getIdStaff())) {
                    // Số điện thoại đã tồn tại
                    return false;
                }
                s.update(staf);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Staff getStaffById(int idStaff) {
        Session s = this.Factory.getObject().getCurrentSession();
        return s.get(Staff.class, idStaff);
    }

    @Override
    public boolean deleteStaff(int idStaff) {
        Session s = this.Factory.getObject().getCurrentSession();
        Staff staf = this.getStaffById(idStaff);
        try {
            s.delete(staf);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isPhoneAlreadyExists(String phone, Integer staffId) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Staff> root = query.from(Staff.class);

        query.select(cb.count(root));
        query.where(
                cb.and(
                        cb.like(root.get("phone"), phone + "%"), // Sử dụng LIKE để cho phép số điện thoại bắt đầu bằng số 0
                        cb.notEqual(root.get("idStaff"), staffId),
                        cb.greaterThanOrEqualTo(cb.length(root.get("phone")), 10) // Độ dài phải lớn hơn hoặc bằng 10
                )
        );
        Long count = s.createQuery(query).uniqueResult();
        return count > 0;
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Staff> getAllStaff() {
        return em.createQuery("SELECT s FROM Staff s", Staff.class).getResultList();
    }

    @Override
    public List<Staff> getStaff(String username) {
        Session s = this.Factory.getObject().getCurrentSession();
        String query = "FROM Staff s ";
        if (!username.isEmpty()) {
            query += "WHERE s.userName =: staff";
            Query q = s.createQuery(query);
            q.setParameter("staff", username);
            return q.getResultList();
        }
        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object[]> getUserByStaffRole(String staffRole) {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q;
        q = s.createQuery("SELECT s.idStaff, s.addressUser, s.nameStaff, s.gender, s.phone FROM Staff s WHERE s.roles = :role");
        q.setParameter("role", staffRole);
        return q.getResultList();
    }

    @Override
    public boolean existUsername(String username) {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User u");
        List<Staff> staffs = q.getResultList();
        for (Staff staff : staffs) {
            if (staff.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean authUser(String username, String password) {
        Staff staff = this.getStaffByUserName(username);
        return this.passwordEncode.matches(password, staff.getPassWord());
    }

    @Override
    public List<Object[]> getUserByUserRoleAndName(String staffRole, String name) {
        Session s = this.Factory.getObject().getCurrentSession();
        String sql;
        sql = "SELECT s.idStaff, s.addressUser, s.nameStaff, s.gender, s.phone FROM Staff s WHERE s.roles = :role AND s.nameStaff LIKE :key";
        Query q = s.createQuery(sql);
        q.setParameter("key", '%' + name + '%');
        q.setParameter("role", staffRole);
        return q.getResultList();
    }

    @Override
    public Staff getCurrentStaff(String username) {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT s FROM Staff s WHERE s.userName = :staff");
        q.setParameter("staff", username);

        List<Staff> staffList = q.getResultList();
        if (!staffList.isEmpty()) {
            return staffList.get(0);
        }
        return null; // Hoặc xử lý trường hợp không tìm thấy nhân viên ở đây
    }

}
