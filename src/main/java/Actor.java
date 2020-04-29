public class Actor {
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

    /*public int[] stats; //size=5, 0:level 1: hp 2:strength 3:dexterity 4:intelligence TODO more stats? define constants for each stat index?
            //TODO if we don't have more than like five stats then it's probably worth ditching the array idea
    public ItemList items;*/

    public Actor(){
        name = "Empty Husk";
        level = 0;
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
            target.damage(NumTools.intLowerBorder(0, this.modAttack, -target.getModDefense()));
        }
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

    private void resurrect(){
        setAlive(true);
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

    public int getModAttack(){
        return modAttack;
    }

    public int getModDefense(){
        return modDefense;
    }

    public int getCurrency(){
        return currency;
    }

    public boolean getAlive(){
        return isAlive;
    }
}