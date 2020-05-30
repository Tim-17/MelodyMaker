import javax.swing.*;
import java.awt.*;

public class GUI{
    
    private Melody melody;
    private JFrame frame;
    private JPanel frameBorderPanel, userInput, extraInput, keyChoice, rhythmDisplay, chordsDisplay, fillPanel, keyParameters, melodyDisplay, sheetMusic;
    private JComboBox keyCB, majorCB, timeSigCB, numberMeasuresCB, smallesSubdivCB;
    private JButton enterRhythmB, enterChordsB, createMelodyB, playMelodyB;
    private JLabel keyL, melodyL, timeSigL, numberMeasuresL, smallestSubdivL;
    
    public GUI(){
        frame = new JFrame("MelodyMaker");
        frameBorderPanel = new JPanel();
        frameBorderPanel.setLayout(new BorderLayout());
        userInput = new JPanel();
        userInput.setLayout(new GridLayout(1,2));
        extraInput = new JPanel();
        extraInput.setLayout(new GridLayout(7,1));
        keyChoice = new JPanel();
        keyChoice.setLayout(new GridLayout(1,2));
        rhythmDisplay = new JPanel();
        chordsDisplay = new JPanel();
        fillPanel = new JPanel();
        fillPanel.setLayout(new GridLayout(2,1));
        keyParameters = new JPanel();
        keyParameters.setLayout(new GridLayout(2,3));
        melodyDisplay = new JPanel();
        melodyDisplay.setLayout(new BorderLayout());
        sheetMusic = new JPanel();
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

        frame.add(frameBorderPanel);
        frame.setLocationRelativeTo(null);
        frame.setSize(500,350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frameBorderPanel.add(userInput, BorderLayout.CENTER);
        frameBorderPanel.add(melodyDisplay, BorderLayout.SOUTH);

        userInput.add(extraInput);
        userInput.add(fillPanel);

        extraInput.add(keyL);
        extraInput.add(keyChoice);
        extraInput.add(enterRhythmB);
        extraInput.add(rhythmDisplay);
        extraInput.add(enterChordsB);
        extraInput.add(chordsDisplay);
        extraInput.add(createMelodyB);

        keyChoice.add(keyCB);
        keyChoice.add(majorCB);

        fillPanel.add(keyParameters);
        fillPanel.add(new JLabel());

        keyParameters.add(timeSigL);
        keyParameters.add(numberMeasuresL);
        keyParameters.add(smallestSubdivL);
        keyParameters.add(timeSigCB);
        keyParameters.add(numberMeasuresCB);
        keyParameters.add(smallesSubdivCB);

        melodyDisplay.add(melodyL, BorderLayout.NORTH);
        melodyDisplay.add(sheetMusic, BorderLayout.CENTER);
        melodyDisplay.add(playMelodyB, BorderLayout.SOUTH);
    }
}
