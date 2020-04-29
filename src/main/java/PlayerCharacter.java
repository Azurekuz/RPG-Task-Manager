public class PlayerCharacter extends Actor {

    private double exp;
    private ItemList inventory;
    private int level;

    public PlayerCharacter(){
        super(); //calls Actor constructor
        this.exp=0;
        this.level=1;
        this.inventory = new ItemList();
    }
    public PlayerCharacter(int exp, ItemList inventory, int[] stats, ItemList equipment, int level){
        super(stats, equipment);
        this.exp = exp;
        this.inventory = inventory;
        this.level=level;
    }

    public double getExp() { return exp; }
    public void addExp(double newExp){ exp+=newExp; }

    public void checklevel(){
        if (exp>=100){
            this.level++;
            this.exp=0;
        }
        return;
    }

    public ItemList getInventory() { return inventory; }

    public String toString(){
        //TODO include itemlists, title
        String result = "[PLAYER CHARACTER]{\n ";
        result=result.concat("[EXP]["+exp+"]\n");
        result=result.concat("[STATS] { [LEVEL]["+stats[0]+"] [HEALTH]["+stats[1]+"] [STRENGTH]["+stats[2]+"] [DEXTERITY]["+stats[3]+"] [INTELLIGENCE]["+stats[4]+"] }");
        return result;
    }
}
