package br.com.compass.services;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.Account;

import javax.persistence.EntityManager;

public class AccountConnection {

    public Account save(Account account) {

        EntityManager em = new ConnectionFactory().getConnection();


        try{
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return account;
    }

}