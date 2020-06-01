import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI{

    private ImageIcon whole, half, quarter, eighth, sixteenth, thirtysecond;
    private Melody melody;
    private JFrame frame, rhythmInput, chordsInput;
    private JPanel frameBorderPanel, userInput, extraInput, keyChoice, rhythmDisplay, chordsDisplay, fillPanel, keyParameters, melodyDisplay, sheetMusic;
    private JComboBox keyCB, majorCB, timeSigCB, numberMeasuresCB, smallestSubdivCB;
    private JButton enterRhythmB, deleteRhythm, enterChordsB, deleteChords, createMelodyB, playMelodyB;
    private JLabel keyL, melodyL, timeSigL, numberMeasuresL, smallestSubdivL;
    private boolean rhythmEntered, chordsEntered;
    
    public GUI(){
        // Initialising

        try{
            whole = new ImageIcon(ImageIO.read(new File("res/edits/whole_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            half = new ImageIcon(ImageIO.read(new File("res/edits/half_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            quarter = new ImageIcon(ImageIO.read(new File("res/edits/quarter_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            eighth = new ImageIcon(ImageIO.read(new File("res/edits/eighth_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            sixteenth = new ImageIcon(ImageIO.read(new File("res/edits/sixteenth_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            thirtysecond = new ImageIcon(ImageIO.read(new File("res/edits/thirtysecond_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
        }
        catch(Exception e){
            System.out.println("Oooops, something went wrong!");
        }

        frame = new JFrame("MelodyMaker");
        rhythmInput = new JFrame("Enter your rhythm");
        chordsInput = new JFrame("Enter your chords");
        frameBorderPanel = new JPanel();
        frameBorderPanel.setLayout(new BorderLayout());
        userInput = new JPanel();
        userInput.setLayout(new GridLayout(1,2));
        extraInput = new JPanel();
        extraInput.setLayout(new GridLayout(7,1));
        keyChoice = new JPanel();
        keyChoice.setLayout(new GridLayout(1,2));
        rhythmDisplay = new JPanel();
        rhythmDisplay.setLayout(new BorderLayout());
        chordsDisplay = new JPanel();
        chordsDisplay.setLayout(new BorderLayout());
        fillPanel = new JPanel();
        fillPanel.setLayout(new GridLayout(2,1));
        keyParameters = new JPanel();
        keyParameters.setLayout(new GridLayout(2,3));
        melodyDisplay = new JPanel();
        melodyDisplay.setLayout(new BorderLayout());
        sheetMusic = new JPanel();
        keyCB = new JComboBox(new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"});
        keyCB.setMaximumRowCount(12);
        majorCB = new JComboBox(new String[]{"Major", "Minor"}); // TODO: Could add other modes
        timeSigCB = new JComboBox(new String[]{"4/4", "3/4", "6/4", "2/4", "5/4", "7/4", "7/8", "15/16"});
        timeSigCB.setSelectedIndex(0);
        numberMeasuresCB = new JComboBox(new String[]{"1", "2", "3", "4"});
        numberMeasuresCB.setSelectedIndex(0);
        smallestSubdivCB = new JComboBox(new ImageIcon[]{whole, half, quarter, eighth, sixteenth, thirtysecond});
        smallestSubdivCB.setSelectedIndex(4);
        enterRhythmB = new JButton("Enter rhythm");
        deleteRhythm = new JButton("Delete rhythm");
        deleteRhythm.setVisible(false);
        enterChordsB = new JButton("Enter chord progression"); // TODO: Add chord creation function
        deleteChords = new JButton("Delete chords");
        deleteChords.setVisible(false);
        createMelodyB = new JButton("Create melody");
        playMelodyB = new JButton("Play melody");
        keyL = new JLabel("Key: ");
        melodyL = new JLabel("Melody: ");
        timeSigL = new JLabel("Time Signature: ");
        numberMeasuresL = new JLabel("Number of measures: ");
        smallestSubdivL = new JLabel("Smallest Subdivision: ");
        setRhythmEntered(false);
        setChordsEntered(false);

        // GUI structuring

        frame.add(frameBorderPanel);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000,700);
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

        rhythmInput.setSize(500, 250);
        rhythmInput.setResizable(false);
        rhythmInput.setLocationRelativeTo(frame);
        rhythmInput.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        rhythmInput.setVisible(false);

        chordsInput.setSize(500, 250);
        chordsInput.setResizable(false);
        chordsInput.setLocationRelativeTo(frame);
        chordsInput.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        chordsInput.setVisible(false);

        rhythmDisplay.add(deleteRhythm, BorderLayout.SOUTH);
        chordsDisplay.add(deleteChords, BorderLayout.SOUTH);

        fillPanel.add(keyParameters);
        fillPanel.add(new JLabel());

        keyParameters.add(timeSigL);
        keyParameters.add(numberMeasuresL);
        keyParameters.add(smallestSubdivL);
        keyParameters.add(timeSigCB);
        keyParameters.add(numberMeasuresCB);
        keyParameters.add(smallestSubdivCB);

        melodyDisplay.add(melodyL, BorderLayout.NORTH);
        melodyDisplay.add(sheetMusic, BorderLayout.CENTER);
        melodyDisplay.add(playMelodyB, BorderLayout.SOUTH);

        // ActionListeners

        enterRhythmB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteRhythm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        enterChordsB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteChords.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        createMelodyB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        playMelodyB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public boolean getRhythmEntered() {
        return this.rhythmEntered;
    }

    public void setRhythmEntered(boolean newRhythmEntered) {
        this.rhythmEntered = newRhythmEntered;
    }

    public boolean getChordsEntered() {
        return this.chordsEntered;
    }

    public void setChordsEntered(boolean newMelodyEntered) {
        this.chordsEntered = newMelodyEntered;
    }
}
