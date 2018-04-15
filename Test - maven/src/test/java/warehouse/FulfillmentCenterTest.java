package warehouse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.lang.IllegalArgumentException;
import static org.junit.Assert.*;
import javax.tools.ToolProvider.*;


public class FulfillmentCenterTest {

    private Item item1;
    private Item item2;
    FulfillmentCenter fulfillmentCenter;
    @Before
    public void setUp(){
        fulfillmentCenter= new FulfillmentCenter("containerName",200.0);
        item1 = new Item("item1", ItemCondition.NEW, 12.0,2);
        item2 = new Item("item2", ItemCondition.NEW, 14.0,4);
        fulfillmentCenter.addProduct(item1);
        fulfillmentCenter.addProduct(item2);
    }

    @Test
    public void shouldAddItemTest(){
        int size = fulfillmentCenter.productList.size();
        Item item = new Item("item", ItemCondition.NEW, 12.0,3);
        fulfillmentCenter.addProduct(item);
        int added = fulfillmentCenter.productList.size();
        //size++;

        assertNotEquals(size,added);
    }
    @Test
    public void shouldNotAddItemTest(){
        int size = fulfillmentCenter.productList.size();
        Item item = new Item("item", ItemCondition.NEW, 12.0,-1);
        fulfillmentCenter.addProduct(item);
        int added = fulfillmentCenter.productList.size();
        //size++;

        assertEquals(size,added);
    }
    @Test
    public void shouldGetOnceItemTest(){
        int amount = item1.amount;
        fulfillmentCenter.getProduct(item1);
        int amount2 = item1.amount;
        assertNotEquals(amount,amount2);
    }
    @Test
    public void shouldGetItemToRemoveTest(){
        int amount ;
        fulfillmentCenter.getProduct(item1);
        fulfillmentCenter.getProduct(item1);
        amount = item1.amount;
        assertEquals(amount,0);
    }
    @Test
    public void shouldSearchItemNameTest(){
            Item itemTest = fulfillmentCenter.search("item2");
            assertEquals(itemTest, item2);
    }
    @Test
    public void shouldSearchPartialItemTest(){
        List<Item> result;
        result=fulfillmentCenter.searchPartial("it");
        assertEquals(result.get(0),item1);
    }
    @Test
    public void shouldCountByConditionTestBeGood(){
     int result = fulfillmentCenter.countByCondition(ItemCondition.NEW);
     assertEquals(result,6);
    }
    @Test
    public void shouldCountByConditionTestBeBad(){
        int result = fulfillmentCenter.countByCondition(ItemCondition.NEW);
        assertNotEquals(result,3);
    }

    @Test
    public void shouldListBeSortedByNameTest(){
        List<Item> result = new ArrayList<>();
        result=fulfillmentCenter.sortByName();
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(item1);
        sortedList.add(item2);

        assertEquals(result,sortedList);
    }

    @Test
    public void shouldListBeSortedByAmount(){
        List<Item> result;
        result = fulfillmentCenter.sortByAmount();
        List<Item> sortedList = new ArrayList<>();
        sortedList.add(item2);
        sortedList.add(item1);
        assertEquals(result,sortedList);

    }

    @Test
    public void shouldReturnMaxItem(){
        Item result;
        result=fulfillmentCenter.max();
        assertEquals(result,item2);
    }
    @Test
    public void shouldNotReturnMaxItem(){
        Item result;
        result=fulfillmentCenter.max();
        assertNotEquals(result,item1);
    }
    @Test(expected = IllegalArgumentException.class)
   public void shouldThrownIllegalArgumentExceptionWhileDeleteTheSameItems(){
        assertTrue(fulfillmentCenter.removeProduct(item1));
        fulfillmentCenter.removeProduct(item1);
    }
    @Test
    public void shouldRemovedItemTest(){
        assertTrue(fulfillmentCenter.removeProduct(item1));
    }
    @Test
    public void shouldPercentOfAmountBeCountedGood(){
        double percent = fulfillmentCenter.percent();
        assertEquals(percent,40.0,0);
    }
}