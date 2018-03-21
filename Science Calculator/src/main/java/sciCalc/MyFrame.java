package sciCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {                    // klasa odpowiedzialna za stworzenie calej ramki
    final JMenuBar menuBar = new JMenuBar();
    JMenu optionsMenu = new JMenu("Options");
    JMenuItem resetMenuItem = new JMenuItem("Reset");
    JMenuItem exitMenuItem = new JMenuItem("Exit");

    SciCalculator mySci = new SciCalculator();

    public MyFrame() throws HeadlessException {
        super("SciCalculator");                        // konstruktor nadklasy w ktorym ustawiamy tytul naszego okna
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // zamkniecie kalkulatora przez "X"
        pack();                                             // automatyczne dopasowanie do rozmiarow dodanego komponentu i zdefiniowanego
        setSize(600, 600);
        setLocation(50,50);
        add(mySci.getMainPanel());                          // dodanie naszego panelu
        optionsMenu.add(resetMenuItem);
        optionsMenu.add(exitMenuItem);
        menuBar.add(optionsMenu);
        setVisible(true);

        exitMenuItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });

    resetMenuItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mySci.getFormulaInput().setText("");
            mySci.getHistoryTextArea().setText("");
        }
    });

}
}
