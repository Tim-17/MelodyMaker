import javax.swing.*;
import java.awt.*;

public class ChordsPanel extends JPanel{

    private int length;
    private Chord[] chords;

    public ChordsPanel(){
        setLength(16);
        setChords(new Chord[getLength()]);
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
            if(getChords()[i-1] != null){
                g.setColor(Color.BLACK);
                g.drawString(getChords()[i-1].getRootNote(), (xPos+rect/2)-(g.getFontMetrics().stringWidth(getChords()[i-1].getRootNote())/2), Main.OTHER_FRAME_HEIGHT/2+(g.getFontMetrics().getHeight()/2)); // place the chordBaseNote String at the center of the boxes
            } else {
                g.setColor(new Color(0,0,0,0));
                g.fillRoundRect(xPos+1, Main.OTHER_FRAME_HEIGHT/3+1, rect-1, Main.OTHER_FRAME_HEIGHT/3-1, 10, 10);
            }
        }
    }

    public int getLength(){
        return this.length;
    }

    public void setLength(int length){
        this.length = length;
    }

    public Chord[] getChords(){
        return this.chords;
    }

    public void setChords(Chord[] chords){
        this.chords = chords;
    }
}
