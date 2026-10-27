package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);


    static void menu () {

        boolean isRunning = true;
        Player player = new Player();

        while (isRunning) {


            System.out.println("What´s your name?");
            String name = scanner.nextLine().trim();
            if (name.isBlank()){
                System.out.println("please enter your name:");
                continue;
            }
            player.setName(name);
            break;
        }

        while (true) {
            System.out.println("Hej " + player.getName() + "!" + "\n" + "Pick a character: ");
            System.out.println("1. Magician" + "\n" + "2. Knight" + "\n" + "3. Dragon" + "\n" + "4. Ranger");


//            try{
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> player.setCharacter(new Magician());
                case "2" -> player.setCharacter(new Knight());
                case "3" -> player.setCharacter(new Dragon());
                case "4" -> player.setCharacter(new Ranger());
                default -> {
                    System.out.println("No character picked!");
                continue;
            }
        }
//
//            }catch(InputMismatchException e){
//                System.out.println("Pick a number between 1-4");
//            }
            System.out.println("You are selected: " + player.getCharacter().getName());
            return;
        }
    }
}
