package graphics.panels;

import models.DrinksModel;
import models.StringsListModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

@SuppressWarnings("FieldCanBeLocal")
public class NamesPanel extends JPanel {
    private DrinksModel dataBase;

    private JList<String> namesList;
    private StringsListModel namesListModel;

    public NamesPanel(DrinksModel dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        namesListModel = new StringsListModel();
        namesList = new JList<>(namesListModel);
        namesList.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));

        add(namesList, BorderLayout.CENTER);
    }

    public void update(){
        Set<String> names = dataBase.namesTreeSet();
        namesListModel.setData(new ArrayList<>(names));
        namesListModel.update();
    }
}
