
import java.util.ArrayList;
import java.util.Collections;
public class ActorList {
    public ArrayList<Actor> Actors;

    public void removeActor(Actor actorToRemove) {
        if (Actor.contains(actorToRemove) == False) {
            throw new IllegalArgumentException("This Actor does not exist in this list");
        } else {


            for (int i = 0; i < Actors.size(); i++) {
                if (i == Actors.IndexOf(actorToRemove)) {
                    Actors.remove(i);

                }
            }
        }
    }

    public void addActor(Actor actorToAdd) {
        if (actorToAdd.getName.length() <= 0) {
            throw new IllegalArgumentException("This Actor has an invalid name");
        } else if (actorToAdd.getLevel < 0) {
            throw new IllegalArgumentException("This Actor has an invalid level ");
        } else if (actorToAdd.getMaxHealth <= 0) {
            throw new IllegalArgumentException("This Actor has an invalid value for Maxhealth ");
        } else if (actorToAdd.getBaseAttack < 0) {
            throw new IllegalArgumentException("This Actor has an invalid attack");
        } else if (actorToAdd.getBaseDefense < 0) {
            throw new IllegalArgumentException("This Actor has an invalid defense ");
        } else {
            Actors.add(actorToAdd);
        }

    }

    public void cleanUpDead() {

        if (Actors.isEmpty==true) {
            throw new IllegalArgumentException("There is nothing in this list to clean up ");

        }
        else {

            for (int i = 0; i < Actors.size(); i++) {
                if (Actors.get(i).getAlive() == false) {
                    Actors.remove(i);


                }
            }
        }
    }

}
