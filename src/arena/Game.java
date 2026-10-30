package arena;

import java.util.Random;

import static arena.Superpower.*;

public class Game {

    private Random random = new Random();

    public void attack(GameCharacter attacker, GameCharacter defender) {
        int damage = attacker.calculateDamage();
        defender.receiveDamage(damage);
    }

    public void useSpecialAction(GameCharacter attacker, GameCharacter defender) {
        Superpower superpower = attacker.getSuperPower();
        switch (superpower) {
            case SUPRISE_ATTACK -> {
                int damage = random.nextInt(25);
                defender.receiveDamage(damage);
            }
            case HEAL -> {
                int heal = attacker.getHp() * 20 / 100;
                attacker.receiveHealth(heal);
            }
            case DEFEND -> System.out.println("DEFEND!!!");
            case DODGE -> System.out.println("DODGE!!!");
        }
    }

    // Displays both characters' remaining HP.
    public void showHp(Player player, Computer computer) {
        System.out.println(player.getName() + " has " + player.getCharacter().getHp() + " HP left.");
        System.out.println(computer.getName() + " has " + computer.getCharacter().getHp() + " HP left.");
    }

    // Displays the winner after the match.
    public void showWinner(Player player, Computer computer) {
        if (player.getCharacter().getHp() <= 0) {
            System.out.println("Winner: " + computer.getName());
        } else {
            System.out.println("Winner: " + player.getName());
        }
    }
}