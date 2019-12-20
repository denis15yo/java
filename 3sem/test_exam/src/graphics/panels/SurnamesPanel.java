package graphics.panels;

import essenses.Worker;
import graphics.interfaces.Updatable;
import models.StringListModel;
import models.WorkersModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class SurnamesPanel extends JPanel implements Updatable {
    private JList<String> surnames;
    private WorkersModel workersModel;

    public SurnamesPanel(WorkersModel workersModel) {
        super(new BorderLayout());
        this.workersModel = workersModel;
        surnames = new JList<>();
        add(surnames, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        List<String> l = workersModel.stream().map(Worker::getSurname).distinct().collect(Collectors.toList());
        surnames.setModel(new StringListModel(l));
    }
}
