package com.example.jed.s12;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.dao.JpaUtil;
import com.example.jed.s05.Coder05;

public class CoderDao {
    private static final Logger log = LoggerFactory.getLogger(CoderDao.class);

    public boolean create(Coder05 coder) {
        EntityManager em = null;
        EntityTransaction tx = null;
        log.trace("enter");

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(coder);
            tx.commit();
            return true;
        } catch (Exception ex) {
            log.error("Can't persist entity", ex);
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.warn("Can't rollback transaction", e);
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean update(Coder05 coder) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(coder);
            tx.commit();
            return true;
        } catch (Exception ex) {
            log.warn("Can't merge entity", ex);
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.warn("Can't rollback transaction", e);
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean delete(long id) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JpaUtil.createEntityManager();
            Coder05 entity = em.find(Coder05.class, id);
            if (entity != null) {
                tx = em.getTransaction();
                tx.begin();
                em.remove(entity);
                tx.commit();
                return true;
            } else {
                log.info("Can't remove missing coder " + id);
                return false;
            }
        } catch (Exception ex) {
            log.warn("Can't remove coder " + id, ex);
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception e) {
                log.warn("Can't rollback transaction", e);
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
