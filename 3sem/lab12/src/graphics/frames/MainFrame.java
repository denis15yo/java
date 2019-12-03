package graphics.frames;

import graphics.panels.ButtonsPanel;
import graphics.panels.RadioPanel;
import graphics.panels.TwoListsPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Two lists", new TwoListsPanel());
        tabbedPane.add("Buttons", new ButtonsPanel(5, 5));
        tabbedPane.add("Radio", new RadioPanel());

        add(tabbedPane);

        pack();
    }
}
