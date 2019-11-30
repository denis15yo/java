package graphics.panels;

import graphics.ButtonEx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ButtonsPanel extends JPanel implements MouseListener {
    public ButtonsPanel(int width, int height){
        super(new GridLayout(width, height, 5, 5));

        int size = width * height;
        for(int i = 1; i <= size; ++i){
            ButtonEx current = new ButtonEx(i, randColor());
            add(current);
            current.addMouseListener(this);
        }
    }

    private Color randColor(){
        Random rand = new Random();
        return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        ((JButton)e.getSource()).setText("Clicked");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ButtonEx temp = (ButtonEx)e.getSource();
        temp.setText(Integer.toString(temp.getId()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ((JButton)e.getSource()).setBackground(randColor());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ButtonEx temp = (ButtonEx)e.getSource();
        temp.setBackground(temp.getColor());
    }

}
