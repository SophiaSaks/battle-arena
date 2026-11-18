package arena.characters;

import arena.gamelogic.Superpower;

public class Knight extends GameCharacter {
    public Knight() {
        super(120, "Knight", Superpower.DEFEND);
    }

    @Override
    public int calculateDamage() {
        return getRandom().nextInt(11) + 8;
    }
}
