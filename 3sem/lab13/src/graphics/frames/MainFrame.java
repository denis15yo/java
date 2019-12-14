package graphics.frames;

import essenses.Toy;
import graphics.dialogs.ToyAddDialog;
import graphics.panels.ToyFilterPanel;
import models.ToysTableModel;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu dataMenu;
    private JMenuItem openMenu;
    private JMenuItem addDataMenu;

    private ToysTableModel model;
    private ToysTableModel FilteredToysModel;

    ToyFilterPanel toyFilterPanel = new ToyFilterPanel();
    public MainFrame() {
        super("Фильтр игрушек");
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
                FileDialog dlg = new FileDialog(this, "Открыть", FileDialog.LOAD);
                dlg.setFilenameFilter((dir, name) -> name.matches(".+\\.((txt)|(xml))"));
                dlg.setVisible(true);
                if(dlg.getFiles().length == 1){
                    File file = dlg.getFiles()[0];
                    if(file.getName().endsWith(".txt")){
                        toyFilterPanel.loadFromFile(file);
                    }
                    else{
                        toyFilterPanel.loadFromXML(file);
                    }

                }
            } catch(NumberFormatException | SAXException |ParserConfigurationException ex){
                JOptionPane.showMessageDialog(this, "Некорректные данные в файле",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка открытия файла",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

        });

        addDataMenu.addActionListener(e -> {
            ToyAddDialog dlg = new ToyAddDialog(this);
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
        fileMenu = new JMenu("Файл");
        dataMenu = new JMenu("Данные");
        openMenu = new JMenuItem("Открыть");
        addDataMenu = new JMenuItem("Добавить");
    }
}
