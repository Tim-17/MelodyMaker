import javax.swing.*;
import java.awt.*;

public class ChordsPanel extends JPanel{

    private int length;
    private Chord[] chords;
    private boolean erase;
    private Melody calcMelody;

    public ChordsPanel(Melody calcMelody){
        setLength(16);
        setErase(false);
        setCalcMelody(calcMelody);
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
        int clear = (width/4)/(getLength()+1); // width of the space in between rectangles
        int rect = (width*3/4)/getLength(); // width of each rectangle
        g.setColor(Color.BLACK);
        for(int i = 1; i <= getLength(); i++){
            g.drawRoundRect(clear*i+rect*(i-1), height/3, rect, height/3, 10, 10);
        }
        /*
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
        */
        int beginningIndex = 0;
        Chord currentChord = getChords()[beginningIndex];
        int drawStartingXCoordinate;
        int drawWidth;
        // TODO: think about how chords that are null should be dealt with/included in the for-loop
        for(int i = 0; i < getLength()-1; i++){ // TODO: check whether last index is included
            // draw small rectangles
            if(getChords()[i] == null){
                g.setColor(Color.BLACK);
            } else if(getCalcMelody().findKeyNoteIndex(Melody.extractActualNoteName(currentChord.getRootNote())) != -1){ // rootNote == keyNote
                g.setColor(Main.keyNoteColors[getCalcMelody().findKeyNoteIndex(Melody.extractActualNoteName(currentChord.getRootNote()))]);
            } else { // rootNote == extraNote
                g.setColor(Color.LIGHT_GRAY);
            }
            g.drawRoundRect(clear*(i+1)+rect*i, height/3, rect, height/3, 10, 10);
            // draw large rectangles
            if(currentChord != getChords()[i+1]){
                if(getCalcMelody().findKeyNoteIndex(Melody.extractActualNoteName(currentChord.getRootNote())) != -1){ // rootNote == keyNote
                    g.setColor(Main.keyNoteColors[getCalcMelody().findKeyNoteIndex(Melody.extractActualNoteName(currentChord.getRootNote()))]);
                } else { // rootNote == extraNote
                    g.setColor(Color.LIGHT_GRAY);
                }
                drawStartingXCoordinate = clear*(beginningIndex+1)+rect*(beginningIndex);
                drawWidth = clear*(i-beginningIndex)+rect*(i-beginningIndex+1);
                g.drawRoundRect(drawStartingXCoordinate, height/2-height/20, drawWidth, height/10, 10, 10); // TODO: center the chord rectangle
                g.drawString(currentChord.getRootNote(), drawStartingXCoordinate+(drawWidth/2)-(g.getFontMetrics().stringWidth(currentChord.getRootNote())/2),height/2);
                beginningIndex = i+1;
                currentChord = getChords()[beginningIndex];
            }
        }
        /* draws black border around text -> usable for display of baseNotes of chords

        Graphics2D g2 = (Graphics2D)g;
        // create glyph vector from text
        GlyphVector glyphVector = getFont().createGlyphVector(g2.getFontRenderContext(), (String)value);
        // get shape object
        Shape textShape = glyphVector.getOutline();
        // activate anti aliasing for text rendering (looks nicer)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // draw outline
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2.0f));
        g2.draw(textShape);
        // fill shape
        g2.setColor(Main.keyNoteColors[getCalcMelody().findKeyNoteIndex(getCalcMelody().extractActualNoteName((String)value))]);
        g2.fill(textShape);
        */
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

    public Melody getCalcMelody(){
        return this.calcMelody;
    }

    public void setCalcMelody(Melody calcMelody){
        this.calcMelody = calcMelody;
    }
}
