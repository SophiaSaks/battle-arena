package test;

import arena.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BattleServiceTest {

    private BattleService battleService;
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
        battleService = new BattleService();
        playerCharacter = player.getCharacter();
        computerCharacter = computer.getCharacter();
        dragonCharacter = playerDragon.getCharacter();
        rangerCharacter = playerRanger.getCharacter();
    }


    @Test
    @DisplayName("Player attacked the computer and lowered its HP.")
    void playerAttackLowersComputerHp() {

        // Arrange
        int computerHpBefore = computerCharacter.getHp();
        int playerHpBefore = playerCharacter.getHp();

        // Act
        battleService.attack(playerCharacter, computerCharacter);

        // Asserrt
        int ComputerHpAfter = computerCharacter.getHp();
        int playerHpAfter = playerCharacter.getHp();

        assertTrue(ComputerHpAfter < computerHpBefore);
        assertEquals(playerHpAfter, playerHpBefore);
    }



    @Test
    @DisplayName("Healing super powers works")
    void healingSuperPowersWorks() {
        // Arrange
        //Playercharacter is magician with 150 HP to start with
        playerCharacter.receiveDamage(50);
        int hpBeforeHeal = playerCharacter.getHp();

        // Act
        battleService.useSpecialAction(playerCharacter, computerCharacter);

        // Assert2
        int hpAfterHeal = playerCharacter.getHp();

        assertEquals(120, hpAfterHeal, "HP should be restored by 20%");

        assertTrue(hpAfterHeal  == hpBeforeHeal + hpBeforeHeal * 20/100,
                "HP should be restored by 20%");

    }

    @Test
    @DisplayName("Surprise attack works")
    void surpriseAttackWorks(){
        //Dragon
    }

    @Test
    @DisplayName("Dodge works")
    void dodgeWorks(){
        //Ranger
    }

    @Test
    @DisplayName("Defend works")
    void defendWorks(){
        //Knight
    }


    @Test
    void computerShouldWinWhenPlayerHasZeroHp() {
        // Arrange
        playerCharacter.receiveDamage(playerCharacter.getHp());

        // Act
        Player winner = battleService.getWinner(player, computer);

        // Assert
        assertSame(computer, winner);
    }

    @Test
    void playerShouldWinWhenComputerHasZeroHp() {
        // Arrange
        computerCharacter.receiveDamage(computerCharacter.getHp());

        // Act
        Player winner = battleService.getWinner(player, computer);

        // Assert
        assertSame(player, winner);
    }



}
