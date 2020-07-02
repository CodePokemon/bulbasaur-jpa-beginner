package com.bbubbush.jpa.entity;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

public class WrongMemberTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    /**
     * Name: save
     * Date: 2020/06/21
     * Info:
     *  [Not OOP 객체 모델링 - 저장]
     *  - 데이터베이스 모델링을 그대로 가져온 객체의 관계 방법이다.
     *  - PK와 FK로 구분하며, 키를 중심으로 관계를 맺는다.
     */
    @Test
    public void save() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            WrongTeam team = new WrongTeam();
            team.setName("Team Pika");
            em.persist(team);

            WrongMember member = new WrongMember();
            member.setName("bbubbush1");
            member.setTeamId(team.getTeamId());
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

        // then - 형식적인 결과 확인
        assertTrue(true);
    }
}
