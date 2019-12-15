package graphics.panels;

import interfaces.Updatable;
import models.ToysModel;
import models.ToysTableModel;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel implements Updatable {
    private JTable dataTable;
    private ToysTableModel dataTableModel;

    public DataPanel(ToysModel toysModel) {
        super(new BorderLayout());
        dataTableModel = new ToysTableModel(toysModel);
        dataTable = new JTable(dataTableModel);

        dataTable.setPreferredScrollableViewportSize(dataTable.getPreferredSize());
        dataTable.setFillsViewportHeight(true);

        add(new JScrollPane(dataTable), BorderLayout.CENTER);
    }

    @Override
    public void update() {
        dataTableModel.fireTableDataChanged();
    }
}
