import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type") @JsonSubTypes({

        @JsonSubTypes.Type(value = Gear.class, name = "gear"),
        @JsonSubTypes.Type(value = Usable.class, name = "usable")
})
public class Item {

    public String name;
    private int ID;
    //MainWeapon, SubWeapon, Head, Torso, Leggings, Boots, Gloves, Acc1, Acc2
    public String type;
    public int worth;

    public Item(){
        name="";
        type="";
        ID=0;
        worth=0;
    }

    public Item(String name, String type, int id, int worth){
        this.name=name;
        this.type=type;
        this.ID=id;
        this.worth=worth;
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

    public int getWorth() { return worth; }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setWorth(int worth) { this.worth = worth; }

    public String toString(){
        String result = ""; //TODO add durability and id if they matter
        result = "[NAME: "+ name+"]  [TYPE: "+type+"]  [WORTH: " + worth+"]";
        return result;
    }
}
