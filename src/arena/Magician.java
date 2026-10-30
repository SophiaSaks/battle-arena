package arena;

public class Magician extends GameCharacter {
    private boolean isMirrorActive;


    public Magician() {
        super(150, "Magician", Superpower.HEAL);
    }


    @Override
    public int calculateDamage() {
        return getRandom().nextInt(11) + 12;
    }

    //Vi kan välja vilken Action vi vill ha här
    /*
    @Override
    public Superpower getSpecialAction() {
        return Superpower.SUPERPOWER;
    }
    */
}
