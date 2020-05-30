import javax.swing.*;
import java.awt.*;

public class GUI{
    
    private Melody melody;
    private JFrame frame;
    private JPanel frameBorderPanel, melodyDisplay, extraInput, keyParameters;
    private JComboBox keyCB, majorCB, timeSigCB, numberMeasuresCB, smallesSubdivCB;
    private JButton enterRhythmB, enterChordsB, createMelodyB, playMelodyB;
    private JLabel keyL, melodyL, timeSigL, numberMeasuresL, smallestSubdivL;
    
    public GUI(){
        frame = new JFrame("MelodyMaker");
        frameBorderPanel = new JPanel();
        frameBorderPanel.setLayout(new BorderLayout());
        melodyDisplay = new JPanel();
        melodyDisplay.setLayout(new BorderLayout());
        extraInput = new JPanel();
        extraInput.setLayout(new GridLayout(5,1));
        keyParameters = new JPanel();
        keyParameters.setLayout(new GridLayout(1,3));
        keyCB = new JComboBox(new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"});
        majorCB = new JComboBox(new String[]{"Major", "Minor"}); // TODO: Could add other modes
        timeSigCB = new JComboBox(new String[]{"4/4", "3/4", "6/4", "2/4", "5/4", "7/4", "7/8", "15/16"});
        numberMeasuresCB = new JComboBox(new String[]{"1", "2", "3", "4"});
        smallesSubdivCB = new JComboBox(new String[]{"Quarter notes", "Eighth notes", "Sixteenth Notes", "Thirty second notes"}); // TODO: replace strings with images
        enterRhythmB = new JButton("Enter rhythm");
        enterChordsB = new JButton("Enter chord progression"); // TODO: Add chord creation function
        createMelodyB = new JButton("Create melody");
        playMelodyB = new JButton("Play melody");
        keyL = new JLabel("Key: ");
        melodyL = new JLabel("Melody: ");
        timeSigL = new JLabel("Time Signature: ");
        numberMeasuresL = new JLabel("Number of measures: ");
        smallestSubdivL = new JLabel("Smallest Subdivision: ");
    }
}
