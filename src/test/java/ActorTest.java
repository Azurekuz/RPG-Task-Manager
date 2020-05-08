import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActorTest {
    @Test
    public void ActorCurrencyTest() throws InsufficientCurrencyException {
        Actor act1 = new Actor();
        Actor act2 = new Actor();

        assertEquals(0, act1.getCurrency());
        assertEquals(0, act2.getCurrency());

        act1.addToCurrency(1);
        assertEquals(1, act1.getCurrency());
        act1.subtractFromCurrency(1);
        assertEquals(0, act1.getCurrency());
        assertThrows(InsufficientCurrencyException.class, ()-> act1.subtractFromCurrency(1));

        assertThrows(InsufficientCurrencyException.class, ()-> act2.subtractFromCurrency(1));
    }
    @Test
    public void ActorLevelingTest() throws IllegalArgumentException{
        Actor a = new Actor();

        assertThrows(IllegalArgumentException.class, ()-> a.grantExperience(-1));

        assertEquals(1, a.getLevel());
        assertEquals(0, a.getExperience());

        a.grantExperience(0.1);
        assertEquals(1, a.getLevel());
        assertEquals(0.1, a.getExperience());

        a.grantExperience(100);
        assertEquals(2, a.getLevel());
        assertEquals(100.1, a.getExperience());
        assertEquals(3, a.getMaxHealth());
        assertEquals(2, a.getBaseAttack());
        assertEquals(2, a.getBaseDefense());

        a.grantExperience(899);
        assertEquals(10, a.getLevel());
        assertEquals(999.1, a.getExperience());
        assertEquals(19, a.getMaxHealth());
        assertEquals(10, a.getBaseAttack());
        assertEquals(10, a.getBaseDefense());

    }

    @Test
    public void ActorCreationTest(){
        Actor testActor = new Actor();
        assertEquals("Empty Husk", testActor.getName());
        assertEquals(1, testActor.getLevel());
        assertEquals(0, testActor.getExperience());
        assertEquals(1, testActor.getMaxHealth());
        assertEquals(1, testActor.getCurHealth());
        assertEquals(1, testActor.getBaseAttack());
        assertEquals(1, testActor.getBaseDefense());
        assertEquals(0, testActor.getCurrency());
        assertTrue(testActor.getAlive());

        Actor testActor2 = new Actor("Test Dummy", 50, 250, 3, 2);
        assertEquals("Test Dummy", testActor2.getName());
        assertEquals(50, testActor2.getLevel());
        assertEquals(0, testActor2.getExperience());
        assertEquals(250, testActor2.getMaxHealth());
        assertEquals(250, testActor2.getCurHealth());
        assertEquals(3, testActor2.getBaseAttack());
        assertEquals(2, testActor2.getBaseDefense());
        assertTrue(testActor2.getAlive());

        assertThrows(IllegalArgumentException.class, ()->{new Actor("", 1, 1, 1, 1);});
        assertThrows(IllegalArgumentException.class, ()->{new Actor("A", -100, 1, 1, 1);});
        assertThrows(IllegalArgumentException.class, ()->{new Actor("A", 1, 0, 1, 1);});
        assertThrows(IllegalArgumentException.class, ()->{new Actor("A", 1, 1, -1000, 0);});
        assertThrows(IllegalArgumentException.class, ()->{new Actor("A", 1, 1, 1, -1000);});
        assertEquals(0, testActor2.getCurrency());
    }

    @Test
    public void ActorCombatTest(){
        Actor dummy1 = new Actor("Act01", 1, 10, 5, 2);
        Actor dummy2 = new Actor("Act02", 1, 10, 3, 3);

        dummy1.attack(dummy2);
        dummy2.attack(dummy1);
        assertEquals(8 ,dummy2.getCurHealth());
        assertEquals(9 ,dummy1.getCurHealth());

        while(dummy2.isAlive){
            dummy1.attack(dummy2);
        }

        assertFalse(dummy2.getAlive());
    }
}
