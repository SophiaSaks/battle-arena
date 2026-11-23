package arena.characters;

import arena.gamelogic.Superpower;

public class Ranger extends GameCharacter{
    public Ranger (){
        super(100, "Ranger", Superpower.DODGE);
    }

    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11) + 5;
    }
}
