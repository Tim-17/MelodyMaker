import javax.swing.*;
import java.awt.*;

public class MelodyPanel extends JPanel{

    private int length;
    private Chord[] chords;
    private boolean erase;
    private Melody melody;

    public MelodyPanel(){
        setLength(16);
        setErase(true);
    }


    // Actual methods

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
        int clear = (width/4)/(getLength()+1); // width of the space in between rectangles
        int rect = (width*3/4)/getLength(); // width of each rectangle
        // draw small rectangles and note names (if available) for every beat
        for(int i = 1; i <= getLength(); i++){
            g.setColor(Color.BLACK);
            g.drawRoundRect(clear*i+rect*(i-1), height/3, rect, height/3, 10, 10);
            if(getMelody().getMelody()[i-1] != null){
                if(getMelody().findKeyNoteIndex(getMelody().getMelody()[i-1]) != -1){ // note == keyNote
                    g.setColor(Main.keyNoteColors[getMelody().findKeyNoteIndex(getMelody().getMelody()[i-1])]);
                } else { // note == extraNote
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.drawString(getMelody().getMelody()[i-1], (clear*i+rect*(i-1)+rect/2)-(g.getFontMetrics().stringWidth(getMelody().getMelody()[i-1])/2)+1, height/2+(g.getFontMetrics().getHeight()/4)); // place the note name at the center of the boxes
            }
        }
        // draw large rectangles for every chord
        int beginningIndex = 0;
        Chord currentChord = getChords()[beginningIndex];
        Chord compareChord;
        int drawStartingXCoordinate;
        int drawWidth;
        for(int i = 0; i < getLength(); i++){
            if(i < getLength()-1){
                compareChord = getChords()[i+1];
            } else {
                compareChord = new Chord("#", false); // create a compareChord that will always be different from the currentChord -> needed to include the last beat
            }
            if(currentChord != null){
                if(!Chord.chordsEqual(currentChord, compareChord)){
                    if(getMelody().findKeyNoteIndex(Melody.extractActualNoteName(currentChord.getRootNote())) != -1){ // rootNote == keyNote
                        g.setColor(Main.keyNoteColors[getMelody().findKeyNoteIndex(Melody.extractActualNoteName(currentChord.getRootNote()))]);
                    } else { // rootNote == extraNote
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    drawStartingXCoordinate = clear*(beginningIndex+1)+rect*(beginningIndex);
                    drawWidth = clear*(i-beginningIndex)+rect*(i-beginningIndex+1);
                    g.drawRoundRect(drawStartingXCoordinate, height*2/3+1, drawWidth, height/8, 10, 10);
                    g.drawString(currentChord.getRootNote(), drawStartingXCoordinate+(drawWidth/2)-(g.getFontMetrics().stringWidth(currentChord.getRootNote())/2),height*2/3+1+(g.getFontMetrics().getHeight()*3/4));
                    if(i < getLength()-1){
                        beginningIndex = i+1;
                        currentChord = getChords()[beginningIndex];
                    }
                }
            } else {
                if(compareChord != null){
                    if(i < getLength()-1){
                        beginningIndex = i+1;
                        currentChord = getChords()[beginningIndex];
                    }
                }
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
        setMelody(new Melody(getLength()));
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

    public Melody getMelody(){
        return this.melody;
    }

    public void setMelody(Melody melody){
        this.melody = melody;
    }
}
