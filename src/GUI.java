import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import static java.awt.Toolkit.getDefaultToolkit;

public class GUI{

    private ImageIcon whole, half, quarter, eighth, sixteenth, thirtysecond;
    private Melody melody;
    private JFrame frame, rhythmInput, chordsInput;
    private JPanel frameBorderPanel, chordsInputPanel, userInput, extraInput, keyChoice, rhythmDisplay, rhythmFramePanel, chordsDisplay, chordsFramePanel, fillPanel, keyParameters, melodyDisplay, sheetMusic; // TODO: chordsInputPanel -> MyPanel
    private RhythmPanel rhythmInputPanel;
    private JComboBox keyCB, majorCB, timeSigCB, numberMeasuresCB, smallestSubdivCB;
    private MutableComboBoxModel subdivModel;
    private JButton enterRhythmB, saveRhythmB, deleteRhythm, enterChordsB, saveChordsB, deleteChords, createMelodyB, playMelodyB;
    private JLabel keyL, melodyL, timeSigL, numberMeasuresL, smallestSubdivL;
    private boolean rhythmEntered, chordsEntered;
    private boolean[] rhythm;
    private int length;
    private static final int MAIN_FRAME_WIDTH = (int)(getDefaultToolkit().getScreenSize().getWidth()*0.75);
    private static final int MAIN_FRAME_HEIGHT = (int)(getDefaultToolkit().getScreenSize().getHeight()*0.75);
    private static final int OTHER_FRAME_WIDTH = (int)(getDefaultToolkit().getScreenSize().getWidth()*0.5);
    private static final int OTHER_FRAME_HEIGHT = (int)(getDefaultToolkit().getScreenSize().getHeight()*0.5);
    
