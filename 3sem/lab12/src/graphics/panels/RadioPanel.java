package graphics.panels;

import javax.swing.*;
import java.awt.*;

public class RadioPanel extends JPanel {
    public RadioPanel() {
        super();
        Box box = Box.createVerticalBox();
        ButtonGroup radioGroup = new ButtonGroup();
        final ImageIcon[] icons = new ImageIcon[] {
                new ImageIcon("img/white.png"),
                new ImageIcon("img/green.png"),
                new ImageIcon("img/blue.png"),
                new ImageIcon("img/red.png"),
        };
        final String[] str = {"Минская", "Брестская",  "Витебская", "Гомельская", "Гродненская", "Могилевская"};

        for (String s : str) {
            JRadioButton temp = new JRadioButton(s, icons[0]);
            temp.setFont(new Font(getFont().getFontName(), Font.PLAIN, 15));
            temp.setPressedIcon(icons[1]);
            temp.setRolloverIcon(icons[2]);
            temp.setSelectedIcon(icons[3]);
            radioGroup.add(temp);
            box.add(temp);
        }
        add(box);
    }
}
