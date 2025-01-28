package br.com.compass.views;

import br.com.compass.entities.models.Account;

import java.util.Scanner;

import static br.com.compass.services.AccountServices.deleteAccount;
import static br.com.compass.views.BankMenu.bankMenu;
import static br.com.compass.views.MainMenu.mainMenu;

public class AccountDeleteMenu {

    public static void accountDeleteMenu(Account acc, String cpf){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n======= Delete account =======");
        System.out.println("|| 1. Delete account          ||");
        System.out.println("|| 0. Go back                 ||");
        System.out.println("================================");
        System.out.print("Choose an option: ");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch(option){
            case 1:
                deleteAccount(acc);
                mainMenu();
                break;
            case 0:
                bankMenu(acc, cpf);
                break;
        }

    }
}
