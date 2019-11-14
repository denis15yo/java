package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private static final String question = "Вам нравится математический анализ?";

    private JLabel questionLabel;
    private JButton yesButton, noButton;

    public MainFrame()  {
        super("Two(one) answer");

        setVisible(true);
        com.apple.eawt.FullScreenUtilities.setWindowCanFullScreen(this,true);
        com.apple.eawt.Application.getApplication().requestToggleFullScreen(this);
        setSize(1440, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel(question);
        yesButton = new JButton("Да");
        noButton = new JButton("Нет");

        questionLabel.setBounds(600, 200, 300, 50);
        yesButton.setBounds(600, 300, 100, 100);
        noButton.setBounds(750, 300, 100, 100);

        setLayout(null);
        add(questionLabel);
        add(yesButton);
        add(noButton);

        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Ну ничего, этот семестр последний!", "Утешение", JOptionPane.PLAIN_MESSAGE);
            }
        });
        yesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int x = (int)(Math.random() * (getWidth() - yesButton.getWidth()));
                int y = (int)(Math.random() * (getHeight() - yesButton.getHeight()));
                yesButton.setLocation(x, y);
            }
        });
    }
}
