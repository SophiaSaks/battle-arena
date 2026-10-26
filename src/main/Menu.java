package main;

import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);


    static void menu () {

        boolean isRunning = true;

        while (isRunning) {

            System.out.println("What´s your name?");
            String name = scanner.nextLine();
            System.out.println("Pick a character: " + name);
        }
    }
}
