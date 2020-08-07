import javax.swing.*;
import java.awt.*;

public class RhythmPanel extends JPanel{

    private int length;
    public boolean[] rhythm;
    private boolean erase;

    public RhythmPanel(){
       setLength(16);
       setErase(false);
    }


    // Actual methods

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(Main.OTHER_FRAME_WIDTH, Main.OTHER_FRAME_HEIGHT);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!getErase()){
            drawRectangles(g, this.getWidth(), this.getHeight());
        } else {
            g.setColor(new Color(0,0,0,0));
            g.fillRect(0,0, this.getWidth(), this.getHeight());
        }
    }

    private void drawRectangles(Graphics g, int width, int height){
        int clear = (width/4)/(getLength()+1);
        int rect = (width*3/4)/getLength();
        g.setColor(Color.BLACK);
        for(int i = 1; i <= getLength(); i++){
            g.drawRoundRect(clear*i+rect*(i-1), height/3, rect, height/3, 10, 10);
        }
        int xPos;
        for(int i = 1; i <= getLength(); i++){
            xPos = clear*i+rect*(i-1);
            if(getRhythm()[i-1]){
                g.setColor(Color.CYAN);
                g.fillRoundRect(xPos+1, height/3+1, rect-1, height/3-1, 10, 10);
            } else {
                g.setColor(new Color(0,0,0,0));
                g.fillRoundRect(xPos+1, height/3+1, rect-1, height/3-1, 10, 10);
                g.setColor(Color.BLACK);
                g.drawRoundRect(xPos, height/3, rect, height/3, 10, 10);
            }
        }
    }


    // Getters & Setters

    public int getLength(){
        return this.length;
    }

    public void setLength(int length){
        this.length = length;
        setRhythm(new boolean[getLength()]);
    }

    public boolean[] getRhythm(){
        return this.rhythm;
    }

    public void setRhythm(boolean[] rhythm){
        this.rhythm = rhythm;
    }

    public boolean getErase(){
        return this.erase;
    }

    public void setErase(boolean erase){
        this.erase = erase;
    }
}
