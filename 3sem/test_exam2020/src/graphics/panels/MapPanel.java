package graphics.panels;

import essenses.Coffee;
import essenses.CoffeeType;
import essenses.TeaType;
import models.DrinksListModel;
import models.DrinksModel;
import models.StringsListModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Map;

public class MapPanel extends JPanel {
    private DrinksModel dataBase;

    private JList<String> strings;
    private StringsListModel stringsListModel;

    public MapPanel(DrinksModel dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        stringsListModel = new StringsListModel();
        strings = new JList<>(stringsListModel);
        strings.setFont(new Font("monospaced", FontUIResource.PLAIN, 10));

        add(strings, BorderLayout.CENTER);
    }

    public void update(){
        stringsListModel.clear();
        if(dataBase.get(0)instanceof Coffee){
            Map<CoffeeType, Double> m = dataBase.coffeeMap();
            m.forEach((l, r) -> {
                stringsListModel.addElement(String.format("%-7s%-5f", l, r));
            });
        }
        else{
            Map<TeaType, Double> m = dataBase.teaMap();
            m.forEach((l, r) -> {
                stringsListModel.addElement(String.format("%-7s%-5f", l, r));
            });
        }

        stringsListModel.update();
    }
}
