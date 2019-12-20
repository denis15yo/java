package graphics.frames;

import essenses.Drink;
import graphics.panels.DrinksPanel;
import graphics.panels.MapPanel;
import graphics.panels.NamesPanel;
import graphics.panels.SortedDrinksPanel;
import models.DrinksModel;
import myUtil.Reader;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainFrame extends JFrame {
//    private DrinksModel drinksModel;
    private List<Drink> drinksModel;

    private DrinksPanel drinksPanel;
    private NamesPanel namesPanel;
    private SortedDrinksPanel sortedDrinksPanel;
    private MapPanel mapPanel;

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Файл");
    JMenu dataMenu = new JMenu("Данные");
    JMenuItem openMenu = new JMenuItem("Открыть");
    JMenuItem sortMenu = new JMenuItem("Сортировать");
    JMenuItem namesMenu = new JMenuItem("Названия");
    JMenuItem mapMenu = new JMenuItem("Map");

    public MainFrame(){
        super("Drinks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

//        drinksModel = new DrinksModel();
        drinksModel = new ArrayList<>();

        drinksPanel = new DrinksPanel(drinksModel);
        sortedDrinksPanel = new SortedDrinksPanel(drinksModel);
        namesPanel = new NamesPanel(drinksModel);
        mapPanel = new MapPanel(drinksModel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(drinksPanel, "Напитки");
        tabbedPane.add(sortedDrinksPanel, "Сортированные");
        tabbedPane.add(namesPanel, "Названия");
        tabbedPane.add(mapPanel, "Map");

        menuBar.add(fileMenu);
        menuBar.add(dataMenu);
        fileMenu.add(openMenu);
        dataMenu.add(sortMenu);
        dataMenu.add(namesMenu);
        dataMenu.add(mapMenu);

        openMenu.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileFilter fileFilter = new FileNameExtensionFilter("XML file", "xml");
            fileChooser.addChoosableFileFilter(fileFilter);
            fileChooser.setFileFilter(fileFilter);
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int res = fileChooser.showDialog(this, "Открыть");
            if(res == JFileChooser.APPROVE_OPTION){
                try {
//                    drinksModel.setData(Reader.readDrinksListFromXML(fileChooser.getSelectedFile()));
                    drinksModel.clear();
                    drinksModel.addAll(Reader.readDrinksListFromXML(fileChooser.getSelectedFile()));
                    tabbedPane.setSelectedComponent(drinksPanel);
                    drinksPanel.update();
                } catch (ParserConfigurationException | SAXException | IllegalArgumentException | IOException ex) {
                    JOptionPane.showMessageDialog(this, "Некорректные данные в файле", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        sortMenu.addActionListener(e -> {
            sortedDrinksPanel.update();
            tabbedPane.setSelectedComponent(sortedDrinksPanel);
        });
        namesMenu.addActionListener(e -> {
            namesPanel.update();
            tabbedPane.setSelectedComponent(namesPanel);
        });
        mapMenu.addActionListener(e -> {
            mapPanel.update();
            tabbedPane.setSelectedComponent(mapPanel);
        });

        setJMenuBar(menuBar);
        add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
    }
}
