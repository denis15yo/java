package graphics.frames;

import essenses.Worker;
import graphics.dialog.ShowWorkerDialog;
import graphics.interfaces.Updatable;
import graphics.panels.*;
import models.WorkersModel;
import myUtil.Reader;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {
    private DataPanel dataPanel;
    private SortedByPostSurnamePanel sortedByPostSurnamePanel;
    private SortedBySalaryPanel sortedBySalaryPanel;
    private SurnamesPanel surnamesPanel;
    private PostsPanel postsPanel;

    private WorkersModel workersModel;
    private Map<String, Integer> map;

    private List<Updatable> listeners = new ArrayList<>();

    public MainFrame(){
        super("exam");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 320));

        workersModel = new WorkersModel();
        map = new HashMap<>();
        dataPanel = new DataPanel(workersModel);
        sortedByPostSurnamePanel = new SortedByPostSurnamePanel(workersModel);
        sortedBySalaryPanel = new SortedBySalaryPanel(workersModel);
        surnamesPanel = new SurnamesPanel(workersModel);
        postsPanel = new PostsPanel(workersModel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Данные", dataPanel);
        tabbedPane.add("По должности", sortedByPostSurnamePanel);
        tabbedPane.add("По зарплате", sortedBySalaryPanel);
        tabbedPane.add("Фамилии", surnamesPanel);
        tabbedPane.add("Должности", postsPanel);

        addListener(dataPanel);
        addListener(sortedByPostSurnamePanel);
        addListener(sortedBySalaryPanel);
        addListener(surnamesPanel);
        addListener(postsPanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenu dataMenu = new JMenu("Данные");
        JMenuItem openMenu = new JMenuItem("Открыть");
        JMenuItem byPostMenu = new JMenuItem("По должности");
        JMenuItem bySalaryMenu = new JMenuItem("По зарплате");
        JMenuItem surnamesMenu = new JMenuItem("Фамилии");
        JMenuItem postsMenu = new JMenuItem("Должности");
        JMenuItem findMenu = new JMenuItem("Поиск");
        JMenuItem numberMenu = new JMenuItem("Количество");
        menuBar.add(fileMenu);
        menuBar.add(dataMenu);
        fileMenu.add(openMenu);
        dataMenu.add(byPostMenu);
        dataMenu.add(bySalaryMenu);
        dataMenu.add(surnamesMenu);
        dataMenu.add(postsMenu);
        dataMenu.add(findMenu);
        dataMenu.add(numberMenu);

        openMenu.addActionListener(e ->{
            FileDialog dlg = new FileDialog(this, "Открыть", FileDialog.LOAD);
            dlg.setFilenameFilter((dir, name) -> name.matches(".+\\.xml"));
            dlg.setVisible(true);
            if(dlg.getFiles().length == 1){
                try {
                    List<Worker> l = Reader.readListOfWorkersFromXML(dlg.getFiles()[0]);
                    workersModel.setList(l);
                    map = asMap(l);
                } catch (ParserConfigurationException | SAXException | IOException ex) {
                    ex.printStackTrace();
                }
                dataPanel.update();
            }
        });

        byPostMenu.addActionListener(e -> {
            sortedByPostSurnamePanel.update();
            tabbedPane.setSelectedComponent(sortedByPostSurnamePanel);
        });
        bySalaryMenu.addActionListener(e -> {
            sortedBySalaryPanel.update();
            tabbedPane.setSelectedComponent(sortedBySalaryPanel);
        });
        surnamesMenu.addActionListener(e -> {
            surnamesPanel.update();
            tabbedPane.setSelectedComponent(surnamesPanel);
        });
        postsMenu.addActionListener(e -> {
            postsPanel.update();
            tabbedPane.setSelectedComponent(postsPanel);
        });
        findMenu.addActionListener(e -> {
            OptionalInt minSkill = workersModel.stream().mapToInt(Worker::calcSkill).min();
            if(minSkill.isPresent()){
                Optional<Worker> worker = workersModel.stream().filter(f -> f.calcSkill() > minSkill.getAsInt() * 3).findFirst();
                if(worker.isPresent()){
                    ShowWorkerDialog showWorkerDialog = new ShowWorkerDialog(this, "Работник", true, worker.get());
                    showWorkerDialog.setVisible(true);
                }
            }
        });
        numberMenu.addActionListener(e -> {
            String post = JOptionPane.showInputDialog(this, "Введите должность");
            if(map.containsKey(post)){
                JOptionPane.showMessageDialog(this, "Количество работников с такой должностью: " + map.get(post),
                        "Количество",JOptionPane.PLAIN_MESSAGE);
            }
        });

        add(tabbedPane);
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }

    public void updateAll(){
        listeners.forEach(Updatable::update);
    }

    public void addListener(Updatable u){
        listeners.add(u);
    }

    private Map<String, Integer> asMap(List<Worker> l){
        Map<String, Integer> m = new HashMap<>();
        for(Worker w : l){
            String post = w.getPost();
            if(m.containsKey(post)){
                m.put(post, m.get(post) + 1);
            }
            else{
                m.put(post, 1);
            }
        }
        return m;
    }
}
