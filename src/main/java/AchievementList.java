import java.awt.*;
import java.util.ArrayList;


public class AchievementList{
    ArrayList<Achievement> AchievementList = new ArrayList<Achievement>(100);

    public void addAchievement(Achievement achievementToAdd){
        while(checkTakenID(achievementToAdd.id)){
            achievementToAdd.setId(achievementToAdd.id+1);
        }
        this.AchievementList.add(achievementToAdd);
        /*if(achievementToAdd.getID()<AchievementList.size()){ //AchievementList.findAchievement().getId()???
            achievementToAdd.setId(achievementToAdd.getID()+1);
            this.AchievementList.add(achievementToAdd);
        }
        else{
            this.AchievementList.add(achievementToAdd);
        }*/
    }

    public boolean checkTakenID(int id){
        for(int achievementID = 0; achievementID < AchievementList.size(); achievementID++){
            if(AchievementList.get(achievementID).id == id){
                return true;
            }
        }
        return false;
    }

    public void clear(){
        AchievementList.clear();
    }

    public ArrayList<Achievement> getAchievementList() {
        return this.AchievementList;
    }

    public Achievement findAchievement(int achievementId) throws NonExistentAchievementException{

        for(int i = 0; i < this.AchievementList.size(); i++){
            if(this.AchievementList.get(i).getID()==achievementId){

                String y = this.AchievementList.get(i).getTitle();
                int x=this.AchievementList.get(i).getID();
                System.out.println("Title: " + y + "AchievementID: " + x);  //returns index of Achievement
                return this.AchievementList.get(i);
            }
        }
        throw new NonExistentAchievementException("Achievement not found! Apparently there is no Achievement with that ID.");
    }

    public String toString(){
        String stringToAdd ="";
        for(int i = 0; i < this.AchievementList.size(); i++){
            String stringToReturn = Integer.toString(this.AchievementList.get(i).getID())+" "+this.AchievementList.get(i).getTitle()+" "+this.AchievementList.get(i).getDescription()+" "+Integer.toString(this.AchievementList.get(i).getQuality())+" "+ this.AchievementList.get(i).getComplete();
            stringToAdd = stringToAdd + stringToReturn+"\n";
        }
        return stringToAdd;
    }
} 