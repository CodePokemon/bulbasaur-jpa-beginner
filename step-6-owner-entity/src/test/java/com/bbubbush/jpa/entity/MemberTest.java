package com.bbubbush.jpa.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MemberTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");


    /**
     * Name: update_team
     * Date: 2020/07/07
     * Info:
     *  [양방향 매핑 - 주인에만 값을 변경하는 경우]
     *
     */
    @Test
    public void update_team_owner() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Team team = new Team();
            team.setName("Team GgobukGgobuk");
            em.persist(team);

            Member member = new Member();
            member.setName("bbubbush");
            member.setTeam(team);
            em.persist(member);
            Long findMemberId = member.getMemberId();

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, findMemberId);
            findMember.setTeam(null);       // UPDATE 쿼리가 실행된다.

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
     * Name: update_team_mappedBy
     * Date: 2020/07/08
     * Info:
     *  [양방향 매핑 - mappedBy에만 값을 변경하는 경우]
     */
    @Test
    public void update_team_mappedBy() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Team team = new Team();
            team.setName("Team GgobukGgobuk");
            em.persist(team);
            Long findTeamId = team.getTeamId();

            Member member = new Member();
            member.setName("bbubbush");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, findTeamId);
            findTeam.setMembers(null); // UPDATE 쿼리가 실행되지 않는다.

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