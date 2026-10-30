package arena;

import java.util.Random;

public abstract class GameCharacter implements CalculateDamage {
    private final Random random = new Random();
    private int hp;
    private final String name;
    private Superpower superpower;
    private boolean isDefendActive = false;
    private boolean isDodgeActive = false;

    public boolean isDefendActive() {
        return isDefendActive;
    }

    public void setDefendActive(boolean active) {
        this.isDefendActive = active;
    }

    public boolean isDodgeActive() {
        return isDodgeActive;
    }

    public void setDodgeActive(boolean active) {
        this.isDodgeActive = active;
    }

    public GameCharacter(int hp, String name, Superpower superpower) {
        this.hp = hp;
        this.name = name;
        this.superpower = superpower;
    }

    // Damage
    public void receiveDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage can not be negative.");
        }
        int actualDamage = adjustDamage(damage);
        hp = Math.max(0, hp - actualDamage);
    }

    // Heal
    public void receiveHealth(int heal) {
        int actualHealth = adjustHealth(heal);
        hp = Math.min(150, hp + actualHealth);
    }

    public Superpower getSuperPower() {
        return superpower;
    }

    protected int adjustDamage(int damage) {
        return damage;
    }

    protected int adjustHealth(int heal) {
        return heal;
    }

    public String getName() {
        return name;
    }

    public Random getRandom() {
        return random;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public abstract int calculateDamage();

}
