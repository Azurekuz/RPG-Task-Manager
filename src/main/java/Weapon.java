public class Weapon extends Item {
    public int damage;

    public Weapon(){
        super("",0);
    }

    public Weapon(String name, int id, int damage){
        super(name,id);
        this.damage=damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
