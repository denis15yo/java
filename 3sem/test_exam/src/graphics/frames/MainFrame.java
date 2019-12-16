package graphics.frames;

import graphics.interfaces.Updatable;
import graphics.panels.*;
import models.WorkersModel;
import myUtil.Reader;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainFrame extends JFrame {
    private DataPanel dataPanel;
    private SortedByPostSurnamePanel sortedByPostSurnamePanel;
    private SortedBySalaryPanel sortedBySalaryPanel;
    private SurnamesPanel surnamesPanel;
    private PostsPanel postsPanel;

    private WorkersModel workersModel;

    private List<Updatable> listeners = new ArrayList<>();

    public MainFrame(){
        super("exam");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        workersModel = new WorkersModel();
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
        JMenuItem openMenu = new JMenuItem("Открыть");
        menuBar.add(fileMenu);
        fileMenu.add(openMenu);

        openMenu.addActionListener(e ->{
            FileDialog dlg = new FileDialog(this, "Открыть", FileDialog.LOAD);
            dlg.setFilenameFilter((dir, name) -> name.matches(".+\\.xml"));
            dlg.setVisible(true);
            if(dlg.getFiles().length == 1){
                try {
                    workersModel.setList(Reader.readListOfWorkersFromXML(dlg.getFiles()[0]));
                } catch (ParserConfigurationException | SAXException | IOException ex) {
                    ex.printStackTrace();
                }
                updateAll();
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
}
