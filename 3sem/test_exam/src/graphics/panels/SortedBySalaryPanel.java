package graphics.panels;

import models.WorkersModel;

public class SortedBySalaryPanel extends WorkersTablePanel {
    public SortedBySalaryPanel(WorkersModel workersModel) {
        super(workersModel);
    }

    @Override
    public void update() {
        workersTableModel.clear();
        workersModel.stream().sorted((l, r) -> r.getSalary() - l.getSalary()).forEachOrdered(e -> workersTableModel.add(e));
        workersTableModel.fireTableDataChanged();
    }
}
