import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI{

    private ImageIcon whole, half, quarter, eighth, sixteenth, thirtysecond;
    private Melody melody, calcMelody;
    private MusicPlayer musicPlayer;
    private JFrame frame, rhythmInput, chordsInput, oneChordInput;
    private JPanel frameBorderPanel, userInput, extraInput, keyChoice, rhythmDisplay, rhythmFramePanel, chordsDisplay, chordsFramePanel, oneChordPanel, oneChordContentPanel, keyNotesCheckBoxPanel, extraNotesCheckBoxPanel, fillPanel, keyParameters, melodyDisplay, sheetMusic;
    private RhythmPanel rhythmInputPanel;
    private ChordsPanel chordsInputPanel;
    private JComboBox keyCB, majorCB, timeSigCB, numberMeasuresCB, smallestSubdivCB, chordBaseNoteCB;
    private MutableComboBoxModel subdivModel, chordBaseNoteModel;
    private ComboBoxColorRenderer comboBoxColorRenderer;
    private JButton enterRhythmB, saveRhythmB, deleteRhythm, enterChordsB, saveChordsB, saveOneChordB, deleteChords, createMelodyB, playMelodyB;
    private JLabel keyL, melodyL, timeSigL, numberMeasuresL, smallestSubdivL, baseNoteL, keyNotesL, extraNotesL;
    private JCheckBox keyNoteCheckBox2, keyNoteCheckBox3, keyNoteCheckBox4, keyNoteCheckBox5, keyNoteCheckBox6, keyNoteCheckBox7, keyNoteCheckBox8, extraNoteCheckBox1, extraNoteCheckBox2, extraNoteCheckBox3, extraNoteCheckBox4, extraNoteCheckBox5;
    private JCheckBox[] keyNotesCheckBoxesArray, extraNotesCheckBoxesArray;
    private boolean rhythmEntered, chordsEntered, editChord;
    private boolean[] rhythm, bufferRhythm;
    private Chord[] chords;
    private Chord bufferOneChord;
    private int length, chordBeginningIndex, chordEndingIndex;
    
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

        musicPlayer = new MusicPlayer();
        frame = new JFrame("MelodyMaker");
        rhythmInput = new JFrame("Enter your rhythm");
        chordsInput = new JFrame("Enter your chords");
        oneChordInput = new JFrame("Build your chord");
        frameBorderPanel = new JPanel();
        frameBorderPanel.setLayout(new BorderLayout());
        rhythmInputPanel = new RhythmPanel();
        chordsInputPanel = new ChordsPanel();
        userInput = new JPanel();
        userInput.setLayout(new GridLayout(1,2));
        extraInput = new JPanel();
        extraInput.setLayout(new GridLayout(7,1));
        keyChoice = new JPanel();
        keyChoice.setLayout(new GridLayout(1,2));
        rhythmDisplay = new JPanel();
        rhythmDisplay.setLayout(new BorderLayout());
        rhythmFramePanel = new JPanel();
        rhythmFramePanel.setPreferredSize(new Dimension(Main.OTHER_FRAME_WIDTH, Main.OTHER_FRAME_HEIGHT));
        rhythmFramePanel.setLayout(new BorderLayout());
        chordsDisplay = new JPanel();
        chordsDisplay.setLayout(new BorderLayout());
        chordsFramePanel = new JPanel();
        chordsFramePanel.setPreferredSize(new Dimension(Main.OTHER_FRAME_WIDTH, Main.OTHER_FRAME_HEIGHT));
        chordsFramePanel.setLayout(new BorderLayout());
        oneChordPanel = new JPanel();
        oneChordPanel.setPreferredSize(new Dimension(Main.CHORD_FRAME_WIDTH, Main.CHORD_FRAME_HEIGHT));
        oneChordPanel.setLayout(new BorderLayout());
        oneChordContentPanel = new JPanel();
        oneChordContentPanel.setLayout(new GridLayout(2,3));
        keyNotesCheckBoxPanel = new JPanel();
        keyNotesCheckBoxPanel.setLayout(new GridLayout(7,1));
        extraNotesCheckBoxPanel = new JPanel();
        extraNotesCheckBoxPanel.setLayout(new GridLayout(5,1));
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
        enterChordsB = new JButton("Enter chord progression"); // TODO: Add chord only creation function
        saveChordsB = new JButton("Save chords");
        saveOneChordB = new JButton("Save chord");
        deleteChords = new JButton("Delete chords");
        deleteChords.setVisible(false);
        createMelodyB = new JButton("Create melody");
        playMelodyB = new JButton("Play melody");
        keyL = new JLabel("Key: ");
        melodyL = new JLabel("Melody: ");
        timeSigL = new JLabel("Time Signature: ");
        numberMeasuresL = new JLabel("Number of measures: ");
        smallestSubdivL = new JLabel("Smallest Subdivision: ");
        baseNoteL = new JLabel("Base note");
        keyNotesL = new JLabel("Key notes");
        extraNotesL = new JLabel("Extra notes");
        keyNoteCheckBox2 = new JCheckBox("2");
        keyNoteCheckBox3 = new JCheckBox("3");
        keyNoteCheckBox4 = new JCheckBox("4");
        keyNoteCheckBox5 = new JCheckBox("5");
        keyNoteCheckBox6 = new JCheckBox("6");
        keyNoteCheckBox7 = new JCheckBox("7");
        keyNoteCheckBox8 = new JCheckBox("8");
        keyNoteCheckBox8.setVisible(false);
        extraNoteCheckBox1 = new JCheckBox("1");
        extraNoteCheckBox2 = new JCheckBox("2");
        extraNoteCheckBox3 = new JCheckBox("3");
        extraNoteCheckBox4 = new JCheckBox("4");
        extraNoteCheckBox5 = new JCheckBox("5");
        keyNotesCheckBoxesArray = new JCheckBox[7];
        keyNotesCheckBoxesArray[0] = keyNoteCheckBox2;
        keyNotesCheckBoxesArray[1] = keyNoteCheckBox3;
        keyNotesCheckBoxesArray[2] = keyNoteCheckBox4;
        keyNotesCheckBoxesArray[3] = keyNoteCheckBox5;
        keyNotesCheckBoxesArray[4] = keyNoteCheckBox6;
        keyNotesCheckBoxesArray[5] = keyNoteCheckBox7;
        keyNotesCheckBoxesArray[6] = keyNoteCheckBox8;
        extraNotesCheckBoxesArray = new JCheckBox[5];
        extraNotesCheckBoxesArray[0] = extraNoteCheckBox1;
        extraNotesCheckBoxesArray[1] = extraNoteCheckBox2;
        extraNotesCheckBoxesArray[2] = extraNoteCheckBox3;
        extraNotesCheckBoxesArray[3] = extraNoteCheckBox4;
        extraNotesCheckBoxesArray[4] = extraNoteCheckBox5;
        setRhythmEntered(false);
        setChordsEntered(false);

        setLength((String)timeSigCB.getSelectedItem(), numberMeasuresCB.getSelectedIndex()+1, 16);
        setCalcMelody(new Melody(getLength()));
        setRhythm(new boolean[getLength()]);
        setChords(new Chord[getLength()]);

        chordBaseNoteModel = new DefaultComboBoxModel();
        chordBaseNoteCB = new JComboBox(chordBaseNoteModel);
        comboBoxColorRenderer = new ComboBoxColorRenderer(chordBaseNoteCB.getRenderer(), getCalcMelody());
        chordBaseNoteCB.setRenderer(comboBoxColorRenderer);
        chordBaseNoteCB.setMaximumRowCount(12);
        updateChordBaseNoteModel();
        updateCheckBoxes();


        // GUI structuring

        frame.add(frameBorderPanel);
        try{
            frame.setIconImage(ImageIO.read(new File("res/edits/quarter_transparent.png")).getScaledInstance(16, 32, Image.SCALE_SMOOTH)); // Image -> Quarter
        } catch (Exception e){
            System.out.println("Oooops, something went wrong!");
        }
        frame.setSize(Main.MAIN_FRAME_WIDTH, Main.MAIN_FRAME_HEIGHT);
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

        oneChordInput.add(oneChordPanel);
        oneChordInput.pack();
        oneChordInput.setResizable(false);
        oneChordInput.setLocationRelativeTo(chordsInput);
        oneChordInput.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        oneChordInput.setVisible(false);

        oneChordPanel.add(oneChordContentPanel, BorderLayout.CENTER);
        oneChordPanel.add(saveOneChordB, BorderLayout.SOUTH);

        oneChordContentPanel.add(baseNoteL);
        oneChordContentPanel.add(keyNotesL);
        oneChordContentPanel.add(extraNotesL);
        oneChordContentPanel.add(chordBaseNoteCB);
        oneChordContentPanel.add(keyNotesCheckBoxPanel);
        oneChordContentPanel.add(extraNotesCheckBoxPanel);

        keyNotesCheckBoxPanel.add(keyNoteCheckBox2);
        keyNotesCheckBoxPanel.add(keyNoteCheckBox3);
        keyNotesCheckBoxPanel.add(keyNoteCheckBox4);
        keyNotesCheckBoxPanel.add(keyNoteCheckBox5);
        keyNotesCheckBoxPanel.add(keyNoteCheckBox6);
        keyNotesCheckBoxPanel.add(keyNoteCheckBox7);
        keyNotesCheckBoxPanel.add(keyNoteCheckBox8);

        extraNotesCheckBoxPanel.add(extraNoteCheckBox1);
        extraNotesCheckBoxPanel.add(extraNoteCheckBox2);
        extraNotesCheckBoxPanel.add(extraNoteCheckBox3);
        extraNotesCheckBoxPanel.add(extraNoteCheckBox4);
        extraNotesCheckBoxPanel.add(extraNoteCheckBox5);

        rhythmDisplay.add(deleteRhythm, BorderLayout.SOUTH); // TODO: add FlowLayout.RIGHT
        chordsDisplay.add(deleteChords, BorderLayout.SOUTH); // TODO: add FlowLayout.RIGHT

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
            }
        });

        rhythmInput.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e){
                super.componentShown(e);
                // setBufferRhythm(getRhythm());
                setBufferRhythm(new boolean[getLength()]); // TODO: use this approach for every setter
                for(int i = 1; i <= getLength(); i++){
                    getBufferRhythm()[i-1] = getRhythm()[i-1];
                }
            }

            @Override
            public void componentHidden(ComponentEvent e){
                super.componentHidden(e);
                rhythmInputPanel.setRhythm(getRhythm());
            }
        });

        rhythmInputPanel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e){
                int clear = (Main.OTHER_FRAME_WIDTH/4)/(getLength()+1);
                int rect = (Main.OTHER_FRAME_WIDTH*3/4)/getLength();
                for(int i = 1; i <= getLength(); i++){
                    if(clear*i+rect*(i-1) <= e.getX() & e.getX() <= clear*i+rect*(i-1)+rect && Main.OTHER_FRAME_HEIGHT/3 <= e.getY() && e.getY() <= Main.OTHER_FRAME_HEIGHT*2/3){
                        getBufferRhythm()[i-1] = !getBufferRhythm()[i-1];
                        break;
                    }
                }
                rhythmInputPanel.setRhythm(getBufferRhythm());
                rhythmInputPanel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e){

            }

            @Override
            public void mouseReleased(MouseEvent e){

            }

            @Override
            public void mouseEntered(MouseEvent e){

            }

            @Override
            public void mouseExited(MouseEvent e){

            }
        });

        saveRhythmB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setRhythm(getBufferRhythm());
                rhythmInput.setVisible(false);
                setRhythmEntered(false);
                for(boolean beat : getRhythm()){
                    if(beat){
                        setRhythmEntered(true);
                        deleteRhythm.setVisible(true);
                        enterRhythmB.setText("Edit rhythm");
                        break;
                    }
                }
                if(!getRhythmEntered()){
                    deleteRhythm.setVisible(false);
                    enterRhythmB.setText("Enter rhythm");
                }
            }
        });

        deleteRhythm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setRhythm(new boolean[getLength()]);
                setBufferRhythm(new boolean[getLength()]);
                rhythmInputPanel.setRhythm(getRhythm());
                rhythmInputPanel.repaint();
                setRhythmEntered(false);
                deleteRhythm.setVisible(false);
                enterRhythmB.setText("Enter rhythm");
            }
        });

        enterChordsB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                chordsInput.setVisible(true);
            }
        });

        chordsInputPanel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e){

            }

            @Override
            public void mousePressed(MouseEvent e){
                if(e.getButton() == MouseEvent.BUTTON1){
                    setEditChord(false);
                } else if(e.getButton() == MouseEvent.BUTTON3){
                    setEditChord(true);
                }
                setChordBeginningIndex(-1);
                setChordEndingIndex(-1);
                int clear = (Main.OTHER_FRAME_WIDTH/4)/(getLength()+1);
                int rect = (Main.OTHER_FRAME_WIDTH*3/4)/getLength();
                for(int i = 1; i <= getLength(); i++){
                    if(clear*i+rect*(i-1) <= e.getX() & e.getX() <= clear*i+rect*(i-1)+rect && Main.OTHER_FRAME_HEIGHT/3 <= e.getY() && e.getY() <= Main.OTHER_FRAME_HEIGHT*2/3){
                        setChordBeginningIndex(i-1);
                        break;
                    }
                }
            }

            // ^
            // |
            // TODO: add function to edit the selected area when dragged over with right mouse button
            // |
            // v

            @Override
            public void mouseReleased(MouseEvent e){
                int clear = (Main.OTHER_FRAME_WIDTH/4)/(getLength()+1);
                int rect = (Main.OTHER_FRAME_WIDTH*3/4)/getLength();
                for(int i = 1; i <= getLength(); i++){
                    if(clear*i+rect*(i-1) <= e.getX() & e.getX() <= clear*i+rect*(i-1)+rect && Main.OTHER_FRAME_HEIGHT/3 <= e.getY() && e.getY() <= Main.OTHER_FRAME_HEIGHT*2/3){
                        if((i-1) < getChordBeginningIndex()){
                            setChordEndingIndex(getChordBeginningIndex());
                            setChordBeginningIndex(i-1);
                        } else {
                            setChordEndingIndex(i-1);
                        }
                        break;
                    }
                }
                if(getChordBeginningIndex() != -1 && getChordEndingIndex() != -1){ // only try to make new chord / edit chord if user selected proper interval of beats
                    boolean openWindow = true;
                    if(getEditChord()){
                        if(getChordBeginningIndex() != getChordEndingIndex()){
                            for(int i = getChordBeginningIndex(); i < getChordEndingIndex(); i++){ // check if there are different chords (also [some chord] && null) in the interval in which the user would like to edit chords
                                                                                                                                            // TODO: make it possible to easily extend a chord's duration by right click-dragging it (only save this change when saveOneChordB is activated) -> different chords should only matter if it's not [some chord] && null
                                if(getChords()[i] != getChords()[i+1]){
                                    openWindow = false;
                                    break;
                                }
                            }
                        }
                        if(openWindow){
                            if(getChords()[getChordBeginningIndex()] == null){ // if no chord is entered as of now, the user can't edit a chord
                                setEditChord(false);
                            }
                        }
                    }
                    if(openWindow){
                        oneChordInput.setVisible(true);
                    }
                }
                System.out.println("editChord: " + getEditChord());
            }

            @Override
            public void mouseEntered(MouseEvent e){

            }

            @Override
            public void mouseExited(MouseEvent e){

            }
        });

        // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        oneChordInput.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                if(getEditChord()){
                    setBufferOneChord(getChords()[getChordBeginningIndex()]);
                } else {
                    setBufferOneChord(new Chord((String)chordBaseNoteCB.getSelectedItem()));
                }
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
            }
        });

        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

        chordBaseNoteCB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateCheckBoxes();
                getBufferOneChord().setBaseNote((String)chordBaseNoteCB.getSelectedItem()); // TODO: fix problem of keyNotesCheckBox.getText() being: [note] + " (" + [place in chord] + ")"
            }
        });

        // TODO: fix problem of keyNotesCheckBox.getText() being: [note] + " (" + [place in chord] + ")"

        // keyNotesCheckBoxes ActionListener
        for(int i = 0; i < keyNotesCheckBoxesArray.length; i++){
            int finalI = i; // i muss "effectively final" sein (warum auch immer)
            keyNotesCheckBoxesArray[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(getCalcMelody().findKeyNoteIndex((String)chordBaseNoteCB.getSelectedItem()) != -1){ // baseNote == keyNote
                        if(finalI != 6){ // put the baseNote (which is at the last index (of 6) in the keyNotesCheckBoxesArray) to the first index of the keyChordNoteArray
                            if(keyNotesCheckBoxesArray[finalI].isSelected()){
                                getBufferOneChord().getKeyChordNotes()[finalI+1] = keyNotesCheckBoxesArray[finalI].getText();
                            } else {
                                getBufferOneChord().getKeyChordNotes()[finalI+1] = null;
                            }
                        } else {
                            if(keyNotesCheckBoxesArray[finalI].isSelected()){
                                getBufferOneChord().getKeyChordNotes()[0] = keyNotesCheckBoxesArray[finalI].getText();
                            } else {
                                getBufferOneChord().getKeyChordNotes()[0] = null;
                            }
                        }
                    } else { // baseNote == extraNote -> copy keyNotesArray the way it is
                        if(keyNotesCheckBoxesArray[finalI].isSelected()){
                            getBufferOneChord().getKeyChordNotes()[finalI] = keyNotesCheckBoxesArray[finalI].getText();
                        } else {
                            getBufferOneChord().getKeyChordNotes()[finalI] = null;
                        }
                    }
                }
            });
        }

        // extraNotesCheckBoxes ActionListener
        for(int i = 0; i < extraNotesCheckBoxesArray.length; i++){
            int finalI = i; // i muss "effectively final" sein (warum auch immer)
            extraNotesCheckBoxesArray[i].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(getCalcMelody().findKeyNoteIndex((String)chordBaseNoteCB.getSelectedItem()) != -1){ // baseNote == keyNote -> copy extraNotesArray the way it is
                        if(extraNotesCheckBoxesArray[finalI].isSelected()){
                            getBufferOneChord().getExtraChordNotes()[finalI] = extraNotesCheckBoxesArray[finalI].getText();
                        } else {
                            getBufferOneChord().getExtraChordNotes()[finalI] = null;
                        }
                    } else { // baseNote == extraNote
                        if(finalI != 4){ // put the baseNote (which is at the last index (of 4) in the extraNotesCheckBoxesArray) to the first index of the extraChordNotesArray
                            if(extraNotesCheckBoxesArray[finalI].isSelected()){
                                getBufferOneChord().getExtraChordNotes()[finalI+1] = extraNotesCheckBoxesArray[finalI].getText();
                            } else {
                                getBufferOneChord().getExtraChordNotes()[finalI+1] = null;
                            }
                        } else {
                            if(extraNotesCheckBoxesArray[finalI].isSelected()){
                                getBufferOneChord().getExtraChordNotes()[0] = extraNotesCheckBoxesArray[finalI].getText();
                            } else {
                                getBufferOneChord().getExtraChordNotes()[0] = null;
                            }
                        }
                    }
                }
            });
        }

        saveChordsB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });

        deleteChords.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });

        // TODO: improve project structure by always using an instance of the Melody class to store all the rhythmic & chords information so that the GUI class actually only deals with GUI stuff

        createMelodyB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setMelody(new Melody(getLength()));
                if(majorCB.getSelectedIndex() == 0){
                    getMelody().createKeyNotes((String)keyCB.getSelectedItem(), true);
                } else {
                    getMelody().createKeyNotes((String)keyCB.getSelectedItem(), false);
                }
                if(getRhythmEntered()){
                    getMelody().setRhythm(getRhythm());
                } else {
                    getMelody().createRhythm();
                    setRhythm(getMelody().getRhythm());
                }
                if(getChordsEntered()){
                    // getMelody().createChordsMelody(getChords());
                } else {
                    getMelody().createMelody();
                }
                for(int i = 1; i <= getLength(); i++){
                    System.out.print("[" + getMelody().getMelody()[i-1] + "] ");
                }
                System.out.println("");
            }
        });

        playMelodyB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(getMelody() != null){
                    for(int i = 0; i < getLength(); i++){
                        if(getRhythm()[i]){
                            if(getMelody().findKeyNoteIndex(getMelody().getMelody()[i]) < getMelody().getChangeIndex()){
                                getMusicPlayer().playNote(getMusicPlayer().getNotes().get(getMelody().getMelody()[i]), 500);
                            } else {
                                getMusicPlayer().playNote(getMusicPlayer().getNotes().get(getMelody().getMelody()[i] + "'"), 500);
                            }
                        } else {
                            getMusicPlayer().playNote(getMusicPlayer().getNotes().get("click"), 500);
                        }
                        try{
                            Thread.sleep(500);
                        }
                        catch(Exception k) {
                            System.out.println("Whoooops...");
                        }
                    }
                }
            }
        });

        keyCB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateCalcMelodyNotes();
                updateChordBaseNoteModel();
            }
        });

        majorCB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateCalcMelodyNotes();
                updateChordBaseNoteModel();
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

    private Melody getMelody() {
        return this.melody;
    }

    private void setMelody(Melody melody) {
        this.melody = melody;
    }

    public Melody getCalcMelody() {
        return this.calcMelody;
    }

    public void setCalcMelody(Melody calcMelody) {
        this.calcMelody = calcMelody;
        updateCalcMelodyNotes();
    }

    public MusicPlayer getMusicPlayer(){
        return this.musicPlayer;
    }

    public void setMusicPlayer(MusicPlayer musicPlayer){
        this.musicPlayer = musicPlayer;
    }

    private boolean getRhythmEntered() {
        return this.rhythmEntered;
    }

    private void setRhythmEntered(boolean rhythmEntered) {
        this.rhythmEntered = rhythmEntered;
    }

    private boolean getChordsEntered() {
        return this.chordsEntered;
    }

    private void setChordsEntered(boolean chordsEntered) {
        this.chordsEntered = chordsEntered;
    }

    public boolean getEditChord(){
        return this.editChord;
    }

    public void setEditChord(boolean editChord) {
        this.editChord = editChord;
    }

    public boolean[] getRhythm() {
        return this.rhythm;
    }

    public void setRhythm(boolean[] rhythm) {
        this.rhythm = rhythm;
    }

    public boolean[] getBufferRhythm() {
        return this.bufferRhythm;
    }

    public void setBufferRhythm(boolean[] bufferRhythm) {
        this.bufferRhythm = bufferRhythm;
    }

    public Chord[] getChords() {
        return this.chords;
    }

    public void setChords(Chord[] chords) {
        this.chords = chords;
    }

    public Chord getBufferOneChord(){
        return this.bufferOneChord;
    }

    public void setBufferOneChord(Chord bufferOneChord){
        this.bufferOneChord = bufferOneChord;
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

    public int getChordBeginningIndex(){
        return this.chordBeginningIndex;
    }

    public void setChordBeginningIndex(int chordBeginningIndex){
        this.chordBeginningIndex = chordBeginningIndex;
    }

    public int getChordEndingIndex(){
        return this.chordEndingIndex;
    }

    public void setChordEndingIndex(int chordEndingIndex){
        this.chordEndingIndex = chordEndingIndex;
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
        setCalcMelody(new Melody(getLength()));
        comboBoxColorRenderer.setCalcMelody(getCalcMelody());
        setRhythm(new boolean[getLength()]);
        setBufferRhythm(new boolean[getLength()]);
        setChords(new Chord[getLength()]);
        rhythmInputPanel.setLength(getLength());
        rhythmInputPanel.setRhythm(getRhythm());
        rhythmInputPanel.repaint();
        chordsInputPanel.setLength(getLength());
        chordsInputPanel.repaint();
        oneChordInput.setVisible(false);
    }

    private void updateCalcMelodyNotes(){
        if(majorCB.getSelectedIndex() == 0){
            calcMelody.createKeyNotes((String)keyCB.getSelectedItem(), true);
        } else {
            calcMelody.createKeyNotes((String)keyCB.getSelectedItem(), false);
        }
    }

    private void updateChordBaseNoteModel(){
        // Delete all elements
        int size = chordBaseNoteModel.getSize();
        for(int i = 0; i < size; i++){
            chordBaseNoteModel.removeElementAt(0);
        }
        // Add new elements
        for(int i = getCalcMelody().findAllNotesIndex((String)keyCB.getSelectedItem()); i < getCalcMelody().findAllNotesIndex((String)keyCB.getSelectedItem()) + getCalcMelody().getAllNotes().length; i++){
            if(i < getCalcMelody().getAllNotes().length){
                chordBaseNoteModel.addElement(getCalcMelody().getAllNotes()[i]);
            } else {
                chordBaseNoteModel.addElement(getCalcMelody().getAllNotes()[i-getCalcMelody().getAllNotes().length]);
            }
        }
    }

    private void updateCheckBoxes(){
        if(getCalcMelody().findKeyNoteIndex((String)chordBaseNoteCB.getSelectedItem()) != -1){ // baseNote == keyNote
            keyNoteCheckBox8.setVisible(false);
            extraNoteCheckBox5.setVisible(true);
            int keyIndex = 0;
            int extraIndex = 0;
            updateCheckBoxNotes();
        } else { // baseNote == extraNote
            keyNoteCheckBox8.setVisible(true);
            extraNoteCheckBox5.setVisible(false);
            updateCheckBoxNotes();
        }
    }

    private void updateCheckBoxNotes(){
        int keyIndex = 0;
        int extraIndex = 0;
        for(int i = getCalcMelody().findAllNotesIndex((String)chordBaseNoteCB.getSelectedItem())+1; i < getCalcMelody().getAllNotes().length; i++){ // start iteration through allNotes on note after baseNote
            if(getCalcMelody().findKeyNoteIndex(getCalcMelody().getAllNotes()[i]) != -1){ // current note = keyNote
                keyNotesCheckBoxesArray[keyIndex].setText(getCalcMelody().getAllNotes()[i] + " (" + (keyIndex+2) + "/" + (keyIndex+2+7) + ")");
                keyIndex++;
            } else { // current note = extraNote
                extraNotesCheckBoxesArray[extraIndex].setText(getCalcMelody().getAllNotes()[i]);
                extraIndex++;
            }
        }
        for(int i = 0; i <= getCalcMelody().findAllNotesIndex((String)chordBaseNoteCB.getSelectedItem()); i++){ // finish iteration through allNotes
            if(getCalcMelody().findKeyNoteIndex(getCalcMelody().getAllNotes()[i]) != -1){ // current note = keyNote
                if(keyIndex != 6){
                    keyNotesCheckBoxesArray[keyIndex].setText(getCalcMelody().getAllNotes()[i] + " (" + (keyIndex+2) + "/" + (keyIndex+2+7) + ")");
                } else {
                    keyNotesCheckBoxesArray[keyIndex].setText(getCalcMelody().getAllNotes()[i] + " (" + (keyIndex+2) + ")");
                }
                keyIndex++;
            } else { // current note = extraNote
                extraNotesCheckBoxesArray[extraIndex].setText(getCalcMelody().getAllNotes()[i]);
                extraIndex++;
            }
        }
    }
}
