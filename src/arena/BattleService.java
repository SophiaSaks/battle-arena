package arena;

import java.util.Random;
import java.util.Scanner;

public class BattleService {
    private static Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public void attack(GameCharacter attacker, GameCharacter defender) {
        int damage = attacker.calculateDamage();
        if(defender.isDefendActive()){
            damage /= 2;
            defender.setDefendActive(false);
        }
        if (defender.isDodgeActive()){
            damage = 0;
            defender.setDodgeActive(false);
        }
        defender.receiveDamage(damage);
    }


    public void useSpecialAction(GameCharacter attacker, GameCharacter defender) {
        Superpower superpower = attacker.getSuperPower();
        switch (superpower) {
            case SUPRISE_ATTACK -> {
                int damage = random.nextInt(25);
                if(defender.isDefendActive()){
                    damage /= 2;
                    defender.setDefendActive(false);
                }
                if (defender.isDodgeActive()){
                    damage = 0;
                    defender.setDodgeActive(false);
                }
                defender.receiveDamage(damage);
            }
            case HEAL -> {
                int heal = attacker.getHp() * 20 / 100;
                attacker.receiveHealth(heal);
            }
            case DEFEND -> attacker.setDefendActive(true);
            case DODGE -> attacker.setDodgeActive(true);
        }
    }

    public void showHp(Player player, Player computer) {
        System.out.println(player.getName() + " has " + player.getCharacter().getHp() + " HP left.");
        System.out.println(computer.getName() + " has " + computer.getCharacter().getHp() + " HP left.");
    }


    public void showWinner(Player player, Player computer) {
        if (player.getCharacter().getHp() <= 0) {
            System.out.println("Winner: " + computer.getName());
        } else {
            System.out.println("Winner: " + player.getName());
        }
    }

    public static void setUpPlayer(int choice, Player player){
        player.selectCharacter(choice, player);
        System.out.println(player.getName() + " picked: " + player.getCharacter().getName());
    }


    public static void playMatch(Player player, Player computer, BattleService battleService) {
        int turn = random.nextInt(2) + 1;

        while (player.getCharacter().isAlive() && computer.getCharacter().isAlive()) {
            if(turn == 1){
                System.out.println("\n" + player.getName() + "'s turn:");
                System.out.println("\nChoose an action:");
                System.out.println("1. Attack\n2. Use special action");
                int action = readChoice(1, 2);

                executeAction(action, player, computer, battleService);

            } else{
                System.out.println("\nComputer's turn:\n");
                int action = random.nextInt(2) + 1;

                if (action == 2) {
                    System.out.println(computer.getName() + " used a special action!");
                }
                executeAction(action, computer, player, battleService);
            }

            battleService.showHp(player, computer);

            if (!computer.getCharacter().isAlive() || !player.getCharacter().isAlive()) {
                break;
            }

            turn = (turn == 1) ? 2 : 1;
        }
        battleService.showWinner(player, computer);
    }

    public static int readChoice(int min, int max) {
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


    public static void executeAction(int action, Player attacker, Player defender, BattleService battleService) {
        if (action == 1) {
            battleService.attack(attacker.getCharacter(), defender.getCharacter());
            System.out.println(attacker.getName() + " attacked " + defender.getName());
        } else {
            battleService.useSpecialAction(attacker.getCharacter(), defender.getCharacter());
        }
    }
}