package arena;

public class Ranger extends GameCharacter{


    public Ranger (){
        super(100, "Ranger");

    }

    @Override
    public boolean useSuperpower(){
        return true;
    }
    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11) + 5;
    }
}
