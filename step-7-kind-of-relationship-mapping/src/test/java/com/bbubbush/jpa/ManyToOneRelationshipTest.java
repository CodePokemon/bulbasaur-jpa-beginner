package com.bbubbush.jpa;

import com.bbubbush.jpa.many.ManyToOneMember;
import com.bbubbush.jpa.many.ManyToOneTeam;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

public class ManyToOneRelationshipTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    /**
     * Name: many_to_one_relationship
     * Date: 2020/07/13
     * Info:
     *  [단방향 매핑 - @ManyToOne]
     *  - Member(Many) : Team(One)
     *  - MANY_TO_ONE_MEMBER.TEAM_ID가 주인이 된다.
     *  - 가장 많이 사용하는 연관관계. 반대는 @OneToMany.
     *  - 양방향 매핑이 필요하면 FK 쪽을 관계의 주인으로 설정한다. (DB 설계상 대부분 Many쪽이 FK를 갖게 된다.)
     */
    @Test
    public void many_to_one_relationship() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            ManyToOneTeam team = new ManyToOneTeam();
            team.setTeamName("Team GgobukGgobuk");
            em.persist(team);

            ManyToOneMember member = new ManyToOneMember();
            member.setMemberName("bbubbush");
            member.setTeam(team);
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
