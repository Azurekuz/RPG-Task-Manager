import com.fasterxml.jackson.annotation.JsonIgnore;

public class RPGManager {
    //public LocationList locations;
    public Player player;

    @JsonIgnore
    private CombatUI combat;

    /************* BACKEND ****************/

    public RPGManager(){ this(false); }

    public RPGManager(boolean genDefault){
        this.player = new Player();

        if(genDefault){
            System.out.println("[NOTICE][Generating Default RPGManager unimplemented.]");
        }
    }

    public Player getPlayer() { return player; }

    public void transferEXP(double xp){
        player.grantExperience(xp);
        player.getLevel();
    }

    //    public LocationList getLocations() {
//        return locations;
//    }
    /************* LOCATIONS *************/

//    public String goTo(String locationName){
//        //return "[SUCCESS][ Went to "+locationName+"].";
//
//        return "[NOTICE][ Unimplemented content.]";
//    }

    /**
     * @return String describing player's current location
     */
//    public String look(){
//        //TODO player char obj stores current location
//        return "[NOTICE][ Unimplemented content.]";
//    }
//
//    public String viewLocations(){
//        return locations.toString();
//    }

    /************* NPCs *************/

    public void shop(String name){
        //TODO return anything? handle more in UI?
    }

    public String talk(String name){ //TODO dialogue options? handle that in UI? return list of Strings maybe?
        return "[NOTICE][ Unimplemented content.]";
    }

    /************* CHARACTER *************/

    public String equip(String name){
        //return "[SUCCESS][ Item equipped.]";
        return "[NOTICE][ Unimplemented content.]";
    }

    public String viewCharacter(){
        return player.toString();
    }

    /************* COMBAT *************/

    public void fight(String name){
        //combat.commandHandler(player, enemy);
    }


}
