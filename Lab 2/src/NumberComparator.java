import java.util.Comparator;

public class NumberComparator implements Comparator<Item>{


    @Override
    public int compare(Item o1, Item o2) {
        int amount=o1.getAmount() - o2.getAmount();          // porownuje ilosc w kierunku malejacym
        if(amount != 0)
            return o2.compareTo(o1);
        return amount;
    }
}
