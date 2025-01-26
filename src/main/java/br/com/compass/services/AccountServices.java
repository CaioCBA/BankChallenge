package br.com.compass.services;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.models.Account;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

    public static Account getAccountByCpf(String cpf) {
        EntityManager em = new ConnectionFactory().getConnection();

        try{
            String query = "SELECT a FROM Account a WHERE a.cpf = :cpf";
            return em.createQuery(query, Account.class).setParameter("cpf", cpf).getSingleResult();
        }catch (NoResultException e) {
            System.out.println("The CPF does not exist!");
            return null;
        }finally {
            em.close();
        }
    }
}


