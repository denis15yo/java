package graphics.panels;

import graphics.interfaces.Updatable;
import models.WorkersModel;
import models.WorkersTableModel;

import javax.swing.*;
import java.awt.*;

public abstract class WorkersTablePanel extends JPanel implements Updatable {
    protected WorkersModel workersModel;

    protected JTable workersTable;
    protected WorkersTableModel workersTableModel;

    public WorkersTablePanel(WorkersModel workersModel, WorkersTableModel workersTableModel) {
        super(new BorderLayout());
        this.workersModel = workersModel;

        this.workersTableModel = workersTableModel;
        workersTable = new JTable(workersTableModel);
        workersTable.setPreferredScrollableViewportSize(workersTable.getPreferredSize());
        workersTable.setFillsViewportHeight(true);

        add(new JScrollPane(workersTable), BorderLayout.CENTER);
    }
}
