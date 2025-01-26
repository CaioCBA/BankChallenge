package br.com.compass.views;

import java.util.Scanner;
import static br.com.compass.views.AccountCreationMenu.accountCreationMenu;
import static br.com.compass.views.LoginMenu.loginMenu;

public class MainMenu {
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("========= Main Menu =========");
            System.out.println("|| 1. Login                ||");
            System.out.println("|| 2. Account Opening      ||");
            System.out.println("|| 0. Exit                 ||");
            System.out.println("=============================");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    loginMenu();
                    return;
                case 2:
                    accountCreationMenu();
                    return;
                case 0:
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
