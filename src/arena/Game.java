package arena;

public class Game {

    public void attack(GameCharacter attacker, GameCharacter defender) {
        int damage = attacker.calculateDamage();
        defender.receiveDamage(damage);
    }
}
