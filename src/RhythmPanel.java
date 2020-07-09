import javax.swing.*;
import java.awt.*;

public class RhythmPanel extends JPanel{

    private int length;
    public boolean[] rhythm;

    public RhythmPanel(){
       setLength(16);
       setRhythm(new boolean[getLength()]);
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
        int xPos;
        for(int i = 1; i <= getLength(); i++){
            xPos = clear*i+rect*(i-1);
            if(getRhythm()[i-1]){
                g.setColor(Color.CYAN);
                g.fillRoundRect(xPos+1, Main.OTHER_FRAME_HEIGHT/3+1, rect-1, Main.OTHER_FRAME_HEIGHT/3-1, 10, 10);
            } else {
                g.setColor(new Color(0,0,0,0));
                g.fillRoundRect(xPos+1, Main.OTHER_FRAME_HEIGHT/3+1, rect-1, Main.OTHER_FRAME_HEIGHT/3-1, 10, 10);
                g.setColor(Color.BLACK);
                g.drawRoundRect(xPos, Main.OTHER_FRAME_HEIGHT/3, rect, Main.OTHER_FRAME_HEIGHT/3, 10, 10);
            }
        }
    }

    public int getLength(){
        return this.length;
    }

    public void setLength(int length){
        this.length = length;
    }

    public boolean[] getRhythm(){
        return this.rhythm;
    }

    public void setRhythm(boolean[] rhythm){
        this.rhythm = rhythm;
    }
}
