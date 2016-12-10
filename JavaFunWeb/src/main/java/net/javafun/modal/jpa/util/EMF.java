package net.javafun.modal.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author ozkansari
 *
 */
public final class EMF {
    private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("javafun-jpa");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
    
    public static EntityManager getEntityManager() {
        return EMF.get().createEntityManager();
    }
}
