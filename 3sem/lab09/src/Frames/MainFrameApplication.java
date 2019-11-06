package Frames;

import Series.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrameApplication extends JFrame implements ActionListener {
    private JLabel firstLabel = new JLabel("First");
    private JLabel stepLabel = new JLabel("Step");
    private JLabel indexLabel = new JLabel("Index");
    private JLabel valueLabel = new JLabel("Value");
    private JLabel sumLabel = new JLabel("Sum");
    private JRadioButton linearRadio = new JRadioButton(("Linear"));
    private JRadioButton exponentialRadio = new JRadioButton(("Exponential"));
    private JCheckBox checkSaveToFile = new JCheckBox("Save to file");
    private JTextField firstEdit = new JTextField();
    private JTextField stepEdit = new JTextField();
    private JTextField indexEdit = new JTextField();
    private JTextField valueEdit = new JTextField();
    private JTextField sumEdit = new JTextField(5);
    private JTextField toStringEdit = new JTextField(20);
    private JTextField fileNameEdit = new JTextField();
    private JButton goButton = new JButton("Go!");

    private Series series = new Linear();

    public MainFrameApplication(){
        super("Series");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(500, 300, 275, 230);

        JPanel startDataPanel = new JPanel(new GridLayout(0, 2));
        startDataPanel.add(firstLabel);
        startDataPanel.add(firstEdit);
        startDataPanel.add(stepLabel);
        startDataPanel.add(stepEdit);

        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        ButtonGroup group = new ButtonGroup();
        group.add(linearRadio);
        group.add(exponentialRadio);
        radioPanel.add(linearRadio);
        radioPanel.add(exponentialRadio);

        JPanel indexPanel = new JPanel(new GridLayout(0, 2));
        indexPanel.add(indexLabel);
        indexPanel.add(indexEdit);
        indexPanel.add(valueLabel);
        indexPanel.add(valueEdit);

        JPanel sumPanel = new JPanel(new FlowLayout());
        sumPanel.add(sumLabel);
        sumPanel.add(sumEdit);

        JPanel firstPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        firstPanel.add(startDataPanel);
        firstPanel.add(radioPanel);
        firstPanel.add(indexPanel);
        firstPanel.add(sumPanel);

        JPanel saveToFilePanel = new JPanel(new GridLayout(0, 1));
        saveToFilePanel.add(checkSaveToFile);
        saveToFilePanel.add(fileNameEdit);
        checkSaveToFile.setSelected(false);
        fileNameEdit.setVisible(false);

        JPanel secondPanel = new JPanel(new GridLayout(0, 2, 49, 1));
        secondPanel.add(saveToFilePanel);
        secondPanel.add(goButton);

        Container p = getContentPane();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        p.add(firstPanel);
        p.add(toStringEdit);
        p.add(secondPanel);

        goButton.addActionListener(this);
        linearRadio.addActionListener(this);
        exponentialRadio.addActionListener(this);
        checkSaveToFile.addActionListener(this);

        linearRadio.setSelected(true);
        valueEdit.setEditable(false);
        sumEdit.setEditable(false);
        toStringEdit.setEditable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goButton || e.getSource() == linearRadio || e.getSource() == exponentialRadio){
            try{
                double first = Double.parseDouble(firstEdit.getText());
                double step = Double.parseDouble(stepEdit.getText());
                series = (linearRadio.isSelected()) ? new Linear(first, step) : new Exponential(first, step);

                int index = Integer.parseInt(indexEdit.getText());
                if(index < 1){
                    throw new IllegalArgumentException();
                }
                valueEdit.setText(Double.toString(series.calcElementByIndex(index)));
                sumEdit.setText(Double.toString(series.calcSum()));
                toStringEdit.setText(series.toString());
                if(checkSaveToFile.isSelected()){
                    series.saveToFile(fileNameEdit.getText());
                }
            }
            catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(this, "Неверный формат числа, попробуйте снова",
                        "Error", JOptionPane.PLAIN_MESSAGE);
            }
            catch(IllegalArgumentException exception){
                JOptionPane.showMessageDialog(this, "Индексация элементов прогрессии начинается с 1. " +
                        "Попробуйте снова ", "Error", JOptionPane.PLAIN_MESSAGE);
            }
            catch(IOException exception){
                JOptionPane.showMessageDialog(this, "Файл с таким названием не может быть создан",
                        "Error", JOptionPane.PLAIN_MESSAGE);
            }
        }
        else if(e.getSource() == checkSaveToFile){
            fileNameEdit.setVisible(checkSaveToFile.isSelected());
        }

    }
}
