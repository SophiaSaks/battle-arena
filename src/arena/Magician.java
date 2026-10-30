package arena;

public class Magician extends GameCharacter {
    private boolean isMirrorActive;


    public Magician() {
        super(150, "Magician");
    }


    @Override
    public int calculateDamage() {
        return getRandom().nextInt(11) + 12;
    }

    //Vi kan välja vilken Action vi vill ha här
    @Override
    public Action getSpecialAction() {
        return Action.SUPERPOWER;
    }
}
