import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphVector;

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

    private void drawRectangles(Graphics g, int width, int height){
        int clear = (width/4)/(getLength()+1); // width of the space in between rectangles
        int rect = (width*3/4)/getLength(); // width of each rectangle
        // draw small rectangles for every beat
        g.setColor(Color.BLACK);
        for(int i = 1; i <= getLength(); i++){
            g.drawRoundRect(clear*i+rect*(i-1), height/3, rect, height/3, 10, 10);
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
                compareChord = new Chord("#", false); // create a compareChord that will always be different from the currentChord -> needed in order to include the last beat
            }
            if(currentChord != null){
                if(!Chord.chordsEqual(currentChord, compareChord)){
                    if(getCalcMelody().findKeyNoteIndex(Melody.extractActualNoteName(currentChord.getRootNote())) != -1){ // rootNote == keyNote
                        g.setColor(Main.keyNoteColors[getCalcMelody().findKeyNoteIndex(Melody.extractActualNoteName(currentChord.getRootNote()))]);
                    } else { // rootNote == extraNote
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    drawStartingXCoordinate = clear*(beginningIndex+1)+rect*(beginningIndex);
                    drawWidth = clear*(i-beginningIndex)+rect*(i-beginningIndex+1);
                    g.drawRoundRect(drawStartingXCoordinate, height/2-height/15, drawWidth, height/8, 10, 10);
                    g.drawString(currentChord.getRootNote(), drawStartingXCoordinate+(drawWidth/2)-(g.getFontMetrics().stringWidth(currentChord.getRootNote())/2),height/2+(g.getFontMetrics().getHeight()/4));

                    // create black border around rootNote String
                    // drawTextBorder((Graphics2D)g, currentChord.getRootNote(), Color.BLACK, drawStartingXCoordinate+(drawWidth/2)-(g.getFontMetrics().stringWidth(currentChord.getRootNote())/2), height/2+(g.getFontMetrics().getHeight()/4));

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

    private void drawTextBorder(Graphics2D g2d, String text, Color borderColor, int x, int y){
        g2d.translate(x, y); // TODO: fix this
        // create glyph vector from text
        GlyphVector glyphVector = getFont().createGlyphVector(g2d.getFontRenderContext(), text);
        // get shape object
        Shape textShape = glyphVector.getOutline();
        // activate anti aliasing for text rendering (looks nicer)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // draw outline
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(0.5f));
        g2d.draw(textShape);
        /*
        // fill shape
        if(getCalcMelody().findKeyNoteIndex(Melody.extractActualNoteName(text)) != -1){ // rootNote == keyNote
            g2d.setColor(Main.keyNoteColors[getCalcMelody().findKeyNoteIndex(Melody.extractActualNoteName(text))]);
        } else {
            g2d.setColor(Color.LIGHT_GRAY); // rootNote == extraNote
        }
        g2d.fill(textShape);
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
