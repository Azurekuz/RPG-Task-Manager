import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Random;

public class RPGManager {
    //public LocationList locations;
    public Player player;
    public ActorList defaultMonsters;

    /************* BACKEND ****************/

    public RPGManager(){ this(false); }

    public RPGManager(boolean genDefault){
        this.player = new Player();
        this.defaultMonsters = new ActorList();

        if(genDefault){
            //System.out.println("[NOTICE][Generating Default RPGManager unimplemented.]");
            try {
                generateDefaultMonsterList();
            } catch (DuplicateObjectException e) {
                System.out.println("[ERROR][ Default RPGManager generation failed:" + e.getMessage()+"]");
            }
        }
    }

    public Player getPlayer() { return player; }
    public ActorList getDefaultMonsters() { return defaultMonsters; }

    public void transferEXP(double xp){
        player.grantExperience(xp);
    }

    public void generateDefaultMonsterList() throws DuplicateObjectException {
        defaultMonsters = new ActorList();
        //Stats are mostly temporary... tweak if you want, but make sure you change it in the file as well?
        Monster orcGrunt = new Monster("Orc Grunt", 1 , 4, 1, 2, 3 );
        Gear club = new Gear("Wooden Club", "MainWeapon",9,100,2,0);
        Gear shoddyHelm = new Gear("Shoddy Helm", "Head", 10, 100, 0, 2);
        orcGrunt.pickUp(club); orcGrunt.pickUp(shoddyHelm);
        defaultMonsters.addActor(orcGrunt);

        Monster bandit = new Monster("Bandit", player.getLevel(), player.getMaxHealth(), player.getBaseDefense()+1, player.getBaseAttack()/2, 10);
        Gear boots = new Gear("Leather Boots", "Boots",11,100,0,1);
        Gear goldRing = new Gear("Gold Ring", "Acc2",12,100,0,1);
        bandit.pickUp(boots); bandit.pickUp(goldRing);
        defaultMonsters.addActor(bandit);

        Monster dragon = new Monster("Dragon", 10, 30, 10, 15, 100);
        Gear dragonShield = new Gear("Dragon Scale Shield", "SubWeapon",13,100,1,4);
        Gear dragonArmor = new Gear("Dragon Scale Armor", "Torso",14,100,0,5);
        Gear dragonLeggings = new Gear("Dragon Scale Leggings", "Leggings",15,100,0,4);
        dragon.pickUp(dragonShield); dragon.pickUp(dragonArmor); dragon.pickUp(dragonLeggings);
        defaultMonsters.addActor(dragon);

        Monster wraith = new Monster("Wraith", 4, 10, 3, 5, 10);
        Usable wraithEssence = new Usable("Health Potion","Usable",16,5);
        Gear spectralRing = new Gear("Spectral Ring", "Acc1", 17, 100, 1, 1);
        wraith.pickUp(wraithEssence); wraith.pickUp(spectralRing);
        defaultMonsters.addActor(wraith);

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
//        //player char obj stores current location
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

    public String equip(String name) throws NonExistentObjectException {
        Gear itemToEquip = (Gear) player.getInventory().getItem(name);
        player.getInventory().removeItem(itemToEquip);
        player.equip(itemToEquip);
        return "[SUCCESS][ Item equipped.]";
        //return "[NOTICE][ Unimplemented content.]";
    }

    public String viewCharacter(){
        return player.toString();
    }

    /************* COMBAT *************/

    public void fight(String name){
        CombatUI combat = new CombatUI(this.player, new Monster(name, 1, 10, (int) (Math.floor(Math.random() * 3) + 1), (int) (Math.floor(Math.random() * 3) + 1), (int) (Math.floor(Math.random() * 15) + 10)));
        combat.handleTurn();
        if(!player.getAlive()){
            player.resurrect();
        }
    }

    public void fightrand(){
        Random random = new Random();
        int randIdx = random.nextInt(defaultMonsters.getSize());
        Actor monster = defaultMonsters.getActorAt(randIdx);
        //Monster monster = new Monster(actor.getName(), actor.getLevel(), actor.getMaxHealth(), actor.getBaseAttack(), actor.getBaseDefense(), actor.getCurrency());
        //TODO is there a better way to do that? InteliJ offered casting the Actor as Monster

        System.out.println("The " + monster.getName() + " stares at you menacingly.");
        CombatUI combat = new CombatUI(this.player, (Monster) monster);
        combat.handleTurn();
        if(!player.getAlive()){
            player.resurrect();
        }
    }

}
