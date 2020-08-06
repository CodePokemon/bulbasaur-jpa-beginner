package com.bbubbush.jpa.entity;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

public class EagerLoadindMemberTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    @Test
    public void find_team() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            EagerLoadingTeam team = new EagerLoadingTeam();
            team.setName("Ya-don");
            em.persist(team);

            EagerLoadindMember member = new EagerLoadindMember();
            member.setName("imesung");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("=============[실제 테스트 로직 시작]=============");
            System.out.println("[Start] findMember");
            EagerLoadindMember findMember = em.find(EagerLoadindMember.class, member.getId());  // TEAM까지 같이 조회한다.
            System.out.println("[End] findMember");

            System.out.println(findMember.getTeam());   // 쿼리가 실행되지 않는다.

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