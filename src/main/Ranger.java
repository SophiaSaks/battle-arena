package main;

public class Ranger extends GameCharacter{
    private String name;

    public Ranger (){
        super(100, true, "Ranger");

    }

    public void useSuperpower(){

    }

    public int calculateDamage(){
        return getRandom().nextInt(11) + 5;
    }
}
