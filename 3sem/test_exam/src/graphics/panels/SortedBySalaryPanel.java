package graphics.panels;

import models.WorkersModel;
import models.WorkersTableModel;

public class SortedBySalaryPanel extends WorkersTablePanel {
    public SortedBySalaryPanel(WorkersModel workersModel) {
        super(workersModel, new WorkersTableModel());
    }

    @Override
    public void update() {
        workersTableModel.clear();
        workersModel.stream().sorted((l, r) -> r.getSalary() - l.getSalary()).forEachOrdered(e -> workersTableModel.add(e));
        workersTableModel.fireTableDataChanged();
    }
}
