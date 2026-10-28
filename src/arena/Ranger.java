package arena;

public class Ranger extends GameCharacter{


    public Ranger (){
        super(100, "Ranger");

    }

    @Override
    public void useSuperpower(){

    }
    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11) + 5;
    }
}
