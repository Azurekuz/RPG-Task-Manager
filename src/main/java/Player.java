public class Player extends Actor{
    Player(){
        super("Player", 1, 8, 3, 2);
    }

    Player(String name, int level, int health, int baseAttack, int baseDefense) throws IllegalArgumentException{
        super(name, level, health, baseAttack, baseDefense);
    }
}
