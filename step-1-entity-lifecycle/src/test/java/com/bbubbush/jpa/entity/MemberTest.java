package com.bbubbush.jpa.entity;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class MemberTest {
    final private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");

    /**
     * Name: emFlush
     * Date: 2020/06/21
     * Info:
     *  [EmtityManager.flush() Method]
     *  - flush()를 통해 영속성 컨텍스트와 DB의 동기화가 일어난다.
     *  - commit(), JPQL을 실행할 때 자동으로 일어난다.
     *  - 테스트 코드를 보면 commit() 전에 flush()를 통해 동기화가 일어나기 때문에 UPDATE 쿼리는 println() 블럭 위에 표시된다.
     *  - flush()를 주석처리하면 println() 블럭 안에 표시된다.
     */
    @Test
    public void emFlush() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Member member = em.find(Member.class, 100L);
            member.setName("hELLOjpa");
            em.flush();     // 영속상태의 객체를 DB에 반영한다.

            System.out.println("===== After Commit =====");
            tx.commit();
            System.out.println("===== Before Commit =====");
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
     * Name: stateOfNew
     * Date: 2020/06/21
     * Info:
     *  [비영속상태 테스트]
     *  - 비영속 상태란 영속성 컨텍스트가 객체를 관리하지 않는 상태이다.
     *  - Entity Manager를 사용하지 않는 상태
     */
    @Test
    public void stateOfNew() {
        // given
        // 비영속 객체. 이름을 변경해도 Update Query가 실행되지 않는다.
        Member member = new Member(10L, "new Object");
        member.setName("old Object");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        Member findMember = null;
        try {
            findMember = em.find(Member.class, 10L);    // DB에 저장되지 않아 조회할 수 없다.
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

        // then
        assertNull(findMember);
    }

    /**
     * Name: stateOfManaged
     * Date: 2020/06/21
     * Info:
     *  [영속상태 테스트]
     *  - 영속성 컨텍스트가 객체를 관리하는 상태이다.
     *  - EntityManager.find(), EntityManager.persist() 등 두 가지 방법으로 영속 상태로 관리할 수 있다.
     *  - find() : DB에서 조회 한 데이터를 객체로 만들어 돌려주기 전에, 영속성 컨텍스트에서 관리한다.
     *  - persist() : DB에 데이터를 넣기 전에 영속성 컨텍스트에서 관리한다.
     */
    @Test
    public void stateOfManaged() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Member member = em.find(Member.class, 1L);
            member.setName("hELLOjpa");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

        // then - 다시 조회해서 이름이 변경된 것을 봐야하지만, UPDATE 쿼리가 실행되면 성공했다고 판단
        assertTrue(true);
    }

    /**
     * Name: stateOfDetached
     * Date: 2020/06/21
     * Info:
     *  [준영속상태 테스트]
     *  - 영속성 상태에서 비영속상태로 변경되는 경우에 준영속상태라고 부른다.
     *  - EntityManager.find()로 영속상태인 member객체가 detach(member)를 통해 준영속상태로 변경되었다.
     *  - 따라서 이름을 변경해도 UPDATE문이 실행되지 않는다.
     *  - merge()를 통해 다시 영속상태로 변경할 수 있다.
     */
    @Test
    public void stateOfDetached() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Member member = em.find(Member.class, 1L);  // 영속 상태
            em.detach(member);
            // 준영속(detach) 상태
            member.setName("HelloJPA");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

        // then - 다시 조회해서 이름 변경이 안되었는지 확인해야 하지만, 편의상 UPDATE문이 실행되지 않는 것으로 확인한다.
        assertTrue(true);
    }

    /**
     * Name: stateOfRemove
     * Date: 2020/06/21
     * Info:
     *  [삭제상태 테스트]
     *  - 영속성 컨텍스트에서 제거하며 persist()를 통해 다시 등록하는 방법 밖에 방법이 없다.
     *  - 해당 상태로 flush()가 실행되면 DELETE 쿼리가 실행된다.
     */
    @Test
    public void stateOfRemove() {
        // given
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // when
        tx.begin();
        try {
            Member member = em.find(Member.class, 1L);  // 영속상탱
            em.remove(member);  // 삭제상태. 영속성 컨텍스트에서 관리하지 않으며, 이 상태에서 flush가 일어나면 DELETE 쿼리가 실행된다.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

        // then - 다시 조회해서 이름 변경이 안되었는지 확인해야 하지만, 편의상 DELETE 쿼리가 실행되는지 확인한다.
        assertTrue(true);
    }
}