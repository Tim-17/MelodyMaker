public class Chord {

    private String rootNote;
    private String[] keyChordNotes;
    private String[] extraChordNotes;
    private boolean arpeggiate;
    private int chordRootNoteCBIndex;

    public Chord(String rootNote, boolean key){
        setKeyChordNotes(new String[7]);
        setExtraChordNotes(new String[5]);
        setRootNote(rootNote, key);
        setChordRootNoteCBIndex(-1);
    }


    // Getters & Setters

    public String getRootNote(){
        return this.rootNote;
    }

    public void setRootNote(String rootNote, boolean key){
        this.rootNote = rootNote;
        if(key){ // rootNote == keyNote
            getKeyChordNotes()[0] = Melody.extractActualNoteName(getRootNote());
        } else { // rootNote == extraNote
            getExtraChordNotes()[0] = Melody.extractActualNoteName(getRootNote());
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

    public boolean getArpeggiate(){
        return this.arpeggiate;
    }

    public void setArpeggiate(boolean arpeggiate){
        this.arpeggiate = arpeggiate;
    }

    public int getChordRootNoteCBIndex(){
        return this.chordRootNoteCBIndex;
    }

    public void setChordRootNoteCBIndex(int chordRootNoteCBIndex){
        this.chordRootNoteCBIndex = chordRootNoteCBIndex;
    }


    // Actual methods

    public static boolean chordsEqual(Chord chord1, Chord chord2){
        // check whether both are not null
        if((chord1 == null && chord2 != null) || (chord1 != null && chord2 == null)){
            return false;
        } else if(chord1 == null && chord2 == null){
            return true;
        } else {
            // compare root notes
            if(!chord1.getRootNote().equals(chord2.getRootNote())){
                return false;
            }
            // compare keyNoteArrays
            for(int i = 0; i < chord1.getKeyChordNotes().length; i++){
                if(chord1.getKeyChordNotes()[i] != null && chord2.getKeyChordNotes()[i] != null){
                    if(!chord1.getKeyChordNotes()[i].equals(chord2.getKeyChordNotes()[i])){
                        return false;
                    }
                } else if(!(chord1.getKeyChordNotes()[i] == null && chord2.getKeyChordNotes()[i] == null)){
                    return false;
                }
            }
            // compare extraNoteArrays
            for(int i = 0; i < chord1.getExtraChordNotes().length; i++){
                if(chord1.getExtraChordNotes()[i] != null && chord2.getExtraChordNotes()[i] != null){
                    if(!chord1.getExtraChordNotes()[i].equals(chord2.getExtraChordNotes()[i])){
                        return false;
                    }
                } else if(!(chord1.getExtraChordNotes()[i] == null && chord2.getExtraChordNotes()[i] == null)){
                    return false;
                }
            }
            // compare arpeggiate
            if(chord1.getArpeggiate() != chord2.getArpeggiate()){
                return false;
            }
            return true;
        }
    }
}
