package Graphics.Frames;

import Graphics.Panels.PaintingPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    private static final int FRAME_POSITION_X = 500;
    private static final int FRAME_POSITION_Y = 150;

    private File workFile;
    private boolean isChanged;
    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    @SuppressWarnings("FieldCanBeLocal")
    private JButton buttonColor;
    private PaintingPanel paintingPanel;

    public void updateTitle(){
        String title = (workFile == null ? "New" : workFile.getName()) + ( (isChanged) ? " - [Changed]" : "");
        setTitle(title);
    }

    public MainFrame() {
        super("New");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(FRAME_POSITION_X, FRAME_POSITION_Y, FRAME_WIDTH, FRAME_HEIGHT);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(isChanged){
                    int answer = JOptionPane.showConfirmDialog(e.getWindow(), "Do you want to save changes?");
                    if(answer == JOptionPane.YES_OPTION){
                        try {
                            saveToFile();
                            isChanged = false;
                            updateTitle();
                        }
                        catch(IllegalArgumentException ex){
                            JOptionPane.showMessageDialog(e.getWindow(), "Required File Extension \".jpg\" or \".png\"",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        catch (IOException ex) {
                            JOptionPane.showMessageDialog(e.getWindow(), "Saving failed",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else if(answer == JOptionPane.NO_OPTION){
                        System.exit(0);
                    }
                }
                else{
                    System.exit(0);
                }
            }
        });

        paintingPanel = new PaintingPanel(this);
        JScrollPane scrollPane = new JScrollPane(paintingPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        buttonColor = new JButton("Color");
        buttonColor.addActionListener(e -> {
            Color currentColor = paintingPanel.getColor();
            paintingPanel.setColor(JColorChooser.showDialog(null, "Choose", currentColor));
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenu = new JMenuItem("New");
        JMenuItem openMenu = new JMenuItem("Open");
        JMenuItem saveMenu = new JMenuItem("Save");
        fileMenu.add(newMenu);
        fileMenu.add(openMenu);
        fileMenu.add(saveMenu);
        menuBar.add(fileMenu);

        newMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintingPanel.setWhiteList();
                paintingPanel.repaint();
                workFile = null;
                isChanged = false;
                updateTitle();
            }
        });

        openMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadFromFile();
                    isChanged = false;
                    updateTitle();
                } catch (IOException ignored) {
                    JOptionPane.showMessageDialog(null, "Opening Eror",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        saveMenu.addActionListener(e -> {
            try {
                saveToFile();
                isChanged = false;
                updateTitle();
            }
            catch(IllegalArgumentException ex){
                JOptionPane.showMessageDialog(null, "Required File Extension \".jpg\" or \".png\"",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Saving failed",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setJMenuBar(menuBar);
        add(scrollPane, BorderLayout.CENTER);
        JPanel temp = new JPanel(new FlowLayout());
        temp.add(buttonColor);
        add(temp, BorderLayout.SOUTH);
    }

    private void saveToFile() throws IOException {
        if(workFile != null){
            ImageIO.write(paintingPanel.getImg(), workFile.getName().substring(workFile.getName().length() - 3), workFile);
        }
        else{
            FileDialog dlg = new FileDialog(this, "Save", FileDialog.SAVE);
            dlg.setVisible(true);
            workFile = dlg.getFiles()[0];
            if(workFile.getName().endsWith(".png")){
                ImageIO.write(paintingPanel.getImg(), "png", workFile);
            }
            else if(workFile.getName().endsWith(".jpg")){
                ImageIO.write(paintingPanel.getImg(), "jpg", workFile);
            }
            else{
                workFile = null;
                throw new IllegalArgumentException();
            }
        }
    }
    private void loadFromFile() throws IOException {
        FileDialog dlg = new FileDialog(this, "Open", FileDialog.LOAD);
        dlg.setFilenameFilter((dir, name) -> name.matches(".+\\.((png)|(jpg))"));
        dlg.setVisible(true);
        if(dlg.getFiles().length == 1){
            workFile = dlg.getFiles()[0];
            paintingPanel.setImg(ImageIO.read(workFile));
            paintingPanel.repaint();
        }else{
            throw new IOException();
        }
    }
}
