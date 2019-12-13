package graphics.frames;

import essenses.Toy;
import graphics.dialogs.AddToyDialog;
import graphics.panels.ToyFilterPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu dataMenu;
    JMenuItem openMenu;
    JMenuItem addDataMenu;

    ToyFilterPanel toyFilterPanel = new ToyFilterPanel();
    public MainFrame() {
        super("Toy Filter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 350);
        setResizable(false);

        initComponents();

        menuBar.add(fileMenu);
        menuBar.add(dataMenu);
        fileMenu.add(openMenu);
        dataMenu.add(addDataMenu);

        openMenu.addActionListener(e -> {
            try {
                FileDialog dlg = new FileDialog(this, "Open", FileDialog.LOAD);
                dlg.setFilenameFilter((dir, name) -> name.matches(".+\\.txt"));
                dlg.setVisible(true);
                if(dlg.getFiles().length == 1){
                    toyFilterPanel.loadFromFile(dlg.getFiles()[0]);
                }
            } catch (IOException ignored) {
                JOptionPane.showMessageDialog(null, "Opening Error",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        addDataMenu.addActionListener(e -> {
            AddToyDialog dlg = new AddToyDialog(this);
            dlg.setVisible(true);
            Toy addedToy = dlg.getAddedToy();
            if(addedToy != null){
                toyFilterPanel.addToy(dlg.getAddedToy());
            }
        });

        setJMenuBar(menuBar);
        add(toyFilterPanel);

        pack();
    }

    public void initComponents(){
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        dataMenu = new JMenu("Data");
        openMenu = new JMenuItem("Open");
        addDataMenu = new JMenuItem("Add");
    }
}
