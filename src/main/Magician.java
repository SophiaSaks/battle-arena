package main;

public class Magician extends GameCharacter{


    public Magician (){
        super(150, "Magician");
    }


    public void useSuperpower(){

    }

    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11) + 12 ;
    }
}
