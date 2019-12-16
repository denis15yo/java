package graphics.panels;

import models.WorkersModel;
import models.WorkersTableModel;

public class DataPanel extends WorkersTablePanel{
    public DataPanel(WorkersModel workersModel) {
        super(workersModel, new WorkersTableModel(workersModel));
    }

    @Override
    public void update() {
        workersTableModel.fireTableDataChanged();
    }
}
