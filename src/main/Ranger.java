package main;

public class Ranger extends GameCharacter{


    public Ranger (){
        super(100, "Ranger");

    }

    public void useSuperpower(){

    }
    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11) + 5;
    }
}
