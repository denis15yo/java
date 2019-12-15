package graphics.panels;

import essenses.AgeBounds;
import essenses.Toy;
import interfaces.Updatable;
import models.ToysModel;
import models.ToysTableModel;

import javax.swing.*;
import java.awt.*;

public class ToyFilterPanel extends JPanel implements Updatable {
    private JSlider minAgeSlider;
    private JSlider maxAgeSlider;
    private JLabel minAgeLabel;
    private JLabel maxAgeLabel;
    private JTextField maxCostField;
    private JLabel maxCostLabel;
    private JSpinner maxCostSpinner;

    private ToysModel toysModel;

    private JTable filteredToysTable;
    private ToysTableModel filteredTableModel;

    public ToyFilterPanel(ToysModel toysModel) {
        super(new BorderLayout());
        initComponents();

        this.toysModel = toysModel;
        filteredTableModel = new ToysTableModel(new ToysModel());
        filteredToysTable = new JTable(filteredTableModel);

        filteredToysTable.setPreferredSize(new Dimension(0, 100));
        filteredToysTable.setPreferredScrollableViewportSize(filteredToysTable.getPreferredSize());
        filteredToysTable.setFillsViewportHeight(true);

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
//        maxCostPanel.add(maxCostField);
        maxCostPanel.add(maxCostSpinner);

        Box filterBox = Box.createVerticalBox();
        filterBox.add(ageBoundsBox);
        filterBox.add(maxCostPanel);
        JPanel filterPanel = new JPanel();
        filterPanel.add(filterBox);

        minAgeSlider.addChangeListener(e -> minAgeLabel.setText("Минимум: " + minAgeSlider.getValue()));
        maxAgeSlider.addChangeListener(e -> maxAgeLabel.setText("Максимум: " + maxAgeSlider.getValue()));

        maxCostField.addActionListener(e -> update());
        minAgeSlider.addChangeListener(e -> update());
        maxAgeSlider.addChangeListener(e -> update());
        maxCostSpinner.addChangeListener(e -> update());

        add(filterPanel, BorderLayout.NORTH);
        add(new JScrollPane(filteredToysTable), BorderLayout.CENTER);
    }


    public void initComponents(){
        maxCostField = new JTextField("1000", 10);
        maxCostLabel = new JLabel("Максимальная цена:");

        maxCostSpinner = new JSpinner();

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

    @Override
    public void update() {
        filteredTableModel.clear();

        int minAge = minAgeSlider.getValue();
        int maxAge = maxAgeSlider.getValue();
        AgeBounds verifiable = new AgeBounds(minAge, maxAge);
//        int maxCost = Integer.parseInt(maxCostField.getText());
        int maxCost = (int) maxCostSpinner.getValue();

        for(Toy t : toysModel){
            if(t.getCost() <= maxCost && ageCheck(t.getAgeBounds(), verifiable)){
                filteredTableModel.addToy(t);
            }
        }
        
        filteredTableModel.fireTableDataChanged();
    }
}
