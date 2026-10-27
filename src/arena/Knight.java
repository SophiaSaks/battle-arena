package arena;

public class Knight extends GameCharacter{
    private boolean shieldActive;

    public Knight (){
        super(120, "Knight");
    }

    @Override
    public void useSuperpower(){
        shieldActive = true;
    }

    @Override
    public int calculateDamage(){
        return getRandom().nextInt(11) + 8;
    }

    @Override
    protected int adjustDamage(int damage) {
        if (shieldActive) {
            damage = damage / 2;
            shieldActive = false;
        }

        return damage;
    }

}
