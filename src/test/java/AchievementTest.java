import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AchievementTest {
    @Test
    void achievementTest(){
    Achievement TestAchievement1 = new Achievement(1, "Achievement1",1,"New Achievement");
    Achievement TestAchievement2 = new Achievement(2, "Achievement2",2,"New Achievement2");
    Achievement TestAchievement3 = new Achievement(3, "Achievement3",3, "New Achievement3");
    Achievement TestAchievement4 = new Achievement(4, "Achievement4",4, "New Achievement4");

    assertThrows(IllegalArgumentException.class, ()->{new Achievement(-3, "Achievement6",-3, "New Achievement6");});
    assertThrows(IllegalArgumentException.class, ()->{new Achievement(3, "Achievement6",-3, "New Achievement6");});
    assertThrows(IllegalArgumentException.class, ()->{new Achievement(-3, "Achievement5",3,"New Achievement5");});


    assertEquals(1,TestAchievement1.getID());
    assertEquals(2,TestAchievement2.getID());
    assertEquals(3,TestAchievement3.getID());
    assertEquals(4,TestAchievement4.getID());


    assertEquals("Achievement1",TestAchievement1.getTitle());
    assertEquals("Achievement2",TestAchievement2.getTitle());
    assertEquals("Achievement3",TestAchievement3.getTitle());
    assertEquals("Achievement4",TestAchievement4.getTitle());

   /* TestAchievement1.getTitle();
    TestAchievement2.getTitle();
    TestAchievement3.getTitle();
    TestAchievement4.getTitle();
    TestAchievement5.getTitle();
    TestAchievement6.getTitle();
*/
    assertEquals("New Achievement",TestAchievement1.getDescription());
    assertEquals("New Achievement2",TestAchievement2.getDescription());
    assertEquals("New Achievement3",TestAchievement3.getDescription());
    assertEquals("New Achievement4",TestAchievement4.getDescription());




   /*
    TestAchievement1.getDescription();
    TestAchievement2.getDescription();
    TestAchievement3.getDescription();
    TestAchievement4.getDescription();
    TestAchievement5.getDescription();
    TestAchievement6.getDescription();**/


    assertEquals(1,TestAchievement1.getQuality());
    assertEquals(2,TestAchievement2.getQuality());
    assertEquals(3,TestAchievement3.getQuality());
    assertEquals(4,TestAchievement4.getQuality());


    assertEquals(1,TestAchievement1.getQuality());
    assertEquals(2,TestAchievement2.getQuality());
    assertEquals(3,TestAchievement3.getQuality());
    assertEquals(4,TestAchievement4.getQuality());

  /*  TestAchievement1.getQuality();
    TestAchievement2.getQuality();
    TestAchievement3.getQuality();
    TestAchievement4.getQuality();
    TestAchievement5.getQuality();
    TestAchievement6.getQuality();

     TestAchievement1.getComplete();
    TestAchievement2.getComplete();
    TestAchievement3.getComplete();
    TestAchievement4.getComplete();
    TestAchievement5.getComplete();
    TestAchievement6.getComplete();
**/
    }
}

