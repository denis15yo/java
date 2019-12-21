package graphics.panels;

import graphics.interfaces.Updatable;
import myUtil.IntPair;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

@SuppressWarnings("FieldCanBeLocal")
public class MapPanel extends JPanel implements Updatable {
    private JList<String> list;
    private DefaultListModel<String> listModel;

    private Map<String, IntPair> map;

    public MapPanel(Map<String, IntPair> map) {
        super(new BorderLayout());

        this.map = map;
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setFont(new Font("monospaced", Font.PLAIN, 12));

        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    @Override
    public void update() {
        listModel.clear();
        map.forEach((k, v) -> {
            listModel.addElement(String.format("%-10s%-10s", k, v));
        });
    }
}
