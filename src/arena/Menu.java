package arena;

import java.lang.classfile.attribute.SourceDebugExtensionAttribute;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);


    static void menu () {

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nBATTLE ARENA\n");
            System.out.println("1. Start new game");
            System.out.println("2. View match history");
            System.out.println("3. Exit");
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice){
                case "1" -> {
                Player player = createPlayer();
                    System.out.println("You selected " + player.getCharacter().getName());
                }
                case "2" ->
                    System.out.println("Match history is not available yet");

                case "3" -> {
                    isRunning = false;
                    System.out.println("Goodbye");
                }
                default ->
                    System.out.println("Invalid choice. Enter 1, 2 or 3");
            }
        }

    }
        public static Player createPlayer(){
        Player player = new Player();

        while (true) {


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

            return player;
        }
    }
}
