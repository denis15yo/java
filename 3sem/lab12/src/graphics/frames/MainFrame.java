package graphics.frames;

import graphics.panels.ButtonsPanel;
import graphics.panels.TwoListsPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);


        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Two lists", new TwoListsPanel());
        tabbedPane.add("Buttons", new ButtonsPanel(10, 10));

        add(tabbedPane);

        pack();
    }
}
