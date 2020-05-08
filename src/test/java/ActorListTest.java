import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActorListTest {
    @Test
    public void ActorListTest() throws DuplicateObjectException, NonExistentObjectException {
        Actor Act1 = new Actor("Eric", 1, 4, 3, 2);
        Actor Act2 = new Actor("Sarah", 0, 9, 8, 0);
        Actor Act3 = new Actor("Morty", 1, 4, 3, 2);
        Actor Act4 = new Actor("Link", 0, 9, 0, 0);
        Actor Act5 = new Actor("John", 91, 4, 30, 25);
        Actor Act6 = new Actor("Riley", 12, 42, 32, 26);
        Actor Act7 = new Actor("Eric", 1, 4, 3, 2);

        ActorList act1List = new ActorList();
        act1List.addActor(Act1);
        act1List.addActor(Act2);
        act1List.addActor(Act3);
        act1List.addActor(Act4);
        act1List.addActor(Act5);
        act1List.addActor(Act6);

        /* Duplicate Actor Added */
        assertThrows(DuplicateObjectException.class, () -> act1List.addActor(Act7));

        /*Tests for The cleanup of all Actors that are "dead"*/
        assertEquals(6, act1List.getSize());
        for (int i = 0; i < 6; i++) {
            act1List.getActorAt(i).setAlive(false);
        }
        act1List.cleanUpDead();

        assertEquals(0, act1List.getSize());

        /*Tests for The cleanup of all Actors that are "dead" if the list is Empty*/
        assertThrows(NonExistentObjectException.class, act1List::cleanUpDead); //double colon is fancy InteliJ "simplification"

        /*Tests for The cleanup of all Actors that are "dead" if half the Actors in the list are "dead"*/
        Actor Act9 = new Actor("CJ", 1, 4, 3, 2);
        Actor Act10 = new Actor("Sally", 0, 9, 8, 0);
        Actor Act11 = new Actor("Mia", 1, 4, 3, 2);
        Actor Act12 = new Actor("Mille", 1, 4, 3, 2);
        act1List.addActor(Act9);
        act1List.addActor(Act10);
        act1List.addActor(Act11);
        act1List.addActor(Act12);
        assertEquals(4, act1List.getSize());

        act1List.getActor("Mia").setAlive(false);
        act1List.getActor("Mille").setAlive(false);
        act1List.cleanUpDead();
        assertEquals(2, act1List.getSize());

        /*Tests for removing Existent and nonExistent Actors in the List  */
        act1List.removeActor(Act9);
        act1List.removeActor(Act10);
        assertEquals(0, act1List.getSize());
        assertThrows(NonExistentObjectException.class, () -> act1List.removeActor(Act11));
        assertThrows(NonExistentObjectException.class, () -> act1List.removeActor(Act12));
        assertThrows(NonExistentObjectException.class, () -> act1List.removeActor(Act5));
        assertThrows(NonExistentObjectException.class, () -> act1List.removeActor(Act6));

        /*getRandomActor*/
        assertThrows(NonExistentObjectException.class, act1List::getRandomActor);
        act1List.addActor(Act1);
        assertEquals(Act1.getName(), act1List.getRandomActor().getName());

        act1List.removeActor(Act1.getName());
        assertEquals(0, act1List.getSize());

    }
}