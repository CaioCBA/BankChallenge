package br.com.compass.views;

import br.com.compass.controllers.ConnectionFactory;
import br.com.compass.entities.models.Account;
import br.com.compass.entities.enums.AccountType;
import br.com.compass.services.AccountServices;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static br.com.compass.services.AccountServices.fieldAlreadyInDatabase;
import static br.com.compass.views.MainMenu.mainMenu;

public class AccountCreationMenu {

    public static void accountCreationMenu() {
        Scanner scanner = new Scanner(System.in);
        Account acc = new Account();
        AccountServices conn = new AccountServices();
        EntityManager em = new ConnectionFactory().getConnection();

        boolean running = true;

        while (running) {
            System.out.println("========= Account Creation Menu =========");
            System.out.println("|| 1. Open Account                      ||");
            System.out.println("|| 0. Go back                           ||");
            System.out.println("=========================================");
            System.out.print("Choose an option: ");

            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.println("========= Account Creation Menu =========");
                    System.out.print("Type your name: ");
                    String name = scanner.nextLine();
                    acc.setName(name);

                    System.out.print("\nType your password: ");
                    String password = scanner.nextLine();
                    acc.setPassword(password);

                    System.out.print("\nType your CPF: ");
                    String cpf = scanner.nextLine();
                    if(fieldAlreadyInDatabase(em, "cpf", cpf)){
                        System.out.println("\nCPF is already in use!");
                        continue;
                    }
                    acc.setCpf(cpf);

                    System.out.print("\nType your phone number: ");
                    String phone = scanner.nextLine();
                    if(fieldAlreadyInDatabase(em, "phone_number", phone)){
                        System.out.println("\nPhone number is already in use!");
                        continue;
                    }
                    acc.setPhone_number(phone);

                    System.out.print("\nType your birth date (yyyy-mm-dd): ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = null;

                    while (date == null) {
                        String input = scanner.nextLine();
                        acc.setBirthDate(LocalDate.parse(input, formatter));
                        try {
                            date = LocalDate.parse(input, formatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date type.");
                        }
                    }

                    System.out.println("\nEnter your account types: ");

                    System.out.println("|| 1. Payments Account                   ||");
                    System.out.println("|| 2. Savings Account                    ||");
                    System.out.println("|| 3. Checking Account                   ||");
                    String[] accountInput = scanner.nextLine().split(",");
                    Set<AccountType> accountTypes = new HashSet<>();



                    for(String type : accountInput){
                        switch (type.trim()) {
                            case "1":
                                accountTypes.add(AccountType.PAYMENTS_ACCOUNT);
                                break;
                            case "2":
                                accountTypes.add(AccountType.SAVINGS_ACCOUNT);
                                break;
                            case "3":
                                accountTypes.add(AccountType.CHECKING_ACCOUNT);
                                break;
                            default:
                                System.out.println("Invalid account type.");
                                break;
                        }
                    }

                    acc.setAccountTypes(accountTypes);

                    conn.saveAccount(acc);
                    mainMenu();
                    break;


                case 0:
                    System.out.println("Exiting...");
                    running = false;
                    mainMenu();

                default:
                    System.out.println("Invalid option! Please try again.");

            }
        }
    }

}