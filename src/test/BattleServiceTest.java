package test;

import arena.*;
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

        // Arrange
        int computerHpBefore = knightCharacter.getHp();
        int playerHpBefore = magicianCharacter.getHp();

        // Act
        battleService.attack(magicianCharacter, knightCharacter);

        // Assert
        int ComputerHpAfter = knightCharacter.getHp();
        int playerHpAfter = magicianCharacter.getHp();

        assertTrue(ComputerHpAfter < computerHpBefore);
        assertEquals(playerHpAfter, playerHpBefore);
    }


    @Test
    @DisplayName("Healing super powers works")
    void healingSuperPowersWorks() {
        // Arrange
        //Playercharacter is magician with 150 HP to start with
        magicianCharacter.receiveDamage(50);
        int hpBeforeHeal = magicianCharacter.getHp();

        // Act
        battleService.useSpecialAction(magicianCharacter, knightCharacter);

        // Assert2
        int hpAfterHeal = magicianCharacter.getHp();

        assertEquals(120, hpAfterHeal, "HP should be restored by 20%");

        assertTrue(hpAfterHeal == hpBeforeHeal + hpBeforeHeal * 20 / 100,
                "HP should be restored by 20%");

    }

    @Test
    @DisplayName("Surprise attack works")
    void surpriseAttackWorks() {
        int playerHpBefore = magicianCharacter.getHp();
        //Act
        battleService.useSpecialAction(dragonCharacter, magicianCharacter);

        //Assert
        int playerHpAfter = magicianCharacter.getHp();
        int damage = playerHpBefore - playerHpAfter;

        assertTrue(damage >= 0 && damage <= 24, "Surprise attack damage should be between 0 and 24");

    }

    @Test
    @DisplayName("Dodge works")
    void dodgeWorks() {

        //Hp before
        int rangerHpBefore = rangerCharacter.getHp();


        //useSpecialAction
        battleService.useSpecialAction(rangerCharacter, magicianCharacter);

        //isDodgeActive?
        assertTrue(rangerCharacter.isDodgeActive());

        battleService.attack(magicianCharacter, rangerCharacter);

        //hp after
        int hpFirstAttack = rangerCharacter.getHp();

        //isDodgeActive == false?
        assertFalse(rangerCharacter.isDodgeActive());

        battleService.attack(magicianCharacter, rangerCharacter);

        int hpSecondAttack = rangerCharacter.getHp();

        //attackera


        assertEquals(rangerHpBefore, hpFirstAttack);
        assertTrue(hpFirstAttack > hpSecondAttack);
        //hp before == hp after
        //Ranger
    }

    @Test
    @DisplayName("Defend works")
    void defendWorks() {
        int initialDamage = magicianCharacter.calculateDamage();
        int hpBefore = knightCharacter.getHp();

        battleService.useSpecialAction(knightCharacter, magicianCharacter);

        battleService.attack(magicianCharacter, knightCharacter);

        int hpAfter = knightCharacter.getHp();
        int damageTaken = hpBefore - hpAfter;

        assertEquals(damageTaken, initialDamage / 2);

        //Knight
    }


    @Test
    void computerShouldWinWhenPlayerHasZeroHp() {
        // Arrange
        magicianCharacter.receiveDamage(magicianCharacter.getHp());

        // Act
        Player winner = battleService.getWinner(playerMagician, playerKnight);

        // Assert
        assertSame(playerKnight, winner);
    }

    @Test
    void playerShouldWinWhenComputerHasZeroHp() {
        // Arrange
        knightCharacter.receiveDamage(knightCharacter.getHp());

        // Act
        Player winner = battleService.getWinner(playerMagician, playerKnight);

        // Assert
        assertSame(playerMagician, winner);
    }


}
