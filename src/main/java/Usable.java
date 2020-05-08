import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("square")
public class Usable extends Item {
    public int value;

    public Usable(){
        super("","",0);
    }

    public Usable(String name, String type, int id, int value){
        super(name,type,id);
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
        result = "[NAME: "+ name+"]  [TYPE: "+type+"]  [VALUE: "+value+"]";
        return result;
    }

}
