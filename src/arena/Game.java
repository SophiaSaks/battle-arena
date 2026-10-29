package arena;

public class Game {

    public void attack(GameCharacter attacker, GameCharacter defender) {
        int damage = attacker.calculateDamage();
        defender.receiveDamage(damage);
    }
    public boolean useSuperpower(GameCharacter character) {
        boolean success = character.useSuperpower();

        if (success) {
            System.out.println("Superpower activated!");
        } else {
            System.out.println("Superpower could not be activated.");
        }

        return success;
    }
}
