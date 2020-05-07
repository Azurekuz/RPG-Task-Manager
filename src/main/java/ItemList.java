import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Random;

public class ItemList {
    public ArrayList<Item> itemList;

    public ItemList() {
        itemList = new ArrayList<Item>();
    }
    public ItemList(ArrayList<Item> listIn){ itemList = listIn; }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void addItem(String name, String type, int id) {
        Item newItem = new Item(name, type, id);
        itemList.add(newItem);
    }

    public void removeItem(int id) {
        for (int i = 0; i < itemList.size(); i++) {
            if (id==(itemList.get(i).getID())) {
                itemList.remove(i);
                return;
            }
        }
        System.out.println("No item found");

    }

    public String toString(){
        String result = "{ Name \t|\t Type \t |\t Damage/Value \t |\t Defense }\n";
        if (itemList.size() == 0){
            return result.concat("\t*No items.*\n");
        }
        for(int i = 0; i < itemList.size(); i++){
            result = result.concat(itemList.get(i).toString());
            result = result.concat("\n");
        }
        return result;
    }

    @JsonIgnore
    public Item getRandomItem() throws NonExistentObjectException{
        if (itemList.size() == 0){
            throw new NonExistentObjectException("Empty ActorList");
        }
        Random random = new Random();
        int randint = random.nextInt(itemList.size());
        return itemList.get(randint);
    }

    public boolean contains(Item item){
        return itemList.contains(item);
    }

    @JsonIgnore
    public int getSize(){
        return itemList.size();
    }

    public Item getItemAt(int index) throws IllegalArgumentException{
        if (index < 0 || index > itemList.size()){
            throw new IllegalArgumentException("Invalid index");
        }
        return itemList.get(index);
    }


}
