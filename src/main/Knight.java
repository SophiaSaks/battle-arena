package main;

public class Knight extends GameCharacter{

    public Knight (){
        super(120, "Knight");
    }

    @Override
    public void useSuperpower(){


    }

    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11) + 8;
    }


}
