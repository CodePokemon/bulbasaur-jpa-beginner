package com.bbubbush.jpa.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

public class WrongMemberTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    /**
     * Name: setUp
     * Date: 2020/07/02
     * Info:
     *  [기초 데이터 저장]
     */
    @Before
    public void setUp() {
        setDefaultData();
    }

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

        // then - 형식적인 결과 확인
        assertTrue(true);
    }

    /**
     * Name: setDefaultData
     * Date: 2020/07/03
     * Info:
     *  [3영의 멤버와 3개의 팀 생성]
     */
    private void setDefaultData(){
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            RightTeam teamA = new RightTeam();
            teamA.setName("Team Pika");
            em.persist(teamA);

            RightMember memberA = new RightMember();
            memberA.setName("bbubbush");
            memberA.setTeam(teamA);
            em.persist(memberA);

            RightTeam teamB = new RightTeam();
            teamB.setName("Team CC");
            em.persist(teamB);

            RightMember memberB = new RightMember();
            memberB.setName("junu");
            memberB.setTeam(teamB);
            em.persist(memberB);

            RightTeam teamC = new RightTeam();
            teamC.setName("Team PaiPai");
            em.persist(teamC);

            RightMember memberC = new RightMember();
            memberC.setName("imesung");
            memberC.setTeam(teamC);
            em.persist(memberC);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Name: destroy
     * Date: 2020/07/03
     * Info:
     *  [EntityManagerFactory 닫기]
     */
    @After
    public void destroy() {
        emf.close();
    }
}
