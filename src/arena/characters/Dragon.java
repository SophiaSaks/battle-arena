package arena.characters;

import arena.gamelogic.Superpower;

public class Dragon extends GameCharacter {
    public Dragon() {
        super(80, "Dragon", Superpower.SURPRISE_ATTACK);
    }

    @Override
    public int calculateDamage() {
        return getRandom().nextInt(11) + 9;
    }
}