import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RPGManagerTest {
    @Test
    public void buySellTest() throws InsufficientCurrencyException, NonExistentObjectException{
        RPGManager r = new RPGManager(true);
        Player p = r.getPlayer();
        assertThrows(NonExistentObjectException.class, () -> r.buy("sdlasjnhd"));
        assertThrows(NonExistentObjectException.class, () -> r.sell("klhnknlhg"));

        assertThrows(InsufficientCurrencyException.class, () -> r.buy("Leather Jacket"));
        p.grantCurrency(15);
        r.buy("Leather Jacket");
        assertTrue(p.getInventory().findItem("Leather Jacket") != -1); //if it was found, means successfully bought
        assertEquals(10, p.getCurrency());

        r.sell("Leather Jacket");
        assertEquals(15, p.getCurrency());
    }
}
