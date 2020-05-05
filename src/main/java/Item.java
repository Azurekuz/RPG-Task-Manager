public class Item {

    public String name;
    private int ID;
    public String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
