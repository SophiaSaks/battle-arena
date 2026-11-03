package arena;

public class Player {
    private String name;
    private GameCharacter character;

    public Player(String name, GameCharacter character) {
        this.name = name;
        this.character = character;
    }

    public Player() {

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

    public void selectCharacter(int choice, Player player){
        switch (choice) {
            case 1 -> player.setCharacter(new Magician());
            case 2 -> player.setCharacter(new Knight());
            case 3 -> player.setCharacter(new Dragon());
            case 4 -> player.setCharacter(new Ranger());
            default -> System.out.println("No character picked!");
        }

    }
}
