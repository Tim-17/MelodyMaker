import javax.swing.*;
import java.awt.*;

public class RhythmPanel extends JPanel {

    private int length;

    public RhythmPanel() {
       setLength(16);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(GUI.getOTHER_FRAME_WIDTH(), GUI.getOTHER_FRAME_HEIGHT());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int clear = (GUI.getOTHER_FRAME_WIDTH()/4)/(getLength()+1);
        int rect = (GUI.getOTHER_FRAME_WIDTH()*3/4)/getLength();
        int total = (getLength()+1)*clear + getLength()*rect;
        System.out.println("(getLength()+1)*Clear + getLength()*Rect: " + total);
        for(int i = 1; i <= getLength(); i++){
            g.drawRoundRect(clear*i+rect*(i-1), GUI.getOTHER_FRAME_HEIGHT()/3, rect, GUI.getOTHER_FRAME_HEIGHT()/3, 10, 10);
        }
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
