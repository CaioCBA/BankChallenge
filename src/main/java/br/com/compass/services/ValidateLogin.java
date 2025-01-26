package br.com.compass.services;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.models.Account;
import javax.persistence.EntityManager;

public class ValidateLogin {
    public static Account validateLogin(String cpf, String password) {
        EntityManager em = new ConnectionFactory().getConnection();

        try {
            String query = "SELECT a FROM Account a WHERE a.cpf = :cpf AND a.password = :password";
            return em.createQuery(query, Account.class).setParameter("cpf", cpf).setParameter("password", password).getSingleResult();


        }catch (Exception e) {
            System.out.println("Error validating login: " + e.getMessage());
        }finally {
            em.close();
        }
        return null;
    }
}
