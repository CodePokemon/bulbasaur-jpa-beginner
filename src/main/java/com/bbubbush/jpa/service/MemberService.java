package com.bbubbush.jpa.service;

import com.bbubbush.jpa.config.EntitimanagerUtils;
import com.bbubbush.jpa.entity.Member;

import javax.persistence.*;

/**
 * Name: MemberService
 * Date: 2020/06/20
 * Info:
 *  학습테스트를 위한 서비스
 *  [Caution!!!]
 *    - 각 메서드들은 메서드 내에서 EntityManagerFactory를 생성 후 종료 한다.
 *    - EntityManagerFactory는 애플리케이션의 생명주기와 같아야 하지만 테스트를 위해 이와 같이 생성하였다.
 *    - 각 서비스를 하나의 EntityManager로 묶어서 진행하도록 제공되어 있지 않기 때문에 꼭! 단일 기능의 결과만 확인하여야 한다.
 *    - 하나의 EntityManagerFactory로 묶어 테스트 하는 기능은 다음 학습 프로젝트부터 제공한다.
 */
public class MemberService {
    /**
     * Name: findMember
     * Date: 2020/06/20
     * Info:
     *  입력한 ID의 Member 객체를 조회한다.
     */
    public Member findMember(Long id) {
        EntityManager em = EntitimanagerUtils.getEntityManagerFactory();
        EntityTransaction tx = em.getTransaction();
        Member member = null;

        tx.begin();
        try {
            member = em.find(Member.class, id);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        EntitimanagerUtils.deployEntityManagerFactory();
        return member;
    }

    /**
     * Name: updateName
     * Date: 2020/06/20
     * Info:
     *  입력한 ID에 해당하는 Member 객체의 이름을 변경한다.
     */
    public void updateName(Long id, String afterName) {
        EntityManager em = EntitimanagerUtils.getEntityManagerFactory();
        EntityTransaction tx = em.getTransaction();
        Member member = null;

        tx.begin();
        try {
            member = em.find(Member.class, id);
            member.setName(afterName);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        EntitimanagerUtils.deployEntityManagerFactory();
    }

    /**
     * Name: insertMember
     * Date: 2020/06/20
     * Info:
     *  Member 객체를 등록한다.
     */
    public void insertMember(Member member) {
        EntityManager em = EntitimanagerUtils.getEntityManagerFactory();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        EntitimanagerUtils.deployEntityManagerFactory();
    }
    /**
     * Name: removeMember
     * Date: 2020/06/20
     * Info:
     *  조회한 Member 객체를 삭제 한다.
     */
    public void removeMember(Long id) {
        EntityManager em = EntitimanagerUtils.getEntityManagerFactory();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            em.remove(em.find(Member.class, id));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        EntitimanagerUtils.deployEntityManagerFactory();
    }
}
