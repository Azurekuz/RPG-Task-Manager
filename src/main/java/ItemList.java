import java.util.ArrayList;
public class ItemList {
    public ArrayList<Item> itemList;

    ItemList() {
        itemList = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void addItem(String name, int id) {
        Item newItem = new Item(name,id);
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

}
