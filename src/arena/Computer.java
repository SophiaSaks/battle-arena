package arena;

public class Computer {
    private String name;
    private GameCharacter character;

    public Computer(String name, GameCharacter character) {
        this.name = name;
        this.character = character;
    }

    public Computer() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
    }

    public GameCharacter getCharacter() {
        return character;
    }
}
