package graphics.panels;

import models.DrinksModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Set;

@SuppressWarnings("FieldCanBeLocal")
public class NamesPanel extends JPanel {
    private DrinksModel dataBase;

    private JList<String> namesList;
    private DefaultListModel<String> namesListModel;

    public NamesPanel(DrinksModel dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        namesListModel = new DefaultListModel<>();
        namesList = new JList<>(namesListModel);
        namesList.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));

        add(namesList, BorderLayout.CENTER);
    }

    public void update(){
        namesListModel.clear();
        Set<String> names = dataBase.namesTreeSet();
        names.forEach(e -> namesListModel.addElement(e));
    }
}
