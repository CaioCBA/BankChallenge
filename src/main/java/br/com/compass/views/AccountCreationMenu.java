package br.com.compass.views;

import br.com.compass.entities.models.Account;
import br.com.compass.entities.enums.AccountType;
import br.com.compass.services.AccountServices;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static br.com.compass.views.MainMenu.mainMenu;

public class AccountCreationMenu {
    public static void accountCreationMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========= Account Creation Menu =========");
            System.out.println("|| 1. Open Account                      ||");
            System.out.println("|| 0. Go back                           ||");
            System.out.println("=========================================");
            System.out.print("Choose an option: ");

            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {

                case 1:
                    AccountServices conn = new AccountServices();
                    Account acc = new Account();

                    System.out.println("\n========= Account Creation Menu =========");
                    System.out.print("Type your name: ");
                    String name = scanner.nextLine();
                    acc.setName(name);

                    System.out.print("\nType your password: ");
                    String password = scanner.nextLine();
                    acc.setPassword(password);

                    System.out.print("\nType your CPF: ");
                    String cpf = scanner.nextLine();
                    acc.setCpf(cpf);

                    System.out.print("\nType your phone number: ");
                    String phone = scanner.nextLine();
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

                    System.out.println("Enter your account type: ");
                    System.out.println("|| 1. Payments Account                   ||");
                    System.out.println("|| 2. Savings Account                    ||");
                    System.out.println("|| 3. Checking Account                   ||");
                    int op_tc = scanner.nextInt();
                    scanner.nextLine();

                    if (op_tc == 1) {
                        acc.setAccountType(AccountType.PAYMENTS_ACCOUNT);
                    } else if (op_tc == 2) {
                        acc.setAccountType(AccountType.SAVINGS_ACCOUNT);
                    } else if (op_tc == 3) {
                        acc.setAccountType(AccountType.CHECKING_ACCOUNT);
                    }

                    conn.save(acc);
                    mainMenu();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
