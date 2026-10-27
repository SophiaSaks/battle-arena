package main;

public class Magician extends GameCharacter{


    public Magician (){
        super(150, true, "Magician");
    }


    public void useSuperpower(){

    }

    //fundera om vi kan flytta denna metod till basklass istället
    public int calculateDamage(){
        return getRandom().nextInt(11) + 12 ;
    }
}
