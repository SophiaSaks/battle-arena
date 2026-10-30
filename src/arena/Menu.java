package arena;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    static void menu() {
        int choice;
        boolean isRunning = true;

        Player player = new Player();
        Player computer = new Player();
        Game game = new Game();

        while (isRunning) {
            System.out.println("What's your name?");

            String name = scanner.nextLine();
            player.setName(name);

            System.out.println(player.getName() + ", Pick a character:");
            System.out.println(
                    "1. Magician\n2. Knight\n3. Dragon\n4. Ranger"
            );

            try {
                choice = scanner.nextInt();
                player.selectCharacter(choice, player);

            } catch (InputMismatchException e) {
                System.out.println("Pick a number between 1-4");
            }

            System.out.println("You picked: " + player.getCharacter().getName() + " with " + player.getCharacter().getHp() + " HP");

            computer.setName("Computer");

            int computerChoice = (int) (Math.random() * 4) + 1;
            computer.selectCharacter(computerChoice, computer);

            System.out.println("Computer picked: " + computer.getCharacter().getName() + " with " + computer.getCharacter().getHp() + " HP");
            while (player.getCharacter().getHp() > 0 && computer.getCharacter().getHp() > 0) {

                System.out.println("\n" + player.getName() + "'s turn:");
                int action = 0;
                while (action < 1 || action > 2) {
                    System.out.println("\nChoose an action:");
                    System.out.println("1. Attack\n2. Use Special action");
                    try {
                        action = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Enter a number.");
                    }
                    scanner.nextLine();
                }

                if (action == 1) {
                    game.attack(player.getCharacter(), computer.getCharacter());
                    System.out.println(player.getName() + " attacked " + computer.getName());
                } else {
                    game.useSpecialAction(player.getCharacter(), computer.getCharacter());
                }

                game.showHp(player, computer);
                if (computer.getCharacter().getHp() <= 0) {
                    break;
                }

                System.out.println("\nComputer's turn:");
                int computerAction = (int) (Math.random() * 2) + 1;

                if (computerAction == 1) {
                    game.attack(computer.getCharacter(), player.getCharacter());
                    System.out.println(computer.getName() + " attacked " + player.getName());
                } else {
                    game.useSpecialAction(computer.getCharacter(), player.getCharacter());
                    System.out.println(computer.getName() + "used special attack!");
                }

                game.showHp(player, computer);
            }

            game.showWinner(player, computer);
        }
    }
}