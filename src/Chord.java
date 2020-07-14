public class Chord {

    private String[] keyChordNotes;
    private String[] extraChordNotes;

    public Chord(){
        setKeyChordNotes(new String[7]);
        setExtraChordNotes(new String[5]);
    }

    // Getters & Setters

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
