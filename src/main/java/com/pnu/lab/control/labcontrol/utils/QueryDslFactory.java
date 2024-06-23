package com.pnu.lab.control.labcontrol.utils;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLTemplates;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class QueryDslFactory {

    private static JPASQLQuery<?> sqlQueryFactory;
    private static JPAQueryFactory queryFactory;

    public QueryDslFactory(EntityManager em) {
        sqlQueryFactory = new JPASQLQuery<>(em, SQLTemplates.DEFAULT);
        queryFactory = new JPAQueryFactory(em);
    }

    public static JPAQueryFactory getQueryFactory() {
        if (queryFactory == null) {
            throw new NullPointerException("Query Factory is not initialized yet");
        }
        return queryFactory;
    }

    public static JPASQLQuery<?> getSqlQueryFactory() {
        if (queryFactory == null) {
            throw new NullPointerException("Sql Query Factory is not initialized yet");
        }
        return sqlQueryFactory;
    }
}
