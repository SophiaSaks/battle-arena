package test;

import arena.characters.*;
import arena.gamelogic.BattleService;
import arena.gamelogic.Player;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BattleServiceTest {

    private BattleService battleService;
    private Player playerMagician;
    private Player playerKnight;
    private GameCharacter magicianCharacter;
    private GameCharacter knightCharacter;
    private GameCharacter dragonCharacter;
    private GameCharacter rangerCharacter;
    private Player playerDragon;
    private Player playerRanger;

    @BeforeEach
    void setUp() {

        playerMagician = new Player("Player1", new Magician());
        playerKnight = new Player("Computer", new Knight());
        playerRanger = new Player("Ranger", new Ranger());
        playerDragon = new Player("Dragon", new Dragon());
        battleService = new BattleService();
        magicianCharacter = playerMagician.getCharacter();
        knightCharacter = playerKnight.getCharacter();
        dragonCharacter = playerDragon.getCharacter();
        rangerCharacter = playerRanger.getCharacter();
    }

    @Test
    @DisplayName("Player attacked the computer and lowered its HP.")
    void playerAttackLowersComputerHp() {

        int computerHpBefore = knightCharacter.getHp();
        int playerHpBefore = magicianCharacter.getHp();

        battleService.attack(magicianCharacter, knightCharacter);

        int ComputerHpAfter = knightCharacter.getHp();
        int playerHpAfter = magicianCharacter.getHp();

        assertTrue(ComputerHpAfter < computerHpBefore, "Computer gets lower HP after attack.");
        assertEquals(playerHpAfter, playerHpBefore, "Player HP should not change after attack.");
    }

    @Test
    @DisplayName("Healing super powers works")
    void healingSuperPowersWorks() {

        magicianCharacter.receiveDamage(50);
        int hpBeforeHeal = magicianCharacter.getHp();

        battleService.useSuperPower(magicianCharacter, knightCharacter);

        int hpAfterHeal = magicianCharacter.getHp();

        assertEquals(120, hpAfterHeal, "HP should be restored by 20%");

        assertTrue(hpAfterHeal == hpBeforeHeal + hpBeforeHeal * 20 / 100,
                "HP should be restored by 20%");
    }

    @Test
    @DisplayName("Surprise attack works")
    void surpriseAttackWorks() {
        int playerHpBefore = magicianCharacter.getHp();

        battleService.useSuperPower(dragonCharacter, magicianCharacter);

        int playerHpAfter = magicianCharacter.getHp();
        int damage = playerHpBefore - playerHpAfter;

        assertTrue(damage >= 0 && damage <= 24, "Surprise attack damage should be between 0 and 24");

    }

    @Test
    @DisplayName("Dodge works")
    void dodgeWorks() {

        int rangerHpBefore = rangerCharacter.getHp();

        battleService.useSuperPower(rangerCharacter, magicianCharacter);

        assertTrue(rangerCharacter.isDodgeActive());

        battleService.attack(magicianCharacter, rangerCharacter);

        int hpFirstAttack = rangerCharacter.getHp();

        assertFalse(rangerCharacter.isDodgeActive());

        battleService.attack(magicianCharacter, rangerCharacter);

        int hpSecondAttack = rangerCharacter.getHp();

        assertEquals(rangerHpBefore, hpFirstAttack, "Dodge should prevent damage from the first attack");
        assertTrue(hpFirstAttack > hpSecondAttack, "Dodge should not prevent damage from the second attack");
    }

    @Test
    @DisplayName("Defend works")
    void defendWorks() {
        int hpBefore = knightCharacter.getHp();

        battleService.useSuperPower(knightCharacter, magicianCharacter);
        battleService.attack(magicianCharacter, knightCharacter);

        int hpAfter = knightCharacter.getHp();
        int damageTaken = hpBefore - hpAfter;

        assertTrue(damageTaken >= 6 && damageTaken <= 11,
                "Defend should halve the damage taken");

        assertFalse(knightCharacter.isDefendActive(),
                "Defend should deactivate after an attack");
    }

    @Test
    void computerShouldWinWhenPlayerHasZeroHp() {

        magicianCharacter.receiveDamage(magicianCharacter.getHp());

        Player winner = battleService.getWinner(playerMagician, playerKnight);

        assertSame(playerKnight, winner, "Computer should be the winner.");
    }

    @Test
    void playerShouldWinWhenComputerHasZeroHp() {

        knightCharacter.receiveDamage(knightCharacter.getHp());

        Player winner = battleService.getWinner(playerMagician, playerKnight);

        assertSame(playerMagician, winner, "Player should be the winner.");
    }
}