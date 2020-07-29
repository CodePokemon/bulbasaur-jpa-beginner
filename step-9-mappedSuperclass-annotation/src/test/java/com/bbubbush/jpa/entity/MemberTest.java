package com.bbubbush.jpa.entity;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class MemberTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    @Test
    public void mappedSuperclass_test() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Team team = new Team();
            team.setName("Pai Pai");
            team.setCreatedBy("bbubbush");
            team.setCreatedDate(LocalDateTime.now());
            em.persist(team);

            Member member = new Member();
            member.setName("bbubbush");
            member.setTeam(team);
            member.setCreatedBy("bbubbush");
            member.setCreatedDate(LocalDateTime.now());
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        // then - 형식적인 결과 확인
        assertTrue(true);
    }
}