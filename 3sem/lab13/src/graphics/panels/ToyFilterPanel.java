package graphics.panels;

import essenses.AgeBounds;
import essenses.Toy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.Vector;

import my_util.Reader;

public class ToyFilterPanel extends JPanel {
    JSlider minAgeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 40, 20);
    JSlider maxAgeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 40, 20);
    JLabel minAgeLabel = new JLabel("Min: ");
    JLabel maxAgeLabel = new JLabel("Max: ");
    JTextField maxCostField;
    JLabel maxCostLabel;
    JButton filterButton;

    JList<Toy> toyJList;
    Vector<Toy> data;

    JList<Toy> filterToys;
    Vector<Toy> filterModel;

    public ToyFilterPanel() {
        super(new BorderLayout());
        data = new Vector<>();
        filterModel = new Vector<>();
        toyJList = new JList<>(data);
        filterToys = new JList<>(filterModel);
        maxCostField = new JTextField(10);
        maxCostLabel = new JLabel("Max cost:");
        filterButton = new JButton("Filter!");

        toyJList.setPreferredSize(new Dimension(200, 0));
        filterToys.setPreferredSize(new Dimension(200, 0));

        minAgeSlider.setMajorTickSpacing(5);
        minAgeSlider.setPaintTicks(true);
        minAgeSlider.setPaintLabels(true);
        minAgeSlider.addChangeListener(e -> minAgeLabel.setText("Min: " + minAgeSlider.getValue()));

        maxAgeSlider.setMajorTickSpacing(5);
        maxAgeSlider.setPaintTicks(true);
        maxAgeSlider.setPaintLabels(true);
        maxAgeSlider.addChangeListener(e -> maxAgeLabel.setText("Max: " + maxAgeSlider.getValue()));

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

        Box filterBox = Box.createVerticalBox();
        filterBox.add(ageBoundsBox);
        filterBox.add(maxCostPanel);
        filterBox.add(filterButton);


        filterButton.addActionListener(e -> {
            int minCheck = minAgeSlider.getValue();
            int maxCheck = maxAgeSlider.getValue();
            int maxCost = Integer.parseInt(maxCostField.getText());
            filterModel = new Vector<>();
            for(Toy elem : data){
                if(elem.getAgeBounds().check(new AgeBounds(minCheck, maxCheck)) && elem.getCost() <= maxCost){
                    filterModel.add(elem);
                }
            }
            filterToys.setListData(filterModel);
        });

        add(filterBox, BorderLayout.CENTER);
        add(toyJList, BorderLayout.WEST);
        add(filterToys, BorderLayout.EAST);
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        data = Reader.readVectorOfToys(file);
        toyJList.setListData(data);
    }

    public void addToy(Toy t){
        data.add(t);
        toyJList.setListData(data);
    }
}
