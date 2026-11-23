package arena.characters;

import arena.gamelogic.Superpower;

public class Magician extends GameCharacter {
    public Magician() {
        super(150, "Magician", Superpower.HEAL);
    }

    @Override
    public int calculateDamage() {
        return getRandom().nextInt(11) + 12;
    }
}
