import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AchievementListTest {
    @Test
    public void AchievmentListTest() throws NonExistentAchievementException{
        //int id, String title,  int quality, boolean complete, String desc,
        //T\
        Achievement testAchievement1 = new Achievement(0, "title1", 2,"TASK TASK TASK");
        Achievement testAchievement2 = new Achievement(1, "title1", 2,"WORD WORD WORD");
        Achievement testAchievement3 = new Achievement(2, "title1", 1,"YEAH YEAH YEAH");
        Achievement testAchievement4 = new Achievement(3, "title1", 3,"YADDA YADDA YADDA");
        Achievement testAchievement5 = new Achievement(4, "title1", 5, "BLAH BLAH BLAH");
        AchievementList testAchievementList = new AchievementList();
        //id, title, desc, quality, timeLimit, type, complete
        testAchievementList.addAchievement(testAchievement1);
        testAchievementList.addAchievement(testAchievement2);
        testAchievementList.addAchievement(testAchievement3);
        testAchievementList.addAchievement(testAchievement4);
        testAchievementList.addAchievement(testAchievement5);


        assertEquals(testAchievement1,testAchievementList.findAchievement(0));
        assertEquals(testAchievement2,testAchievementList.findAchievement(1));
        assertEquals(testAchievement3,testAchievementList.findAchievement(2));
        assertEquals(testAchievement4,testAchievementList.findAchievement(3));
        assertEquals(testAchievement5,testAchievementList.findAchievement(4));

        /*Test for non existent Achievements in the Achievement list*/
        assertThrows(NonExistentAchievementException.class,()->{testAchievementList.findAchievement(6);});
        assertThrows(NonExistentAchievementException.class,()->{testAchievementList.findAchievement(7);});
        assertThrows(NonExistentAchievementException.class,()->{testAchievementList.findAchievement(8);});

        testAchievementList.clear();
        testAchievementList.addAchievement(testAchievement1);
        String stringToReturn="";
        assertEquals(Integer.toString(testAchievementList.findAchievement(0).getID())+" "+testAchievementList.findAchievement(0).getTitle()+" "+testAchievementList.findAchievement(0).getDescription()+" "+Integer.toString(testAchievementList.findAchievement(0).getQuality())+" "+Boolean.toString(testAchievementList.findAchievement(0).getComplete()) + "\n", testAchievementList.toString());

        testAchievementList.clear();
        //assertThrows(NonExistentAchievementException.class,()->{testAchievementList.toString();});
    }
}