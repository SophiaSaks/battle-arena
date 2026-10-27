package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private GameCharacter character;

    static void menu () {
        int choice;
        boolean isRunning = true;
        Player player = new Player();

        while (isRunning) {


            System.out.println("What´s your name?");

            String name = scanner.nextLine();
            player.setName(name);

            System.out.println( player.getName() + ", pick a character: ");
            System.out.println("1. Magician");
            System.out.println("2. Knight");
            System.out.println("3. Dragon");
            System.out.println("4. Ranger");

            try{
                choice = scanner.nextInt();
                switch(choice){
                    case 1 -> { player.setCharacter(new Magician());
                    }
                    case 2 -> player.setCharacter(new Knight());
                    case 3 -> player.setCharacter(new Dragon());
                    case 4 -> player.setCharacter(new Ranger());
                    default -> System.out.println("No character picked!");
                }

            }catch(InputMismatchException e){
                System.out.println("Pick a number between 1-4");
            }



        }
    }
}
