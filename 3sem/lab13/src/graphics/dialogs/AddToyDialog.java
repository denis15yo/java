package graphics.dialogs;

import essenses.AgeBounds;
import essenses.Toy;

import javax.swing.*;

public class AddToyDialog extends JDialog {
    JLabel nameLabel = new JLabel("Name:");
    JLabel costLabel = new JLabel("Cost:");
    JLabel minAgeLabel = new JLabel("Min age:");
    JLabel maxAgeLabel = new JLabel("Max age:");

    JTextField nameField = new JTextField(10);
    JTextField costField = new JTextField(5);
    JTextField minAgeField = new JTextField(2);
    JTextField maxAgeField = new JTextField(2);

    JButton addButton = new JButton("Add");

    private Toy addedToy;

    public Toy getAddedToy() {
        return addedToy;
    }
    public AddToyDialog(JFrame owner) {
        super(owner, "Add toy", true);
        setResizable(false);
        setLocation(owner.getX(), owner.getY());

        Box mainBox = Box.createVerticalBox();
        Box ageBoundsBox = Box.createHorizontalBox();

        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel costPanel = new JPanel();
        costPanel.add(costLabel);
        costPanel.add(costField);

        JPanel minAgePanel = new JPanel();
        JPanel maxAgePanel = new JPanel();
        minAgePanel.add(minAgeLabel);
        minAgePanel.add(minAgeField);
        maxAgePanel.add(maxAgeLabel);
        maxAgePanel.add(maxAgeField);

        ageBoundsBox.add(minAgePanel);
        ageBoundsBox.add(maxAgePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        mainBox.add(namePanel);
        mainBox.add(costPanel);
        mainBox.add(ageBoundsBox);
        mainBox.add(buttonPanel);

        addButton.addActionListener(e -> {
            try{
                int minAge = Integer.parseInt(minAgeField.getText());
                int maxAge = Integer.parseInt(maxAgeField.getText());
                if(minAge <= maxAge){
                    addedToy = new Toy(nameField.getText(), Integer.parseInt(costField.getText()),
                            new AgeBounds(minAge, maxAge));
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Некорректные данные", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Некорректные данные!", "Eroor", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(mainBox);
        pack();
    }

    public void execute(){
        this.setVisible(true);
    }
}
