package arena;

// Contains methods that Menu calls to help run the game.
public class Game {

    public void attack(GameCharacter attacker, GameCharacter defender) {
        int damage = attacker.calculateDamage();
        defender.receiveDamage(damage);
    }

    /**
     * Performs the attacker's special action.
     * @param attacker the character performing the action
     * @param defender the opponent receiving any damage
     * @return true if the action succeeded, otherwise false
     */
    public boolean addSpecialActionToChar(GameCharacter attacker, GameCharacter defender) {
        Action action = attacker.getSpecialAction();

        // action anger vilken specialhandling karaktären har.
        switch (action) {
            case DEFEND -> {
                //TODO: Aktivera karaktärens skydd, exempelvis Knights sköld.
                // Skyddet ska minska skadan från nästa träff.
                System.out.println("DEFEND");

            }
            case HEAL -> {
                //TODO: Återställ HP hos karaktären som utför handlingen.
                // HP ska inte kunna överstiga karaktärens max HP.
                System.out.println("HEAL");
            }
            case SUPERPOWER -> {
                // Eldattacken skadar motståndaren direkt.
                // receiveDamage() hanterar eventuellt skydd och gränsen 0 HP.
                defender.receiveDamage(30);
                System.out.println("Fire breath hits the defender!");
                return true;
            }
            case ATTACK -> {
                //TODO: Regular attacks are handled by attack(). ska vi ta bort det? eller ska vi ha special Attack?
            }
        }

        return false;
    }

    // Displays both characters' remaining HP.
    public void showHp(Player player, Computer computer) {
        System.out.println( player.getName() + " has " + player.getCharacter().getHp() + " HP left.");
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