package sciCalc;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class SciCalculator implements PropertyChangeListener {
    private JPanel mainPanel;
    private JTextArea historyTextArea;
    private JTextField formulaInput;
    private JList<Function> functionsList;
    private JButton evalButton;
    private JScrollPane scrollContainerPane;
    private JPanel secondPanel;
    Function function = new Function();
    Function functionMouse;

    {
        functionMouse = new Function("0","0");
    }

    public SciCalculator() throws HeadlessException {

        functionsList.setModel(Function.getDefaultListModel());              //podpiecie zawartosci do listy

        evalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {                                                       // pojawienie sie w historii wykonanego dzialania
                    historyTextArea.append(function.calculate(formulaInput.getText()));
                    formulaInput.setText("");
                }catch(Exception e1) {
                                     //wylapuje bledny format rownania
                   // JOptionPane.showMessageDialog(null,e1.getMessage(), "Message",JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(null,"Wrong syntax", "ERROR",JOptionPane.ERROR_MESSAGE);
                    formulaInput.setText("");
                }
            }
        });

        functionsList.addMouseListener(new MouseAdapter() {       // reakcja na klikniecie na liste
            @Override
            public void mouseClicked(MouseEvent event)
            {
                //pobieram index wybranego obiektu z listy
                int selected = functionsList.getSelectedIndex();
                // sprawdzenie czy lewy przycisk myszy i czy klikniete dwa razy
                if( event.getButton() == MouseEvent.BUTTON1 && event.getClickCount()==2 ){
                    // przypisuje krotka nazwe wybranego obiektu z listy
                    String shortForm = function.getDefaultListModel().getElementAt(selected).getShortName();
                    // pobiera pozycje jako index na ktorej jest klamra
                    int bracePosition = shortForm.indexOf("(");
                    if(bracePosition >= 0) {
                        // ustawienie pozycji kursora za pierwszym nawiasem
                        final int caretPosition = formulaInput.getCaretPosition() + bracePosition + 1;
                        try {
                                // jesli to jest ostatni element na liscie to pojawia mi sie ostatni zapamietany wynik
                            if (functionsList.getSelectedIndex() == functionsList.getLastVisibleIndex())
                                formulaInput.getDocument().insertString( formulaInput.getCaretPosition(),
                                        Double.toString(function.getLastResult()),null);
                            else   // inaczej pojawia sie krotka forma tego obiektu w miejscu kursora
                            formulaInput.getDocument().insertString(formulaInput.getCaretPosition(), shortForm, null);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                        formulaInput.requestFocus();                             // pobranie aktywnego pola
                        SwingUtilities.invokeLater(new Runnable() {              //tworzenie watku
                            @Override
                            public void run() {
                                formulaInput.setCaretPosition(caretPosition);    // ustawienie pozycji kursora
                            }
                        });
                    }
                     else {
                        try {
                            if (functionsList.getSelectedIndex() == functionsList.getLastVisibleIndex())
                                formulaInput.getDocument().insertString( formulaInput.getCaretPosition(),
                                        Double.toString(function.getLastResult()),null);
                            else
                            formulaInput.getDocument().insertString(formulaInput.getCaretPosition(), shortForm, null);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                        formulaInput.requestFocus();
                    }
                }
            }
        });

        formulaInput.addKeyListener(new KeyAdapter() {          // reakcja na enter oraz strzalke
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getExtendedKeyCode()) {
                    case KeyEvent.VK_UP:                      // strzalka w gore wyswietla ostatnie zapamietane wyrazenie
                        formulaInput.setText(function.getPreviousFormula());
                        break;
                    case KeyEvent.VK_ENTER:
                        try {                                // enter pojawia w historii wykonane dzialanie
                            historyTextArea.append(function.calculate(formulaInput.getText()));
                            formulaInput.setText("");
                        }catch(Exception e1)
                        {                   //wylapuje bledny format rownania
                            //JOptionPane.showMessageDialog(null,e1.getMessage(), "Message",JOptionPane.ERROR_MESSAGE);
                            JOptionPane.showMessageDialog(null,"Wrong syntax", "ERROR",JOptionPane.ERROR_MESSAGE);
                            formulaInput.setText("");

                        }
                }
            }
        });

    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextArea getHistoryTextArea() {
        return historyTextArea;
    }

    public JTextField getFormulaInput() {
        return formulaInput;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) { }
}
