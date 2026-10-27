package main;

public class Dragon extends GameCharacter{

    public Dragon (){
        super(80, true, "Dragon");
    }


    public void useSuperpower(){

    }

    public int calculateDamage(){
        return getRandom().nextInt(11) ;

    }
}
