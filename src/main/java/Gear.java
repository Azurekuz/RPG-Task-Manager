public class Gear extends Item {
    public int durability;
    public int damage;
    public int defense;

    public Gear(){
        super("",0);
    }

    public Gear(String name, int id, int durability){
        super(name,id);
        this.durability=durability;
    }

    public int getDurability() {
        return durability;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDurability(int damage) {
        this.durability = durability;
    }

}
