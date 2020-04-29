import java.lang.*;

public class Actor {
    public int[] stats; //size=5, 0:level 1: hp 2:strength 3:dexterity 4:intelligence TODO more stats? define constants for each stat index?
            //TODO if we don't have more than like five stats then it's probably worth ditching the array idea

    public ItemList items;
    int currency =0;

    public Actor(){
        this.stats = new int[5];
        items = new ItemList();
    }
    public Actor(int[] stats, ItemList items){
        this.stats = stats;
        this.items = items;
    }

    public int[] getStats() { return stats; }

    public ItemList getItems() { return items; }

    public int getCurrency() {
        return currency;
    }

    public void addToCurrency(int toAdd){
        this.currency= this.currency+toAdd;
    }
    public void subtractFromCurrency(int toTake){
        if((this.currency-toTake)>0){
            throw new InsufficentCurrencyException("We are afraid you do not have sufficent funds");
        }
        else {
            this.currency = this.currency + toTake;
        }
    }

}
