package graphics;

import javax.swing.*;
import java.awt.*;

public class ButtonEx extends JButton {
    private int id = 0;
    private Color color;

    public int getId() {
        return id;
    }

    public ButtonEx(int id, Color color) {
        super(Integer.toString(id));
        this.id = id;
        this.color = color;
        setBackground(color);
        setOpaque(true);
    }

    public Color getColor() {
        return color;
    }
}
