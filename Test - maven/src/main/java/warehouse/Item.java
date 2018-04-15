package warehouse;
import java.util.Comparator;

public class Item implements Print, Comparable<Item> {
    String name;
    ItemCondition condition;
    Double mass;
    Integer amount;

    public Item(String name, ItemCondition condition, Double mass, Integer amount) {
        this.name = name;
        this.condition = condition;
        this.mass = mass;
        this.amount = amount;
    }

    public Item(Item item) {
        this.name = item.name;
        this.condition = item.condition;
        this.amount = item.amount;
        this.mass = item.mass;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Item first) {
        return this.name.compareTo(first.name);
    }

    @Override
    public void print() {
        if(this.amount == 0) {
            System.out.println(" There is nothing ");
        }
        else
            System.out.println(" Name: " + name + " Condition: " + condition + " Mass: " + mass + " Amount: " + amount);
    }
}
