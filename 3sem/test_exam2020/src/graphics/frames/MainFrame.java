package graphics.frames;

import graphics.panels.DrinksPanel;
import graphics.panels.MapPanel;
import graphics.panels.NamesPanel;
import graphics.panels.SortedDrinksPanel;
import models.DrinksModel;
import myUtil.Reader;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private DrinksModel drinksModel;

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

        drinksModel = new DrinksModel();

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
            int res = fileChooser.showDialog(this, "Открыть");
            if(res == JFileChooser.APPROVE_OPTION){
                try {
                    drinksModel.setData(Reader.readDrinksListFromXML(fileChooser.getSelectedFile()));
                    tabbedPane.setSelectedComponent(drinksPanel);
                    drinksPanel.update();
                } catch (ParserConfigurationException | IOException | SAXException ex) {
                    JOptionPane.showMessageDialog(this, "Некорректный файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
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
