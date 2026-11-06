package test;

import arena.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameCharacterTest {

    private BattleService battleService;
    private Player player;
    private Player computer;
    private GameCharacter playerCharacter;
    private GameCharacter computerCharacter;

    @BeforeEach
    void setUp() {

        player = new Player("Player1", new Magician());
        computer = new Player("Computer", new Knight());
        battleService = new BattleService();
        playerCharacter = player.getCharacter();
        computerCharacter = computer.getCharacter();
    }

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

    @Test
    @DisplayName("Can see if a player is dead")
    void isPlayerDead() {
        //Arrange
        playerCharacter.receiveDamage(playerCharacter.getHp() + 1);

        //Act
        boolean playerIsAlive = playerCharacter.isAlive();

        //Assert
        assertFalse(playerIsAlive);
        assertTrue(computerCharacter.isAlive());

    }

    @Test
    @DisplayName("Can see if computer is dead")
    void isComputerDead() {
        //Arrange
        computerCharacter.receiveDamage(computerCharacter.getHp() + 1);

        //Act
        boolean computerIsAlive = computerCharacter.isAlive();

        //Assert
        assertFalse(computerIsAlive);
        assertTrue(playerCharacter.isAlive());

    }
}
