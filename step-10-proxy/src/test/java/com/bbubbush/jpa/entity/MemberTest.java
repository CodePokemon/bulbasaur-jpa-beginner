package com.bbubbush.jpa.entity;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class MemberTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    @Test
    public void proxy_sample_01() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Team team = new Team();
            team.setName("Ggoma Ggoma");
            em.persist(team);

            Member member = new Member();
            member.setName("wonrak");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("=============[실제 테스트 로직 시작]=============");
            System.out.println("1. Proxy 객체 조회");
            System.out.println("memberId :: " + member.getId());
            Member referenceMember = em.getReference(Member.class, member.getId());
            System.out.println("referenceMember.getId() :: " + referenceMember.getId());    // SELECT 쿼리가 실행되지 않는다.
            System.out.println("referenceMember.getClass().getName() :: " + referenceMember.getClass().getName());
            System.out.println("\n2. 객체 조회");
            System.out.println("memberId :: " + member.getId());
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember.getId() :: " + findMember.getId());    // SELECT 쿼리가 실행된다.
            System.out.println("findMember.getClass().getName() :: " + findMember.getClass().getName());
            System.out.println("\n3. 비교");
            System.out.println("findMember == referenceMember :: " + (findMember == referenceMember));
            System.out.println("=============[실제 테스트 로직 끝]=============");

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
    public void proxy_sample_02() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Team team = new Team();
            team.setName("Ggoma Ggoma");
            em.persist(team);

            Member member = new Member();
            member.setName("wonrak");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("=============[실제 테스트 로직 시작]=============");
            System.out.println("1. 객체 조회");
            System.out.println("memberId :: " + member.getId());
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember.getId() :: " + findMember.getId());
            System.out.println("findMember.getClass().getName() :: " + findMember.getClass().getName());
            System.out.println("\n2. Proxy 객체 조회");
            System.out.println("memberId :: " + member.getId());
            Member referenceMember = em.getReference(Member.class, member.getId()); // proxy 객체를 호출 하지 않는다.
            System.out.println("referenceMember.getId() :: " + referenceMember.getId());
            System.out.println("referenceMember.getClass().getName() :: " + referenceMember.getClass().getName());
            System.out.println("\n3. 비교");
            System.out.println("findMember == referenceMember :: " + (findMember == referenceMember));
            System.out.println("=============[실제 테스트 로직 끝]=============");

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