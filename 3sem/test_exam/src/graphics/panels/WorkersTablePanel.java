package graphics.panels;

import graphics.interfaces.Updatable;
import models.WorkersModel;
import models.WorkersTableModel;

import javax.swing.*;
import java.awt.*;

public abstract class WorkersTablePanel extends JPanel implements Updatable {
    protected JTable workersTable;
    protected WorkersTableModel workersTableModel;

    protected WorkersModel workersModel;

    public WorkersTablePanel(WorkersModel workersModel) {
        super(new BorderLayout());
        this.workersModel = workersModel;

        workersTableModel = new WorkersTableModel(workersModel);
        workersTable = new JTable(workersTableModel);
        workersTable.setPreferredScrollableViewportSize(workersTable.getPreferredSize());
        workersTable.setFillsViewportHeight(true);

        add(new JScrollPane(workersTable), BorderLayout.CENTER);
    }
}
