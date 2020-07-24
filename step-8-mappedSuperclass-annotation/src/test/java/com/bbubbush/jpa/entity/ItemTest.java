package com.bbubbush.jpa.entity;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class ItemTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    @Test
    public void setUp() {
//        // given
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        // when
//        tx.begin();
//        try {
//
//            tx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//
//        // then - 형식적인 결과 확인
//        assertTrue(true);
    }
}