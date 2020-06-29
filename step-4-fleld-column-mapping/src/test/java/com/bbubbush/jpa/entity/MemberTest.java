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
     *  - @Column :
     *  - @Enumerated :
     *  - @Temporal :
     *  - @Lob :
     *  - @Transient :
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