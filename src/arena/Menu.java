package arena;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    static void menu() {

        Player player = new Player();
        Player computer = new Player();
        Game game = new Game();

        setUpHumanPlayer(player);
        setUpComputerPlayer(computer);
        playMatch(player, computer, game);
    }

    private static void setUpHumanPlayer(Player player) {

        System.out.println("\nWhat's your name?");
        String name = scanner.nextLine();
        player.setName(name);

        System.out.println("Hi " + player.getName() + "! Pick a character:");
        System.out.println("1. Magician\n2. Knight\n3. Dragon\n4. Ranger");
        int characterChoice = readChoice(1, 4);
        player.selectCharacter(characterChoice, player);
        printAssignedCharacter("Your character is ", player);
    }

    private static void setUpComputerPlayer(Player computer) {

        computer.setName("Computer");
        int characterChoice = random.nextInt(4) + 1;
        computer.selectCharacter(characterChoice, computer);
        printAssignedCharacter("Computer's character is ", computer);
    }

    private static void printAssignedCharacter(String message, Player participant) {
        GameCharacter character = participant.getCharacter();
        System.out.println(message + character.getName() + " with " + character.getHp() + " HP");
    }

    private static void playMatch(Player player, Player computer, Game game) {
        //ToDo
        // Tourordning från stat väljs slumpmässigt
        while (player.getCharacter().isAlive() && computer.getCharacter().isAlive()) {
            playHumanTurn(player, computer, game);
            game.showHp(player, computer);

            if (!computer.getCharacter().isAlive()) {
                break;
            }

            playComputerTurn(computer, player, game);
            game.showHp(player, computer);
        }

        game.showWinner(player, computer);
    }

    private static void playHumanTurn(Player player, Player computer, Game game) {
        System.out.println("\n" + player.getName() + "'s turn:");
        System.out.println("\nChoose an action:");
        System.out.println("1. Attack\n2. Use special action");
        int action = readChoice(1, 2);

        executeAction(action, player, computer, game);
    }

    private static void playComputerTurn(Player computer, Player player, Game game) {
        System.out.println("\nComputer's turn:\n");
        int action = random.nextInt(2) + 1;

        if (action == 2) {
            System.out.println(computer.getName() + " used a special action!");
        }
        executeAction(action, computer, player, game);
    }

    private static int readChoice(int min, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);

                if (choice >= min && choice <= max) {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.out.println("Choose a whole number");
            }
            System.out.println("Choose a number between " + min + " and " + max + ".");
        }
    }

    private static void executeAction(int action, Player attacker, Player defender, Game game) {
        if (action == 1) {
            game.attack(attacker.getCharacter(), defender.getCharacter());
            System.out.println(attacker.getName() + " attacked " + defender.getName());
        } else {
            game.useSpecialAction(attacker.getCharacter(), defender.getCharacter());
        }
    }
}