package graphics.panels;

import essenses.Drink;
import models.DrinksModel;
import myUtil.Functions;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class NamesPanel extends JPanel {
//    private DrinksModel dataBase;
    private List<Drink> dataBase;

    private JList<String> namesList;
    private DefaultListModel<String> namesListModel;

    public NamesPanel(List<Drink> dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        namesListModel = new DefaultListModel<>();
        namesList = new JList<>(namesListModel);
        namesList.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));

        add(namesList, BorderLayout.CENTER);
    }

    public void update(){
        namesListModel.clear();
//        Set<String> names = dataBase.namesTreeSet();
        namesListModel.addAll(Functions.namesTreeSet(dataBase));
    }
}