    public GUI(){

        // Initialising
        try{
            whole = new ImageIcon(ImageIO.read(new File("res/edits/whole_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            half = new ImageIcon(ImageIO.read(new File("res/edits/half_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            quarter = new ImageIcon(ImageIO.read(new File("res/edits/quarter_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            eighth = new ImageIcon(ImageIO.read(new File("res/edits/eighth_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            sixteenth = new ImageIcon(ImageIO.read(new File("res/edits/sixteenth_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
            thirtysecond = new ImageIcon(ImageIO.read(new File("res/edits/thirtysecond_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH));
        } catch(Exception e){
            System.out.println("Oooops, something went wrong!");
        }

        frame = new JFrame("MelodyMaker");
        rhythmInput = new JFrame("Enter your rhythm");
        chordsInput = new JFrame("Enter your chords");
        frameBorderPanel = new JPanel();
        frameBorderPanel.setLayout(new BorderLayout());
        rhythmInputPanel = new RhythmPanel();
        chordsInputPanel = new JPanel(); // TODO: declare as MyPanel
        userInput = new JPanel();
        userInput.setLayout(new GridLayout(1,2));
        extraInput = new JPanel();
        extraInput.setLayout(new GridLayout(7,1));
        keyChoice = new JPanel();
        keyChoice.setLayout(new GridLayout(1,2));
        rhythmDisplay = new JPanel();
        rhythmDisplay.setLayout(new BorderLayout());
        rhythmFramePanel = new JPanel();
        rhythmFramePanel.setPreferredSize(new Dimension(getOTHER_FRAME_WIDTH(), getOTHER_FRAME_HEIGHT()));
        rhythmFramePanel.setLayout(new BorderLayout());
        chordsDisplay = new JPanel();
        chordsDisplay.setLayout(new BorderLayout());
        chordsFramePanel = new JPanel();
        chordsFramePanel.setPreferredSize(new Dimension(getOTHER_FRAME_WIDTH(), getOTHER_FRAME_HEIGHT()));
        chordsFramePanel.setLayout(new BorderLayout());
        fillPanel = new JPanel();
        fillPanel.setLayout(new GridLayout(2,1));
        keyParameters = new JPanel();
        keyParameters.setLayout(new GridLayout(2,3));
        melodyDisplay = new JPanel();
        melodyDisplay.setLayout(new BorderLayout());
        sheetMusic = new JPanel();
        keyCB = new JComboBox(new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"});
        keyCB.setSelectedIndex(0);
        keyCB.setMaximumRowCount(12);
        majorCB = new JComboBox(new String[]{"Major", "Minor"}); // TODO: Could add other modes
        majorCB.setSelectedIndex(0);
        timeSigCB = new JComboBox(new String[]{"4/4", "3/4", "6/4", "2/4", "5/4", "7/4", "7/8", "15/16"});
        timeSigCB.setSelectedIndex(0);
        numberMeasuresCB = new JComboBox(new String[]{"1", "2", "3", "4"});
        numberMeasuresCB.setSelectedIndex(0);
        subdivModel = new DefaultComboBoxModel();
        subdivModel.addElement(whole);
        subdivModel.addElement(half);
        subdivModel.addElement(quarter);
        subdivModel.addElement(eighth);
        subdivModel.addElement(sixteenth);
        subdivModel.addElement(thirtysecond);
        smallestSubdivCB = new JComboBox(subdivModel);
        smallestSubdivCB.setSelectedIndex(4);
        enterRhythmB = new JButton("Enter rhythm");
        saveRhythmB = new JButton("Save rhythm");
        deleteRhythm = new JButton("Delete rhythm");
        deleteRhythm.setVisible(false);
        enterChordsB = new JButton("Enter chord progression"); // TODO: Add chord creation function
        saveChordsB = new JButton("Save chords");
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

        setLength((String)timeSigCB.getSelectedItem(), numberMeasuresCB.getSelectedIndex()+1, 16);

        // GUI structuring

        frame.add(frameBorderPanel);
        try{
            frame.setIconImage(ImageIO.read(new File("res/edits/quarter_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH)); // Image -> Quarter
        } catch (Exception e){
            System.out.println("Oooops, something went wrong!");
        }
        frame.setSize(getMAIN_FRAME_WIDTH(), getMAIN_FRAME_HEIGHT());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        rhythmInput.add(rhythmFramePanel);
        rhythmInput.pack();
        rhythmInput.setResizable(false);
        rhythmInput.setLocationRelativeTo(frame);
        rhythmInput.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        rhythmInput.setVisible(false);

        rhythmFramePanel.add(rhythmInputPanel, BorderLayout.CENTER);
        rhythmFramePanel.add(saveRhythmB, BorderLayout.SOUTH); // TODO: add FlowLayout.RIGHT

        chordsInput.add(chordsFramePanel);
        chordsInput.pack();
        chordsInput.setResizable(false);
        chordsInput.setLocationRelativeTo(frame);
        chordsInput.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        chordsInput.setVisible(false);

        chordsFramePanel.add(chordsInputPanel, BorderLayout.CENTER);
        chordsFramePanel.add(saveChordsB, BorderLayout.SOUTH); // TODO: add FlowLayout.RIGHT

        rhythmDisplay.add(deleteRhythm, BorderLayout.SOUTH); // add FlowLayout.RIGHT
        chordsDisplay.add(deleteChords, BorderLayout.SOUTH); // add FlowLayout.RIGHT

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

        frame.setVisible(true);

        // ActionListeners

        enterRhythmB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                rhythmInput.setVisible(true);
                System.out.println("Tatsächliche Breite: " + rhythmInput.getWidth());
            }
        });

        rhythmInputPanel.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e){
                int clear = (getOTHER_FRAME_WIDTH()/4)/(getLength()+1);
                int rect = (getOTHER_FRAME_WIDTH()*3/4)/getLength();
                for(int i = 1; i <= getLength(); i++){
                    if(clear*i+rect*(i-1) <= e.getX() & e.getX() <= clear*i+rect*(i-1)+rect && getOTHER_FRAME_HEIGHT()/3 <= e.getY() && e.getY() <= getOTHER_FRAME_HEIGHT()*2/3){
                        getRhythm()[i-1] = !getRhythm()[i-1];
                        // TODO: color true rectangles
                    }
                }
            }
            public void mousePressed(MouseEvent e){

            }
            public void mouseReleased(MouseEvent e){

            }
            public void mouseEntered(MouseEvent e){

            }
            public void mouseExited(MouseEvent e){

            }
        });

        deleteRhythm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });

        enterChordsB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });

        deleteChords.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });

        createMelodyB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setMelody(new Melody(getLength()));
            }
        });

        playMelodyB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });

