public class Usable extends Item {
    public int value;

    public Usable(){
        super("",0);
    }

    public Usable(String name, int id, int value){
        super(name,id);
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
