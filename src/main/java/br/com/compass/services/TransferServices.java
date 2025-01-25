package br.com.compass.services;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.Account;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class TransferServices {

    public static void transfer(String fromTransferCpf, String toTransferCpf, double totalBalance) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();

            String query = "SELECT a FROM Account a WHERE a.cpf = :cpf";

            Account fromTransfer = em.createQuery(query, Account.class).setParameter("cpf", fromTransferCpf).getSingleResult();

            Account toTransfer = em.createQuery(query, Account.class).setParameter("cpf", toTransferCpf).getSingleResult();

            if(fromTransfer.getTotalBalance() < totalBalance) {
                System.out.println("\nInsufficient funds");
                return;
            }

            fromTransfer.setTotalBalance(fromTransfer.getTotalBalance() - totalBalance);
            toTransfer.setTotalBalance(toTransfer.getTotalBalance() + totalBalance);

            em.merge(fromTransfer);
            em.merge(toTransfer);
            em.getTransaction().commit();

            System.out.println("\nTransfer successful to: " + toTransfer.getName());

        } catch (NoResultException e) {
            System.out.println("\nThe CPF does not exist!");
        }catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("\nError during transfer" + e.getMessage());
        }finally {
            em.close();
        }
    }
}
