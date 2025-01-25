package br.com.compass;

import java.util.Scanner;

import static br.com.compass.views.MainMenu.mainMenu;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        mainMenu();

        scanner.close();
        System.out.println("Application closed");
    }
}