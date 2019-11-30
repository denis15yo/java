package graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TwoListsPanel extends JPanel {
    private JList<String> left;
    private JList<String> right;
    private DefaultListModel<String> leftModel = new DefaultListModel<>();
    private DefaultListModel<String> rightModel = new DefaultListModel<>();
    private JButton toRightButton = new JButton(">");
    private JButton toLeftButton = new JButton("<");

    public TwoListsPanel(){
        super(new BorderLayout());
        left = new JList<>(leftModel);
        right = new JList<>(rightModel);


        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        add(toRightButton, BorderLayout.NORTH);
        add(toLeftButton, BorderLayout.SOUTH);

        final String[] DATA1 = {"Chrome", "Firefox", "Internet Explorer ", "Safari",
                "Opera", "Morrowind", "Hitman", "CMR", "NFS Undercover",
                "Star Wars", "Call of Duty"
        };

        final String[] DATA2 = {"Windows", "Mac OS", "Ubuntu", "Arena",
                "Dagerfall", "Dagerfall", "MS Office", "Open Office ",
        };

        leftModel.addAll(Arrays.asList(DATA1));
        rightModel.addAll(Arrays.asList(DATA2));

        ButtonsListener buttonsListener = new ButtonsListener();
        toLeftButton.addActionListener(buttonsListener);
        toRightButton.addActionListener(buttonsListener);
    }

    private class ButtonsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultListModel<String> dest;
            DefaultListModel<String> source;
            JList<String> sourceJList;
            if(e.getSource() == toLeftButton){
                dest = leftModel;
                source = rightModel;
                sourceJList = right;
            }
            else{
                dest = rightModel;
                source = leftModel;
                sourceJList = left;
            }

            int[] indices = sourceJList.getSelectedIndices();
            int current = dest.size();
            for(int i = indices.length - 1; i >= 0; --i){
                dest.add(current, source.get(indices[i]));
                source.removeElementAt(indices[i]);
            }
        }
    }
}
