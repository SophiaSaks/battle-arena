package arena;

public class Ranger extends GameCharacter{


    public Ranger (){
        super(100, "Ranger");

    }
    //Vi kan välja vilken Action vi vill ha här
    @Override
    public Action getSpecialAction() {
        return Action.HEAL;
    }
    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11) + 5;
    }
}
