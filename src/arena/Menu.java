package arena;

import arena.gamelogic.BattleService;
import arena.gamelogic.Player;
import arena.serverclient.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Random;
import java.util.logging.Logger;

public class Menu {
    private static final Logger logg = Logger.getLogger(Menu.class.getName());
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private static Player player = new Player();
    private static Player computer = new Player();
    private static BattleService battleService = new BattleService();

    static void menu() {
        while (true) {
            System.out.println("\nWelcome! Please make a choice: ");
            System.out.println("\n1. Play a match");
            System.out.println("\n2. See history");

            int answer = BattleService.readChoice(1, 2);
            switch (answer) {
                case 1 -> gameMenu();
                case 2 -> historyMenu();
                default -> {
                    return;
                }
            }
        }
    }

    static void gameMenu() {
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

        Player winner = battleService.getWinner(player, computer);

        String result = player.getName() + " (" + player.getCharacter().getName() + ")"
                + " vs " + computer.getName() + " (" + computer.getCharacter().getName() + ")"
                + " - Winner: " + winner.getName() + " - Rounds: " + battleService.getRounds();
        battleService.showMatchResult(result);

        Client client = new Client();

        if (result != null) {
            client.saveResults(result);
            logg.info(LoggColors.GREEN_INFO.color("Match saved."));
        } else {
            logg.warning(LoggColors.YELLOW_WARNING.color("No result found"));
        }
    }

    static void historyMenu() {
        try {
            String text = Files.readString(Path.of("results.txt"));
            logg.info(LoggColors.GREEN_INFO.color("Match history: "));
            System.out.println(text);

        } catch (IOException e) {
            logg.warning(LoggColors.YELLOW_WARNING.color("Could not read results.txt" + e.getMessage()));
        }
    }
}