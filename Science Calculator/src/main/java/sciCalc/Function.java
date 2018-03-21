package sciCalc;

import javax.swing.*;
import org.mariuszgromada.math.mxparser.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.text.MessageFormat;

public class Function {                  // klasa potrzebna do listy
    private String name;
    private String shortName;
    private static DefaultListModel<Function> defaultListModel;
    private String previousFormula;
    private PropertyChangeSupport propertyChange
            = new PropertyChangeSupport(this);
    private double lastResult=0.0;

    static{                                                     // tworzenie nowego obiektu listy
        defaultListModel= new DefaultListModel<Function>();
    }

    public Function(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public Function() {}

    public void addFunction(){                                                          //dodanie elementow do listy
        defaultListModel.addElement(new Function("sine", "sin()"));
        defaultListModel.addElement(new Function("cosine", "cos()"));
        defaultListModel.addElement(new Function("tangent", "tan()"));
        defaultListModel.addElement(new Function("logarithm", "log(,)"));
        defaultListModel.addElement(new Function("modulo", "mod(,)"));
        defaultListModel.addElement(new Function("Euler number", "Euler(,)"));
        defaultListModel.addElement(new Function("Ludolph's number", "pi"));
        defaultListModel.addElement(new Function("base of Natural logarithm", "e"));
        defaultListModel.addElement(new Function("Plastic constant", "[PN]"));
        defaultListModel.addElement(new Function("Last result", "0"));
    }

    public static DefaultListModel<Function> getDefaultListModel() {
        return defaultListModel;
    }

    public String calculate(String phrase) throws Exception {
        Expression expression = new Expression(phrase);
        if (expression.checkSyntax()) {                             // jesli wyrazenie jest poprawne logicznie
                Double result = expression.calculate();
                previousFormula = phrase;                          // zapamietanie podanego wyrazenia
                lastResult=result;                                 // zapamietanie wyniku
                String historyFormula = MessageFormat.format("{0}\n \t = {1} \n ----- \n", phrase, result);
                propertyChange.firePropertyChange(new PropertyChangeEvent
                        (this, "historyFormula", null, historyFormula));
                return historyFormula;
        }
        else{                                                      // w przeciwnym razie rzuca wyjatek
            String errorMessage = expression.getErrorMessage();
            throw new Exception(errorMessage);
           // throw new Exception("Wrong syntax!");
        }
    }

    public double getLastResult(){
        return lastResult;
    }

    public String getPreviousFormula() {
        return previousFormula;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {          // dzieki temu na liscie pojawia sie tylko nazwa
        return name;
    }
}
