public class PlayerCharacter{

    private double exp;
    //private ItemList inventory;
    private int level;

    public PlayerCharacter(){
        //super(); //calls Actor constructor
        this.exp=0;
        this.level=1;
        //this.inventory = new ItemList();
    }
    public PlayerCharacter(int exp, ItemList inventory, int level){
       // super();
        this.exp = exp;
        //this.inventory = inventory;
        this.level=level;
    }

    public double getExp() { return exp; }
    public void addExp(double newExp){ exp+=newExp; }

    public double getLevel(){ return level; }
    public void checklevel(){
        if (exp>=100){
            this.level+=exp/100;
            this.exp=exp % 100;
        }
    }

    //public ItemList getInventory() { return inventory; }

    public String toString(){
        //TODO include itemlists, title
        String result = "[PLAYER CHARACTER]{\n ";
        result=result.concat("[LEVEL: "+level+"][EXP: "+exp+"]\n");
        return result;
    }
}
