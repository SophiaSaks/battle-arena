package arena;

import java.util.Scanner;
import java.util.Random;

public class Menu {
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    static void menu() {

        Player player = new Player();
        Player computer = new Player();
        BattleService battleService = new BattleService();

        System.out.println("\nWhat's your name?");
        String name = scanner.nextLine();
        player.setName(name);

        System.out.println("Hi " + player.getName() + "! Pick a character:");
        System.out.println("1. Magician\n2. Knight\n3. Dragon\n4. Ranger");
        int characterChoice = BattleService.readChoice(1, 4);


        BattleService.setUpPlayer(characterChoice, player);

        computer.setName("Computer");
        int computerCharacterChoice = random.nextInt(4) + 1;

        BattleService.setUpPlayer(computerCharacterChoice, computer);

        BattleService.playMatch(player, computer, battleService);
    }
}