package graphics.frames;

import graphics.panels.ButtonsPanel;
import graphics.panels.RadioPanel;
import graphics.panels.TwoListsPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Panels");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(500, 200);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Two lists", new TwoListsPanel());
        tabbedPane.addTab("Buttons", new ButtonsPanel(5, 5));
        tabbedPane.addTab("Radio", new RadioPanel());

        add(tabbedPane);

        pack();
    }
}
