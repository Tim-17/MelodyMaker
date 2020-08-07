import javax.swing.*;
import java.awt.*;

public class ChordsPanel extends JPanel{

    private int length;
    private Chord[] chords;
    private boolean erase;

    public ChordsPanel(){
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

    private void drawRectangles(Graphics g, int width, int height){ // TODO: implement colored rectangle chord display
        int clear = (width/4)/(getLength()+1);
        int rect = (width*3/4)/getLength();
        g.setColor(Color.BLACK);
        for(int i = 1; i <= getLength(); i++){
            g.drawRoundRect(clear*i+rect*(i-1), height/3, rect, height/3, 10, 10);
        }
        int xPos;
        for(int i = 1; i <= getLength(); i++){
            xPos = clear*i+rect*(i-1);
            if(getChords()[i-1] != null){
                g.setColor(Color.BLACK);
                g.drawString(getChords()[i-1].getRootNote(), (xPos+rect/2)-(g.getFontMetrics().stringWidth(getChords()[i-1].getRootNote())/2), height/2+(g.getFontMetrics().getHeight()/2)); // place the chordBaseNote String at the center of the boxes
            } else {
                g.setColor(new Color(0,0,0,0));
                g.fillRoundRect(xPos+1, height/3+1, rect-1, height/3-1, 10, 10);
            }
        }
    }


    // Getters & Setters

    public int getLength(){
        return this.length;
    }

    public void setLength(int length){
        this.length = length;
        setChords(new Chord[getLength()]);
    }

    public Chord[] getChords(){
        return this.chords;
    }

    public void setChords(Chord[] chords){
        this.chords = chords;
    }

    public boolean getErase(){
        return this.erase;
    }

    public void setErase(boolean erase){
        this.erase = erase;
    }
}
