import java.util.*;

public class FulfillmentCenterContainer {
    Map<String,FulfillmentCenter> containers ;

    public FulfillmentCenterContainer() {
       containers= new LinkedHashMap<>();
    }

    public void addCenter(String nameContainer, double amount) {
        containers.put(nameContainer, new FulfillmentCenter(nameContainer, amount));  //dodajemy nowy magazyn
    }

    public void addCenter(FulfillmentCenter container) {               // to samo tylko odrazu nasz obiekt
        containers.put(container.containerName, container);
        //System.out.println(containers);
    }

    public void removeCenter(String nameContainer) {
        if (containers.containsKey(nameContainer)){                  // jesli jest taki klucz kontenera to usuwa
        containers.remove(nameContainer);
        System.out.println("The container " + nameContainer + " has been removed ");
    }
        else
            System.out.println(" There is no container with name like " + nameContainer);
    }

   public List<FulfillmentCenter> findEmpty()
   {
       double usedSpace=0;
       List<FulfillmentCenter> result = new ArrayList<>();           // kopia listy
       for(String tmpKey : containers.keySet())  {                    // przechodze zastepczym kluczem po wszytskich kluczach
           usedSpace=0;
           for(Item element : containers.get(tmpKey).productList) {  // przechodze elementem po liscie produktow ; w mapie pod tym kluczem jest lista produktow
               usedSpace+=element.mass * element.amount;         // licze zuzyte miejsce dla danego magazyny
           }

           if(usedSpace == 0){                                    // jesli nie ma zajetego miejsca
               result.add(containers.get(tmpKey));                // to znaczy ze mamy pusty kontener
           }
       }
       return result;
   }

    public void summary()
    {
        for( FulfillmentCenter fulfillmentCenter : containers.values() )
            System.out.println("Container name: " + fulfillmentCenter.containerName + " Full in: "
                    + fulfillmentCenter.percent() + " %" );
    }

}
