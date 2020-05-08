import java.util.Random;

public class RPGManager {
    //public LocationList locations;
    public Player player;
    public ActorList defaultMonsters;
    public ItemList merchantInv;

    /************* BACKEND ****************/

    public RPGManager(){ this(false); }

    public RPGManager(boolean genDefault){
        this.player = new Player();
        this.defaultMonsters = new ActorList();
        this.merchantInv = new ItemList();
        if (genDefault) {
            try {
                generateDefaultMonsterList();
                generateMerchantInventory();
            } catch (DuplicateObjectException e) {
                System.out.println("[ERROR][ Default RPGManager generation failed:" + e.getMessage() + "]");
            }
        }
    }

    public Player getPlayer() { return player; }
    public ActorList getDefaultMonsters() { return defaultMonsters; }
    public ItemList getMerchantInv() { return merchantInv; }

    public void transferEXP(double xp){
        player.grantExperience(xp);
    }

    public void generateDefaultMonsterList() throws DuplicateObjectException {
        defaultMonsters = new ActorList();
        //Stats are mostly temporary... tweak if you want
        Monster orcGrunt = new Monster("Orc Grunt", 1 , 4, 1, 2, 3 );
        Gear club = new Gear("Wooden Club", "MainWeapon",9,100,2,0, 0);
        Gear shoddyHelm = new Gear("Shoddy Helm", "Head", 10, 100, 0, 2, 0);
        orcGrunt.pickUp(club); orcGrunt.pickUp(shoddyHelm);
        defaultMonsters.addActor(orcGrunt);

        //bandit scales off of player stats, might not work well
        Monster bandit = new Monster("Undead Bandit", player.getLevel(), player.getMaxHealth(), player.getBaseDefense(), player.getBaseAttack()/2, 10);
        Gear boots = new Gear("Leather Boots", "Boots",11,100,0,1, 3);
        Gear goldRing = new Gear("Gold Ring", "Acc2",12,100,0,1, 3);
        bandit.pickUp(boots); bandit.pickUp(goldRing);
        defaultMonsters.addActor(bandit);

        Monster dragon = new Monster("Dragon", 10, 30, 10, 15, 100);
        Gear dragonShield = new Gear("Dragon Scale Shield", "SubWeapon",13,100,1,4, 10);
        Gear dragonArmor = new Gear("Dragon Scale Armor", "Torso",14,100,0,5, 10);
        Gear dragonLeggings = new Gear("Dragon Scale Leggings", "Leggings",15,100,0,4, 8);
        dragon.pickUp(dragonShield); dragon.pickUp(dragonArmor); dragon.pickUp(dragonLeggings);
        defaultMonsters.addActor(dragon);

        Monster wraith = new Monster("Wraith", 4, 10, 3, 5, 10);
        Usable wraithEssence = new Usable("Wraith Essence","Health",16,12, 12);
        Gear spectralRing = new Gear("Spectral Ring", "Acc1", 17, 100, 1, 1, 4);
        wraith.pickUp(wraithEssence); wraith.pickUp(spectralRing);
        defaultMonsters.addActor(wraith);

        Monster giantSpider = new Monster("Giant Spider", 2, 5, 2, 2, 4);
        Gear spiderFang = new Gear("Spider Fang", "SubWeapon",18,100,4,0, 8);
        giantSpider.pickUp(spiderFang);
        defaultMonsters.addActor(giantSpider);

        Monster demonLord = new Monster("DEMON LORD",100, 300, 50, 40, 400);
        Gear legendSword = new Gear("Legendary Sword","MainWeapon",19,100,20, 3, 50);
        Gear demonsArmor = new Gear("Demon's Armor", "Torso", 20, 100, 3, 20, 50);
        demonLord.pickUp(legendSword); demonLord.pickUp(demonsArmor);
        defaultMonsters.addActor(demonLord);

    }

    public void generateMerchantInventory() {
        //MainWeapon, SubWeapon, Head, Torso, Leggings, Boots, Gloves, Acc1, Acc2
        this.merchantInv = new ItemList();
        Gear laxe = new Gear("Lumberjack's Axe", "MainWeapon", 0, 100, 3, 0, 21);
        Gear dagger = new Gear("Steel Dagger", "SubWeapon", 0, 100, 2, 0, 21);
        Gear ljacket = new Gear("Leather Jacket", "Torso", 0, 100, 0, 2, 16);
        Gear sandals = new Gear("Sandals", "Boots", 0, 100, 0, 1, 12);
        Gear cpants = new Gear("Combat Pants", "Leggings", 0, 100, 0, 2, 16);
        Gear lgrips = new Gear("Leather Grips", "Gloves", 0, 100, 0, 1, 12);
        Gear bcharm = new Gear("Bone Charm", "Acc1", 0, 100, 0, 1, 12);
        Gear oband = new Gear("Obsidian Band", "Acc2", 0, 100, 0, 2, 16);
        merchantInv.addItem(laxe); merchantInv.addItem(dagger); merchantInv.addItem(ljacket); merchantInv.addItem(sandals);
        merchantInv.addItem(cpants); merchantInv.addItem(lgrips); merchantInv.addItem(bcharm); merchantInv.addItem(oband);
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

    public void buy(String name) throws InsufficientCurrencyException, NonExistentObjectException{
        Item item = merchantInv.getItem(name);
        int worth = item.getWorth();
        if (worth > player.getCurrency()){
            throw new InsufficientCurrencyException("Sorry, you don't have enough money to buy that.");
        }
        player.getInventory().addItem(item);
        player.subtractFromCurrency(worth);
        System.out.println("[SUCCESS] [Item bought for " + worth + "gold!]");
    }

    public void sell(String name) throws NonExistentObjectException{
        Item item = player.getInventory().getItem(name);
        int worth = item.getWorth();
        merchantInv.addItem(item);
        player.grantCurrency(worth);
        player.getInventory().removeItem(item);
        System.out.println("[SUCCESS] [Item sold for " + worth + " gold!]");

    }

    //public String talk(String name){ //TODO dialogue options? handle that in UI? return list of Strings maybe?
        //return "[NOTICE][ Unimplemented content.]";
    //}

    /************* CHARACTER *************/

    public String equip(String name) throws NonExistentObjectException {
        Gear itemToEquip = (Gear) player.getInventory().getItem(name);
        player.equip(itemToEquip);
        return "[SUCCESS][ Item equipped.]";
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
        monster.resurrect();
        System.out.println("The " + monster.getName() + " stares at you menacingly.");
        CombatUI combat = new CombatUI(this.player, (Monster) monster);
        combat.handleTurn();
        if(!player.getAlive()){
            player.resurrect();
        }
    }

}
