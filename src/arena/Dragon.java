package arena;

public class Dragon extends GameCharacter {
    public Dragon() {
        super(80, "Dragon", Superpower.SUPRISE_ATTACK);
    }

    @Override
    public int calculateDamage() {
        // Normal attack deals 9–19 damage.
        return getRandom().nextInt(11) + 9;
    }
}