public class ActorListTest {

    public class ActorListTest {
        @Test
    public Actor Act1 = new Actor(Eric, 1, 4, 3, 2);
    public Actor Act2 = new Actor(Sarah, 0, 9, 8, 0);
    public Actor Act3 = new Actor(Morty, 1, 4, 3, 2);
    public Actor Act4 = new Actor(Link, 0, 9, 0, 0);
    public Actor Act5 = new Actor(John, 91, 4, 30, 25);
    public Actor Act6 = new Actor(Riley, 12, 42, 32, 26);
    public Actor Act7 = new Actor(Link, 0, 0, 0, 0);
    public Actor Act8 = new Actor( , 0, 0, 0, 0);


    ActorList Act1List = new ActorList();
    /*Test for the basic adding of Actors to the list*/
    Act1List.addActor(Act1);
    Act1List.addActor(Act2);
    Act1List.addActor(Act3);
    Act1List.addActor(Act4);
    Act1List.addActor(Act5);
    Act1List.addActor(Act6);

    assertEquals(Act1List.contains(Act1),true);
    assertEquals(Act1List.contains(Act1),true);
    assertEquals(Act1List.contains(Act1),true);
    assertEquals(Act1List.contains(Act1),true);
    assertEquals(Act1List.contains(Act1),true);
    assertEquals(Act1List.contains(Act1),true);

    /*Tests for adding actors with zero values in the parameters*/
        AssertThrows(IllegalArgumentException.class,Act1List.addActor(Act7));
        AssertThrows(IllegalArgumentException.class,Act1List.addActor(Act8));

        /*Tests for The cleanup of all Actors that are "dead"*/
        int check1=Act1List.size();
        AssertEquals(6,check1);
        int i = Act1List.IndexOf(Act1);
        Act1List.get(i).setAlive(false);
        int i = Act1List.IndexOf(Act2);
        Act1List.get(i).setAlive(false);
        int i = Act1List.IndexOf(Act3);
        Act1List.get(i).setAlive(false);
        int i = Act1List.IndexOf(Act4);
        Act1List.get(i).setAlive(false);
        int i = Act1List.IndexOf(Act5);
        Act1List.get(i).setAlive(false);
        int i = Act1List.IndexOf(Act6);
        Act1List.get(i).setAlive(false);

        Act1List.cleanUpDead();
        check2=Act1List.size();
        assertEquals(0,check2);
        /*Tests for The cleanup of all Actors that are "dead" if the list is Empty*/
        assertEquals(Act1List.isEmpty(),true);
        assertThrows(IllegalArgumentException.class,Act1List.cleanUpDead());
        /*Tests for The cleanup of all Actors that are "dead" if half the Actors in the list are "dead"*/
        public Actor Act9 = new Actor(CJ, 1, 4, 3, 2);
        public Actor Act10 = new Actor(Sally, 0, 9, 8, 0);
        public Actor Act11 = new Actor(Mia, 1, 4, 3, 2);
        public Actor Act12 = new Actor(Mille, 1, 4, 3, 2);
        Act1List.addActor(Act9);
        Act1List.addActor(Act10);
        Act1List.addActor(Act11);
        Act1List.addActor(Act12);
        int check3=Act1List.size();
        assertEquals(4,check3);
        int i = Act1List.IndexOf(Act11);
        Act1List.get(i).setAlive(false);
        int i = Act1List.IndexOf(Act12);
        Act1List.get(i).setAlive(false);
        Act1List.cleanUpDead();
        int check4=Act1List.size();
        assertEquals(2,check4);

        /*Tests for removing Existent and nonExistent Actors in the List  */
        int check5=Act1List.size();
        AssertEquals(2,check5);
        Act1List.removeActor(Act9);
        Act1List.removeActor(Act10);
        assertEquals(Act1List.isEmpty,true);
        assertThrows(IllegalArgumentException.class,Act1List.removeActor(Act11));
        assertThrows(IllegalArgumentException.class,Act1List.removeActor(Act12));
        assertThrows(IllegalArgumentException.class,Act1List.removeActor(Act5));
        assertThrows(IllegalArgumentException.class,Act1List.removeActor(Act6));



}