package graphics.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

@SuppressWarnings("FieldCanBeLocal")
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
        left.setFont(new Font(getFont().getFontName(), Font.ITALIC, 15)); //LucidaGrande - стандартный маковский шрифт
        right.setFont(new Font(getFont().getFontName(), Font.ITALIC, 15));

        JScrollPane leftScroll = new JScrollPane(left);
        JScrollPane rightScroll = new JScrollPane(right);


        add(leftScroll, BorderLayout.WEST);
        add(rightScroll, BorderLayout.EAST);
        add(toRightButton, BorderLayout.NORTH);
        add(toLeftButton, BorderLayout.SOUTH);

        final String[] LEFT_DATA = {
                "Bryan Cranston",
                "Anna Gunn",
                "Aaron Paul",
                "Dean Norris",
                "Betsy Brandt",
                "RJ Mitte",
               "Bob Odenkirk",
                "Giancarlo Esposito",
                "Jonathan Banks",
                "Laura Fraser",
                "Jesse Plemons"
        };

        final String[] RIGHT_DATA = {
                "Stewart A. Lyons",
                "Sam Catlin",
                "John Shiban",
                "Peter Gould",
                "George Mastras",
                "Thomas Schnauz",
                "Melissa Bernstein",
                "Diane Mercer",
                "Bryan Cranston",
                "Moira Walley-Beckett",
                "Karen Moore",
                "Patty Lin"
        };

        leftModel.addAll(Arrays.asList(LEFT_DATA));
        rightModel.addAll(Arrays.asList(RIGHT_DATA));

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
