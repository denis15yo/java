package graphics.panels;

import javax.swing.*;

public class RadioPanel extends JPanel {
    public RadioPanel() {
        super();
        Box box = Box.createVerticalBox();
        ButtonGroup radioGroup = new ButtonGroup();
        final ImageIcon[] icons = new ImageIcon[] {
                new ImageIcon("img/grey.png"),
                new ImageIcon("img/green.png"),
                new ImageIcon("img/yellow.png"),
                new ImageIcon("img/red.png"),
        };

        for (int i = 0; i < 4; i++) {
            JRadioButton temp = new JRadioButton(icons[0]);
            temp.setPressedIcon(icons[1]);
            temp.setRolloverIcon(icons[2]);
            temp.setSelectedIcon(icons[3]);
            radioGroup.add(temp);
            box.add(temp);
        }
        add(box);
    }
}
