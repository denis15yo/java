package graphics.panels;

import models.WorkersModel;
import models.WorkersTableModel;

public class SortedByPostSurnamePanel extends WorkersTablePanel {
    public SortedByPostSurnamePanel(WorkersModel workersModel) {
        super(workersModel, new WorkersTableModel());
    }

    @Override
    public void update() {
        workersTableModel.clear();
        workersModel.stream().sorted((l, r) -> {
            if(l.getPost().compareTo(r.getPost()) != 0){
                return l.getPost().compareTo(r.getPost());
            }
            return l.getSurname().compareTo(r.getSurname());
        }).forEachOrdered(e -> workersTableModel.add(e));
        workersTableModel.fireTableDataChanged();
    }
}
