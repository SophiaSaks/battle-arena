package main;

public class Knight extends GameCharacter{

    public Knight (){
        super(120, true, "Knight");
    }

    public void useSuperpower(){

    }

    public int calculateDamage(){
        return getRandom().nextInt(11) + 8;
    }


}
