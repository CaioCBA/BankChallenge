package br.com.compass.services;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.Account;

import javax.persistence.EntityManager;

public class AccountServices {

    public void save(Account acc) {

        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.persist(acc);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void update(Account acc) {

        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();
            em.merge(acc);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
