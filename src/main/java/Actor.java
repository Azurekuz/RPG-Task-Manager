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
        equipment = new Item[10];
    }

    public int attack(Actor target){
        if(isAlive) {
            return target.damage(NumTools.intLowerBorder(0, getModAttack(), -target.getModDefense()));
        }
        return 0;
    }

    public void use(Item itemToUse){
        //TODO
    }

    public void equip(Item equipment){
        //TODO
    }

    private void findProperSlot(){
        //TODO
    }

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
            }
            //TODO stat gain?
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
        toString += "\t[NEXT][ " + (EXP_TO_LEVEL) + " ]\n";
        toString += "\n";
        toString += "\t[HP][ " + this.curHealth + "/" + this.maxHealth + " " + displayAlive() + " ]\n";
        toString += "\t[ATK][ " + this.baseAttack + " + " + (this.modAttack - this.baseAttack) + " ]\n";
        toString += "\t[DEF][ " + this.baseDefense + " + " + (this.modDefense - this.baseDefense) + " ]\n";
        toString += "\n";
        toString += "\t[CURRENCY][ " + this.currency + " Gold" + " ]\n";
        return toString;
    }
}
