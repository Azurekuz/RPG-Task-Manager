import com.fasterxml.jackson.annotation.JsonIgnore;

public class Actor {
    @JsonIgnore

    private String name;
    private int level;

    final double BASE_EXP = 100.0;
    private double EXP_TO_LEVEL;
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
        EXP_TO_LEVEL = (int) (BASE_EXP * Math.pow(1.15, ((double) this.level-1)));
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
        EXP_TO_LEVEL = (int) (BASE_EXP * Math.pow(1.15, ((double) this.level-1)));
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
        equipment = new Item[9];
        //MainWeapon, SubWeapon, Head, Torso, Leggings, Boots, Gloves, Acc1, Acc2
        //String name, String type, int id, int durability, int damage, int defense
        equipment[0] = new Gear("Fist","MainWeapon",0, 100, 0, 0);
        equipment[1] = new Gear("Fist","SubWeapon",1, 100, 0, 0);
        equipment[2] = new Gear("Hair","Head",2, 100, 0, 0);
        equipment[3] = new Gear("T-Shirt","Torso",3, 100, 0, 0);
        equipment[4] = new Gear("Underwear","Leggings",4, 100, 0, 0);
        equipment[5] = new Gear("Bare Feet","Boots",5, 100, 0, 0);
        equipment[6] = new Gear("Hands","Gloves",6, 100, 0, 0);
        equipment[7] = new Gear("N/A","Acc1",7, 100, 0, 0);
        equipment[8] = new Gear("N/A","Acc2",8, 100, 0, 0);


    }

    public int attack(Actor target){
        if(isAlive) {
            return target.damage(NumTools.intLowerBorder(0, getModAttack(), -target.getModDefense()));
        }
        return 0;
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
                break;
            case "SubWeapon":
                slot= 1;
                this.equipment[slot]=equipment;
                this.modAttack+=equipment.getDamage();
                break;
            case "Head":
                slot= 2;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
                break;
            case "Torso":
                slot= 3;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
                break;
            case "Leggings":
                slot= 4;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
                break;
            case "Boots":
                slot= 5;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
                break;
            case "Gloves":
                slot= 6;
                this.equipment[slot]=equipment;
                this.modDefense+=equipment.getDefense();
                break;
            case "Acc1":
                slot= 7;
                this.equipment[slot]=equipment;
                break;
            case "Acc2":
                slot= 8;
                this.equipment[slot]=equipment;
                break;
            default:
                System.out.println("[ERROR][Equip failed, invalid type?]");
        }

    }

    public void pickUp(Item newItem){
        inventory.addItem(newItem);
    }

    public void die(){
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

    public int damage(int healthDeduction){
        this.curHealth = NumTools.intClamp(0,this.maxHealth, this.curHealth, -healthDeduction);
        checkForDeath();
        return healthDeduction;
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
        double curExperience =  experience - EXP_TO_LEVEL;
        if (curExperience >= EXP_TO_LEVEL){
            int levelGain= 0;
            levelGain+= curExperience / EXP_TO_LEVEL;
            for(int levelUp = 0; levelUp < levelGain; levelUp++){
                this.level+= 1;
                EXP_TO_LEVEL += ((int) (BASE_EXP * Math.pow(1.15, ((double) this.level-1))));
                System.out.println("NEXT: " + EXP_TO_LEVEL);
                maxHealth+=levelGain*2;
                baseAttack+=levelGain;
                baseDefense+=levelGain;
            }
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

    public int getCurrency() {
        return currency;
    }

    public ItemList getInventory() { return inventory; }

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

    public String toString(){
        String toString = "";
        toString += "\t[NAME][ " + this.name + " ]\n";
        toString += "\t[LVL][ " + this.level + " ]\n";
        toString += "\t[EXP][ " + this.experience + " ]\n";
        toString += "\t[NEXT][ " + (EXP_TO_LEVEL) + " ]\n";
        toString += "\n";
        toString += "\t[HP][ " + this.curHealth + "/" + this.maxHealth + " " + displayAlive() + " ]\n";
        toString += "\t[ATK][ " + this.baseAttack + " + " + (this.modAttack - this.baseAttack) + " ]\n";
        toString += "\t[DEF][ " + this.baseDefense + " + " + (this.modDefense - this.baseDefense) + " ]\n";
        toString += "\n";
        toString += "\t[CURRENCY][ " + this.currency + " Gold" + " ]\n";
        toString += "\t[EQUIPMENT]" +
                "\n\t\t[Main Weapon: " + this.equipment[0].toString() + "]" +
                "\n\t\t[Sub Weapon:  " + this.equipment[1].toString() + "]" +
                "\n\t\t[Head:   " + this.equipment[2].toString() + "]" +
                "\n\t\t[Torso:  " + this.equipment[3].toString() + "] " +
                "\n\t\t[Legs:   " + this.equipment[4].toString() + "]" +
                "\n\t\t[Boots:  " + this.equipment[5].toString() + "]" +
                "\n\t\t[Gloves: " + this.equipment[6].toString() + "]" +
                "\n\t\t[Acc1:   " + this.equipment[7].toString() + "]" +
                "\n\t\t[Acc2:   " + this.equipment[8].toString() + "]";

        toString += "\n\t[INVENTORY]\n\t" + this.inventory.toString();
        return toString;
    }
}
