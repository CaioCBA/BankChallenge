package br.com.compass.services;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.models.Account;
import br.com.compass.entities.models.Transactions;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class AccountServices {

    public void saveAccount(Account acc) {

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

    public static void deleteAccount(Account acc) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();

            Account removeAccount = em.find(Account.class, acc.getId());

            if (removeAccount == null) {
                throw new IllegalArgumentException("No account found with this Id.");
            }

            String transactionsQuery = "SELECT t FROM Transactions t WHERE t.account.id = :accountId";
            List<Transactions> removeTransactionsList = em.createQuery(transactionsQuery, Transactions.class).setParameter("accountId", acc.getId()).getResultList();

            for (Transactions removeTransactions : removeTransactionsList) {
                removeTransactions.setAccount(null);
                em.merge(removeTransactions);
            }

            em.remove(removeAccount);

            em.getTransaction().commit();

            System.out.println("Account deleted successfully.");
        } catch (Exception e) {
            em.getTransaction().rollback();
//            System.err.println("Error deleting account: " + e.getMessage());
            e.printStackTrace();
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

    public static boolean fieldAlreadyInDatabase(EntityManager em, String field, String value) {
        String query = String.format("SELECT COUNT(a) FROM Account a WHERE a.%s = :value", field);
        Long exists = em.createQuery(query, Long.class).setParameter("value", value).getSingleResult();
        return exists > 0;
    }
}


