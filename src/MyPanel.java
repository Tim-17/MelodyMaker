import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private int length;

    public MyPanel(int length) {
       setLength(length);
    }
    /*
    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRoundRect(0,0,50,100,5,5);
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
