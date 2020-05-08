
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Random;

public class ActorList {
    public ArrayList<Actor> actorList;

    /** BACKEND **/

    public ActorList(){ actorList = new ArrayList<Actor>(); }
    public ActorList(ArrayList<Actor> listIn){ actorList = listIn; }

    public ArrayList<Actor> getActorList(){ return actorList; }
    @JsonIgnore
    public int getSize(){return actorList.size();}

    /** ADDING **/

    public void addActor(Actor newActor) throws DuplicateObjectException {
        try {
            actorList.add(checkDuplicate(newActor));
        }catch(DuplicateObjectException e){
            throw new DuplicateObjectException(e.getMessage());
        }
    }

    public Actor checkDuplicate(Actor newActor) throws DuplicateObjectException {
        for(int i = 0; i < actorList.size(); i++){
            if(actorList.get(i).equals(newActor) || actorList.get(i).getName().toLowerCase().equals(newActor.getName().toLowerCase())){ //these chain methods are getting ridiculous!
                throw new DuplicateObjectException("An Actor already exists with these attributes and/or name!");
            }
        }
        return newActor;
    }

    /** REMOVING **/


    public void removeActor(Actor actorToRemove) throws NonExistentObjectException {
        if (!actorList.contains(actorToRemove)) {
            throw new NonExistentObjectException("This Actor does not exist in this list");
        }
        actorList.remove(findActor(actorToRemove.getName()));
    }
    public void removeActor(String name){
        int index = findActor(name);
        if (index < 0 || index > actorList.size()){
            throw new IllegalArgumentException("Invalid index");
        }
        actorList.remove(index);
    }

    public void cleanUpDead() throws NonExistentObjectException {
        if (actorList.isEmpty()) {
            throw new NonExistentObjectException("ActorList is empty");
        }
        actorList.removeIf(actor -> !actor.getAlive()); //Wow, handy built-in function!
    }

    /** Finding/Searching/Getting **/

    public Actor getActor(String name) throws NonExistentObjectException{
        int index = findActor(name);
        if(index >= actorList.size() || index < 0){
            throw new NonExistentObjectException("Nonexistent or Invalid Actor Requested!");
        }
        return actorList.get(index);
    }

    public Actor getActorAt(int index) throws IllegalArgumentException{
        if (index < 0 || index > actorList.size()){
            throw new IllegalArgumentException("Invalid index");
        }
        return actorList.get(index);
    }
    @JsonIgnore
    public Actor getRandomActor() throws NonExistentObjectException{
        if (actorList.size() == 0){
            throw new NonExistentObjectException("Empty ActorList");
        }
        Random random = new Random();
        int randint = random.nextInt(actorList.size());
        return actorList.get(randint);
    }

    public int findActor(String name){
        for(int i = 0; i < actorList.size(); i++){
            if(actorList.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                return i; //returns index of actor
            }
        }
        return -1; //not found
    }




}
