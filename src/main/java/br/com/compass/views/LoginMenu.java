package br.com.compass.views;

import br.com.compass.entities.models.Account;
import br.com.compass.services.ValidateLogin;
import java.util.Scanner;
import static br.com.compass.views.BankMenu.bankMenu;
import static br.com.compass.views.MainMenu.mainMenu;

public class LoginMenu {

    public static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running) {
            System.out.println("\n========= Login Menu =========");
            System.out.println("Type your CPF: ");
            String cpf = scanner.nextLine();

            System.out.println("Type your password: ");
            String password = scanner.nextLine();

            Account acc =  ValidateLogin.validateLogin(cpf, password);

            if (acc != null) {
                System.out.println("Logging in...");
                running = false;
                bankMenu(acc, cpf);

            }else {
                System.out.println("\nInvalid CPF or password.");
                running = false;
                mainMenu();
            }
        }
        scanner.close();
    }
}
