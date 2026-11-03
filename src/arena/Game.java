package arena;

import java.util.Random;

public class Game {
    private Random random = new Random();

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
}