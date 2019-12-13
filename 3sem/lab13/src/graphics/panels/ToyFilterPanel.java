package graphics.panels;

import essenses.AgeBounds;
import essenses.Toy;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.stream.Collectors;

import models.ToysTableModel;
import my_util.Reader;

public class ToyFilterPanel extends JPanel {
    JSlider minAgeSlider;
    JSlider maxAgeSlider;
    JLabel minAgeLabel;
    JLabel maxAgeLabel;
    JTextField maxCostField;
    JLabel maxCostLabel;
    JButton filterButton;

    JTable toysTable;
    ToysTableModel model;
    JTable filteredToysTable;
    ToysTableModel filteredModel;

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

        minAgeSlider.addChangeListener(e -> minAgeLabel.setText("Min: " + minAgeSlider.getValue()));
        maxAgeSlider.addChangeListener(e -> maxAgeLabel.setText("Max: " + maxAgeSlider.getValue()));

        filterButton.addActionListener(e -> {
            if(!maxCostField.getText().matches("\\s*")){
                try{
                    AgeBounds checkBounds = new AgeBounds(minAgeSlider.getValue(), maxAgeSlider.getValue());
                    int maxCost = Integer.parseInt(maxCostField.getText());
                    filteredModel.setList(model.stream().filter(f -> f.getAgeBounds().check(checkBounds) && f.getCost() <= maxCost).
                            collect(Collectors.toList()));
                    filteredModel.fireTableDataChanged();
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Некорректное поле Max cost!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });


        //toysTable.setFillsViewportHeight(true);

        add(filterPanel, BorderLayout.CENTER);
        add(new JScrollPane(toysTable), BorderLayout.WEST);
        add(new JScrollPane(filteredToysTable), BorderLayout.EAST);
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        model.setList(Reader.readVectorOfToys(file));
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

        toysTable.setPreferredScrollableViewportSize(new Dimension(300, 0));
        filteredToysTable.setPreferredScrollableViewportSize(new Dimension(300, 0));

        maxCostField = new JTextField(10);
        maxCostLabel = new JLabel("Max cost:");
        filterButton = new JButton("Filter!");

        minAgeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 40, 20);
        maxAgeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 40, 20);
        minAgeLabel = new JLabel("Min: " + minAgeSlider.getValue());
        maxAgeLabel = new JLabel("Max: " + maxAgeSlider.getValue());

        minAgeSlider.setMajorTickSpacing(5);
        minAgeSlider.setPaintTicks(true);
        minAgeSlider.setPaintLabels(true);

        maxAgeSlider.setMajorTickSpacing(5);
        maxAgeSlider.setPaintTicks(true);
        maxAgeSlider.setPaintLabels(true);
    }
}
