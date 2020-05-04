public class Item {

    public String name;
    private int ID;

    public Item(){
        name=" ";
        ID=0;
    }

    public Item(String name, int id){
        this.name=name;
        this.ID=id;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
