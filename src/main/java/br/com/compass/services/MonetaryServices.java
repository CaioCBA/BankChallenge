package br.com.compass.services;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.models.Account;
import br.com.compass.entities.models.Transactions;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class MonetaryServices {

    public static void transfer(String fromAccountCpf, String toAccountCpf, double totalBalance) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();

            String query = "SELECT a FROM Account a WHERE a.cpf = :cpf";

            Account fromAccount = em.createQuery(query, Account.class).setParameter("cpf", fromAccountCpf).getSingleResult();

            Account toAccount = em.createQuery(query, Account.class).setParameter("cpf", toAccountCpf).getSingleResult();

            if(fromAccount.getTotalBalance() < totalBalance) {
                System.out.println("\nYou do not have enough money to transfer!");
                em.getTransaction().rollback();
                return;
            }

            fromAccount.setTotalBalance(fromAccount.getTotalBalance() - totalBalance);
            toAccount.setTotalBalance(toAccount.getTotalBalance() + totalBalance);
            em.merge(fromAccount);
            em.merge(toAccount);

            Transactions senderTransaction = new Transactions(fromAccount, 3, totalBalance, fromAccountCpf);
            Transactions recipientTransaction = new Transactions(fromAccount, 4, totalBalance, toAccountCpf);
            em.persist(senderTransaction);
            em.persist(recipientTransaction);

            em.getTransaction().commit();

            System.out.println("\nTransfer successful to: " + toAccount.getName());

        } catch (NoResultException e) {
            System.out.println("\nThe CPF does not exist!");
        }catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("\nError during transfer" + e.getMessage());
        }finally {
            em.close();
        }
    }

    public static boolean deposit(String cpf, double amount) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();

            String query = "SELECT a FROM Account a WHERE a.cpf = :cpf";
            Account acc = em.createQuery(query, Account.class).setParameter("cpf", cpf).getSingleResult();

            acc.setTotalBalance(acc.getTotalBalance() + amount);

            em.merge(acc);

            Transactions depositTransaction = new Transactions(acc, 1, amount, cpf);
            em.persist(depositTransaction);

            em.getTransaction().commit();

            System.out.println("\nDeposit successful!");
            return true;
        } catch(Exception e) {
            em.getTransaction().rollback();
            System.out.println("\nError during deposit" + e.getMessage());
            return false;
        } finally {
            em.close();
        }

    }

    public static boolean withdraw(String cpf, double amount) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();

            String query = "SELECT a FROM Account a WHERE a.cpf = :cpf";
            Account acc = em.createQuery(query, Account.class).setParameter("cpf", cpf).getSingleResult();

            if(acc.getTotalBalance() < amount) {
                System.out.println("\nYou do not have enough money to withdraw!");
            }
            acc.setTotalBalance(acc.getTotalBalance() - amount);

            em.merge(acc);

            Transactions transaction = new Transactions(acc, 2, amount, cpf);
            em.persist(transaction);

            em.getTransaction().commit();

            System.out.println("\nWithdraw successful!");
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("\nError during withdraw" + e.getMessage());
            return false;
        }finally {
            em.close();
        }
    }
}
