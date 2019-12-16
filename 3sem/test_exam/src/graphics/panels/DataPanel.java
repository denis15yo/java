package graphics.panels;

import models.WorkersModel;

public class DataPanel extends WorkersTablePanel{
    public DataPanel(WorkersModel workersModel) {
        super(workersModel);
    }

    @Override
    public void update() {
        workersTableModel.fireTableDataChanged();
    }
}
