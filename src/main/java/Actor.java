import com.fasterxml.jackson.annotation.JsonIgnore;

public class Actor {
    @JsonIgnore
    final double EXP_TO_LEVEL = 100.0;

    private String name;
    private int level;
    private double experience;
    private int maxHealth;
    private int curHealth;
    private int baseAttack;
    private int baseDefense;
    private int modAttack; /* MODIFIED Attack, this is your total attack after taking into account equipment and buffs/debuffs*/
    private int modDefense; /* MODIFIED Defense, this is your total attack after taking into account equipment and buffs/debuffs*/

    boolean isAlive;

    private int currency;
    private ItemList inventory;
    private Item[] equipment; //MainWeapon, SubWeapon, Head, Torso, Leggings, Boots, Gloves, Acc1, Acc2
    public ItemList items;

    public Actor(){
        name = "Empty Husk";
        level = 1;
        experience = 0;
        maxHealth = 1;
        curHealth = maxHealth;
        baseAttack = 1;
        baseDefense = 1;
        modAttack = baseAttack;
        modDefense = baseDefense;

        isAlive = true;

        initialiseItems();
    }

    public Actor(String name, int level, int health, int baseAttack, int baseDefense) throws IllegalArgumentException{
        verifyConstructorInput(name, level, health, baseAttack, baseDefense);
        this.name = name;
        this.level = level;
        this.experience = 0;
        this.maxHealth = health;
        this.curHealth = this.maxHealth;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.modAttack = this.baseAttack;
        this.modDefense = this.baseDefense;

        isAlive = true;

        initialiseItems();
    }

    private void verifyConstructorInput(String name, int level, int health, int baseAttack, int baseDefense){
        if(name.length() <= 0){
            throw new IllegalArgumentException("Name of Improper Length provided!");
        }else if(level < 0){
            throw new IllegalArgumentException("Illegal level provided!");
        }else if(health <= 0){
            throw new IllegalArgumentException("Illegal health value provided!");
        }else if(baseAttack < 0){
            throw new IllegalArgumentException("Illegal attack value provided!");
        }else if(baseDefense < 0){
            throw new IllegalArgumentException("Illegal defense value provided!");
        }
    }

    private void initialiseItems(){
        currency = 0;
        inventory = new ItemList();
        equipment = new Item[10];
    }

    public void attack(Actor target){
        if(isAlive) {
            target.damage(NumTools.intLowerBorder(0, getModAttack(), -target.getModDefense()));
        }
    }

    public void use(Usable itemToUse){
        if (itemToUse.name.equals("health potion")){
            this.curHealth+=itemToUse.getValue();
            itemToUse.setValue(0);
        }

         if (itemToUse.name.equals("damage potion")){
            this.modAttack+=itemToUse.getValue();
            itemToUse.setValue(0);
         }

    }

    public void equip(Gear equipment){
        String type = equipment.getType();
        int slot;
        switch(type){
            case "MainWeapon":
                slot= 0;
                this.equipment[slot]=equipment;
                this.modAttack+=equipment.getDamage();
            case "SubWeapon":
                slot= 1;
                this.equipment[slot]=equipment;
                this.modAttack+=equipment.getDamage();
            case "Head":
                slot= 2;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
            case "Torso":
                slot= 3;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
            case "Leggings":
                slot= 4;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
            case "Boots":
                slot= 5;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
            case "Gloves":
                slot= 6;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
            case "Acc1":
                slot= 7;
                this.equipment[slot]=equipment;
            case "Acc2":
                slot= 8;
                this.equipment[slot]=equipment;

        }

    }

//    private int findProperSlot(String type){
//        switch(type){
//            case "MainWeapon":
//                return 0;
//            case "SubWeapon":
//                return 1;
//            case "Head":
//                return 2;
//            case "Torso":
//                return 3;
//            case "Leggings":
//                return 4;
//            case "Boots":
//                return 5;
//            case "Gloves":
//                return 6;
//            case "Acc1":
//                return 7;
//            case "Acc2":
//                return 8;
//
//        }
//        System.out.println("invalid type");
//        return 0;
//    }

    private void die(){
        setAlive(false);
    }

    public void resurrect(){
        setAlive(true);
        setCurHealth(getMaxHealth());
    }

