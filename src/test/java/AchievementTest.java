public class AchievementTest {
    @Test

        Achievement TestAchievement1 = new Achievement(1, "Achievement1",1,"New Achievement");
        Achievement TestAchievement2 = new Achievement(2, "Achievement2",2,"New Achievement2");
        Achievement TestAchievement3 = new Achievement(3, "Achievement3",3, "New Achievement3");
        Achievement TestAchievement4 = new Achievement(4, "Achievement4",4, "New Achievement4");
        Achievement TestAchievement5 = new Achievement(-3, "Achievement5",3,"New Achievement5");
        Achievement TestAchievement6 = new Achievement(3, "Achievement6",-3, "New Achievement6");
        Achievement TestAchievement7 = new Achievement(-3, "Achievement6",-3, "New Achievement6");

        AssertThrows(IllegalArugmentException.class,TestAchievement7);
        AssertThrows(IllegalArugmentException.class,TestAchievement6);
        AssertThrows(IllegalArugmentException.class,TestAchievement5);


        AssertEquals(1,TestAchievement1.getID());
        AssertEquals(2,TestAchievement2.getID());
        AssertEquals(3,TestAchievement3.getID());
        AssertEquals(4,TestAchievement4.getID());


        AssertEquals("Achievement1",TestAchievement1.getTitle());
        AssertEquals("Achievement2",TestAchievement2.getTitle());
        AssertEquals("Achievement3",TestAchievement3.getTitle());
        AssertEquals("Achievement4",TestAchievement4.getTitle());

       /* TestAchievement1.getTitle();
        TestAchievement2.getTitle();
        TestAchievement3.getTitle();
        TestAchievement4.getTitle();
        TestAchievement5.getTitle();
        TestAchievement6.getTitle();
    */
        AssertEquals("New Achievement"),TestAchievement1.getDescription());
        AssertEquals("New Achievement2"),TestAchievement2.getDescription());
        AssertEquals("New Achievement3"),TestAchievement3.getDescription());
        AssertEquals("New Achievement4"),TestAchievement4.getDescription());




       /*
        TestAchievement1.getDescription();
        TestAchievement2.getDescription();
        TestAchievement3.getDescription();
        TestAchievement4.getDescription();
        TestAchievement5.getDescription();
        TestAchievement6.getDescription();**/


        AssertEquals(1,TestAchievement1.getQuality());
        AssertEquals(2,TestAchievement2.getQuality());
        AssertEquals(3,TestAchievement3.getQuality());
        AssertEquals(4,TestAchievement4.getQuality());


        AssertEquals(1,TestAchievement1.getQuality());
        AssertEquals(2,TestAchievement2.getQuality());
        AssertEquals(3,TestAchievement3.getQuality());
        AssertEquals(4,TestAchievement4.getQuality());

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
