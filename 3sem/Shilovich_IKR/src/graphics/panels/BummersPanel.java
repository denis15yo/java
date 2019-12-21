package graphics.panels;

import essenses.Worker;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("FieldCanBeLocal")
public class BummersPanel extends JPanel {
//    private DrinksModel dataBase;
    private List<Worker> dataBase;

    private JList<Worker> workersList;
    private DefaultListModel<Worker> workersListModel;

    public BummersPanel(List<Worker> dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;

        workersListModel = new DefaultListModel<>();
        workersList = new JList<>(workersListModel);
        workersList.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));

        add(workersList, BorderLayout.CENTER);
    }

    public void update(){
        workersListModel.clear();
        workersListModel.addAll(dataBase.stream().
                sorted(Comparator.comparingDouble(l -> l.calcSalary() / l.getJobRatio())).collect(Collectors.toList()));
    }
}
