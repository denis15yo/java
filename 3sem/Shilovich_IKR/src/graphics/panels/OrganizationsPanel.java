package graphics.panels;

import essenses.Worker;
import myUtil.Functions;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class OrganizationsPanel extends JPanel {
//    private DrinksModel dataBase;
    private List<Worker> dataBase;

    private JList<String> organizationsList;
    private DefaultListModel<String> organizationsListModel;

    public OrganizationsPanel(List<Worker> dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        organizationsListModel = new DefaultListModel<>();
        organizationsList = new JList<>(organizationsListModel);
        organizationsList.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));

        add(organizationsList, BorderLayout.CENTER);
    }

    public void update(){
        organizationsListModel.clear();
//        Set<String> names = dataBase.namesTreeSet();
        organizationsListModel.addAll(Functions.organizationsTreeSet(dataBase));
    }
}
