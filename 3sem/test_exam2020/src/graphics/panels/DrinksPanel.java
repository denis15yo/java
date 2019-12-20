package graphics.panels;

import essenses.Drink;
import models.DrinksListModel;
import models.DrinksModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class DrinksPanel extends JPanel {
    private DrinksModel dataBase;

    private JList<Drink> drinksList;
    private DrinksListModel drinksListModel;

    public DrinksPanel(DrinksModel dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        drinksListModel = new DrinksListModel(dataBase);
        drinksList = new JList<>(drinksListModel);
        drinksList.setFont(new Font("monospaced", FontUIResource.PLAIN, 10));
        drinksList.setPreferredSize(new Dimension(500, 200));

        add(drinksList, BorderLayout.CENTER);
    }

    public void update(){
        drinksListModel.update();
    }
}
