package com.example.jeed.s06;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.example.jeed.s05.CoderPlain;

public class CoderDao {
    public List<CoderPlain> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            // explicit HQL query is "select c from CoderPlain c"
            Query<CoderPlain> query = session.createQuery("from CoderPlain", CoderPlain.class);
            return query.list();
        }
    }
}
