package graphics.frames;

import graphics.panels.ToyFilterPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

import my_util.Reader;

public class MainFrame extends JFrame {
    ToyFilterPanel toyFilterPanel = new ToyFilterPanel();
    public MainFrame() {
        super("Toy Filter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenu = new JMenuItem("Open");

        menuBar.add(fileMenu);
        fileMenu.add(openMenu);

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

        setJMenuBar(menuBar);
        add(toyFilterPanel);

        pack();
    }
}
