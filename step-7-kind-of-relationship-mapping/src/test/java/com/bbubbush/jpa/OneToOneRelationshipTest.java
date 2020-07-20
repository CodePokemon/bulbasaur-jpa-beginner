package com.bbubbush.jpa;

import com.bbubbush.jpa.one.OneToOneMember;
import com.bbubbush.jpa.one.OneToOneTeam;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

public class OneToOneRelationshipTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    /**
     * Name: one_to_one_relationship
     * Date: 2020/07/14
     * Info: 
     *  [양방향 매핑 - @OneToOne]
     *  - Member(One) : Team(One)
     *  - @OneToOne의 단방향은 JPA에서 지원하지 않는다.
     *  - 반대도 @OneToOne.
     *  - Member가 TEAM_ID를 갖거나 Team이 MEMBER_ID를 갖는 방법 모두 가능하기 떄문에 어떤 Entitydp FK를 둘 지에 대해 고민해 볼 필요가 있다.
     */
    @Test
    public void one_to_one_relationship() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            OneToOneMember member = new OneToOneMember();
            member.setMemberName("imesung");
            em.persist(member);

            OneToOneTeam team = new OneToOneTeam();
            team.setTeamName("Team Paipai");
            team.setMember(member);
            em.persist(team);

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
