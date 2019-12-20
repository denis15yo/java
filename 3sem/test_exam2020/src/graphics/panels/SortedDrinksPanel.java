package graphics.panels;

import essenses.Drink;
import models.DrinksListModel;
import models.DrinksModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.stream.Collectors;

public class SortedDrinksPanel extends JPanel {
    private DrinksModel dataBase;

    private JList<Drink> drinksList;
    private DrinksListModel drinksListModel;

    public SortedDrinksPanel(DrinksModel dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        drinksListModel = new DrinksListModel(new DrinksModel());
        drinksList = new JList<>(drinksListModel);
        drinksList.setFont(new Font("monospaced", FontUIResource.PLAIN, 10));

        add(drinksList, BorderLayout.CENTER);
    }

    public void update(){
        drinksListModel.setDrinksModel(new DrinksModel(dataBase.stream().sorted((l, r) -> {
            if(l.getCost() != r.getCost()){
                return r.getCost() - l.getCost();
            }
            return l.getName().compareTo(r.getName());
        }).collect(Collectors.toList())));
        drinksListModel.update();
    }
}
