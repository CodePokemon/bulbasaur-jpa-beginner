package com.bbubbush.jpa.entity;

import com.bbubbush.jpa.type.RoleType;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class MemberTest {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    /**
     * Name: use_column_mapping
     * Date: 2020/06/21
     * Info:
     *  [Column mapping 살펴보기]
     *  Type ::
     *  - @Column : 컬럼 매핑. 다양한 속성값을 활용할 수 있다.(Member 객체 참고)
     *  - @Enumerated : Enum 타입 매핑. EnumType을 꼭 STRING으로 변경해서 사용. 기본값은 ORDINAL.
     *  - @Temporal : 날짜타입 매핑. TemporalType을 통해 날짜(DATE), 시간(TIME), 날짜+시간(TIMESTAMP) 을 선택할 수 있다.
     *      현재는 LocalDateTime과 LocalDate 객체를 사용하여 자동으로 날짜값을 매핑하기 때문에 사용하지 않는다.
     *  - @Lob : CLOB 혹은 BLOB 매핑. 필드 타입이 문자면 CLOB을 매핑하고, 나머지 타입은 BLOB을 매핑한다.
     *  - @Transient : 매핑을 하지 않는다. 반대로 생각하면 매핑하지 않기 위해 필드값에 어떤 애노테이션도 선언하지 않으면 매핑이 일어난다.
     */
    @Test
    public void use_column_mapping() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Member member = new Member();
            member.setId(1L);
            member.setAge(10);
            member.setUsername("bbubbush");
            member.setRoleType(RoleType.Normal);
            member.setCreatedDate(new Date());
            member.setLastModifiedDate(new Date());
            member.setDescription("Description is too much.");

            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

        // then - 형식적인 결과 확인
        assertTrue(true);
    }
}