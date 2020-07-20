package com.bbubbush.jpa;

import com.bbubbush.jpa.one.OneToManyMember;
import com.bbubbush.jpa.one.OneToManyTeam;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

public class OneToManyRelationshipTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    /**
     * Name: one_to_many_relationship
     * Date: 2020/07/13
     * Info:
     *  [단방향 매핑 - @OneToMany]
     *  - Team(One) : Member(Many)
     *  - ONE_TO_MANY_TEAM.TEAM_ID가 주인이 된다.
     *  - 반대는 @ManyToOne.
     *  - @JoinColumn을 꼭 사용해야 한다. 그렇지 않으면 @JoinTable방식으로 구현 된다.
     *  - DB 설계상 1:N 관계에서는 N이 FK를 갖게 된다. @OneToMany는 DB의 설계에 반하는 결과를 갖게 된다.
     *  - 예를 들면 아래 Team에 Member를 추가할 때, 쿼리를 보면 Member 테이블의 업데이트가 이뤄지게 된다.
     *  - 변경한 객체는 Team인데 변경된 테이블은 Member가 되는 아이러니함이 존재한다.
     *
     *  - 결론적으로 @OneToMany를 @ManyToOne으로 풀어서 설계하는 것이 바람직하다.
     */
    @Test
    public void one_to_many_relationship() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            OneToManyMember member = new OneToManyMember();
            member.setMemberName("junu");
            em.persist(member);

            OneToManyTeam team = new OneToManyTeam();
            team.setTeamName("Team Pika");
            team.getMembers().add(member);  // Team 객체의 변경이 있지만, Member 테이블에 UPDATE 쿼리가 실행된다.
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