    private void checkForDeath(){
        if(this.curHealth <= 0){
            die();
        }
    }

    public void damage(int healthDeduction){
        this.curHealth = NumTools.intClamp(0,this.maxHealth, this.curHealth, -healthDeduction);
        checkForDeath();
    }

    public void heal(int healthAddition){
        this.curHealth = NumTools.intClamp(0,this.maxHealth, this.curHealth, healthAddition);
    }

    public void setName(String newName){
        name = newName;
    }

    public void setLevel(int newLevel){
        level = newLevel;
    }

    public void setExperience(double newEXP){
        experience = newEXP;
    }

    public void grantExperience(double expAmount) throws IllegalArgumentException{
        if(expAmount < 0){
            throw new IllegalArgumentException("Invalid EXP amount provided!");
        }

        experience += expAmount;
        checkLevelUp();
    }

    public void grantCurrency(int currencyAmount) throws IllegalArgumentException{
        if(currencyAmount < 0){
            throw new IllegalArgumentException("What are you trying to do? STEAL my money?");
        }

        this.currency += currencyAmount;
    }

    public void levelDown(){
        this.level = this.level - 1;
    }

    public void levelUp(){
        this.level = this.level + 1;
    }

    public void checkLevelUp(){
        double curExperience =  experience - EXP_TO_LEVEL * (level-1);
        if (curExperience >= EXP_TO_LEVEL){
            int levelGain= 0;
            levelGain+= curExperience / EXP_TO_LEVEL;
            this.level+= levelGain;
            maxHealth+=levelGain*2;
            baseAttack+=levelGain;
            baseDefense+=levelGain;
        }

    }

    public void setMaxHealth(int newMaxHP){
        maxHealth = newMaxHP;
    }

    public void setCurHealth(int newCurHP){
        curHealth = newCurHP;
    }

    public void setBaseAttack(int newBaseATK){
        baseAttack = newBaseATK;
    }

    public void setBaseDefense(int newBaseDEF){
        baseDefense = newBaseDEF;
    }

    public void setModAttack(int newModATK){
        modAttack = newModATK;
    }

    public void setModDefense(int newModDEF){
        modDefense = newModDEF;
    }

    public void setCurrency(int newCurrency){
        currency = newCurrency;
    }

    public void setInventory(ItemList newInventory){
        this.inventory = newInventory;
    }

    public void setEquipment(Item[] newEquipment){
        this.equipment = newEquipment;
    }

    public void setAlive(boolean newState){
        isAlive = newState;
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    public double getExperience(){
        return experience;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public int getCurHealth(){
        return curHealth;
    }

    public int getBaseAttack(){
        return baseAttack;
    }

    public int getBaseDefense(){
        return baseDefense;
    }

    public ItemList getItems() { return items; }

    public int getCurrency() {
        return currency;
    }

    public void addToCurrency(int toAdd){
        this.currency+=toAdd;
    }
    public void subtractFromCurrency(int toTake) throws InsufficentCurrencyException {
        if((this.currency-toTake)<0){
            throw new InsufficentCurrencyException("You do not have sufficient funds.");
        }
        else {
            this.currency-=toTake;
        }
    }

    public int getModAttack(){
        return modAttack;
    }

    public int getModDefense(){
        return modDefense;
    }

    public boolean getAlive(){
        return isAlive;
    }

    public String displayAlive(){
        if(isAlive){
            return "";
        }else{
            return "DEAD";
        }
    }

    public String toString(){ //TODO show items
        String toString = "";
        toString += "\t[NAME][ " + this.name + " ]\n";
        toString += "\t[LVL][ " + this.level + " ]\n";
        toString += "\t[EXP][ " + this.experience + " ]\n";
        toString += "\n";
        toString += "\t[HP][ " + this.curHealth + "/" + this.maxHealth + " " + displayAlive() + " ]\n";
        toString += "\t[ATK][ " + this.baseAttack + " + " + (this.modAttack - this.baseAttack) + " ]\n";
        toString += "\t[DEF][ " + this.baseDefense + " + " + (this.modDefense - this.baseDefense) + " ]\n";
        toString += "\n";
        toString += "\t[CURRENCY][ " + this.currency + " Gold" + " ]\n";
        return toString;
    }
}
