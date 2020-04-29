import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerCharacterTest {
    @Test
    public void PlayerCreationTest(){
        Player testPlayer = new Player();
        assertEquals("Player", testPlayer.getName());
        assertEquals(1, testPlayer.getLevel());
        assertEquals(0, testPlayer.getExperience());
        assertEquals(8, testPlayer.getMaxHealth());
        assertEquals(8, testPlayer.getCurHealth());
        assertEquals(1, testPlayer.getBaseAttack());
        assertEquals(1, testPlayer.getBaseDefense());
        assertEquals(0, testPlayer.getCurrency());
        assertEquals(true, testPlayer.getAlive());

        Player testPlayer2 = new Player("Tom", 50, 250, 3, 2);
        assertEquals("Tom", testPlayer2.getName());
        assertEquals(50, testPlayer2.getLevel());
        assertEquals(0, testPlayer2.getExperience());
        assertEquals(250, testPlayer2.getMaxHealth());
        assertEquals(250, testPlayer2.getCurHealth());
        assertEquals(3, testPlayer2.getBaseAttack());
        assertEquals(2, testPlayer2.getBaseDefense());
        assertEquals(true, testPlayer2.getAlive());

        assertThrows(IllegalArgumentException.class, ()->{new Player("", 1, 1, 1, 1);});
        assertThrows(IllegalArgumentException.class, ()->{new Player("A", -100, 1, 1, 1);});
        assertThrows(IllegalArgumentException.class, ()->{new Player("A", 1, 0, 1, 1);});
        assertThrows(IllegalArgumentException.class, ()->{new Player("A", 1, 1, -1000, 0);});
        assertThrows(IllegalArgumentException.class, ()->{new Player("A", 1, 1, 1, -1000);});
        assertEquals(0, testPlayer2.getCurrency());
    }

    @Test
    public void PVP_Test(){
        Player p1 = new Player("Player01", 1, 10, 5, 2);
        Player p2 = new Player("Player02", 1, 10, 3, 3);

        p1.attack(p2);
        p2.attack(p1);
        assertEquals(8 ,p2.getCurHealth());
        assertEquals(9 ,p1.getCurHealth());

        while(p2.isAlive){
            p1.attack(p2);
        }

        assertEquals(false, p2.getAlive());
    }
}
