import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private int length;

    public MyPanel() {
       setLength(16);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int clear = (GUI.getOTHER_FRAME_WIDTH()/4)/(getLength()+1);
        int rect = (GUI.getOTHER_FRAME_WIDTH()*3/4-clear)/getLength();
        for(int i = 1; i <= getLength(); i++){
            g.drawRoundRect(clear*i+rect*(i-1) , GUI.getOTHER_FRAME_HEIGHT()/3, rect, GUI.getOTHER_FRAME_HEIGHT()/3, 10, 10);
        }
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
