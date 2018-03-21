package sciCalc;
import java.awt.*;

public class Home {                                   // uruchomienie ramki oraz listy
    public static void main(String[] args) {
        new MyFrame();
        Function function = new Function();
        function.addFunction();
    }
}
