package arena;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    private GameCharacter character;

    static void menu() {

        int choice;
        boolean isRunning = true;
        Player player = new Player();
        Computer computer = new Computer();
        Game game = new Game();

        while (isRunning) {

            System.out.println("What's your name?");

            String name = scanner.nextLine();
            player.setName(name);
            System.out.println(player.getName() + ", Pick a character: ");
            System.out.println("1. Magician" + "\n" + "2. Knight" + "\n" + "3. Dragon" + "\n" + "4. Ranger");

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> player.setCharacter(new Magician());
                    case 2 -> player.setCharacter(new Knight());
                    case 3 -> player.setCharacter(new Dragon());
                    case 4 -> player.setCharacter(new Ranger());
                    default -> System.out.println("No character picked!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Pick a number between 1-4");
            }
            System.out.println("You picked: " + player.getCharacter().getName() + " with " + player.getCharacter().getHp() + " HP");

            computer.setName("Computer");
            int characterChoice = (int) (Math.random() * 4) + 1;

            switch (characterChoice) {
                case 1 -> computer.setCharacter(new Magician());
                case 2 -> computer.setCharacter(new Knight());
                case 3 -> computer.setCharacter(new Dragon());
                case 4 -> computer.setCharacter(new Ranger());
            }
            System.out.println("Computer picked: " + computer.getCharacter().getName() + " with " + computer.getCharacter().getHp() + " HP");

            while (player.getCharacter().getHp() > 0 && computer.getCharacter().getHp() > 0) {


                // Player attacks computer
                game.attack(player.getCharacter(), computer.getCharacter());
                System.out.println(player.getName() + " attacked " + computer.getName());
                System.out.println(computer.getName() + " has " + computer.getCharacter().getHp() + " HP left.");

                if(computer.getCharacter().getHp() <= 0) {
                    break;
                }

                // Computer attacks player
                game.attack(computer.getCharacter(), player.getCharacter());
                System.out.println(computer.getName() + " attacked " + player.getName());
                System.out.println(player.getName() + " has " + player.getCharacter().getHp() + " HP left.");
            }
            if(player.getCharacter().getHp() == 0){
                System.out.println("You lost!");

            }else{
                System.out.println("Computer lost!");
            }
        }
    }
}