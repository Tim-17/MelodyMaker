public class Chord {

    private String baseNote;
    private String[] keyChordNotes;
    private String[] extraChordNotes;

    public Chord(String baseNote, boolean key){
        setKeyChordNotes(new String[7]);
        setExtraChordNotes(new String[5]);
        setBaseNote(baseNote, key);
    }


    // Getters & Setters

    public String getBaseNote(){
        return this.baseNote;
    }

    public void setBaseNote(String baseNote, boolean key){
        this.baseNote = baseNote;
        if(key){ // baseNote == keyNote
            getKeyChordNotes()[0] = getBaseNote();
        } else { // baseNote == extraNote
            getExtraChordNotes()[0] = getBaseNote();
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


    // Other methods

    public boolean extraNotesEntered(){
        for(int i = 0; i < getExtraChordNotes().length; i++){
            if(getExtraChordNotes()[i] != null){
                return true;
            }
        }
        return false;
    }
}
