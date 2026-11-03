package test;

import arena.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;
    private Player player;
    private Player computer;
    private GameCharacter playerCharacter;
    private GameCharacter computerCharacter;
    private GameCharacter dragonCharacter;
    private GameCharacter rangerCharacter;
    private Player playerDragon;
    private Player playerRanger;

    @BeforeEach
    void setUp() {

        player = new Player("Player1", new Magician());
        computer = new Player("Computer", new Knight());
        playerRanger = new Player("Ranger", new Ranger());
        playerDragon = new Player("Dragon", new Dragon());
        game = new Game();
        playerCharacter = player.getCharacter();
        computerCharacter = computer.getCharacter();
        dragonCharacter = playerDragon.getCharacter();
        rangerCharacter = playerRanger.getCharacter();
    }


    // Test that when the player attacks the computer, the computer's HP decreases
    @Test
    @DisplayName("Player attacked the computer and lowered its HP.")
    void playerAttackLowersComputerHp() {

        // Arrange
        int computerHpBefore = computerCharacter.getHp();
        int playerHpBefore = playerCharacter.getHp();

        // Act
        game.attack(playerCharacter, computerCharacter);

        // Asserrt
        int ComputerHpAfter = computerCharacter.getHp();
        int playerHpAfter = playerCharacter.getHp();

        assertTrue(ComputerHpAfter < computerHpBefore);
        assertEquals(playerHpAfter, playerHpBefore);
    }

    // Test that HP never goes below zero
    @Test
    @DisplayName("Player and computer HP never go below zero")
    void hpNeverGoesBelowZero() {
        // Arrange
        int damageToPlayer = playerCharacter.getHp() + 1;
        int damageToComputer = computerCharacter.getHp() + 1;

        // Act
        playerCharacter.receiveDamage(damageToPlayer);
        computerCharacter.receiveDamage(damageToComputer);

        // Assert
        assertEquals(0, playerCharacter.getHp(), "Player HP should stop at zero");
        assertEquals(0, computerCharacter.getHp(), "Computer HP should stop at zero");
    }

    // Test that healing does not exceed maximum HP
    @Test
    @DisplayName("Healing never exceeds max HP")
    void healNeverGoesOverHp() {
        //Arrange
        playerCharacter.receiveHealth(156);

        //Act
        int healedHp = playerCharacter.getHp();

        //Assert
        assertEquals(150, healedHp, "Player HP should be 150 and no more");
    }

    // att specialförmågor fungerar
    @Test
    @DisplayName("Super powers works")
    void superPowersWorks() {
        // Arrange
        dragonCharacter.receiveDamage(50);
        int hpBeforeHeal = dragonCharacter.getHp();

        // Act

        // Assert2
        int hpAfterHeal = playerCharacter.getHp();

        assertTrue(hpAfterHeal > hpBeforeHeal,
                "Heal should restore HP");

    }

}
