package main;

public class Player {
    private String name;
    private GameCharacter character;

    public Player (String name, GameCharacter character ){
        this.name = name;
        this.character = character;
    }

    public Player(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }
}
