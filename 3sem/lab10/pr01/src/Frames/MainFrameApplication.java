package Frames;

import javax.swing.*;
import java.awt.event.*;

public class MainFrameApplication extends JFrame {
    private static final int WIDTH_BUTTON = 100;
    private static final int HEIGHT_BUTTON = 100;

    private JButton button;
    private JLabel label;

    private int startDragX, startDragY;
    private boolean isDragged = false;

    public MainFrameApplication()  {
        super("DragAndDrop");

        setVisible(true);
        com.apple.eawt.FullScreenUtilities.setWindowCanFullScreen(this,true);
        com.apple.eawt.Application.getApplication().requestToggleFullScreen(this);
        setSize(1440, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button = new JButton("Button");
        button.setSize(WIDTH_BUTTON, HEIGHT_BUTTON);
        button.setLocation((getWidth() - button.getWidth()) / 2, (getHeight() - button.getHeight()) / 2);

        label = new JLabel("x: y: ");
        label.setBounds(getWidth() / 2 - 40, getHeight() - 50, 100, 50);

        setLayout(null);
        getContentPane().add(button);
        getContentPane().add(label);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setLocation(e.getXOnScreen() - button.getWidth() / 2, e.getYOnScreen() - button.getHeight() / 2);
                requestFocusInWindow();
            }
        });
        addMouseMotionListener(new UniversalMouseMotionAdapter());
        addKeyListener(new FrameKeyAdapter());

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.isControlDown() && !isDragged){
                    startDragX = getMousePosition().x - button.getX();
                    startDragY = getMousePosition().y - button.getY();
                    isDragged = true;
                }
            }
        });
        button.addMouseMotionListener(new UniversalMouseMotionAdapter());
        button.addKeyListener(new ButtonKeyAdapter());
    }

    private class UniversalMouseMotionAdapter extends MouseMotionAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
            label.setText("x: " + e.getXOnScreen() + " y: " + e.getYOnScreen());
            if(isDragged){
                button.setLocation(e.getXOnScreen() - startDragX, e.getYOnScreen() - startDragY);
            }
        }
    }

    private class FrameKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getExtendedKeyCode() == KeyEvent.VK_CONTROL && button.getBounds().contains(getMousePosition())){
                startDragX = button.getMousePosition().x;
                startDragY = button.getMousePosition().y;
                isDragged = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getExtendedKeyCode() == KeyEvent.VK_CONTROL){
                isDragged = false;
            }
        }
    }

    private class ButtonKeyAdapter extends  FrameKeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            StringBuilder temp = new StringBuilder(button.getText());
            if(e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE){
                if(temp.length() > 0){
                    temp.deleteCharAt(temp.length() - 1);
                }
            }
            else{
                temp.append(e.getKeyChar());
            }
            button.setText(temp.toString());
        }
    }
}
