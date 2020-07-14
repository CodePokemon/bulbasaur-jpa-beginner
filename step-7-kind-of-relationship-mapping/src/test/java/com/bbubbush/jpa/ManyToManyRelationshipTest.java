package com.bbubbush.jpa;

import com.bbubbush.jpa.many.ManyToManyMember;
import com.bbubbush.jpa.many.ManyToManyTeam;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

public class ManyToManyRelationshipTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    /**
     * Name: many_to_many_relationship
     * Date: 2020/07/14
     * Info:
     *  [단방향 매핑 - @ManyToMany]
     *  - Member(Many) : Team(Many)
     *  - 기본적으로 @JoinTable을 통해 두 테이블 사이에 연결 테이블을 생성한다.
     *  - 아래 샘플 코드를 실행하면 CONNECTION_TABLE 이름의 JoinTable이 생성된다.
     *  - 실무에서는 사용하지 않는 방법이다.
     *  - 그 이유로는 연결 테이블에 비즈니스 데이터를 위해 컬럼이 추가될 여지가 매우 높다.
     *  - 따라서 연결테이블을 엔티티로 변경하여 비즈니스에 맞게 설계하는 것이 낫다.
     *  - Member(N) : Team(M)  ->  Member(N) : (1) New Entity (1) : Team(M)
     *  - 위와 같이 @ManyToOne 혹은 @OneToMany로 풀어낸다.
     *
     *  - 결론 :: @ManyToMany는 새로운 엔티티를 도입하여 비즈니스를 재검토하여 @ManyToOne 혹은 @OneToMany로 변경한다.
     */
    @Test
    public void many_to_many_relationship() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            ManyToManyTeam team = new ManyToManyTeam();
            team.setTeamName("Team Pika");
            em.persist(team);

            ManyToManyMember member = new ManyToManyMember();
            member.setMemberName("junu");
            member.getTeams().add(team);
            em.persist(member);

            tx.commit();        // JoinTable에 INSERT 쿼리가 실행되는 것을 볼 수 있다.
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
