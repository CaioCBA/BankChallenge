package br.com.compass.views;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.enums.AccountType;
import br.com.compass.entities.models.Account;
import br.com.compass.services.AccountServices;

import javax.persistence.EntityManager;
import java.util.Scanner;
import java.util.Set;

import static br.com.compass.services.AccountServices.getAccountByCpf;
import static br.com.compass.views.BankMenu.bankMenu;

public class AddAnotherAccountMenu {
    public static void addAnotherAccountMenu(Account acc, String cpf) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = new ConnectionFactory().getConnection();
        AccountServices accountServices = new AccountServices();

        System.out.println("========= Add Another Account Menu =========");

        Account account = getAccountByCpf(cpf);

        if (account == null) {
            System.out.println("Account not found with the provided CPF.");
            bankMenu(acc, cpf);
            return;
        }

        System.out.println("Your current account types:");
        Set<AccountType> currentAccountTypes = account.getAccountTypes();
        for (AccountType type : currentAccountTypes) {
            System.out.println("- " + type);
        }

        System.out.println("\nSelect an account type to add:");
        System.out.println("1. Payments Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Checking Account");
        System.out.println("0. Go back");
        System.out.print("Choose an option: ");
        String accountType = scanner.nextLine();

        AccountType newAccountType;
        switch (accountType.trim()) {
            case "1":
                newAccountType = AccountType.PAYMENTS_ACCOUNT;
                break;
            case "2":
                newAccountType = AccountType.SAVINGS_ACCOUNT;
                break;
            case "3":
                newAccountType = AccountType.CHECKING_ACCOUNT;
                break;
            case "0":
                bankMenu(acc, cpf);
            default:
                System.out.println("Invalid option! Please try again.");
                bankMenu(acc, cpf);
                return;
        }

        if (currentAccountTypes.contains(newAccountType)) {
            System.out.println("You already have a " + newAccountType + " account.");
            bankMenu(acc, cpf);
            return;
        }

        currentAccountTypes.add(newAccountType);
        account.setAccountTypes(currentAccountTypes);

        accountServices.updateAccount(account);
        System.out.println("Account type added successfully!");

        bankMenu(acc, cpf);
    }
}
