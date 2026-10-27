package main;

import java.util.Random;

public abstract class GameCharacter implements Superpower, CalculateDamage {
    private final Random random = new Random();
    private int hp;
    private boolean isAlive;
    private String name;

    public GameCharacter( int hp, boolean isAlive, String name) {
        this.hp = hp;
        this.isAlive = isAlive;
        this.name = name;
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

    public int calculateDamage(){
        return getRandom().nextInt(11) ;

    }
}