        timeSigCB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // Delete all elements in smallestSubdivCB
                int size = subdivModel.getSize();
                for(int i = 0; i < size; i++){
                    subdivModel.removeElementAt(0);
                }
                // Add all fitting elements to smallestSubdivCB
                switch(timeSigCB.getSelectedIndex()){
                    case 0:
                        subdivModel.addElement(whole);
                        subdivModel.addElement(half);
                        subdivModel.addElement(quarter);
                        subdivModel.addElement(eighth);
                        subdivModel.addElement(sixteenth);
                        subdivModel.addElement(thirtysecond);
                        smallestSubdivCB.setModel(subdivModel);
                        smallestSubdivCB.setSelectedIndex(4);
                        break;
                    case 2:
                    case 3:
                        subdivModel.addElement(half);
                        subdivModel.addElement(quarter);
                        subdivModel.addElement(eighth);
                        subdivModel.addElement(sixteenth);
                        subdivModel.addElement(thirtysecond);
                        smallestSubdivCB.setModel(subdivModel);
                        smallestSubdivCB.setSelectedIndex(3);
                        break;
                    case 1:
                    case 4:
                    case 5:
                        subdivModel.addElement(quarter);
                        subdivModel.addElement(eighth);
                        subdivModel.addElement(sixteenth);
                        subdivModel.addElement(thirtysecond);
                        smallestSubdivCB.setModel(subdivModel);
                        smallestSubdivCB.setSelectedIndex(2);
                        break;
                    case 6:
                        subdivModel.addElement(eighth);
                        subdivModel.addElement(sixteenth);
                        subdivModel.addElement(thirtysecond);
                        smallestSubdivCB.setModel(subdivModel);
                        smallestSubdivCB.setSelectedIndex(1);
                        break;
                    case 7:
                        subdivModel.addElement(sixteenth);
                        subdivModel.addElement(thirtysecond);
                        smallestSubdivCB.setModel(subdivModel);
                        smallestSubdivCB.setSelectedIndex(0);
                        break;
                    default:
                        break;
                }
                updateLength();
            }
        });

        numberMeasuresCB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateLength();
            }
        });

        smallestSubdivCB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateLength();
            }
        });
    }

    // Getters & Setters

    public static int getMAIN_FRAME_WIDTH() {
        return MAIN_FRAME_WIDTH;
    }

    public static int getMAIN_FRAME_HEIGHT() {
        return MAIN_FRAME_HEIGHT;
    }

    public static int getOTHER_FRAME_WIDTH() {
        return OTHER_FRAME_WIDTH;
    }

    public static int getOTHER_FRAME_HEIGHT() {
        return OTHER_FRAME_HEIGHT;
    }

    private Melody getMelody() {
        return this.melody;
    }

    private void setMelody(Melody melody) {
        this.melody = melody;
    }

    private boolean getRhythmEntered() {
        return this.rhythmEntered;
    }

    private void setRhythmEntered(boolean newRhythmEntered) {
        this.rhythmEntered = newRhythmEntered;
    }

    private boolean getChordsEntered() {
        return this.chordsEntered;
    }

    private void setChordsEntered(boolean newMelodyEntered) {
        this.chordsEntered = newMelodyEntered;
    }

    public boolean[] getRhythm() {
        return this.rhythm;
    }

    public void setRhythm(boolean[] rhythm) {
        this.rhythm = rhythm;
    }

    private int getLength() {
        return this.length;
    }

    private void setLength(String timeSig, int numberMeasures, int smallestSubdiv) {
        if(timeSigCB.getSelectedIndex() != 7){
            this.length = numberMeasures * Integer.parseInt(String.valueOf(timeSig.charAt(0))) * smallestSubdiv / Integer.parseInt(String.valueOf(timeSig.charAt(2)));
        }
        else {
            this.length = numberMeasures * 15 * smallestSubdiv / 16; // fixes bug with 15/16
        }
    }

    // Other methods

    private void updateLength(){
        if(smallestSubdivCB.getSelectedItem() == whole){
            setLength((String)timeSigCB.getSelectedItem(), numberMeasuresCB.getSelectedIndex()+1, 1);
        } else if(smallestSubdivCB.getSelectedItem() == half){
            setLength((String)timeSigCB.getSelectedItem(), numberMeasuresCB.getSelectedIndex()+1, 2);
        } else if(smallestSubdivCB.getSelectedItem() == quarter){
            setLength((String)timeSigCB.getSelectedItem(), numberMeasuresCB.getSelectedIndex()+1, 4);
        } else if(smallestSubdivCB.getSelectedItem() == eighth){
            setLength((String)timeSigCB.getSelectedItem(), numberMeasuresCB.getSelectedIndex()+1, 8);
        } else if(smallestSubdivCB.getSelectedItem() == sixteenth){
            setLength((String)timeSigCB.getSelectedItem(), numberMeasuresCB.getSelectedIndex()+1, 16);
        } else if(smallestSubdivCB.getSelectedItem() == thirtysecond){
            setLength((String)timeSigCB.getSelectedItem(), numberMeasuresCB.getSelectedIndex()+1, 32);
        }
        setRhythm(new boolean[getLength()]);
        rhythmInputPanel.setLength(getLength());
        rhythmInputPanel.repaint();
    }
}
