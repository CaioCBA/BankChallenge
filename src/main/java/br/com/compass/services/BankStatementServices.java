package br.com.compass.services;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.models.Transactions;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankStatementServices {

    public static List<Transactions> bankStatement(String cpf) {
        EntityManager em = new ConnectionFactory().getConnection();

        try{
            String query = "SELECT a FROM Transactions a WHERE a.account.cpf = :cpf ORDER BY a.date DESC";
            List<Transactions> transactionsList = em.createQuery(query, Transactions.class).setParameter("cpf", cpf).getResultList();

            System.out.println("\nYour statement: ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss");
            for(Transactions a : transactionsList){
                System.out.printf("\n%s - %s: R$ %.2f", a.getDate().format(formatter), a.getTransactionType(), a.getAmount());
            }
            return transactionsList;
        }finally {
            em.close();
        }
    }

}
