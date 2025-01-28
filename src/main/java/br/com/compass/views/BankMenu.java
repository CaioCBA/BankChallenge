package br.com.compass.views;

import br.com.compass.entities.models.Account;
import br.com.compass.services.AccountServices;
import br.com.compass.services.MonetaryServices;

import java.util.Scanner;

import static br.com.compass.services.BankStatementServices.bankStatement;
import static br.com.compass.views.AccountDeleteMenu.accountDeleteMenu;
import static br.com.compass.views.MainMenu.mainMenu;

public class BankMenu {
    public static void bankMenu(Account acc, String cpf) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("\nWelcome, " + acc.getName());
        while (running) {
            System.out.println("\n========= Bank Menu =========");
            System.out.println("|| 1. Deposit              ||");
            System.out.println("|| 2. Withdraw             ||");
            System.out.println("|| 3. Check Balance        ||");
            System.out.println("|| 4. Transfer             ||");
            System.out.println("|| 5. Bank Statement       ||");
            System.out.println("|| 6. Delete account       ||");
            System.out.println("|| 0. Exit                 ||");
            System.out.println("=============================");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("\nHow much do you want to deposit? R$ ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();

                    MonetaryServices.deposit(cpf, depositAmount);
                    break;
                case 2:
                    Account updateAccountWithdraw = AccountServices.getAccountByCpf(cpf);
                    assert updateAccountWithdraw != null;
                    boolean hasEnoughBalance = false;

                    while(!hasEnoughBalance) {
                        System.out.print("\nHow much do you want to withdraw? R$ ");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();

                        if (updateAccountWithdraw.getTotalBalance() < withdrawAmount) {
                            System.out.println("\nYou do not have enough money to withdraw!");
                            hasEnoughBalance = true;
                        } else if (withdrawAmount <= 0) {
                            System.out.println("\nThe withdraw amount must be greater than zero!");

                            hasEnoughBalance = true;
                        } else {
                            boolean operationSuccess = MonetaryServices.withdraw(cpf, withdrawAmount);

                            if (operationSuccess) {
                                hasEnoughBalance = true;
                            }
                        }
                    }
                    break;
                case 3:
                    Account updateAccountCheckBalance = AccountServices.getAccountByCpf(cpf);
                    assert updateAccountCheckBalance != null : "Account not found";
                    System.out.printf("Your balance is: R$ %.2f", updateAccountCheckBalance.getTotalBalance());

                    break;
                case 4:
                    Account updateAccountTransfer = AccountServices.getAccountByCpf(cpf);
                    assert updateAccountTransfer != null : "Account not found";
                    boolean validInput = false;

                    while(!validInput) {

                        System.out.println("\nType the CPF of who you want to transfer to: ");
                        String recipientCpf = scanner.nextLine();

                        System.out.print("\nType the amount you want to transfer: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        if (amount > updateAccountTransfer.getTotalBalance()) {
                            System.out.println("You do not have enough money to transfer!");
                            validInput = true;
                        }else if(amount <= 0){
                            System.out.println("\nThe transfer amount must be greater than zero!");
                            validInput = true;
                        }else {
                            boolean success = MonetaryServices.transfer(cpf, recipientCpf, amount);

                            if(success) {
                                validInput = true;
                            }
                        }
                    }
                    break;
                case 5:
                    bankStatement(cpf);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    running = false;
                    mainMenu();
                    break;
                case 6:
                    accountDeleteMenu(acc, cpf);
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }
}
