import javax.swing.*;
import java.awt.*;

public class ChordsPanel extends JPanel{

    private int length;

    public ChordsPanel(){
        setLength(16);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(Main.OTHER_FRAME_WIDTH, Main.OTHER_FRAME_HEIGHT);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int clear = (Main.OTHER_FRAME_WIDTH/4)/(getLength()+1);
        int rect = (Main.OTHER_FRAME_WIDTH*3/4)/getLength();
        g.setColor(Color.BLACK);
        for(int i = 1; i <= getLength(); i++){
            g.drawRoundRect(clear*i+rect*(i-1), Main.OTHER_FRAME_HEIGHT/3, rect, Main.OTHER_FRAME_HEIGHT/3, 10, 10);
        }
    }

    public int getLength(){
        return this.length;
    }

    public void setLength(int length){
        this.length = length;
    }

}
