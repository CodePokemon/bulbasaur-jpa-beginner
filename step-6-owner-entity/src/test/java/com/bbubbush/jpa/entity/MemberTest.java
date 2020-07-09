package com.bbubbush.jpa.entity;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

public class MemberTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");


    /**
     * Name: update_team_owner
     * Date: 2020/07/07
     * Info:
     *  [양방향 매핑 - 주인에만 값을 변경하는 경우]
     *  - 관계의 주인이 아니면 데이터 변경의 주도권이 없다. 다른 의미로 mappedBy매핑은 오로지 조회만 가능하다.
     *  - 이는 관계형 DB는 FK로 여러 테이블을 하나로 연결하지만, 객체지향에서는 이렇게 구현될 수 없다.
     *  - 따라서 양 Entity에서 단방향으로 서로를 접근할 수 있게 구현하여 마치 '양방향'처럼 행동하게 한다.
     *    따라서 매핑의 주인을 설정하여 한쪽에서만 데이터의 변경을 관리하게 해야한다.
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
     *  - 관계의 주인이 아니면 데이터 변경의 주도권이 없다. 다른 의미로 mappedBy매핑은 오로지 조회만 가능하다.
     *  - 이는 관계형 DB는 FK로 여러 테이블을 하나로 연결하지만, 객체지향에서는 이렇게 구현될 수 없다.
     *  - 따라서 양 Entity에서 단방향으로 서로를 접근할 수 있게 구현하여 마치 '양방향'처럼 행동하게 한다.
     *    따라서 매핑의 주인을 설정하여 한쪽에서만 데이터의 변경을 관리하게 해야한다.
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

    /**
     * Name: update_team_both
     * Date: 2020/07/09
     * Info:
     *  [양방향 매핑 - 양쪽 모두 값을 변경하는 경우]
     *  - 권장되는 방법이다. 변경 이후 detach 상태가 되어도 데이터베이스와 일원화된 상태로 객체가 남기 때문에 오류가 발생할 원인이 줄어든다.
     *  -
     */
    @Test
    public void update_team_both() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        String expectedTeamName = "Team New Pika";
        String afterUpdateTeamName = null;

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
            Team newTeam = new Team();
            newTeam.setName(expectedTeamName);
            em.persist(newTeam);

            // Member, Team 둘 다 관리해야 한다.
            findMember.setTeam(newTeam);
            newTeam.getMembers().add(findMember);
            afterUpdateTeamName = findMember.getTeam().getName();
            
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        // then
        assertThat(afterUpdateTeamName, equalTo(expectedTeamName));
    }
}