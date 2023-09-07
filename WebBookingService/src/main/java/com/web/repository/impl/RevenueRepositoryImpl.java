/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.repository.impl;

import com.web.pojo.Coachstripcoachseat;
import com.web.pojo.Orderships;
import com.web.pojo.Transporttruck;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.web.repository.RevenueRepository;
import java.util.Date;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class RevenueRepositoryImpl implements RevenueRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public int totalRevenue() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Coachstripcoachseat> cRoot = query.from(Coachstripcoachseat.class);
        Predicate condition = builder.equal(cRoot.get("statusSeat"), 1);
        query.multiselect(builder.sum(cRoot.get("price"))).where(condition);
        javax.persistence.Query q = s.createQuery(query);
        return ((BigDecimal) q.getSingleResult()).intValue();
    }

    @Override
    public int totalRevenueOrder() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);
        Root<Orderships> oRoot = query.from(Orderships.class);
        query.select(builder.sum(oRoot.get("amountSent")));

        javax.persistence.Query q = s.createQuery(query);
        return ((BigDecimal) q.getSingleResult()).intValue();
    }

    @Override
    public List<Object[]> RevenueStatistics(LocalDate date, int typeStat) {
        Session s = this.factory.getObject().getCurrentSession();
        int year = date.getYear();
        int month = date.getMonthValue();
        int quarter = date.get(IsoFields.QUARTER_OF_YEAR);
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Coachstripcoachseat> seatRoot = cq.from(Coachstripcoachseat.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(seatRoot.get("statusSeat"), 1));
        Predicate condition = cb.and(predicates.toArray(Predicate[]::new));
        if (typeStat == 1) {
            predicates.add(cb.equal(cb.function("YEAR", Integer.class, seatRoot.get("departureTime")), year));
            predicates.add(cb.equal(cb.function("QUARTER", Integer.class, seatRoot.get("departureTime")), quarter));

            predicates.add(cb.equal(seatRoot.get("statusSeat"), 1));

            cq.multiselect(
                    cb.function("YEAR", Integer.class, seatRoot.get("departureTime")).alias("year"),
                    cb.function("QUARTER", Integer.class, seatRoot.get("departureTime")).alias("quarter"),
                    cb.sum(seatRoot.get("price")).alias("totalRevenue")
            );
            cq.groupBy(
                    cb.function("YEAR", Integer.class, seatRoot.get("departureTime")),
                    cb.function("QUARTER", Integer.class, seatRoot.get("departureTime"))
            );
            cq.orderBy(
                    cb.asc(cb.function("QUARTER", Integer.class, seatRoot.get("departureTime"))),
                    cb.asc(cb.function("YEAR", Integer.class, seatRoot.get("departureTime")))
            );
            cq.where(predicates.toArray(new Predicate[0]));
            Query query = s.createQuery(cq);
            return query.getResultList();
        }
        if (typeStat == 2) {

            predicates.add(cb.equal(cb.function("YEAR", Integer.class, seatRoot.get("departureTime")), year));
            predicates.add(cb.equal(seatRoot.get("statusSeat"), 1));
            cq.multiselect(
                    cb.function("YEAR", Integer.class, seatRoot.get("departureTime")).alias("year"),
                    cb.sum(seatRoot.get("price")).alias("totalRevenue")
            );
            cq.groupBy(cb.function("YEAR", Integer.class, seatRoot.get("departureTime")));
            cq.orderBy(cb.asc(cb.function("YEAR", Integer.class, seatRoot.get("departureTime"))));
            cq.where(predicates.toArray(new Predicate[0]));
            Query query = s.createQuery(cq);
            return query.getResultList();
        }
        if (typeStat == 3) {
            predicates.add(cb.equal(cb.function("YEAR", Integer.class, seatRoot.get("departureTime")), year));
            predicates.add(cb.equal(cb.function("MONTH", Integer.class, seatRoot.get("departureTime")), month));
            predicates.add(cb.equal(seatRoot.get("statusSeat"), 1));

            cq.multiselect(
                    cb.function("YEAR", Integer.class, seatRoot.get("departureTime")).alias("year"),
                    cb.function("MONTH", Integer.class, seatRoot.get("departureTime")).alias("month"),
                    cb.sum(seatRoot.get("price")).alias("totalRevenue")
            );
            cq.groupBy(
                    cb.function("YEAR", Integer.class, seatRoot.get("departureTime")),
                    cb.function("MONTH", Integer.class, seatRoot.get("departureTime"))
            );
            cq.orderBy(
                    cb.asc(cb.function("YEAR", Integer.class, seatRoot.get("departureTime"))),
                    cb.asc(cb.function("MONTH", Integer.class, seatRoot.get("departureTime")))
            );
            cq.where(predicates.toArray(new Predicate[0]));
            Query query = s.createQuery(cq);
            return query.getResultList();
        }
        predicates.add(cb.equal(seatRoot.get("statusSeat"), 1));
        cq.multiselect(
                cb.function("YEAR", Integer.class, seatRoot.get("departureTime")).alias("year"),
                cb.function("MONTH", Integer.class, seatRoot.get("departureTime")).alias("month"),
                cb.sum(seatRoot.get("price")).alias("totalRevenue")
        );
        cq.groupBy(
                cb.function("YEAR", Integer.class, seatRoot.get("departureTime")),
                cb.function("MONTH", Integer.class, seatRoot.get("departureTime"))
        );
        cq.orderBy(
                cb.asc(cb.function("YEAR", Integer.class, seatRoot.get("departureTime"))),
                cb.asc(cb.function("MONTH", Integer.class, seatRoot.get("departureTime")))
        );
        cq.where(predicates.toArray(new Predicate[0]));
        Query query = s.createQuery(cq);
        return query.getResultList();

    }

    @Override
    public List<Object[]> RevenueOrder(LocalDate date, int typeStat) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Orderships> orderRoot = cq.from(Orderships.class);
        Join<Orderships, Transporttruck> transportJoin = orderRoot.join("idTrans", JoinType.INNER);

        int year = date.getYear();
        int month = date.getMonthValue();
        int quarter = date.get(IsoFields.QUARTER_OF_YEAR);

        List<Predicate> predicates = new ArrayList<>();

        if (typeStat == 1) {

            predicates.add(cb.equal(cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")), year));
            predicates.add(cb.equal(cb.function("QUARTER", Integer.class, transportJoin.get("dateTrans")), quarter));

            cq.multiselect(
                    cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")).alias("year"),
                    cb.function("QUARTER", Integer.class, transportJoin.get("dateTrans")).alias("quarter"),
                    cb.sum(orderRoot.get("amountSent")).alias("totalAmountSent")
            );
            cq.groupBy(
                    cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")),
                    cb.function("QUARTER", Integer.class, transportJoin.get("dateTrans"))
            );
            cq.orderBy(
                    cb.asc(cb.function("QUARTER", Integer.class, transportJoin.get("dateTrans"))),
                    cb.asc(cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")))
            );
            cq.where(predicates.toArray(new Predicate[0]));
            Query query = session.createQuery(cq);
            return query.getResultList();
        }
        if (typeStat == 2) {
            cq.where(predicates.toArray(new Predicate[0]));
            predicates.add(cb.equal(cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")), year));
            cq.multiselect(
                    cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")).alias("year"),
                    cb.function("MONTH", Integer.class, transportJoin.get("dateTrans")),
                    cb.sum(orderRoot.get("amountSent")).alias("totalAmountSent")
            );
            cq.groupBy(
                    cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")),
                    cb.function("MONTH", Integer.class, transportJoin.get("dateTrans"))
            );
            cq.orderBy(cb.asc(cb.function("YEAR", Integer.class, transportJoin.get("dateTrans"))));
            cq.where(predicates.toArray(new Predicate[0]));
            Query query = session.createQuery(cq);
            return query.getResultList();
        }
        if (typeStat == 3) {

            predicates.add(cb.equal(cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")), year));
            predicates.add(cb.equal(cb.function("MONTH", Integer.class, transportJoin.get("dateTrans")), month));

            cq.multiselect(
                    cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")).alias("year"),
                    cb.function("MONTH", Integer.class, transportJoin.get("dateTrans")),
                    cb.sum(orderRoot.get("amountSent")).alias("totalAmountSent")
            );
            cq.groupBy(
                    cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")),
                    cb.function("MONTH", Integer.class, transportJoin.get("dateTrans"))
            );
            cq.orderBy(
                    cb.asc(cb.function("MONTH", Integer.class, transportJoin.get("dateTrans"))),
                    cb.asc(cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")))
            );
            cq.where(predicates.toArray(new Predicate[0]));
            Query query = session.createQuery(cq);
            return query.getResultList();
        }
        cq.multiselect(
                cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")).alias("year"),
                cb.function("MONTH", Integer.class, transportJoin.get("dateTrans")),
                cb.sum(orderRoot.get("amountSent")).alias("totalAmountSent")
        );
        cq.groupBy(
                cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")),
                cb.function("MONTH", Integer.class, transportJoin.get("dateTrans"))
        );
        cq.orderBy(
                cb.asc(cb.function("MONTH", Integer.class, transportJoin.get("dateTrans"))),
                cb.asc(cb.function("YEAR", Integer.class, transportJoin.get("dateTrans")))
        );

        Query query = session.createQuery(cq);
        return query.getResultList();
    }

}
