package arena;

public class Dragon extends GameCharacter{

    private boolean fireCharged;
    public Dragon (){
        super(80, "Dragon");
    }


    @Override
    public boolean useSuperpower(){
        if (fireCharged) {
            return false;
        }
        fireCharged = true;
        return true;
    }

    @Override
    public int calculateDamage(){
        int damage = getRandom().nextInt(11)+9 ;
        if (fireCharged) {
            damage += 10;
            fireCharged = false;
        }
        return damage;
    }
}
