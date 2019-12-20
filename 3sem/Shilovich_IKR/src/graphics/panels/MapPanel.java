package graphics.panels;

import essenses.Coffee;
import essenses.CoffeeType;
import essenses.TeaType;
import models.DrinksModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Map;

@SuppressWarnings("FieldCanBeLocal")
public class MapPanel extends JPanel {
    private DrinksModel dataBase;

    private JList<String> strings;
    private DefaultListModel<String> stringsListModel;

    public MapPanel(DrinksModel dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        stringsListModel = new DefaultListModel<>();
        strings = new JList<>(stringsListModel);
        strings.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));

        add(strings, BorderLayout.CENTER);
    }

    public void update(){
        stringsListModel.clear();
        if(dataBase.size() > 0){
            if(dataBase.get(0)instanceof Coffee){
                Map<CoffeeType, Double> m = dataBase.coffeeMap();
                m.forEach((l, r) -> {
                    stringsListModel.addElement(String.format("%-7s%-5.3f", l, r));
                });
            }
            else{
                Map<TeaType, Double> m = dataBase.teaMap();
                m.forEach((l, r) -> {
                    stringsListModel.addElement(String.format("%-7s%-5.3f", l, r));
                });
            }
        }
    }
}
