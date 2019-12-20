package graphics.panels;

import essenses.Drink;
import models.DrinksModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class SortedDrinksPanel extends JPanel {
    private DrinksModel dataBase;

    private JList<Drink> drinksList;
    private DefaultListModel<Drink> drinksListModel;

    public SortedDrinksPanel(DrinksModel dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        drinksListModel = new DefaultListModel<>();
        drinksList = new JList<>(drinksListModel);
        drinksList.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));

        add(drinksList, BorderLayout.CENTER);
    }

    public void update(){
        drinksListModel.clear();
        dataBase.stream().sorted((l, r) -> {
            if(l.getCost() != r.getCost()){
                return r.getCost() - l.getCost();
            }
            return l.getName().compareTo(r.getName());
        }).forEachOrdered(e -> drinksListModel.addElement(e));
    }
}
