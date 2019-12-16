package graphics.panels;

import essenses.Worker;
import graphics.interfaces.Updatable;
import models.StringListModel;
import models.WorkersModel;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostsPanel extends JPanel implements Updatable {
    private JList<String> posts;
    private WorkersModel workersModel;

    public PostsPanel(WorkersModel workersModel) {
        super(new BorderLayout());
        this.workersModel = workersModel;
        posts = new JList<>();
        add(posts, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        List<String> l = workersModel.stream().map(Worker::getPost).distinct().collect(Collectors.toList());
        Collections.reverse(l);
        posts.setModel(new StringListModel(l));
    }
}
