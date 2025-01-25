package br.com.compass.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("bank-challenge");

    public EntityManager getConnection() {
        return emf.createEntityManager();
    }
}
