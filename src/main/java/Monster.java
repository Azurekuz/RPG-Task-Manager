public class Monster extends Actor{
    Monster(){
        super("Monster", 1, 10, 3, 1);
        setCurrency(10);
    }

    Monster(String name, int level, int health, int baseAttack, int baseDefense, int currency) throws IllegalArgumentException{
        super(name, level, health, baseAttack, baseDefense);
        setCurrency(currency);
    }
}
