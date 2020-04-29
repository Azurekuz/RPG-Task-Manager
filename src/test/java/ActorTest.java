import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActorTest {
    @Test
    public void ActorTest() {
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


    }
}
