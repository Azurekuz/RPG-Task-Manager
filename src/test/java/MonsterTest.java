import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonsterTest {
    @Test
    public void MonsterCreationTest(){
        Monster testMonster = new Monster();
        assertEquals("Monster", testMonster.getName());
        assertEquals(1, testMonster.getLevel());
        assertEquals(0, testMonster.getExperience());
        assertEquals(10, testMonster.getMaxHealth());
        assertEquals(10, testMonster.getCurHealth());
        assertEquals(3, testMonster.getBaseAttack());
        assertEquals(1, testMonster.getBaseDefense());
        assertEquals(10, testMonster.getCurrency());
        assertEquals(true, testMonster.getAlive());

        Monster testMonster2 = new Monster("Kobold", 50, 250, 3, 2, 100);
        assertEquals("Kobold", testMonster2.getName());
        assertEquals(50, testMonster2.getLevel());
        assertEquals(0, testMonster2.getExperience());
        assertEquals(250, testMonster2.getMaxHealth());
        assertEquals(250, testMonster2.getCurHealth());
        assertEquals(3, testMonster2.getBaseAttack());
        assertEquals(2, testMonster2.getBaseDefense());
        assertEquals(100, testMonster2.getCurrency());
        assertEquals(true, testMonster2.getAlive());
        assertEquals(100, testMonster2.getCurrency());
        
        assertThrows(IllegalArgumentException.class, ()->{new Monster("", 1, 1, 1, 1, 0);});
        assertThrows(IllegalArgumentException.class, ()->{new Monster("A", -100, 1, 1, 1,0);});
        assertThrows(IllegalArgumentException.class, ()->{new Monster("A", 1, 0, 1, 1,0);});
        assertThrows(IllegalArgumentException.class, ()->{new Monster("A", 1, 1, -1000, 0,0);});
        assertThrows(IllegalArgumentException.class, ()->{new Monster("A", 1, 1, 1, -1000,0);});

    }

    @Test
    public void PVP_Test(){
        Monster m1 = new Monster("Monster01", 1, 10, 5, 2,0);
        Monster m2 = new Monster("Monster02", 1, 10, 3, 3,0);

        m1.attack(m2);
        m2.attack(m1);
        assertEquals(8 ,m2.getCurHealth());
        assertEquals(9 ,m1.getCurHealth());

        while(m2.isAlive){
            m1.attack(m2);
        }

        assertEquals(false, m2.getAlive());
    }
}