import java.util.ArrayList;
import java.util.List;

public class Home {
    public static void main(String[] args) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter("My container",177.0 );
        Item item1 = new Item("itemA", ItemCondition.NEW, 12.0,2);
        Item item2 = new Item("zitemB", ItemCondition.REFURBISHED, 10.0,5);
        Item item3 = new Item("itemC", ItemCondition.USED, 8.0,4);
        Item item4 = new Item("itemD", ItemCondition.NEW, 9.0,3);
        Item item5 = new Item("itemE", ItemCondition.REFURBISHED, 20.0,1);
        Item item6 = new Item("itemF", ItemCondition.USED, 15.0,2);

        // b
        System.out.println("\n Compare item1 to zitem2 by name " + item1.compareTo(item2));
        item1.print();
        item2.print();

        // c i
        System.out.println("\n Adding items: ");
        fulfillmentCenter.addProduct(item1);    // maxMass 24
        fulfillmentCenter.addProduct(item1);    // 24
        fulfillmentCenter.addProduct(item2);    // 50
        fulfillmentCenter.addProduct(item3);    // 32
        fulfillmentCenter.addProduct(item4);    // 27
        fulfillmentCenter.addProduct(item5);    // 20 = 177
        fulfillmentCenter.summary();
        System.out.println(" Another one should not be possible to add");
        fulfillmentCenter.addProduct(item6);    // 9
        fulfillmentCenter.summary();

        // c ii
        System.out.println(" \n getProduct for item1. Now should by 3 elements like this");
        fulfillmentCenter.getProduct(item1);
        fulfillmentCenter.summary();

        // c iii
        System.out.println(" \n removeProduct for item3. ");
        try {
            fulfillmentCenter.removeProduct(item3);
            fulfillmentCenter.removeProduct(item3);
        }catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        fulfillmentCenter.summary();

        // c iv
        System.out.println("\nSearch product by name.  First zitem2 , second zitemB");
        fulfillmentCenter.search("zitem2").print();
        fulfillmentCenter.search("zitemB").print();

        // c v
        System.out.println("\n Find all products which have 'D' in its name");
        for (Item element : fulfillmentCenter.searchPartial("D"))
            element.print();


        // c vi
        System.out.println(" \n An amount of products with REFURBISHED state" );
        fulfillmentCenter.summary();
        fulfillmentCenter.countByCondition(ItemCondition.REFURBISHED);

        // c vii
        System.out.println("\n Summary to show everything which container contains");
        fulfillmentCenter.summary();

        // c viii
        System.out.println("\n Products sorted by name: ");
        for (Item element : fulfillmentCenter.sortByName())
            element.print();

        // c ix
        System.out.println("\n Products sorted by amount: ");
        for (Item element : fulfillmentCenter.sortByAmount())
            element.print();

        // c x
        System.out.println("\n Shows product with the biggest amount");
        fulfillmentCenter.max().print();

        // d i
        System.out.println("\n \n Adding new containers  ");
        FulfillmentCenterContainer fulfillmentCenterContainer = new FulfillmentCenterContainer();
        fulfillmentCenterContainer.addCenter("container1", 50.0);
        fulfillmentCenterContainer.addCenter("container2", 76.0);
        fulfillmentCenterContainer.addCenter("container3", 50.0);
        fulfillmentCenterContainer.addCenter("container4", 30.0);
        fulfillmentCenterContainer.addCenter(fulfillmentCenter);
        fulfillmentCenterContainer.summary();
        System.out.println("\n \n Adding some products to them ");
        fulfillmentCenterContainer.containers.get("container2").addProduct(item4);
        fulfillmentCenterContainer.containers.get("My container").addProduct(item4);
        fulfillmentCenterContainer.containers.get("container2").addProduct(item5);
        System.out.println(" This product should not be posibble to add to container2 ");
        fulfillmentCenterContainer.containers.get("container2").addProduct(item6);
        fulfillmentCenterContainer.summary();
        //System.out.println("\n Some operations on container2");
        //fulfillmentCenterContainer.containers.get("container2").getProduct(item4);
        //fulfillmentCenterContainer.containers.get("container2").removeProduct(item4);

        // d ii
        System.out.println(" \n Deleting containers ");
        fulfillmentCenterContainer.removeCenter("LA");
        fulfillmentCenterContainer.removeCenter("container1");
        fulfillmentCenterContainer.summary();

        // d iii
        System.out.println("\n List of empty containers ");
        List<FulfillmentCenter> result = new ArrayList<>();
        result = fulfillmentCenterContainer.findEmpty();
        for( FulfillmentCenter element : result) System.out.println(element.containerName);

        // d iv
        System.out.println("\n Information about containers");
        fulfillmentCenterContainer.summary();

    }
}
