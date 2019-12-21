package graphics.panels;

import essenses.Worker;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("FieldCanBeLocal")
public class ValuableWorkersPanel extends JPanel {
    private List<Worker> dataBase;

    private String keyOrganization;

    public String getKeyOrganization() {
        return keyOrganization;
    }

    public void setKeyOrganization(String keyOrganization) {
        this.keyOrganization = keyOrganization;
    }

    private JList<Worker> workersList;
    private DefaultListModel<Worker> workersListModel;

    public ValuableWorkersPanel(List<Worker> dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        keyOrganization = "";

        workersListModel = new DefaultListModel<>();
        workersList = new JList<>(workersListModel);
        workersList.setPreferredSize(new Dimension(400, 200));
        workersList.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));

        add(workersList, BorderLayout.CENTER);
    }

    public void update(){
        workersListModel.clear();
        workersListModel.addAll(dataBase.stream().filter(e -> e.getOrganization().equals(keyOrganization)).
                sorted((l, r) -> Double.compare(r.calcSalary(), l.calcSalary())).collect(Collectors.toList()));
    }
}
