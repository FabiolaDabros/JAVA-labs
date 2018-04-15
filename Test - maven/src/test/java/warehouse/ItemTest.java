package warehouse;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    Item item1;
    Item item2;
    Item item3;
    @Before
    public void setUp(){
        item1 = new Item("itemA", ItemCondition.NEW, 12.0,2);
        item2 = new Item("itemA", ItemCondition.REFURBISHED, 13.0,3);
        item3 = new Item("itemB", ItemCondition.REFURBISHED, 13.0,3);

    }

    @Test
    public void getAmountTest() {
        Integer result;
        result = item1.getAmount();
        assertEquals(result,(Integer)2);
    }

    @Test
    public void shouldBeCompareTwoTheSameItemsTestGood() {
        int result = item1.name.compareTo(item2.name);
        assertEquals(result, 0);
    }

    @Test
    public void shouldBeCompareTwoDifferentItemsTestGood() {
        int result = item1.name.compareTo(item3.name);
        assertNotEquals(result, 0);
    }

}