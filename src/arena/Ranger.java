package arena;

public class Ranger extends GameCharacter{


    public Ranger (){
        super(100, "Ranger", Superpower.DODGE);

    }

    /*
    //Vi kan välja vilken Action vi vill ha här
    @Override
    public Superpower getSpecialAction() {
        return Superpower.HEAL;
    }
     */

    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11) + 5;
    }
}
