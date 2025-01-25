package br.com.compass.views;

import br.com.compass.entities.Account;
import br.com.compass.services.AccountServices;
import br.com.compass.services.TransferServices;

import java.util.InputMismatchException;
import java.util.Scanner;
import static br.com.compass.views.MainMenu.mainMenu;

public class BankMenu {
    public static void bankMenu(Account acc, String cpf) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        AccountServices conn = new AccountServices();

        System.out.println("\nWelcome, " + acc.getName());
        while (running) {
            System.out.println("\n========= Bank Menu =========");
            System.out.println("|| 1. Deposit              ||");
            System.out.println("|| 2. Withdraw             ||");
            System.out.println("|| 3. Check Balance        ||");
            System.out.println("|| 4. Transfer             ||");
            System.out.println("|| 5. Bank Statement       ||");
            System.out.println("|| 0. Exit                 ||");
            System.out.println("=============================");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("\nHow much do you want to deposit? R$ ");
                    int deposit = scanner.nextInt();
                    scanner.nextLine();

                    acc.setTotalBalance(acc.getTotalBalance() + deposit);
                    conn.update(acc);

                    break;
                case 2:
                    boolean hasEnoughBalance = false;

                    while(!hasEnoughBalance) {
                        System.out.print("\nHow much do you want to withdraw? R$ ");
                        int withdraw = scanner.nextInt();
                        scanner.nextLine();

                        if (acc.getTotalBalance() < withdraw) {
                            System.out.println("\nYou do not have enough money to withdraw!");
                        } else {
                            hasEnoughBalance = true;
                            acc.setTotalBalance(acc.getTotalBalance() - withdraw);
                        }
                    }
                    conn.update(acc);

                    break;
                case 3:
                    Account updateAccount = AccountServices.getAccountByCpf(cpf);
                    assert updateAccount != null : "Account not found";
                    System.out.println("Your balance is: R$ " + updateAccount.getTotalBalance());

                    break;
                case 4:
                    String cpfTransfer = "";
                    boolean validCpf = false;

                    while(!validCpf) {
                        System.out.println("\nType the CPF of who you want to transfer to: ");
                        cpfTransfer = scanner.nextLine();
                        if(cpfTransfer.equals(cpf)) {
                            System.out.println("You can not transfer money to yourself!");
                        }else {
                            validCpf = true;
                        }
                    }

                    double amount = 0;
                    boolean validInput = false;

                    while(!validInput) {
                        System.out.println("\nType the total amount you want to transfer: ");
                        try {
                            amount = scanner.nextDouble();
                            scanner.nextLine();
                            if(amount <= 0) {
                                System.out.println("\nThe transfer amount must be greater than zero!");


                            }else{
                                validInput = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input!");
                            scanner.nextLine();
                        }
                    }
                    TransferServices.transfer(cpf, cpfTransfer, amount);

                    break;
                case 5:
                    // ToDo...
                    System.out.println("Bank Statement.");
                    break;
                case 0:
                    // ToDo...
                    System.out.println("Logging out...");
                    running = false;
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }
}
