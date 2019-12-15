package graphics.frames;

import essenses.Toy;
import graphics.dialogs.ToyAddDialog;
import graphics.panels.DataPanel;
import graphics.panels.ToyFilterPanel;
import interfaces.Updatable;
import models.ToysModel;
import myUtil.Reader;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu dataMenu;
    private JMenuItem openMenu;
    private JMenuItem addDataMenu;

    private DataPanel dataPanel;
    private ToyFilterPanel toyFilterPanel;

    private ToysModel model;
    private List<Updatable> listeners = new ArrayList<>();

    public MainFrame() {
        super("Фильтр игрушек");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200, 350);
//        setResizable(false);

        initComponents();

        menuBar.add(fileMenu);
        menuBar.add(dataMenu);
        fileMenu.add(openMenu);
        dataMenu.add(addDataMenu);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Данные", dataPanel);
        tabbedPane.addTab("Фильтр", toyFilterPanel);


        openMenu.addActionListener(e -> {
            try {
                FileDialog dlg = new FileDialog(this, "Открыть", FileDialog.LOAD);
                dlg.setFilenameFilter((dir, name) -> name.matches(".+\\.((txt)|(xml))"));
                dlg.setVisible(true);
                if(dlg.getFiles().length == 1){
                    File file = dlg.getFiles()[0];
                    if(file.getName().endsWith(".txt")){
                        model.setData(Reader.readListOfToys(file));
                    }
                    else{
                        model.setData(Reader.readListOfToysFromXML(file));
                    }
                    updateAll();
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
                model.add(addedToy);
                updateAll();
            }
        });

        setJMenuBar(menuBar);
        add(tabbedPane);

        pack();
    }

    private void initComponents(){
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        dataMenu = new JMenu("Данные");
        openMenu = new JMenuItem("Открыть");
        addDataMenu = new JMenuItem("Добавить");

        model = new ToysModel();
        dataPanel = new DataPanel(model);
        toyFilterPanel = new ToyFilterPanel(model);
        addListener(dataPanel);
        addListener(toyFilterPanel);
    }

    private void updateAll(){
        listeners.forEach(Updatable::update);
    }

    private void addListener(Updatable u){
        listeners.add(u);
    }
}
