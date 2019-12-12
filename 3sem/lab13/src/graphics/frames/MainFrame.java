package graphics.frames;

import graphics.dialogs.AddToyDialog;
import graphics.panels.ToyFilterPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    ToyFilterPanel toyFilterPanel = new ToyFilterPanel();
    public MainFrame() {
        super("Toy Filter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300, 300);
//        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu dataMenu = new JMenu("Data");
        JMenuItem openMenu = new JMenuItem("Open");
        JMenuItem addDataMenu = new JMenuItem("Add");

        menuBar.add(fileMenu);
        menuBar.add(dataMenu);
        fileMenu.add(openMenu);
        dataMenu.add(addDataMenu);

        openMenu.addActionListener(e -> {
            try {
                FileDialog dlg = new FileDialog(this, "Open", FileDialog.LOAD);
                dlg.setFilenameFilter((dir, name) -> name.matches(".+\\.txt"));
                dlg.setVisible(true);
                toyFilterPanel.loadFromFile(dlg.getFiles()[0]);
            } catch (IOException ignored) {
                JOptionPane.showMessageDialog(null, "Opening Error",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        addDataMenu.addActionListener(e -> {
            AddToyDialog dlg = new AddToyDialog(this);
            dlg.execute();
            toyFilterPanel.addToy(dlg.getAddedToy());
        });

        setJMenuBar(menuBar);
        add(toyFilterPanel);

        pack();
    }
}
