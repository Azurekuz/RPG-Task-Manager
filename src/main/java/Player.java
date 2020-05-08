public class Player extends Actor{
    Player(){
        super("Player", 1, 8, 3, 2);
    }

    Player(String name, int level, int health, int baseAttack, int baseDefense) throws IllegalArgumentException{
        super(name, level, health, baseAttack, baseDefense);
    }

    public void damage(int healthDeduction){
        setCurHealth(NumTools.intClamp(0, getMaxHealth(), getCurHealth(), -healthDeduction));
        checkForDeath();
    }

    private void checkForDeath(){
        if(getCurHealth() <= 0){
            this.die();
        }
    }

    public void die(){
        setAlive(false);
        //setCurrency((int) (((double) getCurrency())*0.75)); //currency penalty already present in combatUI
    }
}
