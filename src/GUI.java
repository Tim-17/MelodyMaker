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
    private JPanel frameBorderPanel, userInput, extraInput, keyChoice, rhythmDisplay, rhythmFramePanel, chordsDisplay, chordsFramePanel, oneChordPanel, oneChordContentPanel, fillPanel, keyParameters, melodyDisplay, sheetMusic;
    private RhythmPanel rhythmInputPanel;
    private ChordsPanel chordsInputPanel;
    private JComboBox keyCB, majorCB, timeSigCB, numberMeasuresCB, smallestSubdivCB, chordBaseNoteCB;
    private MutableComboBoxModel subdivModel, chordBaseNoteModel;
    private JButton enterRhythmB, saveRhythmB, deleteRhythm, enterChordsB, saveChordsB, saveOneChordB, deleteChords, createMelodyB, playMelodyB;
    private JLabel keyL, melodyL, timeSigL, numberMeasuresL, smallestSubdivL;
    private boolean rhythmEntered, chordsEntered;
    private boolean[] rhythm, bufferRhythm;
    private Chord[] chords;
    private int length, currentChordIndex;
    
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
        oneChordContentPanel.setLayout(new GridLayout(1,3));
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
        setRhythmEntered(false);
        setChordsEntered(false);

        setLength((String)timeSigCB.getSelectedItem(), numberMeasuresCB.getSelectedIndex()+1, 16);
        setCalcMelody(new Melody(getLength()));
        updateCalcMelodyNotes();
        setRhythm(new boolean[getLength()]);
        setChords(new Chord[getLength()]);

        chordBaseNoteModel = new DefaultComboBoxModel();
        for(int i = getCalcMelody().findAllNotesIndex((String)keyCB.getSelectedItem()); i < getCalcMelody().findAllNotesIndex((String)keyCB.getSelectedItem()) + getCalcMelody().getAllNotes().length; i++){
            if(i < getCalcMelody().getAllNotes().length){
                if(getCalcMelody().findKeyNoteIndex(getCalcMelody().getAllNotes()[i]) != -1){
                    chordBaseNoteModel.addElement(getCalcMelody().getAllNotes()[i] + " (" + toRoman(getCalcMelody().findKeyNoteIndex(getCalcMelody().getAllNotes()[i]) + 1) + ")");
                } else {
                    chordBaseNoteModel.addElement(getCalcMelody().getAllNotes()[i]);
                }
            } else {
                if(getCalcMelody().findKeyNoteIndex(getCalcMelody().getAllNotes()[i-getCalcMelody().getAllNotes().length]) != -1){
                    chordBaseNoteModel.addElement(getCalcMelody().getAllNotes()[i-getCalcMelody().getAllNotes().length] + " (" + toRoman(getCalcMelody().findKeyNoteIndex(getCalcMelody().getAllNotes()[i-getCalcMelody().getAllNotes().length]) + 1) + ")");
                } else {
                    chordBaseNoteModel.addElement(getCalcMelody().getAllNotes()[i-getCalcMelody().getAllNotes().length]);
                }
            }
        }
        chordBaseNoteCB = new JComboBox(chordBaseNoteModel);

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

        oneChordContentPanel.add(chordBaseNoteCB);

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

        rhythmInput.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                // setBufferRhythm(getRhythm());
                setBufferRhythm(new boolean[getLength()]); // TODO: use this approach for every setter
                for(int i = 1; i <= getLength(); i++){
                    getBufferRhythm()[i-1] = getRhythm()[i-1];
                }
            }

            @Override
            public void componentHidden(ComponentEvent e) {
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
            public void actionPerformed(ActionEvent e) {
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

        chordsInputPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clear = (Main.OTHER_FRAME_WIDTH/4)/(getLength()+1);
                int rect = (Main.OTHER_FRAME_WIDTH*3/4)/getLength();
                for(int i = 1; i <= getLength(); i++){
                    if(clear*i+rect*(i-1) <= e.getX() & e.getX() <= clear*i+rect*(i-1)+rect && Main.OTHER_FRAME_HEIGHT/3 <= e.getY() && e.getY() <= Main.OTHER_FRAME_HEIGHT*2/3){
                        setCurrentChordIndex(i-1);
                        oneChordInput.setVisible(true);
                        break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        saveChordsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

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

        keyCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateCalcMelodyNotes();
            }
        });

        majorCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateCalcMelodyNotes();
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

    public int getCurrentChordIndex() {
        return this.currentChordIndex;
    }

    public void setCurrentChordIndex(int currentChordIndex) {
        this.currentChordIndex = currentChordIndex;
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
        setRhythm(new boolean[getLength()]);
        setBufferRhythm(new boolean[getLength()]);
        setChords(new Chord[getLength()]);
        rhythmInputPanel.setLength(getLength());
        rhythmInputPanel.setRhythm(getRhythm());
        rhythmInputPanel.repaint();
        chordsInputPanel.setLength(getLength());
        chordsInputPanel.repaint();
    }

    private void updateCalcMelodyNotes(){
        if(majorCB.getSelectedIndex() == 0){
            calcMelody.createKeyNotes((String)keyCB.getSelectedItem(), true);
        } else {
            calcMelody.createKeyNotes((String)keyCB.getSelectedItem(), false);
        }
    }

    private static String toRoman(int number) {
        return String.valueOf(new char[number]).replace('\0', 'I')
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC")
                .replace("CCCCC", "D")
                .replace("CCCC", "CD")
                .replace("DD", "M")
                .replace("DCD", "CM");
    }
}
