package warehouse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class FulfillmentCenterContainerTest {

    FulfillmentCenterContainer fulfillmentCenterContainer;
    FulfillmentCenter fulfillmentCenter1 = new FulfillmentCenter("My container1",177.0 );
    FulfillmentCenter fulfillmentCenter2 = new FulfillmentCenter("My container2",177.0 );
    Item item1 = new Item("item1", ItemCondition.NEW, 12.0,2);
    Item item2 = new Item("item2", ItemCondition.NEW, 14.0,4);

    @Before
    public void setUp(){
        fulfillmentCenterContainer= new FulfillmentCenterContainer();
        fulfillmentCenterContainer.addCenter(fulfillmentCenter1);
        fulfillmentCenterContainer.addCenter(fulfillmentCenter2);
        fulfillmentCenter1.addProduct(item1);
        fulfillmentCenter1.addProduct(item2);
        fulfillmentCenter2.addProduct(item2);

    }
    @Test
    public void shouldFindEmptyWarehousesTest(){
        List<FulfillmentCenter> result;
        result= fulfillmentCenterContainer.findEmpty();
        List<FulfillmentCenter> result1 = new ArrayList<>();
        assertEquals(result,result1);
    }

    @Test
    public void shouldAddContainerTest(){
        int size = fulfillmentCenterContainer.containers.size();
        Item item = new Item("item", ItemCondition.NEW, 12.0,2);
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("My container",177.0 );
        fulfillmentCenter.addProduct(item);
        fulfillmentCenterContainer.addCenter(fulfillmentCenter);
        int added = fulfillmentCenterContainer.containers.size();

       // size++; 3 Equals
        assertNotEquals(size,added);
    }

    @Test
    public void shouldRemoveContainerTest(){
        int size ;
        Item item = new Item("item", ItemCondition.NEW, 12.0,2);
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("My container",177.0 );
        fulfillmentCenter.addProduct(item);
        fulfillmentCenterContainer.removeCenter("My container");
        //size--;
        size = fulfillmentCenterContainer.containers.size();
        assertEquals(size,2);
    }
}