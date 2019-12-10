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

        JPanel ageBoundsPanel = new JPanel(new BorderLayout());
        JPanel minAgeBoundPanel = new JPanel(new BorderLayout());
        JPanel maxAgeBoundPanel = new JPanel(new BorderLayout());
        minAgeBoundPanel.add(minAgeSlider, BorderLayout.NORTH);
        minAgeBoundPanel.add(minAgeLabel, BorderLayout.SOUTH);
        maxAgeBoundPanel.add(maxAgeSlider, BorderLayout.NORTH);
        maxAgeBoundPanel.add(maxAgeLabel, BorderLayout.SOUTH);
        ageBoundsPanel.add(minAgeBoundPanel, BorderLayout.WEST);
        ageBoundsPanel.add(maxAgeBoundPanel, BorderLayout.EAST);

        JPanel maxCostPanel = new JPanel();
        maxCostPanel.add(maxCostLabel);
        maxCostPanel.add(maxCostField);

        ageBoundsPanel.add(maxCostPanel, BorderLayout.SOUTH);

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        add(ageBoundsPanel,BorderLayout.CENTER);
        add(toyJList, BorderLayout.WEST);
        add(filterToys, BorderLayout.EAST);
        add(filterButton, BorderLayout.SOUTH);
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        data = Reader.readVectorOfToys(file);
        toyJList.setListData(data);
    }

}
