package main;

import java.util.Random;

public abstract class GameCharacter implements Superpower, CalculateDamage {
    private final Random random = new Random();
    private int hp;
    private final String name;

    public GameCharacter( int hp, String name) {
        this.hp = hp;
        this.name = name;
    }

    public void receiveDamage(int damage){
        if (damage < 0){
            throw new IllegalArgumentException("Damage can not be negative.");
        }
        int actualDamage = adjustDamage(damage);
        hp = Math.max(0, hp - actualDamage);
    }

    public boolean isAlive(){
        return hp>0;
    }

    protected int adjustDamage (int damage){
        return damage;
    }

    public String getName(){
        return name;
    }

    public Random getRandom(){
        return random;
    }


    public int getHp(){
        return hp;
    }

    @Override
    public abstract int calculateDamage();
}
