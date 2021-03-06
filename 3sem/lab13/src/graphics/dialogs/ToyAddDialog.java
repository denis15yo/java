package graphics.dialogs;

import essenses.AgeBounds;
import essenses.Toy;

import javax.swing.*;

public class ToyAddDialog extends JDialog {
    private JLabel nameLabel;
    private JLabel costLabel;
    private JLabel minAgeLabel;
    private JLabel maxAgeLabel;

    private JTextField nameField;
    private JTextField costField;
    private JTextField minAgeField;
    private JTextField maxAgeField;

    private JButton addButton;

    private Toy addedToy;
    public Toy getAddedToy() {
        return addedToy;
    }
    
    public ToyAddDialog(JFrame owner) {
        super(owner, "Добавление", true);
        setResizable(false);

        initComponents();

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
                    JOptionPane.showMessageDialog(this, "Некорректные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Некорректные данные!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(mainBox);
        pack();
        setLocationRelativeTo(owner);
    }

    public void initComponents(){
        nameLabel = new JLabel("Название:");
        costLabel = new JLabel("Стоимость:");
        minAgeLabel = new JLabel("Минимальный возраст:");
        maxAgeLabel = new JLabel("Максимальный возраст:");

        nameField = new JTextField(10);
        costField = new JTextField(5);
        minAgeField = new JTextField(2);
        maxAgeField = new JTextField(2);

        addButton = new JButton("Добавить");
    }
}
