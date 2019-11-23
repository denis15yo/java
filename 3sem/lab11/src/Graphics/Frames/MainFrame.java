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

    private PaintingPanel paintingPanelLeft;
    private PaintingPanel paintingPanelRight;

    public void updateTitle(){
        String title = (workFile == null ? "New" : workFile.getName()) + ( (isChanged) ? " - [Changed]" : "");
        setTitle(title);
    }

    public MainFrame() {
        super("New");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(FRAME_POSITION_X, FRAME_POSITION_Y, FRAME_WIDTH, FRAME_HEIGHT);

        paintingPanelLeft = new PaintingPanel(this);
        JScrollPane scrollPane = new JScrollPane(paintingPanelLeft, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paintingPanelRight = new PaintingPanel(this);

        JButton buttonColor = new JButton("Color");
        buttonColor.addActionListener(e -> {
            Color currentColor = paintingPanelLeft.getColor();
            Color newColor = JColorChooser.showDialog(null, "Choose color", currentColor);
            paintingPanelLeft.setColor(newColor);
            paintingPanelRight.setColor(newColor);
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenu = new JMenuItem("New");
        JMenuItem openMenu = new JMenuItem("Open");
        JMenuItem saveMenu = new JMenuItem("Save");
        JMenuItem saveAsMenu = new JMenuItem("Save as");
        fileMenu.add(newMenu);
        fileMenu.add(openMenu);
        fileMenu.add(saveMenu);
        fileMenu.add(saveAsMenu);
        menuBar.add(fileMenu);

        newMenu.addActionListener(e -> {
            paintingPanelLeft.setWhiteCanvas(PaintingPanel.DEFAULT_IMAGE_WIDTH, PaintingPanel.DEFAULT_IMAGE_HEIGHT);
            workFile = null;
            isChanged = false;
            updateTitle();
        });

        openMenu.addActionListener(e -> {
            try {
                FileDialog dlg = new FileDialog(this, "Open", FileDialog.LOAD);
                dlg.setFilenameFilter((dir, name) -> name.matches(".+\\.png"));
                dlg.setVisible(true);
                if(dlg.getFiles().length == 1){
                    workFile = dlg.getFiles()[0];
                    paintingPanelLeft.setImg(ImageIO.read(workFile));
                    paintingPanelLeft.repaint();
                    isChanged = false;
                    updateTitle();
                }
            } catch (IOException ignored) {
                JOptionPane.showMessageDialog(null, "Opening Error",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        saveMenu.addActionListener(e -> {
            if(workFile != null){
                try {
                    if(ImageIO.write(paintingPanelLeft.getImg(), workFile.getName().substring(workFile.getName().length() - 3), workFile)){
                        isChanged = false;
                        updateTitle();
                    }else{
                        throw new IOException();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Saving failed",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                showDialogAndSaveToSelectedFile();
            }
        });
        saveAsMenu.addActionListener(e -> {
            showDialogAndSaveToSelectedFile();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(isChanged){
                    int answer = JOptionPane.showConfirmDialog(e.getWindow(), "Do you want to save changes?");
                    if(answer == JOptionPane.YES_OPTION){
                        showDialogAndSaveToSelectedFile();
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

        setJMenuBar(menuBar);
        JPanel paintingPanels = new JPanel(new GridLayout(0, 2, 5, 0));
        paintingPanels.add(paintingPanelLeft);
        paintingPanels.add(paintingPanelRight);
        add(paintingPanels, BorderLayout.CENTER);
        JPanel temp = new JPanel(new FlowLayout());
        temp.add(buttonColor);
        add(temp, BorderLayout.SOUTH);
    }

    private File showSaveDialog() throws IOException {
        FileDialog dlg = new FileDialog(this, "Save", FileDialog.SAVE);
        dlg.setVisible(true);
        File file;
        if(dlg.getFiles().length == 1){
            file = dlg.getFiles()[0];
            if(!file.getName().endsWith(".png")){
                throw new IllegalArgumentException();
            }
        }
        else{
            file = null;
        }
        return file;
    }
    private void showDialogAndSaveToSelectedFile(){
        try{
            File temp = showSaveDialog();
            if(temp != null) {
                workFile = temp;
                if(ImageIO.write(paintingPanelLeft.getImg(), "png", workFile)){
                    isChanged = false;
                    updateTitle();
                }
                else{
                    throw new IOException();
                }
            }
        }
        catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null, "Required File Extension \".png\"",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Saving failed",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
