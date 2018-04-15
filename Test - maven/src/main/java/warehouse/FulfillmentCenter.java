package warehouse;
import java.util.*;

public class FulfillmentCenter {
    String containerName;
    List<Item> productList = new ArrayList<Item>();
    Double maxMass;
    double usedSpace=0;

    public FulfillmentCenter(String containerName, Double maxMass) {
        this.containerName = containerName;
        this.productList = new ArrayList<>();
        this.maxMass = maxMass;
    }

    public void addProduct(Item item) {
        double usedSpace=0;            // narazie nie ma zajetego miejsca
        for(Item element : productList) {              // dla kazdego elementu z listy produktow
            usedSpace += element.mass * element.amount;      // obliczamy ich zajete miejsce
        }

        if(maxMass >= usedSpace + item.mass*item.amount) {// maksymalna pojemnosc musi przekraczac zajete
            int index = productList.indexOf(item);         // miejsce przez te ktore juz sa plus ten ktory dodamy
            if (index == -1) {                              // jesli tego produktu nie ma
               if(item.amount>=0) {
                   productList.add(item);
                   System.out.println(" Added new item " + item.name);
               }
            } else {                                        // jest jest to pod tym indexem do tej ilosci
                if(item.amount>=0)
                    productList.get(index).amount += item.amount;  //  dodajemy ilosc tego co dodajemy
            }
        }
        else
            System.err.println(" You can't add the item - the list of products is full! ");
    }

    public void getProduct(Item item) {
        System.out.println(" Before amount of element " + item.amount);
        Iterator<Item> listIterator1 = productList.listIterator();   //przechodzimy po wszytskich
        while (listIterator1.hasNext()) {
            Item element = listIterator1.next();
            if (element.compareTo(item)==0) {                                  // sprawdzamy czy produkty sa rowne
                element.amount -= 1;                                        // jak tak to zmniejszamy jego ilosc o 1
                System.out.println(" After amount of element " + item.amount);

                if (element.amount == 0) {                                  // jesli teraz jego stan jest rowny 0 to usuwamy produkt
                    listIterator1.remove();
                }
                else
                    System.out.println(" There is a product " + item.name + " with an amount " + item.amount );
                break;
            }
            else {                                                       // jesli nie byly rowne oznacza to
                System.out.println(" There is any product like this ");   // ze nie ma wgl takiego produktu
                break;
            }
        }
    }

    public boolean removeProduct(Item item) throws IllegalArgumentException{
        boolean firstItem = false;
        ListIterator<Item> listIterator1 = productList.listIterator();
        while (listIterator1.hasNext()) {
            Item element = listIterator1.next();
            if (element.equals(item)) {         // jesli napotka rowny element to do usuwa
                listIterator1.remove();
                firstItem=true;
                System.out.println(" Product " + item.name + " has been removed ");
            }
        }
        if(firstItem==false)
            throw new IllegalArgumentException(" There is any product like this ");
        return true;
    }

    public Item search(String productName) {
        Item item;
        Collections.sort(productList, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return i1.name.compareTo(i2.name);
            }
        });

        int index = Collections.binarySearch(productList, new Item(productName,
                null,null,null), new Comparator<Item>() {
            public int compare(Item i1, Item i2) {
                return i1.name.compareTo(i2.name);
            }
        });

        if(index>-1){   // jesli jest to zwraca
            item=productList.get(index);
        }
        else {
            System.out.println("No found this item");
            item = new Item(productName, null, null, 0);
        }
        return item;

//        Item searchName;                          // imie ktorego szukam
//        List<Item> copyList = new ArrayList<>();    // kopia listy aby ja posortowac
//        for(Item element : productList) {
//            Item copy = new Item(element);
//             copy.name=  copy.name.toLowerCase();    // zmiana na male
//            copyList.add(copy);
//        }
//        Collections.sort(copyList);                 // poortowanie listy
//
//        //stworzenie indexu
//        int index = Collections.binarySearch(copyList, new Item(productName, null, null, null),
//                new Comparator<Item> () {
//                    @Override
//                    public int compare(Item o1, Item o2) {
//                        return o1.compareTo(o2);
//                    }
//                });
//        if(index>=0)
//            searchName = copyList.get(index);
//        else
//            searchName=new Item(null,null,null,0);
//        return searchName;
    }

    public List<Item> searchPartial(String partialProductName) {
        ArrayList<Item> productsName = new ArrayList<>();        // tworzymy nowa liste
        partialProductName = partialProductName.toLowerCase();   // nazwy zamieniamy na male litery
        for(Item element : productList) {                        // przechodzimy po wszytskich elementach pierwotnej listy
            String lowerName = element.name.toLowerCase();
            if (lowerName.contains(partialProductName))           // jesli element zawiera jakas czesc nazwy to dodaje sie do naszej listy
                productsName.add(element);
        }
        return productsName;
    }

    public int countByCondition(ItemCondition itemCondition) {
        int counter=0;
        ListIterator<Item> listIterator1 = productList.listIterator();
        while (listIterator1.hasNext()) {                              // przechodzimy iteratorem po produktach
            Item element = listIterator1.next();
            if (element.condition.equals(itemCondition)) {             // jesli stan elementu jest rowny z jakims produktem
                counter+=element.amount;                               // to zwiekszamy licznik o ilosc tych elemntow
            }
        }
        System.out.println(counter);
        return counter;
    }

    public void summary() {
        for(Item element : productList) {             // dla kazdego elementu z naszej listy wywolujemy metode print
            element.print();                          // ktora z klasy Item pokazuje nam wszytskie informacje o produktach
        }
    }

    public List<Item> sortByName() {
//        List<Item> sortedName = new ArrayList<>();     // tworzymy nowa liste ktora bedzie kopia tamtej
//        for(Item element : productList)
//            sortedName.add(new Item(element));

        Collections.sort(productList);
        return productList;
    }

    public List<Item> sortByAmount() {
//        List<Item> sortedAmount = new ArrayList<>();     // tworzymy nowa liste ktora bedzie kopia tamtej
//        for(Item element : productList)
//            sortedAmount.add(new Item(element));
        Collections.sort(productList, new NumberComparator());    // sortujemy nasza liste przy uzyciu stworzonego komparatora

        return productList;
    }

    public Item max() {
        return Collections.max(productList);
        //return Collections.max(productList, Collections.reverseOrder(new NumberComparator()));
    }

    public double percent() {
        double space= 0.0;
        for(Item element : productList)
            space +=  element.mass * element.amount;
        return (space/maxMass)*100.0;
    }
}
