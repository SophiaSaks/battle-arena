package arena;

public class Dragon extends GameCharacter {

    public Dragon() {
        super(80, "Dragon");
    }

    @Override
    public Action getSpecialAction() {
        return Action.SUPERPOWER;
    }

    @Override
    public int calculateDamage() {
        // Normal attack deals 9–19 damage.
        return getRandom().nextInt(11) + 9;
    }
}