package com.bbubbush.jpa.entity;

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
     * Name: you_can_change_ddl_option
     * Date: 2020/06/21
     * Info:
     *  [DDL 옵션 변경해보기]
     *  File Path :: META-INF/persistence.xml
     *  Property name :: hibernate.hbm2ddl.auto
     *  can use value ::
     *  - create : 애플리케이션 구동 시점에 @Entity 객체를 통해 DROP, CREATE TABLE을 순차적으로 실행한다. 당연히 데이터는 모두 삭제된다.
     *  - create-drop : 기본적으로 create 옵션과 동일하나, 애플리케이션 종료 시점에 테이블을 DROP한다.
     *  - update : 애플리케이션 구동 시점에 Entity의 변경이 있는 경우에만 ALTER 쿼리를 실행해준다.
     *      다만 여기서 말하는 변경은 새로운 컬럼의 추가를 말한다. 기존 프로퍼티(컬럼)를 삭제하는 경우는 오류가 발생하므로 주의.
     *  - validate : 애플리케이션 구동 시점에 Entity와 DB 테이블이 정상적으로 매핑이 되는지 점검한다. 변경사항이 발생하면 오류 발생.
     *  - none : 사용하지 않는다.
     *
     *  단, 파일을 변경하면서 확인하기 어려우니 EntityManagerFactory 생성 시 Map으로 properties를 지정하여 전달하는 방식으로
     *  이번 테스트를 진행하면 된다.
     */
    @Test
    public void you_can_change_ddl_option() {
        // given
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("hibernate.hbm2ddl.auto", "validate");   /* 주석을 토대로 테스트 할 속성값을 지정한다. */
        emf = Persistence.createEntityManagerFactory("bbubbush", properties);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Member member = new Member(1L, "HelloJPA");

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