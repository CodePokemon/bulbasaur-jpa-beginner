package com.bbubbush.jpa.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntitimanagerUtils {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bbubbush");;
    /**
     * Name: getEntityManagerFactory
     * Date: 2020/06/20
     * Info:
     *  기본값을 사용하여 EMF를 생성
     */
    public static EntityManager getEntityManagerFactory() {
        return getEntityManagerFactory("bbubbush");
    }

    /**
     * Name: getEntityManagerFactory
     * Date: 2020/06/20
     * Info:
     *  Unit name을 통해 EMF를 생성 후 EM을 반환
     */
    public static EntityManager getEntityManagerFactory(String persistenceUnitName) {
        if (persistenceUnitName != null) {
            emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        }
        return emf.createEntityManager();
    }

    /**
     * Name: deployEntityManagerFactory
     * Date: 2020/06/20
     * Info:
     *  EMF을 종료하여 자원을 반환한다.
     */
    public static void deployEntityManagerFactory(){
        emf.close();
    }
}
