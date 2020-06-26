package com.bbubbush.jpa.entiy;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;

import static org.junit.Assert.*;

public class MemberTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    /**
     * Name: use_id_annotation
     * Date: 2020/06/26
     * Info:
     *  [@Id 사용하기]
     *  - @Id는 기본키를 직접 할당 해야한다.
     *  - @GeneratedValue와 조합하여 자동으로 할당할 수 있다.
     *  - 생략하면 예외를 발생시킬 것 같았으나, INSERT 문이 실행되지 않고 종료된다.(H2 DB 기준)
     */
    @Test
    public void use_id_annotation() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            MemberUseId member = new MemberUseId();
//            member.setId(1L); // 기본키 직접 할당
            member.setName("bbubbush");

            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

        // then - 형식적인 결과 확인
        assertTrue(true);
    }

    /**
     * Name: you_can_change_generated_strategy
     * Date: 2020/06/26
     * Info:
     *  [@GeneratedValue 전략 변경 해보기]
     *  File Path :: com.bbubbush.jpa.entiy.MemberUseIdAndGeneratedValue
     *  Strategy ::
     *   - AUTO : DB Dialect에 따라 기준이 다르다. 별도의 전략을 정하지 않으면 AUTO가 기본값으로 사용된다.
     *   - IDENTITY : 데이터베이스에 생성방법을 위임한다.
     *   - SEQUENCE : 시퀀스 객체를 생성하여 다음값을 요청하여 받는다. 객체를 생성하는 쿼리와 값을 요청하는 쿼리를 확인할 수 있다.
     *   - TABLE : 기본키값을 관리할 테이블을 생성한다.
     */
    @Test
    public void you_can_change_generated_strategy() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            // 아래 객체에서 전략을 변경해보면서 로그를 확인한다.
            MemberUseIdAndGeneratedValue member = new MemberUseIdAndGeneratedValue();
//            member.setId(1L);
            member.setName("bbubbush");

            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

        // then - 형식적인 결과 확인
        assertTrue(true);
    }
}