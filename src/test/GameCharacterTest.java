package test;

import arena.characters.GameCharacter;
import arena.characters.Knight;
import arena.characters.Magician;
import arena.gamelogic.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameCharacterTest {

    private Player player;
    private Player computer;
    private GameCharacter playerCharacter;
    private GameCharacter computerCharacter;

    @BeforeEach
    void setUp() {

        player = new Player("Player1", new Magician());
        computer = new Player("Computer", new Knight());
        playerCharacter = player.getCharacter();
        computerCharacter = computer.getCharacter();
    }

    @Test
    @DisplayName("Healing never exceeds max HP")
    void healNeverGoesOverHp() {

        playerCharacter.receiveHealth(156);

        int healedHp = playerCharacter.getHp();

        assertEquals(150, healedHp, "Player HP should be 150 and no more");
    }

    @Test
    @DisplayName("Player and computer HP never go below zero")
    void hpNeverGoesBelowZero() {

        int damageToPlayer = playerCharacter.getHp() + 1;
        int damageToComputer = computerCharacter.getHp() + 1;

        playerCharacter.receiveDamage(damageToPlayer);
        computerCharacter.receiveDamage(damageToComputer);

        assertEquals(0, playerCharacter.getHp(), "Player HP should stop at zero");
        assertEquals(0, computerCharacter.getHp(), "Computer HP should stop at zero");
    }

    @Test
    @DisplayName("Can see if a player is dead")
    void isPlayerDead() {

        playerCharacter.receiveDamage(playerCharacter.getHp() + 1);

        boolean playerIsAlive = playerCharacter.isAlive();

        assertFalse(playerIsAlive, "Player is not alive.");
        assertTrue(computerCharacter.isAlive(), "Computer is alive.");
    }

    @Test
    @DisplayName("Can see if computer is dead")
    void isComputerDead() {

        computerCharacter.receiveDamage(computerCharacter.getHp() + 1);

        boolean computerIsAlive = computerCharacter.isAlive();

        assertFalse(computerIsAlive, "Computer is not alive.");
        assertTrue(playerCharacter.isAlive(), "Player is alive.");
    }
}
