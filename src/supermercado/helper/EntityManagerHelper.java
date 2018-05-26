/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author KillerWorkstation
 */
public class EntityManagerHelper {
    private static EntityManager entityManager;
    
    public static EntityManager entityManager(){
        if(entityManager == null){
            entityManager = Persistence.createEntityManagerFactory("SupermercadoPU").createEntityManager();
        }
        return entityManager;
    }
    
    public static EntityManagerFactory entityManagerFactory(){
           return Persistence.createEntityManagerFactory("SupermercadoPU");

    }
}
