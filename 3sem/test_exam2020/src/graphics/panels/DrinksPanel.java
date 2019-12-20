package graphics.panels;

import essenses.Drink;
import models.DrinksModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class DrinksPanel extends JPanel {
    private DrinksModel dataBase;

    private JList<Drink> drinksList;
    private DefaultListModel<Drink> drinksListModel;

    public DrinksPanel(DrinksModel dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;

        drinksListModel = new DefaultListModel<>();
        drinksList = new JList<>(drinksListModel);
        drinksList.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));
        drinksList.setPreferredSize(new Dimension(350, 200));

        add(drinksList, BorderLayout.CENTER);
    }

    public void update(){
        drinksListModel.clear();
        dataBase.forEach(e -> drinksListModel.addElement(e));
    }
}
