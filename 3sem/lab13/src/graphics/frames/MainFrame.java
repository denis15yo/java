package graphics.frames;

import essenses.Toy;
import graphics.dialogs.ToyAddDialog;
import graphics.panels.DataPanel;
import graphics.panels.MapPanel;
import graphics.panels.ToyFilterPanel;
import graphics.interfaces.Updatable;
import models.ToysModel;
import myUtil.Exporter;
import myUtil.Functions;
import myUtil.IntPair;
import myUtil.Reader;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenuItem openMenu;
    private JMenuItem saveMenu;
    private JMenuItem addDataMenu;

    private DataPanel dataPanel;
    private ToyFilterPanel toyFilterPanel;
    private MapPanel mapPanel;

    private ToysModel model;
    private List<Updatable> listeners = new ArrayList<>();

    private Map<String, IntPair> map;

    public MainFrame() {
        super("Фильтр игрушек");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        fileMenu.add(openMenu);
        fileMenu.add(saveMenu);
        editMenu.add(addDataMenu);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Данные", dataPanel);
        tabbedPane.addTab("Фильтр", toyFilterPanel);
        tabbedPane.addTab("Map", mapPanel);

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
                    map.clear();
                    Map<String, IntPair> m = Functions.makeMap(model.getData());
                    m.forEach((l, r) -> map.put(l, r));
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

        saveMenu.addActionListener(e -> {
            FileDialog dlg = new FileDialog(this, "Сохранение", FileDialog.SAVE);
            dlg.setVisible(true);
            if(dlg.getFiles().length == 1){
                try {
                    Exporter.exportListOfToysToXML(model.getData(), dlg.getFiles()[0]);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка сохранения", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addDataMenu.addActionListener(e -> {
            ToyAddDialog dlg = new ToyAddDialog(this);
            dlg.setVisible(true);
            Toy addedToy = dlg.getAddedToy();
            if(addedToy != null){
                map.clear();
                model.add(addedToy);
                Map<String, IntPair> m = Functions.makeMap(model.getData());
                m.forEach((l, r) -> map.put(l, r));
                updateAll();
            }
        });

        setJMenuBar(menuBar);
        add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
        //setResizable(false);
    }

    private void initComponents(){
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        editMenu = new JMenu("Изменить");
        openMenu = new JMenuItem("Открыть");
        saveMenu = new JMenuItem("Сохранить");
        addDataMenu = new JMenuItem("Добавить");

        map = new TreeMap<>();
        model = new ToysModel();
        dataPanel = new DataPanel(model);
        toyFilterPanel = new ToyFilterPanel(model);
        mapPanel = new MapPanel(map);
        addListener(dataPanel);
        addListener(toyFilterPanel);
        addListener(mapPanel);
    }

    private void updateAll(){
        listeners.forEach(Updatable::update);
    }

    private void addListener(Updatable u){
        listeners.add(u);
    }
}
