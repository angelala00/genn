package cltest;

/**
 * Created by chenlong on 2014/8/30.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.lang.String;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class ImageCopy extends JFrame {
    public ImageCopy() {
        add(new DrawPanel());
    }
    public static void main(String[] A00) {
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        JFrame L00 = new ImageCopy();
        L00.setTitle("+");
        L00.setDefaultCloseOperation(EXIT_ON_CLOSE);
        L00.setSize(400, 400);
        L00.setVisible(true);
    }
}
class DrawPanel extends JPanel {
    static final String C00 = "D:\\java\\xiaohua\\webapp-new\\src\\main\\webapp\\resources\\xiaohuaContent\\image\\a2014083010\\1409365640673.gif";
    static final int C01 = 130; // scale rate(%)
    Image I00;
    int I01;
    int I02;
    Image I03;
    public DrawPanel() {
        setBackground(Color.white);
        I00 = new ImageIcon(C00).getImage();
        I01 = I00.getWidth(this);
        I02 = I00.getHeight(this);
        I03 = I00.getScaledInstance(I01 * C01 / 100, -1, Image.SCALE_SMOOTH);
        MediaTracker L00 = new MediaTracker(this);
        L00.addImage(I03, 1);
        try {
            L00.waitForAll();
        } catch (InterruptedException L01) {
        }
    }
    public void paintComponent(Graphics A00) {
        super.paintComponent(A00);
        A00.drawString("ORG", 10, 20);
        A00.drawImage(I00, 10, 25, this);
        A00.drawString("OUT ZOOM(" + C01 + "%)", 10, 180);
        A00.drawImage(I00, 10, 185, I01 * C01 / 100, I02 * C01 / 100, this);
        A00.drawString("OUT IN SCALE(" + C01 + "%)", 200, 180);
        A00.drawImage(I03, 200, 185, this);
    }
}