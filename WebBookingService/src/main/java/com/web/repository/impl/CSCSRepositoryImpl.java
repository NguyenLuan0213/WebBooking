/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Coachstripcoachseat;
import com.web.repository.CSCSRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class CSCSRepositoryImpl implements CSCSRepository {

    @Autowired
    private LocalSessionFactoryBean Factory;

    @Autowired
    private Environment env;

    @Override
    public List<Coachstripcoachseat> getCoachstripcoachseat(Map<String, String> params) {
        Session s = this.Factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Coachstripcoachseat> q = b.createQuery(Coachstripcoachseat.class);
        Root<Coachstripcoachseat> root = q.from(Coachstripcoachseat.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
//                Predicate idCoachNamePredicate = b.like(root.get("idCoach").get("name").as(String.class), String.format("%%%s%%", kw));
                Predicate nameSeatPredicate = b.like(root.get("nameSeat").as(String.class), String.format("%%%s%%", kw));
                Predicate departureTimePredicate = b.like(root.get("departureTime").as(String.class), String.format("%%%s%%", kw));

                // Parse kw as a LocalDateTime
                if (params.containsKey("formattedDepartureTime")) {
                    String formattedDepartureTime= params.get("formattedDepartureTime");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    try {
                        Date formattedDate = dateFormat.parse(formattedDepartureTime);

                        // Convert Date to LocalDateTime to get rid of .0
                        Instant instant = formattedDate.toInstant();
                        ZoneId zone = ZoneId.systemDefault();
                        LocalDateTime departureTime = instant.atZone(zone).toLocalDateTime();

                        predicates.add(b.equal(root.get("departureTime"), departureTime));
                    } catch (ParseException e) {
                        // Handle date format error
                    }
                }

                // Combine the predicates using OR or AND based on your requirements
                predicates.add(b.or(nameSeatPredicate, departureTimePredicate));
            }

            q.where(predicates.toArray(new Predicate[0]));
        }
        q.orderBy(b.desc(root.get("idCSCS")));

        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null) {
                int pageSize = Integer.parseInt(this.env.getProperty("PAGECSCS_SIZE"));
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public int countCoachstripcoachseat() {
        Session s = this.Factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Coachstripcoachseat");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addOrUpdateCoachstripcoachseat(Coachstripcoachseat cscs) {
        Session s = this.Factory.getObject().getCurrentSession();
        try {
            if (cscs.getIdCSCS() == null) {
                s.save(cscs);
            } else {
                s.update(cscs);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Coachstripcoachseat getCoachstripcoachseatById(int idCSCS) {
        Session s = this.Factory.getObject().getCurrentSession();
        return s.get(Coachstripcoachseat.class, idCSCS);
    }

    @Override
    public boolean deleteCoachstripcoachseat(int idCSCS) {
        Session s = this.Factory.getObject().getCurrentSession();
        Coachstripcoachseat sta = this.getCoachstripcoachseatById(idCSCS);
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
    public List<Coachstripcoachseat> getAllCoachstripcoachseat() {
        return em.createQuery("SELECT s FROM Coachstripcoachseat s", Coachstripcoachseat.class).getResultList();
    }

//    @Override
//    public boolean updateCoachstripcoachseatData() {
//        Session s = this.Factory.getObject().getCurrentSession();
//
//        try {
//            // Cập nhật statusSeat
//            String updateQuery = "UPDATE Coachstripcoachseat c SET c.statusSeat = 1 WHERE c.departureTime < CURRENT_TIMESTAMP";
//            int updateResult = s.createQuery(updateQuery).executeUpdate();
//
//            // Thêm các bản ghi mới
//            String insertQuery = "INSERT INTO coachstripcoachseat (idCoach, idCoachStrips, price, departureTime, statusSeat, nameSeat, idStaff) "
//                    + "SELECT idCoach, idCoachStrips, price, DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY) AS newDepartureTime, "
//                    + "1 AS statusSeat, nameSeat, idStaff "
//                    + "FROM coachstripcoachseat "
//                    + "WHERE DATE(departureTime) = CURRENT_DATE ";
//            int insertResult = s.createSQLQuery(insertQuery).executeUpdate();
//
//            System.out.println("isUpdate");
//
//            // Kiểm tra nếu cả hai câu lệnh đều thực hiện thành công
//            return updateResult > 0 && insertResult > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
