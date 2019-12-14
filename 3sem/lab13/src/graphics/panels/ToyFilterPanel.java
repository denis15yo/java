package graphics.panels;

import essenses.AgeBounds;
import essenses.Toy;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Collectors;

import models.ToysTableModel;
import my_util.Reader;
import org.xml.sax.SAXException;

public class ToyFilterPanel extends JPanel {
    private JSlider minAgeSlider;
    private JSlider maxAgeSlider;
    private JLabel minAgeLabel;
    private JLabel maxAgeLabel;
    private JTextField maxCostField;
    private JLabel maxCostLabel;
    private JButton filterButton;

    private JTable toysTable;
    private ToysTableModel model;
    private JTable filteredToysTable;
    private ToysTableModel filteredModel;

    public ToyFilterPanel() {
        super(new BorderLayout());
        initComponents();

        Box ageBoundsBox = Box.createHorizontalBox();
        Box minAgeBoundPanel = Box.createVerticalBox();
        Box maxAgeBoundPanel = Box.createVerticalBox();
        minAgeBoundPanel.add(minAgeSlider);
        minAgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minAgeBoundPanel.add(minAgeLabel);
        maxAgeBoundPanel.add(maxAgeSlider);
        maxAgeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxAgeBoundPanel.add(maxAgeLabel);
        ageBoundsBox.add(minAgeBoundPanel);
        ageBoundsBox.add(maxAgeBoundPanel);

        JPanel maxCostPanel = new JPanel();
        maxCostPanel.add(maxCostLabel);
        maxCostPanel.add(maxCostField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(filterButton);

        Box filterBox = Box.createVerticalBox();
        filterBox.add(ageBoundsBox);
        filterBox.add(maxCostPanel);
        filterBox.add(buttonPanel);
        JPanel filterPanel = new JPanel();
        filterPanel.add(filterBox);

        minAgeSlider.addChangeListener(e -> minAgeLabel.setText("Минимум: " + minAgeSlider.getValue()));
        maxAgeSlider.addChangeListener(e -> maxAgeLabel.setText("Максимум: " + maxAgeSlider.getValue()));

        filterButton.addActionListener(e -> {
            if(!maxCostField.getText().matches("\\s*")){
                try{
                    int minAge = minAgeSlider.getValue();
                    int maxAge = maxAgeSlider.getValue();
                    if(minAge <= maxAge){
                        AgeBounds verifiable = new AgeBounds(minAge, maxAge);
                        int maxCost = Integer.parseInt(maxCostField.getText());
                        filteredModel.setList
                                (model.stream().
                                filter(f -> ageCheck(f.getAgeBounds(), verifiable) && f.getCost() <= maxCost).
                                collect(Collectors.toList()));
                        filteredModel.fireTableDataChanged();
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Некорректные возрастные границы",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Некорректная максимальная стоимость!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        add(filterPanel, BorderLayout.CENTER);
        add(new JScrollPane(toysTable), BorderLayout.WEST);
        add(new JScrollPane(filteredToysTable), BorderLayout.EAST);
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        model.setList(Reader.readListOfToys(file));
        model.fireTableDataChanged();
    }

    public void loadFromXML(File file) throws IOException, SAXException, ParserConfigurationException {
        model.setList(Reader.readListOfToysFromXML(file));
        model.fireTableDataChanged();
    }

    public void addToy(Toy t){
        model.addToy(t);
        model.fireTableDataChanged();
    }

    public void initComponents(){
        model = new ToysTableModel();
        toysTable = new JTable(model);
        filteredModel = new ToysTableModel();
        filteredToysTable = new JTable(filteredModel);

        toysTable.setPreferredScrollableViewportSize(toysTable.getPreferredSize());
        filteredToysTable.setPreferredScrollableViewportSize(filteredToysTable.getPreferredSize());

        toysTable.setFillsViewportHeight(true);
        filteredToysTable.setFillsViewportHeight(true);

        maxCostField = new JTextField(10);
        maxCostLabel = new JLabel("Максимальная цена:");
        filterButton = new JButton("Найти!");

        minAgeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 40, 20);
        maxAgeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 40, 20);
        minAgeLabel = new JLabel("Минимум: " + minAgeSlider.getValue());
        maxAgeLabel = new JLabel("Максимум: " + maxAgeSlider.getValue());

        minAgeSlider.setMajorTickSpacing(5);
        minAgeSlider.setPaintTicks(true);
        minAgeSlider.setPaintLabels(true);

        maxAgeSlider.setMajorTickSpacing(5);
        maxAgeSlider.setPaintTicks(true);
        maxAgeSlider.setPaintLabels(true);
    }

    public boolean ageCheck(AgeBounds acceptable, AgeBounds verifiable){
        return verifiable.getMin() >= acceptable.getMin() && verifiable.getMax() <= acceptable.getMax();
    }
}
