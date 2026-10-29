package arena;

public class Magician extends GameCharacter {
    private boolean isMirrorActive;


    public Magician() {
        super(150, "Magician");
    }


    @Override
    public boolean useSuperpower() {
        return isMirrorActive = true;

    }

    @Override
    public int calculateDamage() {
        return getRandom().nextInt(11) + 12;
    }
}
