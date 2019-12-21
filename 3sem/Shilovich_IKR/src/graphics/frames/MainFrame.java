package graphics.frames;

import essenses.Seller;
import essenses.Worker;
import graphics.panels.*;
import myUtil.DoublePair;
import myUtil.Functions;
import myUtil.Reader;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainFrame extends JFrame {
    private List<Worker> workersModel;

    private ValuableWorkersPanel valuableWorkersPanel;
    private BummersPanel bummersPanel;
    private OrganizationsPanel organizationsPanel;

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Файл");
    JMenu dataMenu = new JMenu("Данные");
    JMenuItem valuableMenu = new JMenuItem("Ценные");
    JMenuItem bummerMenu = new JMenuItem("Лентяи");
    JMenuItem organizationsMenu = new JMenuItem("Организации");
    JMenuItem findMenu = new JMenuItem("Поиск");
    JMenuItem rangeMenu = new JMenuItem("Диапазон");

    JMenuItem openMenu = new JMenuItem("Открыть");

    String keyOrganization;
    Map<String, DoublePair> m;

    public MainFrame(){
        super("Drinks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        workersModel = new ArrayList<>();
        m = new TreeMap<>();

        valuableWorkersPanel = new ValuableWorkersPanel(workersModel);
        bummersPanel = new BummersPanel(workersModel);
        organizationsPanel = new OrganizationsPanel(workersModel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(valuableWorkersPanel, "Ценные");
        tabbedPane.add(bummersPanel, "Лентяи");
        tabbedPane.add(organizationsPanel, "Организации");

        menuBar.add(fileMenu);
        menuBar.add(dataMenu);
        fileMenu.add(openMenu);
        dataMenu.add(valuableMenu);
        dataMenu.add(bummerMenu);
        dataMenu.add(organizationsMenu);
        dataMenu.add(findMenu);
        dataMenu.add(rangeMenu);



        openMenu.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileFilter fileFilter = new FileNameExtensionFilter("XML file", "xml");
            fileChooser.addChoosableFileFilter(fileFilter);
            fileChooser.setFileFilter(fileFilter);
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int res = fileChooser.showOpenDialog(this);
            if(res == JFileChooser.APPROVE_OPTION){
                try {
                    workersModel.clear();
                    workersModel.addAll(Reader.readWorkersListFromXML(fileChooser.getSelectedFile()));
                    m = Functions.makeMap(workersModel);
                    JOptionPane.showMessageDialog(this, "Количество работников: " + workersModel.size(),
                            "Количество работников", JOptionPane.PLAIN_MESSAGE);
                } catch (ParserConfigurationException | SAXException | IllegalArgumentException | IOException ex) {
                    JOptionPane.showMessageDialog(this, "Некорректные данные в файле", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        valuableMenu.addActionListener(e -> {
            String organization = JOptionPane.showInputDialog(this, "Введите организацию:");
            if(organization != null){
                valuableWorkersPanel.setKeyOrganization(organization);
                valuableWorkersPanel.update();
                tabbedPane.setSelectedComponent(valuableWorkersPanel);
            }

        });
        bummerMenu.addActionListener(e -> {
            bummersPanel.update();
            tabbedPane.setSelectedComponent(bummersPanel);
        });
        organizationsMenu.addActionListener(e -> {
            organizationsPanel.update();
            tabbedPane.setSelectedComponent(organizationsPanel);
        });
        findMenu.addActionListener(e -> {
            if(!workersModel.isEmpty()){
                if(workersModel.get(0) instanceof Seller){
                    OptionalDouble min = workersModel.stream().mapToDouble(Worker::calcSalary).min();
                    OptionalDouble max = workersModel.stream().mapToDouble(Worker::calcSalary).max();
                    Double average = (min.getAsDouble() + max.getAsDouble()) / 2;
                    Optional<Worker> optionalWorker = workersModel.stream().filter(k -> k.calcSalary() == average).findFirst();
                    if(optionalWorker.isPresent()){
                        JOptionPane.showMessageDialog(null, optionalWorker.get(), "Работник", JOptionPane.PLAIN_MESSAGE);
                    }

                }
            }

        });
        rangeMenu.addActionListener(e -> {
            String organization = JOptionPane.showInputDialog(this, "Введите организацию:");
            if(organization != null){
                JOptionPane.showMessageDialog(this, m.get(organization), "Диапазон", JOptionPane.PLAIN_MESSAGE);
            }

        });

        setJMenuBar(menuBar);
        add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
    }


}
