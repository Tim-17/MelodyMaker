public class Chord {

    private String rootNote;
    private String[] keyChordNotes;
    private String[] extraChordNotes;
    private Melody calcMelody;

    public Chord(String rootNote, boolean key){
        setKeyChordNotes(new String[7]);
        setExtraChordNotes(new String[5]);
        setRootNote(rootNote, key);
    }


    // Getters & Setters

    public String getRootNote(){
        return this.rootNote;
    }

    public void setRootNote(String rootNote, boolean key){
        this.rootNote = rootNote;
        if(key){ // rootNote == keyNote
            getKeyChordNotes()[0] = getCalcMelody().extractActualNoteName(getRootNote());
        } else { // rootNote == extraNote
            getExtraChordNotes()[0] = getCalcMelody().extractActualNoteName(getRootNote());
        }
    }

    public String[] getKeyChordNotes() {
        return this.keyChordNotes;
    }

    public void setKeyChordNotes(String[] keyChordNotes) {
        this.keyChordNotes = keyChordNotes;
    }

    public String[] getExtraChordNotes() {
        return this.extraChordNotes;
    }

    public void setExtraChordNotes(String[] extraChordNotes) {
        this.extraChordNotes = extraChordNotes;
    }

    public Melody getCalcMelody(){
        return this.calcMelody;
    }

    public void setCalcMelody(Melody calcMelody){
        this.calcMelody = calcMelody;
    }


    // Actual methods

    public boolean extraNotesEntered(){
        for(int i = 0; i < getExtraChordNotes().length; i++){
            if(getExtraChordNotes()[i] != null){
                return true;
            }
        }
        return false;
    }
}
