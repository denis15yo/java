package graphics.dialog;

import essenses.Worker;

import javax.swing.*;
import java.awt.*;

public class ShowWorkerDialog extends JDialog {
    @SuppressWarnings("FieldCanBeLocal")
    private Worker worker;

    public ShowWorkerDialog(Frame owner, String title, boolean modal, Worker worker) {
        super(owner, title, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.worker = worker;

        JLabel surnameLabel = new JLabel("Фамилия: " + worker.getSurname());
        JLabel postLabel = new JLabel("Должность: " + worker.getPost());
        JLabel salaryLabel = new JLabel("Зарплата: " + worker.getSalary());
        JLabel skillLabel = new JLabel("Квалификация: " + worker.calcSkill());

        Box infoBox = Box.createVerticalBox();
        infoBox.add(surnameLabel);
        infoBox.add(postLabel);
        infoBox.add(salaryLabel);
        infoBox.add(skillLabel);

        JPanel infoPanel = new JPanel();
        infoPanel.add(infoBox);

        add(infoPanel);
        pack();
        setLocationRelativeTo(owner);
    }
}
