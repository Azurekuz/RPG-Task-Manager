import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActorTest {
    @Test
    public void ActorCurrencyTest() {
        Actor Act1 = new Actor();
        Actor Act2 = new Actor();
        Actor Act3 = new Actor();
        Actor Act4 = new Actor();
        Actor Act5 = new Actor();


        AssertEquals(0, Act1.getCurrency);
        AssertEquals(0, Act2.getCurrency);
        AssertEquals(0, Act3.getCurrency);
        AssertEquals(0, Act4.getCurrency);
        AssertEquals(0, Act5.getCurrency);


    @Test
    public void ActorCreationTest(){
        Actor testActor = new Actor();
        assertEquals("Empty Husk", testActor.getName());
        assertEquals(0, testActor.getLevel());
        assertEquals(0, testActor.getExperience());
        assertEquals(1, testActor.getMaxHealth());
        assertEquals(1, testActor.getCurHealth());
        assertEquals(1, testActor.getBaseAttack());
        assertEquals(1, testActor.getBaseDefense());
        assertEquals(0, testActor.getCurrency());
        assertEquals(true, testActor.getAlive());

        Actor testActor2 = new Actor("Test Dummy", 50, 250, 3, 2);
        assertEquals("Test Dummy", testActor2.getName());
        assertEquals(50, testActor2.getLevel());
        assertEquals(0, testActor2.getExperience());
        assertEquals(250, testActor2.getMaxHealth());
        assertEquals(250, testActor2.getCurHealth());
        assertEquals(3, testActor2.getBaseAttack());
        assertEquals(2, testActor2.getBaseDefense());
        assertEquals(true, testActor2.getAlive());

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

        assertEquals(false, dummy2.getAlive());
    }
}
