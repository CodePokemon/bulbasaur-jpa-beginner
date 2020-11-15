package com.bbubbush.jpa.entity;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

public class CascadeTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    @Test
    public void cascade_team() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Team team = new Team();
            team.setName("Hong-su-mon");

            Member member1 = new Member();
            member1.setName("imesung");
            Member member2 = new Member();
            member2.setName("junu");

            team.addMember(member1);
            team.addMember(member2);

            em.persist(team);
            // Cascade.All을 설정하면 자식 엔티티를 부모에서 관리할 수 있다.
//            em.persist(member1);
//            em.persist(member2);

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
    @Test
    public void orphanRemoval_team() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Team team = new Team();
            team.setName("Hong-su-mon");

            Member member1 = new Member();
            member1.setName("imesung");
            Member member2 = new Member();
            member2.setName("junu");

            team.addMember(member1);
            team.addMember(member2);

            em.persist(team);
            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getTeamId());

            findTeam.getMembers().remove(0);

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
