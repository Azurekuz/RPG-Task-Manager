import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("usable")
public class Usable extends Item {
    public int value;

    public Usable(){
        super("","",0, 0);
    }

    public Usable(String name, String type, int id, int value, int worth){
        super(name,type,id, worth);
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString(){
        String result = ""; //TODO add id if it matters
        result = "[NAME: "+ name+"]  [TYPE: "+type+"]  [WORTH: " + worth+"]  [VALUE: "+value+"]";
        return result;
    }

}
