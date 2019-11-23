package Graphics.Panels;

import Graphics.Frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PaintingPanel extends JPanel {
    private static final int IMAGE_WIDTH = 1435;
    private static final int IMAGE_HEIGHT = 835;

    private MainFrame frame;
    private BufferedImage img;
    private Color color = Color.BLACK;
    private int oldX = 0, oldY = 0;

    public void setColor(Color c){
        color = c;
    }
    public Color getColor() {
        return color;
    }
    public BufferedImage getImg() {
        return img;
    }
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    private PaintingPanel() {}; // запрет на конструктор без параметров
    public PaintingPanel(MainFrame frame){
        super();

        this.frame = frame;

        setWhiteList();
        setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));

        PaintingPanelMouseAdapter mouseAdapter = new PaintingPanelMouseAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    private class PaintingPanelMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            oldX = e.getX();
            oldY = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Graphics2D gr = img.createGraphics();
            gr.setColor(color);
            gr.drawLine(oldX, oldY, x, y);
            frame.setChanged(true);
            frame.updateTitle();
            repaint();
            oldX = x;
            oldY = y;
        }
    }

    public void setWhiteList(){
        img = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        repaint();
    }
}
