package graphics.panels;

import essenses.Coffee;
import essenses.CoffeeType;
import essenses.Drink;
import essenses.TeaType;
import models.DrinksModel;
import myUtil.Functions;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MapPanel extends JPanel {
//    private DrinksModel dataBase;
    private List<Drink> dataBase;

    private JList<String> strings;
    private DefaultListModel<String> stringsListModel;

    public MapPanel(List<Drink> dataBase) {
        super(new BorderLayout());

        this.dataBase = dataBase;
        stringsListModel = new DefaultListModel<>();
        strings = new JList<>(stringsListModel);
        strings.setFont(new Font("monospaced", FontUIResource.PLAIN, 12));

        add(strings, BorderLayout.CENTER);
    }

    public void update(){
        stringsListModel.clear();

        Map<CoffeeType, Double> coffeeTypeMap = Functions.coffeeMap(dataBase);
        coffeeTypeMap.forEach((l, r) -> stringsListModel.addElement(String.format("%-7s%-5.3f", l, r)));

        Map<TeaType, Double> teaTypeMap = Functions.teaMap(dataBase);
        teaTypeMap.forEach((l, r) -> stringsListModel.addElement(String.format("%-7s%-5.3f", l, r)));
    }
}
